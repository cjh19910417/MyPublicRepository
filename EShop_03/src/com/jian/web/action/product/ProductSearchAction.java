package com.jian.web.action.product;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import com.jian.bean.product.ProductInfo;
import com.jian.service.base.QueryResult;
import com.jian.service.product.ProductQueryService;
import com.jian.web.bean.PageViewData;
import com.jian.web.form.product.ProductQueryForm;
/**
 * 前台产品搜索
 * @author JOJO
 * @date 2012-9-22
 */
@Controller("/product/query")
public class ProductSearchAction extends Action
{
    @Resource ProductQueryService queryService;
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ProductQueryForm formbean = (ProductQueryForm) form;
        // 最大一页显示多少条记录  
        int maxResult = 2;
        // 得到当前要去的页码
        int currentPage = formbean.getPage();
        // 设置条目开始索引
        int startIndex = currentPage == 0 ? 0 : (currentPage - 1) * maxResult;
        QueryResult<ProductInfo> qr;
        if(request.getMethod().equalsIgnoreCase("get"))//url请求的时候,会有中文乱码问题,所以..
        {
            formbean.setWord(request.getAttribute("word").toString());
        }
        qr = queryService.find(formbean.getWord(), startIndex, maxResult);
        
        PageViewData<ProductInfo> pageViewData = new PageViewData<ProductInfo>(qr, currentPage, maxResult);
        
        request.setAttribute("pageViewData", pageViewData);
        return mapping.findForward("list");
    }

}
