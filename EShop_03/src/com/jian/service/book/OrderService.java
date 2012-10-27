package com.jian.service.book;

import com.jian.bean.book.Order;
import com.jian.bean.shopping.BuyCart;
import com.jian.bean.shopping.DeliverWay;
import com.jian.bean.shopping.PaymentWay;
import com.jian.service.base.DAO;
/**
 * 用户订单service
 * @author JOJO
 * @date 2012-9-11
 */
public interface OrderService extends DAO
{
    /**
     * 构建订单,并保存到数据库
     * 因为确认订单后还要显示订单的详情,给用户支付,所以生成订单且保存好后,同时把订单返回
     * @param buyCart
     * @param username
     * @return
     */
    public  Order createOrder(BuyCart buyCart, String username);
    /**
     * 得到支付方式
     * @param orderid
     * @return
     */
    public PaymentWay queryPaymentWay(String orderid);
    /**
     * 更新支付方式
     * @param orderid
     * @param paymentWay
     */
    public void updatePaymentWay(String orderid, PaymentWay paymentWay);
    /**
     * 得到订单的配送方式
     * @param orderid
     * @return
     */
    public DeliverWay queryDeliverWay(String orderid);
    /**
     * 得到订单的配送费
     * @param orderid
     * @return
     */
    public Float queryDeliverFee(String orderid);
    /**
     * 修改订单的配送费
     * @param orderid
     * @param deliverFee
     */
    void updateDeliverFee(String orderid, Float deliverFee);
    /**
     * 取消订单
     * @param orderid
     */
    public void cancelOrder(String orderid);
    /**
     * 审核订单通过
     * @param orderid
     */
    public void confirmOrder(String orderid);
    /**
     * 确认付款
     * @param orderid
     */
    public void confirmPayment(String orderid);
    /**
     * 等待发货
     * @param orderid
     */
    public void turnWaitdeliver(String orderid);
    /**
     * 已发货
     * @param orderid
     */
    public void turnDelivered(String orderid);
    /**
     * 已收货
     * @param orderid
     */
    public void turnReceived(String orderid);
    /**
     * 给订单加锁
     * @param username
     * @param id
     * @return
     */
    public Order addUserLock(String username, String id);
    /**
     * 批量解锁订单
     * @param orderids
     */
    void unlockuser(String... orderids);
}
