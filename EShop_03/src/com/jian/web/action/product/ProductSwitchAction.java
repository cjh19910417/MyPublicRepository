package com.jian.web.action.product;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.stereotype.Controller;

import com.jian.bean.product.ProductInfo;
import com.jian.service.product.ProductInfoService;
import com.jian.web.form.product.FrontProductForm;
import com.jian.web.util.WebUtil;
@Controller("/product/switch")
public class ProductSwitchAction extends DispatchAction
{
    @Resource(name = "productInfoServiceImpl")
    ProductInfoService productInfoService;
    /**
     * 获取10个最畅销的产品
     */
    public ActionForward topsell(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        FrontProductForm formbean = (FrontProductForm) form;
        
        request.setAttribute("topsellproducts", productInfoService.getTopSell(formbean.getTypeId(), 10));
        return mapping.findForward("topsell");
    }
    
    /**
     * 获取最近的十个浏览历史记录
     */
    public ActionForward getViewHistory(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        String productid = request.getParameter("productid");
        
        if(productid!=null && !"".equals(productid))
        {
            //给客户端设定cookie
            String cookieValue = WebUtil.getCookieValueByName(request, "viewHistory");
            LinkedList<ProductInfo> viewHistory;
            if(cookieValue!=null && !"".equals(cookieValue))
            {
                String[] values = cookieValue.split("-");
                String value = buildCookieValue(values,productid);
                WebUtil.addCookie(response, "viewHistory", value, 30*24*60*60);
                
                String[] values2 = value.split("-"); 
                Integer[] productids = new Integer[values2.length];
                for (int i = 0; i < productids.length; i++)
                {
                    productids[i] = new Integer(values2[i]);
                }
                //得到相应的product
                
                List<ProductInfo> products =  productInfoService.getViewHistory(productids);
                viewHistory = sortHistory(products,values2);
                
            }else{
                WebUtil.addCookie(response, "viewHistory", productid, 30*24*60*60);
                viewHistory = new LinkedList<ProductInfo>();
                viewHistory.add(new ProductInfo(Integer.parseInt(productid)));
            }
            request.setAttribute("viewHistory", viewHistory);
            return mapping.findForward("viewHistory");
        }
        
        String cookieValue = WebUtil.getCookieValueByName(request, "viewHistory");
        if(cookieValue!=null && !"".equals(cookieValue))
        {
            /*得到productids*/
            String[] values = cookieValue.split("-");
            Integer[] productids = new Integer[values.length];
            for (int i = 0; i < productids.length; i++)
            {
                productids[i] = new Integer(values[i]);
            }
            //得到相应的product
            
            List<ProductInfo> products =  productInfoService.getViewHistory(productids);
            LinkedList<ProductInfo> viewHistory = sortHistory(products,values);
            request.setAttribute("viewHistory", viewHistory);
        }
        return mapping.findForward("viewHistory");
    }
    /**
     * 显示产品大图
     */
    public ActionForward showimage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        
        return mapping.findForward("showimage");
    }
    private LinkedList<ProductInfo> sortHistory(List<ProductInfo> products,
            String[] values)
    {
        LinkedList<ProductInfo> viewHistory = new LinkedList<ProductInfo>();
        for (int i = 0; i < values.length; i++)
        {
            for(ProductInfo product : products)
            {
                if(product.getId().toString().equals(values[i]))
                {
                    viewHistory.offer(product);
                    break;
                }
            }
        }
        
        return viewHistory;
    }
    
    /**
     * 建立cookie的值,93-87-12
     * 最多10个
     * @param values
     * @param id
     * @return
     */
    private String buildCookieValue(String[] values, String id)
    {
        Integer maxSize = 5;
        StringBuffer value = new StringBuffer();
        LinkedList<String> history = new LinkedList<String>();
        String newRecord = id;
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
