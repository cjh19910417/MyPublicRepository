package com.jian.web.form.book;

import com.jian.bean.book.OrderState;
import com.jian.bean.shopping.DeliverWay;
import com.jian.bean.shopping.PaymentWay;
import com.jian.bean.user.Gender;
import com.jian.web.form.product.BaseForm;

public class OrderForm extends BaseForm
{
    /*订单id*/
    private String orderid;
    /*用户名*/
    private String username;
    /*收货人姓名*/
    private String recipients;
    /*购买者姓名*/
    private String buyer;
    /*订单状态*/
    private OrderState state;
    
    private Integer contactid;
    private Integer deliverid;
    private String address;
    private String postalcode;
    private String email;
    private String tel;
    private String mobile;
    private Gender gender;
    
    private Gender buyer_gender;
    private String buyer_address;
    private String buyer_postalcode;
    private String buyer_tel;
    private String buyer_mobile;
    
    private DeliverWay deliverWay;
    private PaymentWay paymentWay;
    
    private Integer amount;
    private Integer orderitemid;
    
    private Float deliverFee;
    
    private String [] orderids;
    
    private String message;
    public String getOrderid()
    {
        return orderid;
    }
    public void setOrderid(String orderid)
    {
        this.orderid = orderid;
    }
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    public String getRecipients()
    {
        return recipients;
    }
    public void setRecipients(String recipients)
    {
        this.recipients = recipients;
    }
    public String getBuyer()
    {
        return buyer;
    }
    public void setBuyer(String buyer)
    {
        this.buyer = buyer;
    }
    public OrderState getState()
    {
        return state;
    }
    public void setState(OrderState state)
    {
        this.state = state;
    }
    public Integer getContactid()
    {
        return contactid;
    }
    public void setContactid(Integer contactid)
    {
        this.contactid = contactid;
    }
    public String getAddress()
    {
        return address;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }
    public String getPostalcode()
    {
        return postalcode;
    }
    public void setPostalcode(String postalcode)
    {
        this.postalcode = postalcode;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getTel()
    {
        return tel;
    }
    public void setTel(String tel)
    {
        this.tel = tel;
    }
    public String getMobile()
    {
        return mobile;
    }
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
    public Integer getDeliverid()
    {
        return deliverid;
    }
    public void setDeliverid(Integer deliverid)
    {
        this.deliverid = deliverid;
    }
    public Gender getGender()
    {
        return gender;
    }
    public void setGender(Gender gender)
    {
        this.gender = gender;
    }
    public Gender getBuyer_gender()
    {
        return buyer_gender;
    }
    public void setBuyer_gender(Gender buyer_gender)
    {
        this.buyer_gender = buyer_gender;
    }
    public String getBuyer_address()
    {
        return buyer_address;
    }
    public void setBuyer_address(String buyer_address)
    {
        this.buyer_address = buyer_address;
    }
    public String getBuyer_postalcode()
    {
        return buyer_postalcode;
    }
    public void setBuyer_postalcode(String buyer_postalcode)
    {
        this.buyer_postalcode = buyer_postalcode;
    }
    public String getBuyer_tel()
    {
        return buyer_tel;
    }
    public void setBuyer_tel(String buyer_tel)
    {
        this.buyer_tel = buyer_tel;
    }
    public String getBuyer_mobile()
    {
        return buyer_mobile;
    }
    public void setBuyer_mobile(String buyer_mobile)
    {
        this.buyer_mobile = buyer_mobile;
    }
    public DeliverWay getDeliverWay()
    {
        return deliverWay;
    }
    public void setDeliverWay(DeliverWay deliverWay)
    {
        this.deliverWay = deliverWay;
    }
    public PaymentWay getPaymentWay()
    {
        return paymentWay;
    }
    public void setPaymentWay(PaymentWay paymentWay)
    {
        this.paymentWay = paymentWay;
    }
    public Integer getAmount()
    {
        return amount;
    }
    public void setAmount(Integer amount)
    {
        this.amount = amount;
    }
    public Integer getOrderitemid()
    {
        return orderitemid;
    }
    public void setOrderitemid(Integer orderitemid)
    {
        this.orderitemid = orderitemid;
    }
    public Float getDeliverFee()
    {
        return deliverFee;
    }
    public void setDeliverFee(Float deliverFee)
    {
        this.deliverFee = deliverFee;
    }
    public String[] getOrderids()
    {
        return orderids;
    }
    public void setOrderids(String[] orderids)
    {
        this.orderids = orderids;
    }
    public String getMessage()
    {
        return message;
    }
    public void setMessage(String message)
    {
        this.message = message;
    }
    
    
}
