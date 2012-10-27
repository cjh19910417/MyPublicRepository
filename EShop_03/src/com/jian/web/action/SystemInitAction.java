package com.jian.web.action;

import java.util.ArrayList;
import java.util.Date;
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
import com.jian.bean.privilege.IDCard;
import com.jian.bean.privilege.SystemPrivilege;
import com.jian.bean.privilege.SystemPrivilegeGroup;
import com.jian.bean.user.Gender;
import com.jian.service.privilege.EmployeeService;
import com.jian.service.privilege.PrivilegeGroupService;
import com.jian.service.privilege.PrivilegeService;
import com.jian.web.util.UrlTools;
@Controller("/system/init")
public class SystemInitAction extends Action
{
    @Resource PrivilegeService privilegeService; 
    @Resource PrivilegeGroupService groupService;
    @Resource EmployeeService employeeService;
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        /**
         * 系统权限初始化
         */
        if(privilegeService.getCount(SystemPrivilege.class)==0)
        {
            //初始化系统所需权限
            initPrivileges();
            //初始化系统管理员权限组
            initAdminGroup();
            //初始化系统管理员
            initAdmin();
        }else{
            request.setAttribute("message", "系统已经初始化过了!");
            request.setAttribute(UrlTools.JUMP, UrlTools.getUrlSite("main"));
            return mapping.findForward("message");
        }
        
        request.setAttribute(UrlTools.JUMP, UrlTools.getUrlSite("employeelogon"));
        request.setAttribute("message", "系统初始化成功!");
        return mapping.findForward("message");
    }
    /**
     * 初始化系统管理员
     */
    private void initAdmin()
    {
        if(employeeService.getCount(Employee.class)==0)
        {
            Employee admin = new Employee();
            admin.setUsername("admin");
            admin.setPassword("admin");
            admin.setGender(Gender.MAN);
            admin.setRealname("系统管理员");
            admin.setIdCard(new IDCard("100000000000000001","广东韶关",new Date()));
            admin.getGroups().addAll(groupService.getScrollData(SystemPrivilegeGroup.class).getResultlist());
            employeeService.save(admin);
        }
    }
    /**
     * 初始化系统管理员权限组
     */
    private void initAdminGroup()
    {
        if(groupService.getCount(SystemPrivilegeGroup.class)==0)
        {
            SystemPrivilegeGroup adminGroup = new SystemPrivilegeGroup();
            adminGroup.setName("系统管理员");
            adminGroup.getPrivileges().addAll(groupService.getScrollData(SystemPrivilege.class).getResultlist());
            groupService.save(adminGroup);
        }
    }
    /**
     * 初始化所需系统权限
     */
    private void initPrivileges()
    {
        List<SystemPrivilege> privileges = new ArrayList<SystemPrivilege>();
        
        /*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~  部门权限    ~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*/
        
        privileges.add(new SystemPrivilege("department", "insert", "部门添加"));
        privileges.add(new SystemPrivilege("department", "update", "部门修改"));
        privileges.add(new SystemPrivilege("department", "remove", "部门删除"));
        privileges.add(new SystemPrivilege("department", "view", "部门查看"));
        
        /*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~  员工权限    ~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*/
        
        privileges.add(new SystemPrivilege("employee", "insert", "员工添加"));
        privileges.add(new SystemPrivilege("employee", "update", "员工修改"));
        privileges.add(new SystemPrivilege("employee", "leave", "员工离职设置"));
        privileges.add(new SystemPrivilege("employee", "view", "员工查看"));
        privileges.add(new SystemPrivilege("employee", "query", "员工查询"));
        privileges.add(new SystemPrivilege("employee", "privilegeSet", "员工权限分配"));
        
        /*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~  权限管理模块    ~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*/
        
        /*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~  订单模块权限    ~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*/
        
        /*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~  产品模块权限    ~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*/
        
        /*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~  文件管理模块权限    ~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*/
        
        /*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~  用户模块权限    ~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*/
        privilegeService.saves(privileges);
    }
    
    
}
