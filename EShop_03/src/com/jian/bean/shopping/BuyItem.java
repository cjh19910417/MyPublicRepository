package com.jian.bean.shopping;

import com.jian.bean.product.ProductInfo;
import com.jian.bean.product.ProductStyle;

public class BuyItem
{
    
    /** 要购买的产品 **/
    private ProductInfo product;
    /** 要购买产品的样式 **/
    private ProductStyle style;
    /** 要购买的数量 **/
    private Integer amount;
    
    public BuyItem()
    {
    }
    
    public BuyItem(ProductInfo pruduct, ProductStyle style, Integer amount)
    {
        super();
        this.product = pruduct;
        this.style = style;
        this.amount = amount;
    }

    public ProductInfo getProduct()
    {
        return product;
    }
    public void setProduct(ProductInfo product)
    {
        this.product = product;
    }
    public ProductStyle getStyle()
    {
        return style;
    }

    public void setStyle(ProductStyle style)
    {
        this.style = style;
    }
    public Integer getAmount()
    {
        return amount;
    }
    public void setAmount(Integer amount)
    {
        this.amount = amount;
    }
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((product == null) ? 0 : product.hashCode());
        result = prime * result + ((style == null) ? 0 : style.hashCode());
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
        BuyItem other = (BuyItem) obj;
        if (product == null)
        {
            if (other.product != null)
                return false;
        }
        else if (!product.equals(other.product))
            return false;
        if (style == null)
        {
            if (other.style != null)
                return false;
        }
        else if (!style.equals(other.style))
            return false;
        return true;
    }
}
