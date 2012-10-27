package com.jian.service.user;

import com.jian.service.base.DAO;

public interface UserService extends DAO
{
    /**
     * 检测用户名是否存在
     * @param username
     * @return
     */
    public boolean isExist(String username);
    /**
     * 验证帐号密码的正确性(密码不MD5)
     * @param username
     * @param password
     * @return
     */
    public boolean validate(String username,String password);
    /**
     * 验证帐号密码的正确性(密码MD5)
     * @param username
     * @param password
     * @return
     */
    public boolean validateMD5(String username,String password);
    /**
     * 设置用户的可用性
     * @param usernames
     * @param status
     */
    public void setVisibleStatus(Object[] usernames, boolean status);
}
