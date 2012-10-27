package com.jian.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.jian.bean.shopping.BuyCart;
import com.jian.web.util.UrlTools;
import com.jian.web.util.WebUtil;
/**
 * 拦截/customer/shopping开头的路径请求,要求购物车不能为空!至少有一个购物项
 * @author JOJO
 * @date 2012-9-8
 */
public class BuyCartValidateFilter implements Filter
{

    @Override
    public void destroy()
    {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) req;
        BuyCart cart = WebUtil.getBuyCart(request);
        if(cart==null || cart.getItems().size()==0)
        {//购物车为空,或者购物车里面没有商品,让其跳转到主页挑选要购买的商品
            request.setAttribute("message", "购物车至少要有一件商品~");
            request.setAttribute(UrlTools.JUMP, "/");//调转到主页
            request.getRequestDispatcher("/WEB-INF/page/share/message.jsp").forward(req, res);
        }else{
            chain.doFilter(req, res);
        }
        
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException
    {
    }

}
