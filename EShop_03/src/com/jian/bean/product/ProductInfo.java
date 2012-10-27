package com.jian.bean.product;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.compass.annotations.Index;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableComponent;
import org.compass.annotations.SearchableId;
import org.compass.annotations.SearchableProperty;
import org.compass.annotations.Store;

import com.jian.web.util.WebUtil;

/**
 * a) 产品具有一下属性:  
 * Code         货号  
 * Name         产品名称  
 * Brand        品牌  
 * Model        型号  
 * Baseprice    底价(采购进来的价格)
 * Marketprice  市场价  
 * Sellprice    销售价  
 * Weight       重量  
 * Description  产品简介
 * Buyexplain   购买说明  
 * Visible      是否可见  
 * Producttype  产品类型  
 * Createdate   上架日期 
 * Clickcount   人气指数  
 * Sellcount    销售量  
 * Commend      是否推荐  
 * Sexrequest   性别要求
 * 
 * @author JOJO
 * @date 2012-8-9
 */
@Entity @Searchable
public class ProductInfo implements Serializable
{
   
    private static final long serialVersionUID = 2715678931046311398L;
    /**
     * 产品id
     */
    private Integer id;
    /**
     *  Code         货号  
     */
    private String code;
    /**
     * 产品名称
     */
    private String name;
    /**
     * 产品品牌
     */
    private Brand brand;
    /**
     * 产品型号
     */
    private String model;
    /**
     * 底价(采购进来的价格)
     */
    private Float baseprice;
    /**
     * 市场价
     */
    private Float marketprice;
    /**
     * 销售价
     */
    private Float sellprice;
    /**
     * 产品重量(单位:g)
     */
    private Integer weight;
    /**
     * 产品表述
     */
    private String description;
    /**
     * 购买说明
     */
    private String buyexplain;
    /**
     * 是否可见,默认为可见
     */
    private boolean visible = true;
    /**
     * 产品类别
     */
    private ProductType type;
    /**
     * 上架时间,默认为创建时间
     */
    private Date createdate = new Date();
    /**
     * 人气指数
     */
    private Integer clickcount = 0;
    /**
     * 销售总量
     */
    private Integer sellcount = 0;
    /**
     * 是否推荐,默认不推荐
     */
    private boolean commend = false;
    /**
     * 性别要求,默认不限性别
     */
    private Sex sexrequest = Sex.NONE;
    /**
     * 产品样式
     */
    private Set<ProductStyle> productStyles = new HashSet<ProductStyle>();
    /**
     * 省下的钱
     */
    @SuppressWarnings("unused")
    private Float savedPrice;
    /**
     * 图文版显示时的简单表述
     */
    private String simpleDescription;
    
