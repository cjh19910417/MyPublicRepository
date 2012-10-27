package com.jian.web.action.privilege;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.stereotype.Controller;

import com.jian.bean.privilege.Department;
import com.jian.bean.privilege.Employee;
import com.jian.bean.privilege.IDCard;
import com.jian.bean.privilege.Permission;
import com.jian.bean.privilege.SystemPrivilegeGroup;
import com.jian.bean.user.Gender;
import com.jian.service.base.QueryResult;
import com.jian.service.privilege.DepartmentService;
import com.jian.service.privilege.EmployeeService;
import com.jian.service.privilege.PrivilegeGroupService;
import com.jian.web.form.privilege.EmployeeForm;
import com.jian.web.util.UrlTools;

/**
 * 员工管理
 * 
 * @author JOJO
 * @date 2012-9-15
 */
@Controller("/control/employee/manage")
public class EmployeeManageAction extends DispatchAction
{
    @Resource
    DepartmentService departmentService;
    @Resource
    EmployeeService employeeService;
    @Resource PrivilegeGroupService groupService;
    
    /**
     * 设置员工权限组
     */
    public ActionForward privilegeGroupSet(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        EmployeeForm formbean = (EmployeeForm) form;
        
        Employee employee = employeeService.find(Employee.class, formbean.getUsername());
        
        employee.getGroups().clear();//先清除掉
        
        //再添加进去
        for(String id :formbean.getGroupids())
        {
            employee.add2Groups(new SystemPrivilegeGroup(id));
        }
        employeeService.update(employee);
        request.setAttribute("message", "设置员工权限成功!");
        request.setAttribute(UrlTools.JUMP, UrlTools.getUrlSite("employeelist"));
        return mapping.findForward("message");
    }
    
    /**
     * 设置员工权限组界面
     */
    @Permission(module="employee",privilege="privilegeSet")
    public ActionForward privilegeGroupSetUI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        EmployeeForm formbean = (EmployeeForm) form;
        
        request.setAttribute("groups", groupService.getScrollData(SystemPrivilegeGroup.class).getResultlist());
        request.setAttribute("usergroups", employeeService.find(Employee.class, formbean.getUsername()).getGroups());
        
