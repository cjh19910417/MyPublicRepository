package com.jian.bean.privilege;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
/**
 * 系统权限的符合主键
 * @author JOJO
 * @date 2012-9-17
 */
@Embeddable//使用嵌入注解
public class SystemPrivilegePK implements Serializable//★★★★★复合主键一定要实现序列化接口★★★★★
{
    //权限模块-->部门?员工?
    private String module;
    //权限操作-->添加?删除?修改?查看?
    private String privilege;
    public SystemPrivilegePK(){}
    public SystemPrivilegePK(String module, String privilege)
    {
        this.module = module;
        this.privilege = privilege;
    }
    @Column(length=20,nullable=false)
    public String getModule()
    {
        return module;
    }
    public void setModule(String module)
    {
        this.module = module;
    }
    @Column(length=20,nullable=false)
    public String getPrivilege()
    {
        return privilege;
    }
    public void setPrivilege(String privilege)
    {
        this.privilege = privilege;
    }
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((module == null) ? 0 : module.hashCode());
        result = prime * result
                + ((privilege == null) ? 0 : privilege.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SystemPrivilegePK other = (SystemPrivilegePK) obj;
        if (module == null)
        {
            if (other.module != null)
                return false;
        }
        else if (!module.equals(other.module))
            return false;
        if (privilege == null)
        {
            if (other.privilege != null)
                return false;
        }
        else if (!privilege.equals(other.privilege))
            return false;
        return true;
    }
    
    
}
