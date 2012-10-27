package com.jian.web.action.product;

import java.util.ArrayList;
import java.util.LinkedList;
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
import com.jian.bean.product.ProductType;
import com.jian.service.product.ProductInfoService;
import com.jian.service.product.ProductTypeService;
import com.jian.web.form.product.FrontProductForm;
import com.jian.web.util.WebUtil;

@Controller("/product/view")
public class ViewProductAction extends Action
{
    @Resource(name = "productInfoServiceImpl")
    ProductInfoService productInfoService;
    @Resource(name = "productTypeServiceImpl")
    ProductTypeService productTypeService;
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        FrontProductForm formbean = (FrontProductForm) form;
        ProductInfo product = productInfoService.find(ProductInfo.class, formbean.getProductid());
        if(product==null)
        {
            //没有该产品
            request.setAttribute("messge", "没有你要浏览的产品!");
            request.setAttribute("urladdress", "/");
            return mapping.findForward("message");
        }
        //给客户端设定cookie
        String cookieValue = WebUtil.getCookieValueByName(request, "viewHistory");
        if(cookieValue!=null && !"".equals(cookieValue))
        {
            String[] values = cookieValue.split("-");
            String value = buildCookieValue(values,product.getId());
            WebUtil.addCookie(response, "viewHistory", value, 30*24*60*60);
        }else{
            WebUtil.addCookie(response, "viewHistory", product.getId().toString(), 30*24*60*60);
        }
        
        /**
         * 得到产品轨迹导航
         */
        List<ProductType> typeTrace;
        typeTrace = getTypeTrace(product.getType().getTypeId());
        for(int i = 0;i<typeTrace.size();i++)
        {
            System.out.print(typeTrace.get(i).getName()+">>");
        }
        request.setAttribute("stypes", typeTrace);
        request.setAttribute("product", product);
        return mapping.findForward("product");
    }
    /**
     * 得到产品轨迹导航
     */
    private List<ProductType> getTypeTrace(Integer typeId)
    {
        ProductType type = productTypeService.find(ProductType.class, typeId);
        List<ProductType> typeTrace = new ArrayList<ProductType>();
        typeTrace.add(type);
        while(type.getParent()!=null)
        {
            typeTrace.add( type.getParent());
            type = type.getParent();
        }
        return typeTrace;
    }
    /**
     * 建立cookie的值,93-87-12
     * 最多10个
     * @param values
     * @param id
     * @return
     */
    private String buildCookieValue(String[] values, Integer id)
    {
        Integer maxSize = 5;
        StringBuffer value = new StringBuffer();
        LinkedList<String> history = new LinkedList<String>();
        String newRecord = id.toString();
        for (int i = 0; i < values.length; i++)
        {
            history.offer(values[i]);
        }
        if(history.contains(newRecord))
        {
            history.remove(newRecord);
        }else if(history.size() >= maxSize)
        {
            //history.poll();
            history.remove(history.size()-1);
        }
        //history.offer(newRecord);
        history.add(0, newRecord);
        for(String str : history)
        {
            value.append(str).append("-");
        }
        value.deleteCharAt(value.length()-1);
        return value.toString();
    }
}
