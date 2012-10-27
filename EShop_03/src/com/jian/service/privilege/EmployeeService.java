package com.jian.service.privilege;

import com.jian.service.base.DAO;

/**
 * 员工service
 * @author JOJO
 * @date 2012-9-15
 */
public interface EmployeeService extends DAO {
    /**
     * 判断用户名是否存在
     * @param username 用户名
     * @return
     */
	public boolean exist(String username);
	/**
     * 判断用户名及密码是否正确
     * @param username 用户名
     * @param password 密码
     * @return
     */
	public boolean validate(String username, String password);
	/**
	 * 设置员工离职
	 * @param username 用户名
	 */
    public void leave(String username);
}
