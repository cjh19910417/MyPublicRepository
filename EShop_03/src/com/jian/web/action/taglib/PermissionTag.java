package com.jian.web.action.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.jian.bean.privilege.Employee;
import com.jian.bean.privilege.SystemPrivilege;
import com.jian.bean.privilege.SystemPrivilegeGroup;
import com.jian.bean.privilege.SystemPrivilegePK;
import com.jian.web.util.WebUtil;
/**
 * 用自定义标签,实现在jsp里实行权限验证
 * @author JOJO
 * @date 2012-9-20
 */
public class PermissionTag extends TagSupport
{
    private String module;
    private String privilege;
    
    public String getModule()
    {
        return module;
    }
    public void setModule(String module)
    {
        this.module = module;
    }
    public String getPrivilege()
    {
        return privilege;
    }
    public void setPrivilege(String privilege)
    {
        this.privilege = privilege;
    }
    @Override
    public int doStartTag() throws JspException
    {
        boolean result = false;
        //验证用户权限
        Employee employee = WebUtil.getEmployeeInSession((HttpServletRequest)pageContext.getRequest());
        SystemPrivilege privilege = new SystemPrivilege(new SystemPrivilegePK(this.module, this.privilege));
        for(SystemPrivilegeGroup group : employee.getGroups())
        {
            if(group.getPrivileges().contains(privilege))
            {
                result = true;
            }
        }
        return result? EVAL_BODY_INCLUDE:SKIP_BODY;//result为true就执行标签体,false则不执行标签体
    }
    
}
