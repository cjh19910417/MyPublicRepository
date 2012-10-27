package com.jian.web.action.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import com.jian.bean.user.Buyer;
import com.jian.service.user.UserService;
import com.jian.web.form.user.UserLogonForm;
import com.jian.web.util.MD5;
import com.jian.web.util.UrlTools;
import com.jian.web.util.WebUtil;
/**
 * 用户登录
 * @author JOJO
 * @date 2012-9-6
 */
@Controller("/user/logon")
public class BuyerLogonAction extends Action
{
    @Resource(name="userServiceImpl")
    UserService userService;
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        UserLogonForm formbean = (UserLogonForm) form;
        if(userService.validateMD5(formbean.getUsername(), formbean.getPassword()))
        {
            //验证通过
            Buyer buyer = userService.find(Buyer.class, formbean.getUsername());
            request.getSession().setAttribute("user", buyer);
            if("true".equals(formbean.getIsAutoLogon()))
            {
                //登录成功,在客户端cookie用户名密码,格式:   用户名-密码(MD5后)
                String cookieValue = formbean.getUsername()+"-"+MD5.MD5Encode(formbean.getPassword());
                WebUtil.addCookie(response, "user", cookieValue, 1*24*60*60);//缓存一天
            }
            
            
            request.setAttribute("message", "恭喜登录成功!");
            String target = "/";
            if(formbean.getDirectUrl()!=null&&!"".equals(formbean.getDirectUrl()))
            {
                target = formbean.getDirectUrl();
            }
            request.setAttribute(UrlTools.JUMP, target);//跳转到来源页
            return mapping.findForward("message");
        }
        //验证没通过
        request.setAttribute("error", "用户名或密码错误!");
        
        return mapping.findForward("false");
    }
    
}
