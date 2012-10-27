package com.jian.web.form.shopping;

import com.jian.bean.shopping.DeliverWay;
import com.jian.bean.shopping.PaymentWay;
import com.jian.bean.user.Gender;
import com.jian.web.form.product.BaseForm;
import com.jian.web.util.WebUtil;

public class DeliverForm extends BaseForm
{
    /** 收件人 **/
    private String recipients;
    
    /** 收货人性别 **/
    private Gender gender = Gender.MAN;//需要类型转换器
    
    /** 收货人地址 **/
    private String address;
    
    /** 电子邮件 **/
    private String email;
    
    /** 邮政编码 **/
    private String postalcode;
    
    /** 电话  **/
    private String tel;
    
    /** 手机 **/
    private String mobile;
    
    /** 购买人与收货人是否相同 **/
    private boolean buyerIsrecipients = true;

    /****************当购买者与收货人不同时****************/
    
    /** 购买者 **/
    private String buyer;
    
    /** 购买人邮编 **/
    private String buyer_postalcode;
    
    /** 购买人地址 **/
    private String buyer_address;
    
    /** 购买人电话 **/
    private String buyer_tel;
    
    /** 购买人手机 **/
    private String buyer_mobile;
    
    /** 购买人性别 **/
    private Gender buyer_gender = Gender.MAN;
    
    /**********************配送方式********************/
    
    /*配送方式*/
    private DeliverWay deliverway;
    /*配送时间要求*/
    private String requirement;
    /*特殊说明*/
    private String delivernote;
    /*支付方式*/
    private PaymentWay paymentway;
    
    /** 直接跳转到directUrl **/
    private String directUrl;
    
    /**
     * 客服留言
     */
    private String note;
    
    public String getRecipients()
    {
        return recipients;
    }

    public void setRecipients(String recipients)
    {
        this.recipients = recipients;
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

    public boolean getBuyerIsrecipients()
    {
        return buyerIsrecipients;
    }

    public void setBuyerIsrecipients(boolean buyerIsrecipients)
    {
        this.buyerIsrecipients = buyerIsrecipients;
    }

    public Gender getGender()
    {
        return gender;
    }

    public void setGender(Gender gender)
    {
        this.gender = gender;
    }

    public String getBuyer()
    {
        return buyer;
    }

    public void setBuyer(String buyer)
    {
        this.buyer = buyer;
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

    public PaymentWay getPaymentway()
    {
        return paymentway;
    }

    public void setPaymentway(PaymentWay paymentway)
    {
        this.paymentway = paymentway;
    }

    public String getDirectUrl()
    {
        return directUrl;
    }

    public void setDirectUrl(String directUrl)
    {
        this.directUrl = WebUtil.Base64decode(directUrl);
    }

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }
    
    
}
