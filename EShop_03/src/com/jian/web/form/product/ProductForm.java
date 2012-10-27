package com.jian.web.form.product;

import org.apache.struts.upload.FormFile;

public class ProductForm extends BaseForm
{
    
    public Integer[] getStylesids()
    {
        return stylesids;
    }
    public void setStylesids(Integer[] stylesids)
    {
        this.stylesids = stylesids;
    }
    public Integer getProductstyleid()
    {
        return productstyleid;
    }
    public void setProductstyleid(Integer productstyleid)
    {
        this.productstyleid = productstyleid;
    }
    private static final long serialVersionUID = 2819774135541252485L;
    /** 货号 */
    private String code;
    /*** 产品名称     */
    private String name;
    /** 产品品牌     */
    private String brandid;
    /** 产品型号     */
    private String model;
    /** 底价(采购进来的价格) */
    private Float baseprice;
    /**市场价*/
    private Float marketprice;
    /** 销售价*/
    private Float sellprice;
    /**产品重量(单位:g)*/
    private Integer weight;
    /** 产品表述*/
    private String description;
    /** 购买说明*/
    private String buyexplain;
    /** 产品类别id*/
    private Integer typeid;
    /** 性别要求,默认不限性别*/
    private String sex ;
    /** 产品样式名称 **/
    private String stylename;
    /** 产品图片名称 **/
    private FormFile imagefile;
    /** 产品id**/
    private Integer productid;
    private Integer[] productids;
    private Float startsellprice;
    private Float endsellprice;
    private Float startbaseprice;
    private Float endbaseprice;
    
    private Integer productstyleid;
    private Integer[] stylesids;
    
    
    public String getCode()
    {
        return code;
    }
    public void setCode(String code)
    {
        this.code = code;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getBrandid()
    {
        return brandid;
    }
    public void setBrandid(String brandid)
    {
        this.brandid = brandid;
    }
    public String getModel()
    {
        return model;
    }
    public void setModel(String model)
    {
        this.model = model;
    }
    public Float getBaseprice()
    {
        return baseprice;
    }
    public void setBaseprice(Float baseprice)
    {
        this.baseprice = baseprice;
    }
    public Float getMarketprice()
    {
        return marketprice;
    }
    public void setMarketprice(Float marketprice)
    {
        this.marketprice = marketprice;
    }
    public Float getSellprice()
    {
        return sellprice;
    }
    public void setSellprice(Float sellprice)
    {
        this.sellprice = sellprice;
    }
    public Integer getWeight()
    {
        return weight;
    }
    public void setWeight(Integer weight)
    {
        this.weight = weight;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public String getBuyexplain()
    {
        return buyexplain;
    }
    public void setBuyexplain(String buyexplain)
    {
        this.buyexplain = buyexplain;
    }
    public Integer getTypeid()
    {
        return typeid;
    }
    public void setTypeid(Integer typeid)
    {
        this.typeid = typeid;
    }
    public String getSex()
    {
        return sex;
    }
    public void setSex(String sex)
    {
        this.sex = sex;
    }
    public String getStylename()
    {
        return stylename;
    }
    public void setStylename(String stylename)
    {
        this.stylename = stylename;
    }
    public FormFile getImagefile()
    {
        return imagefile;
    }
    public void setImagefile(FormFile imagefile)
    {
        this.imagefile = imagefile;
    }
    public Integer[] getProductids()
    {
        return productids;
    }
    public void setProductids(Integer[] productids)
    {
        this.productids = productids;
    }
    public Float getStartsellprice()
    {
        return startsellprice;
    }
    public void setStartsellprice(Float startsellprice)
    {
        this.startsellprice = startsellprice;
    }
    public Float getEndsellprice()
    {
        return endsellprice;
    }
    public void setEndsellprice(Float endsellprice)
    {
        this.endsellprice = endsellprice;
    }
    public Float getStartbaseprice()
    {
        return startbaseprice;
    }
    public void setStartbaseprice(Float startbaseprice)
    {
        this.startbaseprice = startbaseprice;
    }
    public Float getEndbaseprice()
    {
        return endbaseprice;
    }
    public void setEndbaseprice(Float endbaseprice)
    {
        this.endbaseprice = endbaseprice;
    }
    public Integer getProductid()
    {
        return productid;
    }
    public void setProductid(Integer productid)
    {
        this.productid = productid;
    }
}