        return mapping.findForward("privilegeGroupSet");
    }
    /**
     * 添加员工界面
     */
    @Permission(module="employee",privilege="insert")
    public ActionForward regEmployeeUI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        EmployeeForm formbean = (EmployeeForm) form;
        QueryResult<Department> qr = departmentService.getScrollData(Department.class);// 得到所有部门
        List<Department> departments = qr.getResultlist();
        if(formbean.getGender()==null) formbean.setGender(Gender.MAN);
        
        request.setAttribute("departments", departments);
        return mapping.findForward("add");
    }

    /**
     * 添加员工
     */
    public ActionForward regEmployee(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        EmployeeForm formbean = (EmployeeForm) form;
        if(employeeService.exist(formbean.getUsername()))
        {
            //用户名存在
            request.setAttribute("message", "用户名存在!");
            request.setAttribute(UrlTools.JUMP, UrlTools.getUrlSite("back"));
            return mapping.findForward("message");
        }
        Employee employee = new Employee();
        
        employee.setDegree(formbean.getDegree());
        employee.setDepartment(new Department(formbean.getDepartmentid()));
        employee.setEmail(formbean.getEmail());
        employee.setGender(formbean.getGender());
        employee.setIdCard(new IDCard(formbean.getCardno(),
                formbean.getAddress(), formbean.getBirthday()));
        employee.setPassword(formbean.getPassword());
        employee.setPhone(formbean.getPhone());
        employee.setRealname(formbean.getRealname());
        employee.setSchool(formbean.getSchool());
        employee.setUsername(formbean.getUsername());

        // 保存图片
        if (formbean.getPicture() != null
                && formbean.getPicture().getFileSize() > 0)
        {
            String imageName = savePictureFromForm(request, formbean, employee);

            employee.setImageName(imageName);
        }
        employeeService.save(employee);

        request.setAttribute("message", "添加员工成功!");
        request.setAttribute(UrlTools.JUMP, UrlTools.getUrlSite("employeelist"));
        return mapping.findForward("message");
    }

    /**
     * 验证用户名是否存在
     */
    public ActionForward exist(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        EmployeeForm formbean = (EmployeeForm) form;

        request.setAttribute("result", employeeService.exist(formbean.getUsername()));
        return mapping.findForward("isExist");
    }
    
    
    /**
     * 员工信息修改界面
     */
    @Permission(module="employee",privilege="update")
    public ActionForward editEmployeeUI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        EmployeeForm formbean = (EmployeeForm) form;
        Employee employee = employeeService.find(Employee.class, formbean.getUsername());
        
        formbean.setAddress(employee.getIdCard().getAddress());
        formbean.setBirthday(employee.getIdCard().getBirthday());
        formbean.setCardno(employee.getIdCard().getCardno());
        formbean.setDegree(employee.getDegree());
        formbean.setEmail(employee.getEmail());
        formbean.setGender(employee.getGender());
        formbean.setPhone(employee.getPhone());
        formbean.setRealname(employee.getRealname());
        formbean.setSchool(employee.getSchool());
        QueryResult<Department> qr = departmentService.getScrollData(Department.class);// 得到所有部门
        List<Department> departments = qr.getResultlist();
        
        request.setAttribute("departments", departments);
        if(employee.getDepartment()!=null)
        request.setAttribute("selectdepartmentid", employee.getDepartment().getDepartmentid());
        request.setAttribute("imagePath", employee.getImagePath());
        
        return mapping.findForward("edit");
    }
    
    
    /**
     * 员工信息修改
     */
    public ActionForward editEmployee(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        EmployeeForm formbean = (EmployeeForm) form;
        Employee employee = employeeService.find(Employee.class, formbean.getUsername());
        
        employee.setIdCard(new IDCard(formbean.getCardno(), formbean.getAddress(), formbean.getBirthday()));
        employee.setDegree(formbean.getDegree());
        employee.setEmail(formbean.getEmail());
        employee.setGender(formbean.getGender());
        employee.setPhone(formbean.getPhone());
        employee.setRealname(formbean.getRealname());
        employee.setSchool(formbean.getSchool());
        
        if(formbean.getDepartmentid()!=null)
        employee.setDepartment(new Department(formbean.getDepartmentid()));
        if(formbean.getPicture()!=null && formbean.getPicture().getFileSize()>0)
        {
            String imageName = savePictureFromForm(request, formbean, employee);
            
            employee.setImageName(imageName);
        }
        
        
        employeeService.update(employee);
        
        request.setAttribute("message", "修改员工信息成功!");
        request.setAttribute(UrlTools.JUMP, UrlTools.getUrlSite("employeelist"));
        return mapping.findForward("message");
    }
    /**
     * 保存formbean里面的picture到硬盘
     */
    private String savePictureFromForm(HttpServletRequest request,
            EmployeeForm formbean, Employee employee)
            throws FileNotFoundException, IOException
    {
        String ext = "";
        String originalName = formbean.getPicture().getFileName();
        ext = originalName.substring(originalName.lastIndexOf('.'));
        String imageName = UUID.randomUUID() + ext;
        String fileDir = "/images/employee/" + employee.getUsername();
        String realPath = request.getSession().getServletContext().getRealPath(fileDir);
        File saveDir = new File(realPath);
        if(!saveDir.exists())
        {
            saveDir.mkdirs();
        }
        OutputStream out = new FileOutputStream(new File(realPath, imageName));
        out.write(formbean.getPicture().getFileData());
        out.close();
        return imageName;
    }
    
    /**
     * 员工离职
     */
    @Permission(module="employee",privilege="leave")
    public ActionForward leave(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        EmployeeForm formbean = (EmployeeForm) form;
        
        employeeService.leave(formbean.getUsername());
        
        request.setAttribute("message", "设置离职成功!");
        request.setAttribute(UrlTools.JUMP, UrlTools.getUrlSite("employeelist"));
        return mapping.findForward("message");
    }
}
