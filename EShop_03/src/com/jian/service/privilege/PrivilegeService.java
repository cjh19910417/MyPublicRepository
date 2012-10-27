package com.jian.service.privilege;

import java.util.List;

import com.jian.bean.privilege.SystemPrivilege;
import com.jian.service.base.DAO;

public interface PrivilegeService extends DAO
{
    /**
     * 批量保存系统权限
     * @param privileges
     */
    public void saves(List<SystemPrivilege> privileges);
}
