package com.jian.web.form.product;

public class FrontProductForm extends BaseForm
{
    public String getStyle()
    {
        return style;
    }
    public void setStyle(String style)
    {
        this.style = style;
    }
    private static final long serialVersionUID = 2414096827427239595L;
    private String sort;
    private String brandid;
    private String sex;
    private Integer typeId;
    private Integer productid;
    private String style;
    
    public Integer getProductid()
    {
        return productid;
    }
    public void setProductid(Integer productid)
    {
        this.productid = productid;
    }
    public String getSort()
    {
        return sort;
    }
    public void setSort(String sort)
    {
        this.sort = sort;
    }
    public String getBrandid()
    {
        return brandid;
    }
    public void setBrandid(String brandid)
    {
        this.brandid = brandid;
    }
    public String getSex()
    {
        return sex;
    }
    public void setSex(String sex)
    {
        this.sex = sex;
    }
    public Integer getTypeId()
    {
        
        return typeId;
    }
    public void setTypeId(Integer typeId)
    {
        this.typeId = typeId;
    }
    
}
