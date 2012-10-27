package com.jian.bean.user;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ContactInfo
{
    /** id **/
    private Integer contactid;
    /** 地址 **/
    private String address;
    /** 邮编 **/
    private String postalcode;
    /** 座机 **/
    private String phone;
    /** 手机 **/
    private String mobile;
    /** 所属用户 **/
    private Buyer buyer;
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getContactid()
    {
        return contactid;
    }
    public void setContactid(Integer contactid)
    {
        this.contactid = contactid;
    }
    @Column(length=60,nullable=false)
    public String getAddress()
    {
        return address;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }
    @Column(length=6)
    public String getPostalcode()
    {
        return postalcode;
    }
    public void setPostalcode(String postalcode)
    {
        this.postalcode = postalcode;
    }
    @Column(length=18)
    public String getPhone()
    {
        return phone;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
    @Column(length=11)
    public String getMobile()
    {
        return mobile;
    }
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
    @OneToOne(mappedBy="contactInfo",cascade={CascadeType.REFRESH})
    public Buyer getBuyer()
    {
        return buyer;
    }
    public void setBuyer(Buyer buyer)
    {
        this.buyer = buyer;
    }
    
}
