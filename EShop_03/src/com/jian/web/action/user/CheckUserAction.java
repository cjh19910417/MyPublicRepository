package com.jian.web.action.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import com.jian.service.user.UserService;
/**
 * 用于检测用户名是否存在
 * @author JOJO
 * @date 2012-9-5
 */
@Controller("/user/checkuser")
public class CheckUserAction extends Action
{

    @Resource(name="userServiceImpl")
    UserService service;
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        System.out.println("正在检测"+request.getParameter("username")+"是否存在...");
        request.setAttribute("exsit", service.isExist(request.getParameter("username")));
        return mapping.findForward("checkuser");
    }
    
}
