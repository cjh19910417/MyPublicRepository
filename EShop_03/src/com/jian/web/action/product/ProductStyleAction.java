package com.jian.web.action.product;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import com.jian.bean.product.ProductInfo;
import com.jian.bean.product.ProductStyle;
import com.jian.service.base.QueryResult;
import com.jian.service.product.ProductStyleService;
import com.jian.web.form.product.ProductForm;

@Controller("/control/product/style/list")
public class ProductStyleAction extends Action
{
    @Resource(name = "productStyleServiceImpl")
    ProductStyleService productStyleService;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ProductForm productForm = (ProductForm) form;
        // 设置排序方式
        LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
        orderby.put("visible", "desc");
        orderby.put("id", "asc");
        QueryResult<ProductStyle> qr = null;
        StringBuffer jpql = new StringBuffer();
        jpql.append(" o.product.id = ?1");

        List<Object> queryParams = new ArrayList<Object>();
        queryParams.add(productForm.getProductid());
        qr = productStyleService.getScrollData(ProductStyle.class, -1,
                                               -1, jpql.toString(),
                                               queryParams.toArray(), orderby);
        request.setAttribute("styles", qr.getResultlist());
        request.setAttribute("productname", productStyleService.find(ProductInfo.class, productForm.getProductid()).getName());
        return mapping.findForward("list");
    }

}
