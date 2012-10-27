package com.jian.service.privilege.impl;

import java.util.List;

import javax.persistence.Entity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jian.bean.privilege.SystemPrivilege;
import com.jian.service.base.DAOSupport;
import com.jian.service.privilege.PrivilegeService;

@Service
public class PrivilegeServiceImpl extends DAOSupport implements
        PrivilegeService
{
    @Override
    public void saves(List<SystemPrivilege> privileges)
    {
        for(SystemPrivilege p : privileges)
        {
            super.save(p);
        }
    }
    
    @Override
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public <T> Long getCount(Class<T> clazz)
    {
        String tableName = "";
        if(clazz.isAnnotationPresent(Entity.class))
        {
            Entity entity = clazz.getAnnotation(Entity.class);
            if(entity.name()!=null&&!"".equals(entity.name()))
            {
                tableName = entity.name();
            }
        }
        tableName = clazz.getSimpleName();
        return (Long) em.createQuery("select count(o.name) from "+ tableName +" o").getSingleResult();
    }
}
