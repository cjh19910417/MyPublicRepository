package com.jian.web.action.shopping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import com.jian.bean.product.ProductInfo;
import com.jian.bean.product.ProductStyle;
import com.jian.bean.shopping.BuyCart;
import com.jian.bean.shopping.BuyItem;
import com.jian.bean.shopping.SiteSessionListener;
import com.jian.service.product.ProductStyleService;
import com.jian.web.form.shopping.CartForm;
import com.jian.web.util.WebUtil;

@Controller("/shopping/cart")
public class BuyCartAction extends Action
{
    @Resource(name = "productStyleServiceImpl")
    ProductStyleService productStyleService;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        CartForm formbean = (CartForm) form;
        // 1.从session中拿购物车
        BuyCart buyCart = (BuyCart) request.getSession().getAttribute("buyCart");

        /*
         * 2.如果没有拿到,两种情况 a.从来没有创建过购物车
         * b.新开了窗口,在之前的窗口关闭的时候把session丢失了,在当前新session里面没有buycart,因为session是全新的
         * 所以要从sessions里面获取.
         */
        if (buyCart == null)
        {
            // 去尝试在之前的session里面拿buycart
            String sessionid = WebUtil.getCookieValueByName(request, "sessionid");
            if (sessionid != null && !"".equals(sessionid))
            {
                HttpSession session = SiteSessionListener.getSession(sessionid);
                if (session != null)
                {
                    buyCart = (BuyCart) session.getAttribute("buyCart");
                    
                }
            }
            else
            {
                // 往客户端写cookie
                WebUtil.addCookie(response, "sessionid",
                                  request.getSession().getId(),
                                  request.getSession().getMaxInactiveInterval());
            }
        }

        if (buyCart == null)
        {
            // 从来没有创建过buycart,或者session已经销毁了
            // 创建buycart
            buyCart = new BuyCart();
        }
        if (formbean.getProductid() != null && formbean.getProductid() > 0)
        {
            BuyItem buyItem = new BuyItem(
                    productStyleService.find(ProductInfo.class,
                                             formbean.getProductid()),
                    productStyleService.find(ProductStyle.class,
                                             formbean.getStyleid()),
                    formbean.getAmount());

            buyCart.addBuyItem(buyItem);
        }

        request.getSession().setAttribute("buyCart", buyCart);
        request.setAttribute("buyCart", buyCart);
        return mapping.findForward("cart");
    }

}
