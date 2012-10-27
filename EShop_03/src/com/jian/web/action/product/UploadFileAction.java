package com.jian.web.action.product;

import java.util.LinkedHashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import com.jian.bean.product.UploadFile;
import com.jian.service.base.QueryResult;
import com.jian.service.product.UploadFileService;
import com.jian.web.bean.PageViewData;
import com.jian.web.form.product.UploadFileForm;

@Controller("/control/uploadfile/list")
public class UploadFileAction extends Action
{
    @Resource(name="uploadFileServiceImpl")
    UploadFileService uploadFileService;
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        
        UploadFileForm uploadFileForm = (UploadFileForm) form;
        // 最大一页显示多少条记录
        int maxResult = 12;
        // 得到当前要去的页码
        int currentPage = uploadFileForm.getPage();
        // 设置条目开始索引
        int startIndex = currentPage == 0 ? 0 : (currentPage - 1) * maxResult;
        // 设置排序方式
        LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
        //根据id降序排序
        orderby.put("id", "desc");
        QueryResult<UploadFile> qr = null;
        
        qr = uploadFileService.getScrollData(UploadFile.class, startIndex, maxResult, orderby);

        PageViewData<UploadFile> pageViewData = new PageViewData<UploadFile>(qr,
                currentPage, maxResult);

        request.setAttribute("pageViewData", pageViewData);
        System.out.println(pageViewData.getRecords().size());
        return mapping.findForward("list");
    }
    
}
