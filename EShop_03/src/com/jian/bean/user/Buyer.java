package com.jian.bean.user;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class Buyer
{
    
    /** 用户名 **/
    private String username;
    /** 密码 **/
    private String password;
    /** 注册时间 **/
    private Date regTime = new Date();
    /** 性别 
     *      枚举
     */
    private Gender gender = Gender.MAN;
    /** 真实名称 **/
    private String realname;
    /** email **/
    private String email;
    /** 是否禁用 **/
    private boolean visible = true;
    /** 联系信息 **/
    private ContactInfo contactInfo;
    
    @Id @Column(length=20)
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    @Column(length=32,nullable=false)
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    @Temporal(value=TemporalType.TIMESTAMP)
    public Date getRegTime()
    {
        return regTime;
    }
    public void setRegTime(Date regTime)
    {
        this.regTime = regTime;
    }
    @Enumerated(value=EnumType.STRING) @Column(nullable=false,length=5)
    public Gender getGender()
    {
        return gender;
    }
    public void setGender(Gender gender)
    {
        this.gender = gender;
    }
    @Column(length=10)
    public String getRealname()
    {
        return realname;
    }
    public void setRealname(String realname)
    {
        this.realname = realname;
    }
    @Column(length=40,nullable=false)
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    @Column(nullable=false)
    public boolean isVisible()
    {
        return visible;
    }
    public void setVisible(boolean visible)
    {
        this.visible = visible;
    }
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="contact_id")
    public ContactInfo getContactInfo()
    {
        return contactInfo;
    }
    public void setContactInfo(ContactInfo contactInfo)
    {
        this.contactInfo = contactInfo;
    }
    
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((username == null) ? 0 : username.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Buyer other = (Buyer) obj;
        if (username == null)
        {
            if (other.username != null)
                return false;
        }
        else if (!username.equals(other.username))
            return false;
        return true;
    }
}
