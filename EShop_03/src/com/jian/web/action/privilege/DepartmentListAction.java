package com.jian.web.action.privilege;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import com.jian.bean.privilege.Department;
import com.jian.bean.privilege.Employee;
import com.jian.bean.privilege.Permission;
import com.jian.bean.privilege.SystemPrivilege;
import com.jian.bean.privilege.SystemPrivilegeGroup;
import com.jian.service.base.QueryResult;
import com.jian.service.privilege.DepartmentService;
import com.jian.web.bean.PageViewData;
import com.jian.web.form.privilege.DepartmentForm;
import com.jian.web.util.WebUtil;

/**
 * 部门分页列表
 */
@Controller("/control/department/list")
public class DepartmentListAction extends Action {
	@Resource DepartmentService departmentService;

	@Override @Permission(module="department",privilege="view")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DepartmentForm formbean = (DepartmentForm)form;
		// 最大一页显示多少条记录
        int maxResult = 12;
        // 得到当前要去的页码
        int currentPage = formbean.getPage();
        // 设置条目开始索引
        int startIndex = currentPage == 0 ? 0 : (currentPage - 1) * maxResult;
		QueryResult<Department> qr = departmentService.getScrollData(Department.class,startIndex,maxResult);
		
		Employee employee = WebUtil.getEmployeeInSession(request);
		for(SystemPrivilegeGroup group : employee.getGroups())
		{
		    for(SystemPrivilege privilege : group.getPrivileges())
		    {
		        System.out.println(privilege.getName());
		    }
		}
		
        PageViewData<Department> pageView = new PageViewData<Department>(qr, currentPage, maxResult);
		request.setAttribute("pageViewData", pageView);
		return mapping.findForward("list");
	}

}