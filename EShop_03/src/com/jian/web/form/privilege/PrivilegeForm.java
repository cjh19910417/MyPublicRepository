package com.jian.web.form.privilege;

import com.jian.bean.privilege.SystemPrivilegePK;
import com.jian.web.form.product.BaseForm;
/**
 * 权限form
 * @author JOJO
 * @date 2012-9-17
 */
public class PrivilegeForm extends BaseForm
{
    /* 权限组名  */
    private String name;
    /* 权限组拥有的权限 */
    private SystemPrivilegePK[] privileges;
    /* 权限组id */
    private String groupid;
    
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public SystemPrivilegePK[] getPrivileges()
    {
        return privileges;
    }
    public void setPrivileges(SystemPrivilegePK[] privileges)
    {
        this.privileges = privileges;
    }
    public String getGroupid()
    {
        return groupid;
    }
    public void setGroupid(String groupid)
    {
        this.groupid = groupid;
    }
    
}
