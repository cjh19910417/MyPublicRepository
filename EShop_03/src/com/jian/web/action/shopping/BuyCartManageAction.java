package com.jian.web.action.shopping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.stereotype.Controller;

import com.jian.bean.product.ProductInfo;
import com.jian.bean.product.ProductStyle;
import com.jian.bean.shopping.BuyCart;
import com.jian.bean.shopping.BuyItem;
import com.jian.web.form.shopping.CartForm;
import com.jian.web.util.WebUtil;

@Controller("/shopping/cart/manage")
public class BuyCartManageAction extends DispatchAction
{
    
    /**
     * 点击结算
     */
    public ActionForward settleAccounts(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        CartForm formbean = (CartForm) form;
        BuyCart buyCart = WebUtil.getBuyCart(request);
        if (buyCart != null)
        {
            //更改数量
            setCartAmounts(request, buyCart);
        }
        //重定向到  配送信息页面
        String url = "/customer/shopping/deliver.do";
        if(formbean.getDirectUrl()!=null&&!"".equals(formbean.getDirectUrl()))
        {
            url = formbean.getDirectUrl();
        }
        request.setAttribute("directUrl", url);
        return mapping.findForward("directUrl");
    }
    /**
     * 删除购物车某条记录
     */
    public ActionForward delete(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        CartForm formbean = (CartForm) form;
        if (formbean.getBuyitemid() != null
                && !"".equals(formbean.getBuyitemid()))
        {
            BuyCart buyCart = WebUtil.getBuyCart(request);
            BuyItem item = new BuyItem(
                    new ProductInfo(formbean.getProductid()), new ProductStyle(
                            formbean.getStyleid()), 1);
            buyCart.removeItem(item);
        }
        //正常情况下重定向回cart
        String url = "/shopping/cart.do";
        if(formbean.getDirectUrl()!=null&&!"".equals(formbean.getDirectUrl()))
        {
            url = "/shopping/cart.do?directUrl="+WebUtil.Base64encode(formbean.getDirectUrl());
        }
        request.setAttribute("directUrl", url);
        return mapping.findForward("directUrl");
    }

    /**
     * 清空购物车
     */
    public ActionForward deleteAll(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        CartForm formbean = (CartForm) form;
        BuyCart buyCart = WebUtil.getBuyCart(request);
        buyCart.removeAll();
        //正常情况下重定向回cart
        String url = "/shopping/cart.do";
        if(formbean.getDirectUrl()!=null&&!"".equals(formbean.getDirectUrl()))
        {
            url = "/shopping/cart.do?directUrl="+WebUtil.Base64encode(formbean.getDirectUrl());
        }
        request.setAttribute("directUrl", url);
        return mapping.findForward("directUrl");
    }

    /**
     * 更新产品的数量action
     */
    public ActionForward updateAmount(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        CartForm formbean = (CartForm) form;
        BuyCart buyCart = WebUtil.getBuyCart(request);
        if (buyCart != null)
        {
            //更改数量
            setCartAmounts(request, buyCart);
        }

        //正常情况下重定向回cart
        String url = "/shopping/cart.do";
        if(formbean.getDirectUrl()!=null&&!"".equals(formbean.getDirectUrl()))
        {
            url = "/shopping/cart.do?directUrl="+WebUtil.Base64encode(formbean.getDirectUrl());
        }
        request.setAttribute("directUrl", url);
        return mapping.findForward("directUrl");
    }
    /**
     * 更改购物车的数量
     * @param request
     * @param buyCart
     */
    private void setCartAmounts(HttpServletRequest request, BuyCart buyCart)
    {
        for (BuyItem item : buyCart.getItems())
        {
            StringBuffer key = new StringBuffer();
            key.append("amount_");
            key.append(item.getProduct().getId()).append("_");
            key.append(item.getStyle().getId());
            String amount = request.getParameter(key.toString());
            if (amount != null && !"".equals(amount))
            {
                try
                {
                    int target = Integer.parseInt(amount);
                    if (target > 0)
                    {
                        item.setAmount(target);
                    }
                }
                catch (Exception e)
                {
                    System.err.println("不能转换为数字!");
                    e.printStackTrace();
                }
            }

        }
    }
}
