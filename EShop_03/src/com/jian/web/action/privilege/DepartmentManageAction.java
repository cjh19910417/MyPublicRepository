package com.jian.web.action.privilege;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.stereotype.Controller;

import com.jian.bean.privilege.Department;
import com.jian.bean.privilege.Permission;
import com.jian.service.privilege.DepartmentService;
import com.jian.web.form.privilege.DepartmentForm;
import com.jian.web.util.UrlTools;

@Controller("/control/department/manage")
public class DepartmentManageAction extends DispatchAction {
	@Resource DepartmentService departmentService;
	/**
	 * 部门添加界面
	 */
	@Permission(module="department",privilege="insert")
	public ActionForward addDepartmentUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		return mapping.findForward("add");
	}
	/**
	 * 添加部门
	 */
	public ActionForward addDepartment(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DepartmentForm formbean = (DepartmentForm)form;
		Department department = new Department();
		department.setName(formbean.getName());
		departmentService.save(department);
		
		request.setAttribute("message", "部门添加成功");		
		request.setAttribute("urladdress", UrlTools.getUrlSite("control.department.list"));
		return mapping.findForward("message");
	}
	
	/**
	 * 部门修改界面
	 */
	@Permission(module="department",privilege="update")
	public ActionForward editDepartmentUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		DepartmentForm formbean = (DepartmentForm)form;
		Department department = departmentService.find(Department.class,formbean.getDepartmentid());
		formbean.setName(department.getName());
		return mapping.findForward("edit");
	}
	/**
	 * 修改部门信息
	 */
	
	public ActionForward editDepartment(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DepartmentForm formbean = (DepartmentForm)form;
		Department department = departmentService.find(Department.class,formbean.getDepartmentid());
		department.setName(formbean.getName());
		departmentService.update(department);
		
		request.setAttribute("message", "部门修改成功");		
		request.setAttribute("urladdress", UrlTools.getUrlSite("control.department.list"));
		return mapping.findForward("message");
	}
	/**
	 * 删除部门
	 */
	@Permission(module="department",privilege="remove")
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DepartmentForm formbean = (DepartmentForm)form;
		departmentService.delete(Department.class,formbean.getDepartmentid());
		
		request.setAttribute("message", "部门删除成功");		
		request.setAttribute("urladdress", UrlTools.getUrlSite("control.department.list"));
		return mapping.findForward("message");
	}
	
}