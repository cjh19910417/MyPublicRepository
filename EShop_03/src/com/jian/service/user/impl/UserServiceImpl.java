package com.jian.service.user.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.jian.bean.user.Buyer;
import com.jian.service.base.DAOSupport;
import com.jian.service.user.UserService;
import com.jian.web.util.MD5;

@Service
public class UserServiceImpl extends DAOSupport implements UserService
{

    /**
     * 密码要MD5
     */
    @Override
    public void save(Object entity)
    {
        Buyer buyer = (Buyer)entity;
        buyer.setPassword(MD5.MD5Encode(buyer.getPassword().trim()));
        super.save(buyer);
    }
    /**
     * 检测用户名是否存在
     */
    public boolean isExist(String username)
    {
        Object count = this.em.createQuery("select count(o) from Buyer o where o.username=?1").setParameter(1, username.trim()).getSingleResult();
        int counts = Integer.parseInt(count.toString());
        return counts>0;
    }
    
    /**
     * 验证帐号密码的正确性(密码MD5)
     */
    public boolean validateMD5(String username,String password)
    {
        Object count = this.em.createQuery("select count(o) from Buyer o where o.username=?1 and o.password=?2 ").setParameter(1, username.trim()).setParameter(2, MD5.MD5Encode(password)).getSingleResult();
        int counts = Integer.parseInt(count.toString());
        return counts>0;
    }
    
    /**
     * 验证账号密码(密码不MD5)
     */
    public boolean validate(String username,String password)
    {
        Object count = this.em.createQuery("select count(o) from Buyer o where o.username=?1 and o.password=?2 ").setParameter(1, username.trim()).setParameter(2, password).getSingleResult();
        int counts = Integer.parseInt(count.toString());
        return counts>0;
    }
    public void setVisibleStatus(Object[] usernames, boolean status)
    {
        StringBuffer jpql = new StringBuffer();
        for (int i = 0; i < usernames.length; i++)
        {
            jpql.append("?"+(i+2)).append(",");
        }
        jpql.deleteCharAt(jpql.length()-1);
        Query query = em.createQuery("update Buyer o set o.visible = ?1 where o.username in("+jpql+")");
        query.setParameter(1, status);
        for (int i = 0; i < usernames.length; i++)
        {
            query.setParameter(i+2, usernames[i]);
        }
        query.executeUpdate();
    }
}