    public ProductInfo()
    {
        super();
    }
    public ProductInfo(Integer id)
    {
        super();
        this.id = id;
    }
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @SearchableId   //@SearchableId为compass的映射元id
    public Integer getId()
    {
        return id;
    }
    public void setId(Integer id)
    {
        this.id = id;
    }
    @Column(length=30) 
    public String getCode()
    {
        return code;
    }
    public void setCode(String code)
    {
        this.code = code;
    }
    @Column(length=50,nullable=false) @SearchableProperty(boost=2,name="productName")//建立索引,保存索引,优先等级为2(越高越优先)
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    @ManyToOne(cascade={CascadeType.REFRESH})
    @JoinColumn(name="brandid")
    @SearchableComponent //组件索引,用于复杂类型
    public Brand getBrand()
    {
        return brand;
    }
    public void setBrand(Brand brand)
    {
        this.brand = brand;
    }
    @Column(length=20)
    public String getModel()
    {
        return model;
    }
    public void setModel(String model)
    {
        this.model = model;
    }
    @Column(nullable=false)
    public Float getBaseprice()
    {
        return baseprice;
    }
    public void setBaseprice(Float baseprice)
    {
        this.baseprice = baseprice;
    }
    @Column(nullable=false) @SearchableProperty(index=Index.NO,store=Store.YES)
    public Float getMarketprice()
    {
        return marketprice;
    }
    public void setMarketprice(Float marketprice)
    {
        this.marketprice = marketprice;
    }
    @Column(nullable=false) @SearchableProperty(index=Index.NO,store=Store.YES)
    public Float getSellprice()
    {
        return sellprice;
    }
    public void setSellprice(Float sellprice)
    {
        this.sellprice = sellprice;
    }
    @SearchableProperty(index=Index.NO,store=Store.YES)
    public Integer getWeight()
    {
        return weight;
    }
    public void setWeight(Integer weight)
    {
        this.weight = weight;
    }
    @Lob @Column(nullable=false) 
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
        this.setSimpleDescription(WebUtil.HtmltoText(description));//设置simpleDescription
    }
    @Column(length=30) @SearchableProperty(index=Index.NO,store=Store.YES)
    public String getBuyexplain()
    {
        return buyexplain;
    }
    public void setBuyexplain(String buyexplain)
    {
        this.buyexplain = buyexplain;
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
    @ManyToOne(cascade={CascadeType.REFRESH},optional=false)
    @JoinColumn(name="typeid") @SearchableComponent
    public ProductType getType()
    {
        return type;
    }
    public void setType(ProductType type)
    {
        this.type = type;
    }
    /**
     * 时间,精确到天
     * @return
     */
    @Temporal(TemporalType.DATE)
    public Date getCreatedate()
    {
        return createdate;
    }
    public void setCreatedate(Date createdate)
    {
        this.createdate = createdate;
    }
    @Column(nullable=false)
    public Integer getClickcount()
    {
        return clickcount;
    }
    public void setClickcount(Integer clickcount)
    {
        this.clickcount = clickcount;
    }
    @Column(nullable=false)
    public Integer getSellcount()
    {
        return sellcount;
    }
    public void setSellcount(Integer sellcount)
    {
        this.sellcount = sellcount;
    }
    @Column(nullable=false)
    public boolean isCommend()
    {
        return commend;
    }
    public void setCommend(boolean commend)
    {
        this.commend = commend;
    }
    /**
     * 枚举
     * @return
     */
    @Enumerated(EnumType.STRING) @Column(length=5)
    public Sex getSexrequest()
    {
        return sexrequest;
    }
    public void setSexrequest(Sex sexrequest)
    {
        this.sexrequest = sexrequest;
    }
    /**
     * 产品样式
     * @return
     */
    @OneToMany(cascade={CascadeType.REMOVE,CascadeType.PERSIST},mappedBy="product",fetch=FetchType.LAZY)
    @OrderBy("visible desc,id asc")
    @SearchableComponent
    public Set<ProductStyle> getProductStyles()
    {
        return productStyles;
    }
    public void setProductStyles(Set<ProductStyle> productStyles)
    {
        this.productStyles = productStyles;
    }
    
    /**
     * 添加样式到样式集合
     */
    public void addProductStyle(ProductStyle productStyle){ 
       if(!productStyles.contains(productStyle))  
       {
           productStyles.add(productStyle);
           productStyle.setProduct(this);
       }
    }
    /**
     * 从样式集合中删除
     */
    public void removeProductStyle(ProductStyle productStyle){
        if(productStyles.contains(productStyle))  
        {
            productStyles.remove(productStyle);
            productStyle.setProduct(null);
        }
     }
    @Lob @SearchableProperty(boost=1,index=Index.ANALYZED,store=Store.YES,name="simpleDescription")
    public String getSimpleDescription()
    {
        return this.simpleDescription;
    }
    public void setSimpleDescription(String simpleDescription)
    {
        this.simpleDescription = simpleDescription;
    }
    @Transient
    public Float getSavedPrice()
    {
        return (marketprice - sellprice);
    }
    public void setSavedPrice(Float savedPrice)
    {
        this.savedPrice = savedPrice;
    }
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        ProductInfo other = (ProductInfo) obj;
        if (id == null)
        {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        return true;
    }
    
}
