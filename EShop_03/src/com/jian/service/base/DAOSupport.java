package com.jian.service.base;

import java.util.LinkedHashMap;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jian.bean.privilege.SystemPrivilege;

/**
 * 针对dao的抽象类
 * 
 * @author JOJO
 * @date 2012-8-2
 */
@Transactional
public abstract class DAOSupport implements DAO
{
    @PersistenceContext
    protected EntityManager em;
    
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
        System.out.println(tableName);
        if(clazz.equals(SystemPrivilege.class))//复合主键
        {
            System.out.println("_____________________________");
            return (Long) em.createQuery("select count(o.name) from "+ tableName +" o").getSingleResult();
        }
        return (Long) em.createQuery("select count(o) from "+ tableName +" o").getSingleResult();
    }
    
    @Override
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public <T> QueryResult<T> getScrollData(Class<T> entityClass,
            int startIndex, int maxResult, String whereJpql,
            Object[] queryParams)
    {
        return getScrollData(entityClass, startIndex, maxResult, whereJpql,
                             queryParams, null);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public <T> QueryResult<T> getScrollData(Class<T> entityClass,
            int startIndex, int maxResult, LinkedHashMap<String, String> orderby)
    {
        return getScrollData(entityClass, startIndex, maxResult, null, null,
                             orderby);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public <T> QueryResult<T> getScrollData(Class<T> entityClass,
            int startIndex, int maxResult)
    {
        return getScrollData(entityClass, startIndex, maxResult, null, null,
                             null);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public <T> QueryResult<T> getScrollData(Class<T> entityClass)
    {
        return getScrollData(entityClass, -1, -1);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public <T> QueryResult<T> getScrollData(Class<T> entityClass,
            int startIndex, int maxResult, String whereJpql,
            Object[] queryParams, LinkedHashMap<String, String> orderby)
    {
        QueryResult<T> qr = new QueryResult<T>();
        // 得到实体名
        String entityName = getEntityName(entityClass);
        // 得到排序语句
        String orderJpql = getOrderJpql(orderby);
        // 创建查询语句
        Query query = em.createQuery("select o from " + entityName + " o "
                + (whereJpql == null ? "" : "where " + whereJpql) + orderJpql);
        // 设置要从哪里开始取,最多取多少
        if (startIndex != -1 && maxResult != -1)
        {
            query.setFirstResult(startIndex).setMaxResults(maxResult);
        }

        // 设置查询where参数
        setQueryParams(query, queryParams);

        qr.setResultlist(query.getResultList());
        
        if(entityName.equals("SystemPrivilege"))
        {
            query = em.createQuery("select count(o.name) from " + entityName + " o ");
        }
        else{
            query = em.createQuery("select count(o) from " + entityName + " o "
                    + (whereJpql == null ? "" : "where " + whereJpql));
            setQueryParams(query, queryParams);
        }
        
        qr.setTotalrecord((Long) query.getSingleResult());
        return qr;
    }

    /**
     * 设置where语句里面的参数 where o.property = ?1 and o.xxx like ?2
     * 
     * @param query
     * @param queryParams
     */
    protected void setQueryParams(Query query, Object[] queryParams)
    {
        if (queryParams != null && queryParams.length > 0)
        {
            for (int i = 0; i < queryParams.length; i++)
            {
                query.setParameter(i + 1, queryParams[i]);
            }
        }

    }

    /**
     * 拼凑order by 语句 order by o.property desc , o.xxx asc
     * 
     * @param orderby
     * @return
     */
    protected String getOrderJpql(LinkedHashMap<String, String> orderby)
    {
        StringBuffer orderbyJpql = new StringBuffer("");
        if (orderby != null && orderby.size() != 0)
        {
            orderbyJpql.append(" order by ");
            for (String key : orderby.keySet())
            {
                orderbyJpql.append("o." + key + " ").append(orderby.get(key)
                                                                    + ",");
            }
            orderbyJpql.deleteCharAt(orderbyJpql.length() - 1);
        }
        //System.out.println(orderbyJpql.toString());
        return orderbyJpql.toString();
    }

    /**
     * 得到实体名
     * 
     * @param entityClass
     * @return
     */
    protected <T> String getEntityName(Class<T> entityClass)
    {
        String entityName = entityClass.getSimpleName();
        Entity entity = entityClass.getAnnotation(Entity.class);
        if (entity.name() != null && !"".equals(entity.name()))
        {
            entityName = entity.name();
        }

        return entityName;
    }

    @Override
    public void save(Object entity)
    {
        em.persist(entity);
    }

    @Override
    public void update(Object entity)
    {
        /**
         * merge-->把 "游离态" 的实体同步回数据库
         */
        em.merge(entity);
    }

    @Override
    public <T> void delete(Class<T> entityClass, Object id)
    {
        this.delete(entityClass, new Object[] { id });
    }

    @Override
    public <T> void delete(Class<T> entityClass, Object[] ids)
    {
        for (Object id : ids)
        {
            em.remove(em.getReference(entityClass, id));
        }
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    // 这里不用开启事务,其它都是使用默认开启
    public <T> T find(Class<T> entityClass, Object id)
    {
        return em.find(entityClass, id);
    }

}
