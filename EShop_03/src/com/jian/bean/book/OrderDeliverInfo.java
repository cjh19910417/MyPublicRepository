package com.jian.bean.book;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.jian.bean.shopping.DeliverWay;
import com.jian.bean.user.Gender;

/**
 * 配送信息
 * @author JOJO
 * @date 2012-9-7
 */
@Entity
public class OrderDeliverInfo
{
    /** 配送id **/
    @Id @GeneratedValue 
    private Integer deliverid;
    
    /** 收件人 **/
    @Column(length=10,nullable=false)
    private String recipients;
    
    /** 收货人性别 **/
    @Enumerated(EnumType.STRING) @Column(length=5,nullable=false)
    private Gender gender = Gender.MAN;//需要类型转换器
    
    /** 收货人地址 **/
    @Column(length=50,nullable=false)
    private String address;
    
    /** 电子邮件 **/
    @Column(length=40)
    private String email;
    
    /** 邮政编码 **/
    @Column(length=6)
    private String postalcode;
    
    /** 电话  **/
    @Column(length=18)
    private String tel;
    
    /** 手机 **/
    @Column(length=11)
    private String mobile;
    
    /*配送方式*/
    @Enumerated(EnumType.STRING) @Column(length=23,nullable=false)
    private DeliverWay deliverway = DeliverWay.EXPRESSDELIVERY;//默认为快递配送
    
    /*配送时间要求*/
    @Column(length=30)
    private String requirement;
    
    /*特殊说明*/
    @Column(length=40)
    private String delivernote;
    
    /**所属订单**/
    @OneToOne(cascade=CascadeType.REFRESH,mappedBy="orderDeliverInfo")
    private Order order;
    
    
    public String getRecipients()
    {
        return recipients;
    }

    public void setRecipients(String recipients)
    {
        this.recipients = recipients;
    }

    public Gender getGender()
    {
        return gender;
    }

    public void setGender(Gender gender)
    {
        this.gender = gender;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPostalcode()
    {
        return postalcode;
    }

    public void setPostalcode(String postalcode)
    {
        this.postalcode = postalcode;
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

    public DeliverWay getDeliverway()
    {
        return deliverway;
    }

    public void setDeliverway(DeliverWay deliverway)
    {
        this.deliverway = deliverway;
    }

    public String getRequirement()
    {
        return requirement;
    }

    public void setRequirement(String requirement)
    {
        this.requirement = requirement;
    }

    public String getDelivernote()
    {
        return delivernote;
    }

    public void setDelivernote(String delivernote)
    {
        this.delivernote = delivernote;
    }

    public Integer getDeliverid()
    {
        return deliverid;
    }

    public void setDeliverid(Integer deliverid)
    {
        this.deliverid = deliverid;
    }

    public Order getOrder()
    {
        return order;
    }

    public void setOrder(Order order)
    {
        this.order = order;
    }
    
    
}
