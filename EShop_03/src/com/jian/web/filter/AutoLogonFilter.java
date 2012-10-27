package com.jian.web.filter;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.jian.bean.user.Buyer;
import com.jian.service.user.UserService;
import com.jian.web.util.WebUtil;

/**
 * **自动登录过滤器**
 * `````用spring注入filter`````
 * @author JOJO
 * @date 2012-9-10
 */
@Service
public class AutoLogonFilter implements Filter
{   
    @Resource(name="userServiceImpl")
    public UserService userService;
    @Override
    public void destroy()
    {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse res,
            FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest req = (HttpServletRequest) request;
        if(req.getSession().getAttribute("user")==null)
        {
            //用户没有登录,使用cookie自动登录
            String cookieValue = WebUtil.getCookieValueByName(req, "user");
            if(cookieValue!=null&&!"".equals(cookieValue))
            {
                //拆解cookieValue,等到用户名和密码(MD5后的)
                String[] values = cookieValue.split("-");
                String username = values[0];
                String password = values[1];
                //登录
                if(userService.validate(username, password))
                {
                    //验证通过
                    Buyer buyer = userService.find(Buyer.class, username);
                    req.getSession().setAttribute("user", buyer);
                }
            }
            
        }
        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException
    {
    }

    public UserService getUserService()
    {
        return userService;
    }

    public void setUserService(UserService userService)
    {
        this.userService = userService;
    }

}
