package com.jian.bean.book;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.jian.bean.user.Gender;
/**
 * 订购者联系信息
 * @author JOJO
 * @date 2012-9-7
 */
@Entity
public class OrderContactInfo
{
    /** 联系信息id **/
    @Id @GeneratedValue
    private Integer contactid;
    /** 购买者 **/
    @Column(length=10,nullable=false)
    private String buyerName;
    
    /** 购买人邮编 **/
    @Column(length=6)
    private String postalcode;
    
    /** 购买人地址 **/
    @Column(length=50,nullable=false)
    private String address;
    
    /** 购买人电话 **/
    @Column(length=18)
    private String tel;
    
    /** 购买人手机 **/
    @Column(length=11)
    private String mobile;
    
    /** 购买人性别 **/
    @Column(length=6,nullable=false)
    private Gender gender = Gender.MAN;
    
    /** 邮箱 **/
    @Column(length=30)
    private String email;

    /** 所属订单 **/
    @OneToOne(cascade=CascadeType.REFRESH,mappedBy="orderContactInfo")
    private Order order;
    
    public String getBuyerName()
    {
        return buyerName;
    }

    public void setBuyerName(String buyerName)
    {
        this.buyerName = buyerName;
    }

    public String getPostalcode()
    {
        return postalcode;
    }

    public void setPostalcode(String postalcode)
    {
        this.postalcode = postalcode;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
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

    public Gender getGender()
    {
        return gender;
    }

    public void setGender(Gender gender)
    {
        this.gender = gender;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Integer getContactid()
    {
        return contactid;
    }

    public void setContactid(Integer contactid)
    {
        this.contactid = contactid;
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
