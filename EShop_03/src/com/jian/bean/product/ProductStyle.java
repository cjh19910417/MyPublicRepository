package com.jian.bean.product;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.compass.annotations.Index;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableProperty;
import org.compass.annotations.SearchableReference;
import org.compass.annotations.Store;

@Entity @Searchable(root=false)//root=false明确指出这个类不是搜索的基类，只是Product类的附属，是个关联类而已
public class ProductStyle implements Serializable
{

    private static final long serialVersionUID = -6200941901417172345L;
    private Integer id;
    /**
     * 样式名称
     */
    private String name;
    /**
     * 图片名称
     */
    private String imagename;
    /**
     * 是否可见
     */
    private boolean visible = true;
    /**
     * 样式所属产品
     */
    private ProductInfo product;
    /**
     * 原图片的全路径
     */
    @SuppressWarnings("unused")
    private String imageFullPath;
    /**
     * 140x图片路径
     */
    @SuppressWarnings("unused")
    private String image140FullPath;
    public ProductStyle()
    {}
    
    
    public ProductStyle(Integer id)
    {
        super();
        this.id = id;
    }


    public ProductStyle(String name, String imagename)
    {
        super();
        this.name = name;
        this.imagename = imagename;
    }


    @Id @GeneratedValue @SearchableProperty(index=Index.NO,store=Store.YES)
    public Integer getId()
    {
        return id;
    }
    public void setId(Integer id)
    {
        this.id = id;
    }
    @Column(length=30,nullable=false) @SearchableProperty(index=Index.NO,store=Store.YES,name="styleName")
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    @Column(length=200,nullable=false) @SearchableProperty(index=Index.NO,store=Store.YES)
    public String getImagename()
    {
        return imagename;
    }
    public void setImagename(String imagename)
    {
        this.imagename = imagename;
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
    @JoinColumn(name="productid")
    @SearchableReference()
    public ProductInfo getProduct()
    {
        return product;
    }
    public void setProduct(ProductInfo product)
    {
        this.product = product;
    }
    @Transient//标注为瞬态变量,不会存入数据库
    public String getImageFullPath()
    {
        return imageFullPath = "/images/product/" + 
                + this.product.getType().getTypeId() + "/" + this.product.getId() + "/prototype/" + this.imagename;
    }
    @Transient//标注为瞬态变量,不会存入数据库
    public String getImage140FullPath()
    {
        return image140FullPath = "/images/product/" + 
                + this.product.getType().getTypeId() + "/" + this.product.getId() + "/140x/" + this.imagename;
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
        ProductStyle other = (ProductStyle) obj;
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
