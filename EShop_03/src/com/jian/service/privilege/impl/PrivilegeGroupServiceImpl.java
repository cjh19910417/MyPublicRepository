package com.jian.service.privilege.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jian.bean.privilege.Employee;
import com.jian.bean.privilege.SystemPrivilegeGroup;
import com.jian.service.base.DAOSupport;
import com.jian.service.privilege.PrivilegeGroupService;
@Service
public class PrivilegeGroupServiceImpl extends DAOSupport implements
        PrivilegeGroupService
{
    /**
     * 权限组id要求UUID生成
     */
    @Override
    public void save(Object entity)
    {
        ((SystemPrivilegeGroup)entity).setId(UUID.randomUUID().toString());
        super.save(entity);
    }
    
    /**
     * 因为增加了在employee表里面的多对多关系,在中间表里面作为关系的被维护端,所以不能再一下就删除实体了,要先在employee里面解除掉关系才能删除
     */
    @Override
    public <T> void delete(Class<T> entityClass, Object[] ids)
    {
        for(Object id : ids)
        {
            SystemPrivilegeGroup group = this.find(SystemPrivilegeGroup.class, id);
            for(Employee e :group.getEmployees())
            {
                e.getGroups().remove(group);
            }
        }
        super.delete(entityClass, ids);
    }
}
