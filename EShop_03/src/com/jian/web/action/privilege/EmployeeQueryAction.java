package com.jian.web.action.privilege;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import com.jian.bean.privilege.Department;
import com.jian.bean.privilege.Permission;
import com.jian.service.base.QueryResult;
import com.jian.service.privilege.DepartmentService;
@Controller("/control/employee/query")
public class EmployeeQueryAction extends Action
{
    @Resource
    DepartmentService departmentService;
    @Override @Permission(module="employee",privilege="query")
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        QueryResult<Department> qr = departmentService.getScrollData(Department.class);// 得到所有部门
        List<Department> departments = qr.getResultlist();
        
        request.setAttribute("departments", departments);
        return mapping.findForward("queryUI");
    }
    
}
