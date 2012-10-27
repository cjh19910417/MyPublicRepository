package com.jian.web.action.privilege;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import com.jian.bean.privilege.Employee;
import com.jian.bean.privilege.Permission;
import com.jian.service.base.QueryResult;
import com.jian.service.privilege.EmployeeService;
import com.jian.web.bean.PageViewData;
import com.jian.web.form.privilege.EmployeeForm;

/**
 * 员工分页列表
 * @author JOJO
 * @date 2012-9-15
 */
@Controller("/control/employee/list")
public class EmployeeListAction extends Action {
	@Resource EmployeeService employeeService;
	@Permission(module="employee",privilege="view")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		EmployeeForm formbean = (EmployeeForm) form;
		// 最大一页显示多少条记录
        int maxResult = 5;
        // 得到当前要去的页码
        int currentPage = formbean.getPage();
        // 设置条目开始索引
        int startIndex = currentPage == 0 ? 0 : (currentPage - 1) * maxResult;
        QueryResult<Employee> qr ;
		PageViewData<Employee> pageView; 
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("realname", "desc");
		if("true".equals(formbean.getQuery())){//如果来自查询页面
			StringBuilder jpql = new StringBuilder();
			List<Object> params = new ArrayList<Object>();
			if(formbean.getUsername()!=null && !"".equals(formbean.getUsername().trim())){
				params.add("%"+ formbean.getUsername().trim() +"%");
				jpql.append("o.username like ?").append(params.size());
			}
			if(formbean.getRealname()!=null && !"".equals(formbean.getRealname().trim())){
				if(!params.isEmpty()) jpql.append(" and ");
				params.add("%"+ formbean.getRealname().trim() +"%");
				jpql.append("o.realname like ?").append(params.size());
			}
			if(formbean.getDepartmentid()!=null && !"".equals(formbean.getDepartmentid().trim())){
				if(!params.isEmpty()) jpql.append(" and ");
				params.add(formbean.getDepartmentid());
				jpql.append("o.department.departmentid=?").append(params.size());
			}
			qr = employeeService.getScrollData(Employee.class,startIndex,maxResult,
							jpql.toString(), params.toArray(), orderby);
		}else{
			qr = employeeService.getScrollData(Employee.class,startIndex,maxResult, orderby);
		}		
		pageView = new PageViewData<Employee>(qr, currentPage, maxResult);
		request.setAttribute("pageViewData", pageView);
		return mapping.findForward("list");
	}

}