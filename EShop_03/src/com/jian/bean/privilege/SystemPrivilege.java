package com.jian.bean.privilege;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
/**
 * 系统权限
 * @author JOJO
 * @date 2012-9-17
 */
@Entity
public class SystemPrivilege
{
    //权限的标识
    private SystemPrivilegePK id;
    //权限名称
    private String name;
    public SystemPrivilege(){}
    public SystemPrivilege(SystemPrivilegePK id)
    {
        this.id = id;
    }
    public SystemPrivilege(SystemPrivilegePK id, String name)
    {
        this.id = id;
        this.name = name;
    }
    public SystemPrivilege(String module,String privilege, String name)
    {
        this.id = new SystemPrivilegePK(module, privilege);
        this.name = name;
    }
    
    @Column(length=20,nullable=false)
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    @EmbeddedId 
    public SystemPrivilegePK getId()
    {
        return id;
    }
    public void setId(SystemPrivilegePK id)
    {
        this.id = id;
    }
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        SystemPrivilege other = (SystemPrivilege) obj;
        if (id == null)
        {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        return true;
    }
    
    
}
