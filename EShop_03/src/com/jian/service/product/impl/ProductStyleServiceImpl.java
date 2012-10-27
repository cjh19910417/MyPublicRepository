package com.jian.service.product.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jian.service.base.DAOSupport;
import com.jian.service.product.ProductStyleService;

@Service
@Transactional
public class ProductStyleServiceImpl extends DAOSupport implements
        ProductStyleService
{
    @Override
    public void setVisibleStatus(Object[] productids, boolean status)
    {
        StringBuffer jpql = new StringBuffer();
        for (int i = 0; i < productids.length; i++)
        {
            jpql.append("?"+(i+2)).append(",");
        }
        jpql.deleteCharAt(jpql.length()-1);
        Query query = em.createQuery("update ProductStyle o set o.visible = ?1 where o.id in("+jpql+")");
        query.setParameter(1, status);
        for (int i = 0; i < productids.length; i++)
        {
            query.setParameter(i+2, productids[i]);
        }
        query.executeUpdate();
    }
}
