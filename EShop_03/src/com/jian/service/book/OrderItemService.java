package com.jian.service.book;

import com.jian.service.base.DAO;
/**
 * 订单项service
 * @author JOJO
 * @date 2012-9-14
 */
public interface OrderItemService extends DAO
{
    /**
     * 修改相应id的产品数量
     * @param orderitemid
     * @param amount
     */
    void updateAmount(Integer orderitemid, Integer amount,String orderid);
    /**
     * 删除订单项
     * @param itemid
     * @param orderid
     */
    void deleteItem(Integer itemid, String orderid);

    

}
