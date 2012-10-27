package com.jian.service.product;

import org.springframework.transaction.annotation.Transactional;

import com.jian.service.base.DAO;
@Transactional
public interface ProductStyleService extends DAO
{
    /**
     * 批量设置样式的 上架或下架
     * @param productids
     * @param status
     */
    public void setVisibleStatus(Object[] productids ,boolean status);
   
    
}
