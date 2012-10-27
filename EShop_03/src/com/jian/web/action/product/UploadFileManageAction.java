package com.jian.web.action.product;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.stereotype.Controller;

import com.jian.bean.product.UploadFile;
import com.jian.service.product.UploadFileService;
import com.jian.web.form.product.UploadFileForm;
import com.jian.web.util.UrlTools;

/**
 * 单独上传文件管理action
 * 
 * @author JOJO
 * @date 2012-8-7
 */
@Controller("/control/uploadfile/manage")
public class UploadFileManageAction extends DispatchAction
{
    @Resource(name = "uploadFileServiceImpl")
    // 注入
    UploadFileService uploadFileService;

    /**
     * 上传界面
     */
    public ActionForward uploadUI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        return mapping.findForward("uploadfileUI");
    }

    /**
     * 上传文件
     */
    public ActionForward uploadfile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        UploadFile uploadFile = new UploadFile();
        UploadFileForm uploadfileForm = (UploadFileForm) form;
        if (uploadfileForm.getUploadfile() != null
                && uploadfileForm.getUploadfile().getFileSize() > 0)
        {
            // 1.判断上传文件是否合法
            if (!uploadfileForm.validateFileType(uploadfileForm.getUploadfile()))
            {
                request.setAttribute("message",
                                     "上传的文件格式不合法!只允许上传图片/flash动画/word文件/exe文件/pdf文件/TxT文件/xls文件/ppt文件!");
                request.setAttribute("urladdress", UrlTools.getUrlSite("back"));
                return mapping.findForward("message");
            }

            // /uploadfile/2012/08/06/UUID.ext
            String filePath = "";
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd/HH");
            String filePathDir = "/images/brand/"
                    + dateFormat.format(new Date());
            // 得到在硬盘上的真实路径
            String fileRealPathDir = request.getSession().getServletContext().getRealPath(filePathDir);
            System.out.println("fileRealPathDir --> " + fileRealPathDir);
            // 文件名称,用UUID生成
            String ext = uploadfileForm.getUploadfile().getFileName().substring(uploadfileForm.getUploadfile().getFileName().lastIndexOf('.'));
            String fileName = UUID.randomUUID().toString() + ext;
            // 创建目录
            File fileSaveDir = new File(fileRealPathDir);
            if (!fileSaveDir.exists())
                fileSaveDir.mkdirs();

            FileOutputStream out = new FileOutputStream(new File(fileRealPathDir, fileName));
            out.write(uploadfileForm.getUploadfile().getFileData());
            out.close();

            filePath = filePathDir + "/" + fileName;
            uploadFile.setFilepath(filePath);
            uploadFile.setUploadtime(new Date());
            uploadFileService.save(uploadFile);
            request.setAttribute("message", "文件上传成功!");
            request.setAttribute("imagepath", filePath);
            return mapping.findForward("uploadfinish");
        }
        else
        {
            request.setAttribute("message", "请选择一个文件上传!");
            request.setAttribute("urladdress", UrlTools.getUrlSite("back"));
            return mapping.findForward("message");
        }

    }
    /**
     * 删除
     */
    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        //1.首先删除在硬盘上面的上传文件
        
        Integer[] ids = ((UploadFileForm)form).getFileids();
        if(ids != null && ids.length > 0){
            for (int id : ids)
            {
                UploadFile uploadFile = uploadFileService.find(UploadFile.class, id);
                if(uploadFile != null){
                    String filepath = uploadFile.getFilepath();
                    String realpath = request.getSession().getServletContext().getRealPath(filepath);
                    File file = new File(realpath);
                    if(file.exists()){
                        //删除在硬盘上的文件
                        file.delete();
                    }
                }
            }
            //2.再删除上传文件在数据库上面的记录
            uploadFileService.delete(UploadFile.class, ids);
            
            request.setAttribute("message", "删除成功!");
            
        }else{
            request.setAttribute("message", "请至少选择一条记录删除!");
        }
        request.setAttribute("urladdress", UrlTools.getUrlSite("uploadlist"));
        return mapping.findForward("message");
    }
}
