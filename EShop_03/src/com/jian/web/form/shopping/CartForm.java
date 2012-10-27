package com.jian.web.form.shopping;

import org.apache.struts.action.ActionForm;

import com.jian.web.util.WebUtil;

public class CartForm extends ActionForm
{

    private Integer productid;
    private Integer styleid;
    private Integer amount = 1;
    // buyitemid = productid-styleid
    private String buyitemid;
    private String directUrl;
    
    public Integer getAmount()
    {
        return amount;
    }

    public void setAmount(Integer amount)
    {
        this.amount = amount;
    }

    public Integer getStyleid()
    {
        return styleid;
    }

    public void setStyleid(Integer styleid)
    {
        this.styleid = styleid;
    }
    public String getBuyitemid()
    {
        return buyitemid;
    }

    public void setBuyitemid(String buyitemid)
    {
        // buyitemid = productid-styleid
        this.buyitemid = buyitemid;
        if (buyitemid != null && !"".equals(buyitemid))
        {
            String[] ids = buyitemid.split("-");
            this.productid = new Integer(ids[0]);
            this.styleid = new Integer(ids[1]);
        }

    }

    public Integer getProductid()
    {
        return productid;
    }

    public void setProductid(Integer productid)
    {
        this.productid = productid;
    }

    public String getDirectUrl()
    {
        return directUrl;
    }

    public void setDirectUrl(String directUrl)
    {
        this.directUrl = WebUtil.Base64decode(directUrl);
    }

}
