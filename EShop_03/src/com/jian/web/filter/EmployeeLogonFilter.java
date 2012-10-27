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
 * 过滤/control/*
 * @author JOJO
 * @date 2012-9-16
 */
public class EmployeeLogonFilter implements Filter
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
        if(WebUtil.getEmployeeInSession(request)==null)//员工没有登录,跳转到登录界面
        {
            HttpServletResponse response = (HttpServletResponse) res;
            response.sendRedirect("/employee/logon.do");
        }else
        {
            chain.doFilter(request, res);
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException
    {
    }

}
