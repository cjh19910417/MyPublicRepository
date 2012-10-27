package com.jian.service.privilege.impl;

import org.springframework.stereotype.Service;

import com.jian.service.base.DAOSupport;
import com.jian.service.privilege.EmployeeService;
/**
 * 员工service实现
 * @author JOJO
 * @date 2012-9-15
 */
@Service
public class EmployeeServiceImpl extends DAOSupport implements EmployeeService
{

    @Override
    public boolean exist(String username)
    {
        Long count  = (Long) this.em.createQuery("select count(o) from Employee o where o.username=?1").setParameter(1, username).getSingleResult();
        return count>0;
    }

    @Override
    public boolean validate(String username, String password)
    {
        Long count = (Long)this.em.createQuery("select count(o) from Employee o where o.username=?1 and o.password=?2").setParameter(1, username).setParameter(2, password).getSingleResult();
        return count>0;
    }

    @Override
    public void leave(String username)
    {
        this.em.createQuery("update Employee o set o.visible=?1 where o.username=?2").setParameter(1, false).setParameter(2, username).executeUpdate();
    }

}
