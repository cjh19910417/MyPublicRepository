package com.jian.service.privilege.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jian.bean.privilege.Department;
import com.jian.bean.privilege.Employee;
import com.jian.service.base.DAOSupport;
import com.jian.service.privilege.DepartmentService;
/**
 * 部门业务bean
 * @author JOJO
 * @date 2012-9-15
 */
@Service
public class DepartmentServiceImpl extends DAOSupport implements DepartmentService {

	@Override
	public <T> void delete(Class<T> entityClass, Object[] ids) 
	{
		for(Object id : ids){
			Department department = this.find(Department.class,id);
			for(Employee employee : department.getEmployees()){
				employee.setDepartment(null);
			}
			em.remove(department);
		}
	}
	

	@Override
	public void save(Object entity)
	{
	    ((Department)entity).setDepartmentid(UUID.randomUUID().toString());
        super.save(entity);
	}

}