package com.jian.service.book.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.jian.bean.book.Order;
import com.jian.bean.book.OrderItem;
import com.jian.bean.book.OrderState;
import com.jian.bean.product.ProductStyle;
import com.jian.bean.shopping.BuyCart;
import com.jian.bean.shopping.BuyItem;
import com.jian.bean.shopping.DeliverWay;
import com.jian.bean.shopping.PaymentWay;
import com.jian.bean.user.Buyer;
import com.jian.bean.user.ContactInfo;
import com.jian.service.base.DAOSupport;
import com.jian.service.book.OrderService;

@Service
public class OrderServiceImpl extends DAOSupport implements OrderService
{
    /**
     * 构建订单,并保存到数据库
     * @param buyCart
     * @param username
     * @return
     */
    @Override
    public synchronized Order createOrder(BuyCart buyCart, String username){
        Order order = new Order();
        Buyer buyer = em.find(Buyer.class, username);
        order.setBuyer(buyer);
        order.setDeliverFee(buyCart.getDeliveFee());
        order.setNote(buyCart.getNote());
        order.setOrderContactInfo(buyCart.getContactInfo());
        order.setOrderDeliverInfo(buyCart.getDeliverInfo());
        order.setState(OrderState.WAITCONFIRM);//等待确认
        order.setPaymentWay(buyCart.getPaymentway());       
        order.setProductTotalPrice(buyCart.getTotalSellPrice());
        order.setTotalPrice(buyCart.getOrderTotalPrice());
        order.setPayablefee(buyCart.getOrderTotalPrice());
        for(BuyItem item : buyCart.getItems()){
            ProductStyle style = item.getStyle();
            OrderItem oitem = new OrderItem(item.getProduct().getName(), item.getProduct().getId(),
                    item.getProduct().getSellprice(), item.getAmount(), style.getName(), style.getId());
            order.addOrderItem(oitem);
        }
        if(buyer.getContactInfo()==null){
            buyer.setContactInfo(new ContactInfo());
            buyer.getContactInfo().setAddress(order.getOrderContactInfo().getAddress());
            buyer.getContactInfo().setPostalcode(order.getOrderContactInfo().getPostalcode());
            buyer.getContactInfo().setPhone(order.getOrderContactInfo().getTel());
            buyer.getContactInfo().setMobile(order.getOrderContactInfo().getMobile());
            if(buyer.getRealname()==null) buyer.setRealname(order.getOrderContactInfo().getBuyerName());
            if(buyer.getGender()==null) buyer.setGender(order.getOrderContactInfo().getGender());
        }
        order.setOrderid(buildOrderid(order.getCreateDate()));
        this.save(order);
        return order;       
    }
    
