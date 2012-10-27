package com.jian.service.base;

import java.util.LinkedHashMap;

/**
 * DAO
 * 
 * @author JOJO
 * @date 2012-8-2
 */
public interface DAO
{
    /**
     * 保存实体
     * 
     * @param entity
     */
    public void save(Object entity);
    
    /**
     * 得到该实体的记录数,P.S.复合主键要重写!
     */
    public <T> Long getCount(Class<T> clazz);
    /**
     * 更新实体
     * 
     * @param entity
     */
    public void update(Object entity);

    /**
     * 根据id删除单个
     * 
     * @param id
     */
    public <T> void delete(Class<T> entityClass, Object id);

    /**
     * 根据ids删除一些
     * 
     * @param ids
     */
    public <T> void delete(Class<T> entityClass, Object[] ids);

    /**
     * 查询
     * 
     * @param entityClass
     *            实体类
     * @param id
     *            查询的id
     * @return
     */
    public <T> T find(Class<T> entityClass, Object id);

    /**
     * ☆☆☆☆☆ 获取分页数据 ☆☆☆☆☆
     * 
     * @param entityClass
     *            实体类
     * @param startIndex
     *            开始索引
     * @param maxResult
     *            需要获取的记录数
     * @param whereJpql
     *            where查询语句
     * @param queryParams
     *            查询参数
     * @param orderby
     *            key-->o.property ,value-->asc(升序) or desc(降序)
     * @return
     */
    public <T> QueryResult<T> getScrollData(Class<T> entityClass,
            int startIndex, int maxResult, String whereJpql,
            Object[] queryParams, LinkedHashMap<String, String> orderby);

    public <T> QueryResult<T> getScrollData(Class<T> entityClass,
            int startIndex, int maxResult, String whereJpql,
            Object[] queryParams);

    public <T> QueryResult<T> getScrollData(Class<T> entityClass,
            int startIndex, int maxResult, LinkedHashMap<String, String> orderby);

    public <T> QueryResult<T> getScrollData(Class<T> entityClass,
            int startIndex, int maxResult);

    public <T> QueryResult<T> getScrollData(Class<T> entityClass);

}
