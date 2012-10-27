package com.jian.service.book.impl;

import org.springframework.stereotype.Service;

import com.jian.bean.book.OrderItem;
import com.jian.service.base.DAOSupport;
import com.jian.service.book.OrderItemService;
@Service
public class OrderItemServiceImpl extends DAOSupport implements
        OrderItemService
{

    @Override
    public void updateAmount(Integer orderitemid, Integer amount,String orderid)
    {
        
        Float productPrice = (Float) this.em.createQuery("select o.productPrice from OrderItem o where o.itemid=?1").setParameter(1, orderitemid).getSingleResult();
        Float deliverFee = (Float) this.em.createQuery("select o.deliverFee from Order o where orderid=?1").setParameter(1, orderid).getSingleResult();
        Float productTotalPrice = (Float) this.em.createQuery("select o.productTotalPrice from Order o where o.orderid=?1").setParameter(1, orderid).getSingleResult();
        Integer oldAmount = (Integer) this.em.createQuery("select o.amount from OrderItem o where o.itemid=?1").setParameter(1, orderitemid).getSingleResult();
        
        Float oldItemTotalPrice = productPrice*oldAmount;//之前该订单项总价
        Float itemTotalPrice = productPrice*amount;//现在该购买项总价
        productTotalPrice = productTotalPrice - oldItemTotalPrice + itemTotalPrice;//最新的订单总价
        Float totalPrice = productTotalPrice+deliverFee;
        Float payablefee = totalPrice;
        this.em.createQuery("update Order o set o.productTotalPrice = ?1,o.totalPrice =?2 , o.payablefee =?3 where o.orderid=?4")
        .setParameter(1, productTotalPrice)
        .setParameter(2, totalPrice)
        .setParameter(3,payablefee)
        .setParameter(4,orderid)
        .executeUpdate();
        this.em.createQuery("update OrderItem o set o.amount = ?1 where o.itemid=?2").setParameter(1, amount).setParameter(2, orderitemid).executeUpdate();
        
    }
    @Override
    public void deleteItem(Integer itemid,String orderid)
    {
        Float productPrice = (Float) this.em.createQuery("select o.productPrice from OrderItem o where o.itemid=?1").setParameter(1, itemid).getSingleResult();
        Integer amount = (Integer) this.em.createQuery("select o.amount from OrderItem o where o.itemid=?1").setParameter(1, itemid).getSingleResult();
        Float productTotalPrice = (Float) this.em.createQuery("select o.productTotalPrice from Order o where o.orderid=?1").setParameter(1, orderid).getSingleResult();
        Float deliverFee = (Float) this.em.createQuery("select o.deliverFee from Order o where orderid=?1").setParameter(1, orderid).getSingleResult();
        Float itemTotalPrice = productPrice*amount;
        productTotalPrice = productTotalPrice-itemTotalPrice;
        Float totalPrice = productTotalPrice+deliverFee;
        Float payablefee = totalPrice;
        this.em.createQuery("update Order o set o.productTotalPrice = ?1,o.totalPrice =?2 , o.payablefee =?3 where o.orderid=?4")
        .setParameter(1, productTotalPrice)
        .setParameter(2, totalPrice)
        .setParameter(3,payablefee)
        .setParameter(4,orderid)
        .executeUpdate();
        super.delete(OrderItem.class, itemid);
    }
}
