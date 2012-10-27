package com.jian.web.action.privilege;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import com.jian.bean.privilege.Employee;
import com.jian.service.privilege.EmployeeService;
import com.jian.web.form.privilege.EmployeeForm;
/**
 * 员工登录
 * @author JOJO
 * @date 2012-9-16
 */
@Controller("/employee/logon")
public class EmployeeLogonAction extends Action
{
    @Resource
    EmployeeService employeeService;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        EmployeeForm formbean = (EmployeeForm) form;
        if(formbean.getUsername()!=null&&!"".equals(formbean.getUsername().trim())
                &&formbean.getPassword()!=null&&!"".equals(formbean.getPassword().trim()))
        {
            if(employeeService.validate(formbean.getUsername().trim(), formbean.getPassword().trim()))
            {
                request.getSession().setAttribute("employee", employeeService.find(Employee.class, formbean.getUsername()));
                return mapping.findForward("control");
            }
            request.setAttribute("message", "用户名或密码有误!");
        }else{
            //request.setAttribute("message", "用户名或密码为空!");
        }
        
        return mapping.findForward("logon");
    }
    
}
