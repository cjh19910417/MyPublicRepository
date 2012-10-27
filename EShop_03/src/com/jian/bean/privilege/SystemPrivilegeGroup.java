package com.jian.bean.privilege;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
/**
 * 权限组
 * @author JOJO
 * @date 2012-9-17
 */
@Entity
public class SystemPrivilegeGroup
{
    //权限组标识符    UUID生成
    private String id;
    //权限组名称
    private String name;
    //该权限组下所拥有的权限   多对多关系
    private Set<SystemPrivilege> privileges = new HashSet<SystemPrivilege>();
    //拥有该权限组的员工,定义为双向关系
    private Set<Employee> employees = new HashSet<Employee>();
    public SystemPrivilegeGroup()
    {
    }
    public SystemPrivilegeGroup(String id)
    {
        this.id = id;
    }
    
    @Id
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
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
    /**
     * 多对多关系,根据业务需求这里还要积极加载,不能使用懒加载
     *  多对多-->@JoinTable(name="指定中间表的表名",inverseJoinColumns={用来反转映射在中间表中的其中一个字段,那个不在Group之外的那个表,就是SystemPrivilege表的PK},JoinColumns="用来指定剩下的那个字段,就是本表的PK")
     *  
     * @return
     */
    @ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.REFRESH)
    @JoinTable(name="ps",inverseJoinColumns=
        {@JoinColumn(name="module",referencedColumnName="module")
        ,@JoinColumn(name="privilege",referencedColumnName="privilege")},
        joinColumns=@JoinColumn(name="group_id")
            )
    public Set<SystemPrivilege> getPrivileges()
    {
        return privileges;
    }
    public void setPrivileges(Set<SystemPrivilege> privileges)
    {
        this.privileges = privileges;
    }
    
    /**
     * 往权限组添加权限
     */
    public void addSystemPrivilege(SystemPrivilege privilege)
    {
        this.privileges.add(privilege);
    }
    
    @ManyToMany(mappedBy="groups",cascade=CascadeType.REFRESH)
    public Set<Employee> getEmployees()
    {
        return employees;
    }
    public void setEmployees(Set<Employee> employees)
    {
        this.employees = employees;
    }
    
}
