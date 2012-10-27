package com.jian.web.action.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.stereotype.Controller;

import com.jian.service.user.UserService;
import com.jian.web.form.user.BuyerForm;
import com.jian.web.util.UrlTools;
@Controller("/control/user/manage")
public class BuyerManageAction extends DispatchAction
{
    @Resource(name="userServiceImpl")
    UserService service;
    /**
     * 禁用用户
     */
    public ActionForward disable(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        BuyerForm formbean = (BuyerForm) form;
        if(formbean.getUsernames()!=null && formbean.getUsernames().length>0)
        {
            String [] usernames = formbean.getUsernames();
            service.setVisibleStatus(usernames, false);
            request.setAttribute("message", "禁用用户成功!");
            
        }else{
            request.setAttribute("message", "请至少选择一条记录!");
        }
        request.setAttribute(UrlTools.JUMP, "/control/user/list.do");
        return mapping.findForward("message");
    }
    /**
     * 激活用户
     */
    public ActionForward enable(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        BuyerForm formbean = (BuyerForm) form;
        if(formbean.getUsernames()!=null && formbean.getUsernames().length>0)
        {
            String [] usernames = formbean.getUsernames();
            service.setVisibleStatus(usernames, true);
            request.setAttribute("message", "激活用户成功!");
            
        }else{
            request.setAttribute("message", "请至少选择一条记录!");
        }
        request.setAttribute(UrlTools.JUMP, "/control/user/list.do");
        return mapping.findForward("message");
    }
}
