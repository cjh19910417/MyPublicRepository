package com.jian.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jian.web.util.WebUtil;
/**
 * 用户登录后才能访问/customer/*
 * @author JOJO
 * @date 2012-9-7
 */
public class BuyerLogonValidateFilter implements Filter
{

    @Override
    public void destroy()
    {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest req = (HttpServletRequest) request;
        if(WebUtil.getUserInSession(req) == null)
        {
            //没有buyer对象,让用户重定向到用户登录界面
            
            String subUrl = WebUtil.getRequestURIWithParam(req);
            System.out.println(subUrl);
            String directUrl = WebUtil.Base64encode(subUrl);//经过base64编码
            HttpServletResponse res = (HttpServletResponse) response;
            res.sendRedirect("/user/logonUI.do?directUrl="+directUrl);
        }else
        {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException
    {
    }

}
