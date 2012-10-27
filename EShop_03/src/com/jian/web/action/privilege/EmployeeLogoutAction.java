package com.jian.web.action.privilege;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;
/**
 * 员工登出
 * @author JOJO
 * @date 2012-9-16
 */
@Controller("/employee/logout")
public class EmployeeLogoutAction extends Action
{

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        request.getSession().removeAttribute("employee");
        return mapping.findForward("logon");
    }
    
}
