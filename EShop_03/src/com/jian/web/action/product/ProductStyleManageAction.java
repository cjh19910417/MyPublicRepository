package com.jian.web.action.product;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import org.springframework.stereotype.Controller;

import com.jian.bean.product.ProductInfo;
import com.jian.bean.product.ProductStyle;
import com.jian.bean.product.UploadFile;
import com.jian.service.product.ProductStyleService;
import com.jian.service.product.UploadFileService;
import com.jian.web.form.product.ProductForm;
import com.jian.web.util.ImageSizer;
import com.jian.web.util.UrlTools;

@Controller("/control/product/style/manage")
public class ProductStyleManageAction extends DispatchAction
{
    @Resource(name = "productStyleServiceImpl")
    ProductStyleService productStyleService;
    @Resource(name = "uploadFileServiceImpl")
    UploadFileService uploadFileService;
    /**
     * 修改界面
     */
    public ActionForward editUI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ProductForm productForm = (ProductForm) form;
        if (productForm.getProductstyleid() != null
                && productForm.getProductstyleid() > 0)
        {
            ProductStyle productStyle = productStyleService.find(ProductStyle.class,
                                                                 productForm.getProductstyleid());
            productForm.setStylename(productStyle.getName());
            String imageFullPath = productStyle.getImageFullPath();
            request.setAttribute("imagepath", imageFullPath);
        }
        return mapping.findForward("editUI");
    }

    /**
     * 修改
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ProductForm productForm = (ProductForm) form;

        FormFile image = productForm.getImagefile();
        ProductStyle productStyle = productStyleService.find(ProductStyle.class,
                                                             productForm.getProductstyleid());
        Integer productid = productStyle.getProduct().getId();
        if (image != null && image.getFileSize() > 0)
        {
            // 验证图片格式,不是有效格式就返回
            if (!productForm.validateImageFileType(productForm.getImagefile()))
            {
                request.setAttribute("message", "图片格式不对,请选择有效的图片格式!");
                request.setAttribute("urladdress", UrlTools.getUrlSite("back"));
                return mapping.findForward("message");
            }
            String imageRealPath = request.getSession().getServletContext().getRealPath(productStyle.getImageFullPath());
            System.out.println(imageRealPath);
            
            /* 2.上传现在的图片 */
            byte[] imageData = image.getFileData();
            String ext = image.getFileName().substring(image.getFileName().lastIndexOf('.'));
            String imagename = UUID.randomUUID().toString() + ext;
            File protoFile = new File(imageRealPath.substring(0, imageRealPath.lastIndexOf('\\')),imagename);
            OutputStream out = new FileOutputStream(protoFile);
            out.write(imageData);
            out.close();

            productStyle.setImagename(imagename);
            
            /*****************************保存压缩图片start*****************************/
            //得到要保存的路径
            
            String filePathDir140x = "/images/product/" + productStyle.getProduct().getType().getTypeId() + "/" + productid +"/140x";
            //得到140x在硬盘上的真实路径
            String fileRealPathDir140x = request.getSession().getServletContext().getRealPath(filePathDir140x);
            // 创建140x目录
            File image140xSaveDir = new File(fileRealPathDir140x);
            if(!image140xSaveDir.exists())
            {
                image140xSaveDir.mkdirs();
            }
            //写入到140x目录
            ImageSizer.resize(protoFile, new File(image140xSaveDir,imagename), 140, "jpg");
            
            /*****************************保存压缩图片e n d*****************************/
            
            
            /*弄到上传文件管理里面,便于管理*/
            UploadFile uploadFile = new UploadFile();
            String filepath = productStyle.getImageFullPath().substring(0,productStyle.getImageFullPath().lastIndexOf('/')+1)+imagename;
            uploadFile.setFilepath(filepath);
            uploadFile.setUploadtime(new Date());
            productStyleService.save(uploadFile);
        }
        /* 3.更改数据库数据 */
        productStyle.setName(productForm.getStylename());
        productStyleService.update(productStyle);
        request.setAttribute("urladdress", "/control/product/style/list.do?productid="+productid);
        request.setAttribute("message", "修改样式成功!");
        return mapping.findForward("message");
    }
    /**
     * 添加界面
     */
    public ActionForward addUI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        return mapping.findForward("addUI");
    }
    /**
     * 添加样式
     */
    public ActionForward add(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ProductForm productForm = (ProductForm) form;
        FormFile image = productForm.getImagefile();
        ProductStyle productStyle = new ProductStyle();
        if (image != null && image.getFileSize() > 0)
        {
            // 验证图片格式,不是有效格式就返回
            if (!productForm.validateImageFileType(productForm.getImagefile()))
            {
                request.setAttribute("message", "图片格式不对,请选择有效的图片格式!");
                request.setAttribute("urladdress", UrlTools.getUrlSite("back"));
                return mapping.findForward("message");
            }
            String ext = image.getFileName().substring(image.getFileName().lastIndexOf('.'));
            String imagename = UUID.randomUUID().toString() + ext;
            ProductInfo product = productStyleService.find(ProductInfo.class, productForm.getProductid());
            String filePathDir = "/images/product/" + product.getType().getTypeId() + "/" + productForm.getProductid()+"/prototype";
            // 得到在硬盘上的真实路径
            String fileRealPathDir = request.getSession().getServletContext().getRealPath(filePathDir);
            System.out.println("imageRealPathDir --> " + fileRealPathDir);

            // 创建目录
            File imageSaveDir = new File(fileRealPathDir);
            if (!imageSaveDir.exists())
                imageSaveDir.mkdirs();
            File protoFile = new File(fileRealPathDir, imagename);
            FileOutputStream out = new FileOutputStream(protoFile);
            out.write(productForm.getImagefile().getFileData());
            out.close();

            /*****************************保存压缩图片start*****************************/
            //得到要保存的路径
            String filePathDir140x = "/images/product/" + product.getType().getTypeId() + "/" + productForm.getProductid()+"/140x";
            //得到140x在硬盘上的真实路径
            String fileRealPathDir140x = request.getSession().getServletContext().getRealPath(filePathDir140x);
            // 创建140x目录
            File image140xSaveDir = new File(fileRealPathDir140x);
            if(!image140xSaveDir.exists())
            {
                image140xSaveDir.mkdirs();
            }
            //写入到140x目录
            ImageSizer.resize(protoFile, new File(image140xSaveDir,imagename), 140, "jpg");
            
            /*****************************保存压缩图片e n d*****************************/
            
            /*在数据库保存上传文件*/
            String filePath = filePathDir + "/" + imagename;
            UploadFile uploadFile = new UploadFile();
            uploadFile.setFilepath(filePath);
            uploadFile.setUploadtime(new Date());
            uploadFileService.save(uploadFile);
            
            /*在数据库保存样式*/
            productStyle.setImagename(imagename);
            productStyle.setName(productForm.getStylename());
            productStyle.setProduct(product);
            
            productStyleService.save(productStyle);
            request.setAttribute("urladdress", "/control/product/style/list.do?productid="+productForm.getProductid());
            request.setAttribute("message", "样式添加成功!");
            return mapping.findForward("message");
        }else{
            request.setAttribute("message", "请选择一张图片上传!");
            request.setAttribute("urladdress", UrlTools.getUrlSite("back"));
            return mapping.findForward("message");
        }
        
    }
    /**
     * 设置产品上架
     */
    public ActionForward visible(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ProductForm productForm = (ProductForm) form;
        Integer[] styleids = productForm.getStylesids();
        productStyleService.setVisibleStatus(styleids, true);
        request.setAttribute("message", "样式上架成功!");
        request.setAttribute("urladdress", UrlTools.getUrlSite("back"));
        return mapping.findForward("message");
    }
    /**
     * 设置产品下架
     */
    public ActionForward disable(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ProductForm productForm = (ProductForm) form;
        Integer[] styleids = productForm.getStylesids();
        productStyleService.setVisibleStatus(styleids, false);
        request.setAttribute("message", "样式下架成功!");
        request.setAttribute("urladdress", UrlTools.getUrlSite("back"));
        return mapping.findForward("message");
    }
}