    /**
     * 生成订单号,订单号的组成:两位年份两位月份两位日期+(当天订单总数+1),如果订单总数的长度不够8位,前面补零,如:09120200000001
     * @return
     */
    private String buildOrderid(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        StringBuilder sb = new StringBuilder(dateFormat.format(date));
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date now = dateFormat.parse(dateFormat.format(date));// 2009-12-02 00:00
            Query query = em.createQuery("select count(o) from Order o where o.createDate>=?1");
            query.setParameter(1, now);
            long count = (Long)query.getSingleResult();
            sb.append(fillZero(8, String.valueOf(count+1)));
        } catch (ParseException e) {
            throw new RuntimeException("生成订单号失败");
        }       
        return sb.toString();
    }
    /**
     * 补零
     * @param length 补零后的长度
     * @param source 需要补零的长符串
     * @return
     */
    private String fillZero(int length, String source) {//7
        StringBuilder result = new StringBuilder(source);
        for(int i=result.length(); i<length ; i++){
            result.insert(0, '0');
        }
        return result.toString();
    }

    @Override
    public PaymentWay queryPaymentWay(String orderid)
    {
        Query query = this.em.createQuery("select o.paymentWay from Order o where o.orderid=?1").setParameter(1, orderid);
        PaymentWay paymentWay = (PaymentWay) query.getSingleResult();
        return paymentWay;
    }

    @Override
    public void updatePaymentWay(String orderid, PaymentWay paymentWay)
    {
        Query query = this.em.createQuery("update Order o set o.paymentWay = ?1 where o.orderid=?2").setParameter(1, paymentWay).setParameter(2, orderid);
        query.executeUpdate();
    }

    @Override
    public DeliverWay queryDeliverWay(String orderid)
    {
        Query query = this.em.createQuery("select o.orderDeliverInfo.deliverway from Order o where o.orderid=?1").setParameter(1, orderid);
        DeliverWay deliverWay = (DeliverWay) query.getSingleResult();
        return deliverWay;
    }

    @Override
    public Float queryDeliverFee(String orderid)
    {
        Query query = this.em.createQuery("select o.deliverFee from Order o where o.orderid=?1").setParameter(1, orderid);
        Float deliverFee = (Float) query.getSingleResult();
        return deliverFee;
    }
    @Override
    public void updateDeliverFee(String orderid,Float deliverFee)
    {
        Float productPrice = (Float) this.em.createQuery("select o.productTotalPrice from Order o where o.orderid=?1").setParameter(1, orderid).getSingleResult();
        Float totalPrice = productPrice + deliverFee;
        Float payablefee = totalPrice;
        this.em.createQuery("update Order o set o.deliverFee = ?1,o.totalPrice =?2 , o.payablefee =?3 where o.orderid=?4")
        .setParameter(1, deliverFee)
        .setParameter(2, totalPrice)
        .setParameter(3,payablefee)
        .setParameter(4,orderid)
        .executeUpdate();
    }

    @Override
    public void cancelOrder(String orderid)
    {
        Order order = this.find(Order.class, orderid);
        if(!OrderState.RECEIVED.equals(order.getState()))//用户可能在收货前的任何状态下取消订单
        {
            order.setState(OrderState.CANCEL);
        }
        order.setLockuser(null);
    }

    @Override
    public void confirmOrder(String orderid)
    {
        Order order = this.find(Order.class, orderid);
        if(OrderState.WAITCONFIRM.equals(order.getState()))
        {
            if(!order.getPaymentstate() && !PaymentWay.COD.equals(order.getPaymentWay()))
            {
                //如果 不是货到付款且没有支付,转到等待支付状态
                order.setState(OrderState.WAITPAYMENT);
            }else{
                //其它,货到付款,已经支付,转到正在配货
                order.setState(OrderState.ADMEASUREPRODUCT);
            }
        }
        order.setLockuser(null);
    }

    @Override
    public void confirmPayment(String orderid)
    {
        Order order = this.find(Order.class, orderid);
        if(!order.getPaymentstate())
        {
            if(OrderState.WAITPAYMENT.equals(order.getState()))
            {
                order.setPaymentstate(true);
                order.setState(OrderState.ADMEASUREPRODUCT);
            }else if(OrderState.DELIVERED.equals(order.getState())&&PaymentWay.COD.equals(order.getPaymentWay()))
            {
                //货到付款的确认收货
                order.setPaymentstate(true);
                order.setState(OrderState.RECEIVED);
            }
        }
        order.setLockuser(null);
        
    }

    @Override
    public void turnWaitdeliver(String orderid)
    {
        Order order = this.find(Order.class, orderid);
        if(OrderState.ADMEASUREPRODUCT.equals(order.getState()))
        {
            order.setState(OrderState.WAITDELIVER);
        }
        order.setLockuser(null);
    }

    @Override
    public void turnDelivered(String orderid)
    {
        Order order = this.find(Order.class, orderid);
        if(OrderState.WAITDELIVER.equals(order.getState()))
        {
            order.setState(OrderState.DELIVERED);
        }
        order.setLockuser(null);
    }

    @Override
    public void turnReceived(String orderid)
    {
        Order order = this.find(Order.class,orderid);
        if(OrderState.DELIVERED.equals(order.getState())&&order.getPaymentstate())
        {
            order.setState(OrderState.RECEIVED);
        }
        order.setLockuser(null);
    }

    @Override
    public Order addUserLock(String username, String id)
    {
        /**
         * 必须加上"and o.lockuser is null"
         * 不然在两个员工同时载入订单的时候会达不到排他的效果
         * 
         *   因为在先执行的update语句的那个事务,会在数据库端加锁上排他锁,直到事务提交后释放
         *   ,那么在后面一点的员工也把lockuser设置为自己的id,那么也可以加载订单,所以只有在
         *   订单lockuser为null的时候,才能给订单加锁!
         * 
         */
        this.em.createQuery("update Order o set o.lockuser = ?1 where o.orderid = ?2 and o.lockuser is null")
        .setParameter(1, username)
        .setParameter(2, id)
        .executeUpdate();
        this.em.flush();//强行让jpql先执行队列里面的更新语句
        
        return this.find(Order.class, id);
    }
    
    @Override
    public void unlockuser(String... orderids)
    {
        StringBuffer jpql = new StringBuffer();
        for (int i = 0; i < orderids.length; i++)
        {
            jpql.append("?").append(i+1).append(",");
        }
        jpql.deleteCharAt(jpql.lastIndexOf(","));
        
        Query query = this.em.createQuery("update Order o set o.lockuser = ?0 where o.orderid in("+jpql+")");
        query.setParameter(0, null);
        for (int i = 0; i < orderids.length; i++)
        {
            query.setParameter(i+1, orderids[i]);
        }
        query.executeUpdate();
    }
}
