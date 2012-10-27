package com.jian.web.action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import com.jian.web.util.UrlTools;
import com.jian.web.util.WebUtil;
/**
 * 用户登出
 * @author JOJO
 * @date 2012-9-9
 */
@Controller("/user/logout")
public class LogoutAction extends Action
{

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        if(request.getSession().getAttribute("user")!=null)
        {
            request.getSession().setAttribute("user", null);
            WebUtil.addCookie(response, "user", "", 0); //把cookie设置为过期   
        }
        request.setAttribute("message", "成功登出!点击返回登录界面~");
        request.setAttribute(UrlTools.JUMP, UrlTools.getUrlSite("logonUI"));
        return mapping.findForward("message");
    }
    
}
