package com.jian.bean.product;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.compass.annotations.Index;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableProperty;
import org.compass.annotations.Store;
/**
 * 品牌
 * @author JOJO
 */
@Entity @Searchable(root=false)//root=false明确指出这个类不是搜索的基类，只是Product类的附属，是个关联类而已
public class Brand implements Serializable
{
    private static final long serialVersionUID = -4911004812410298325L;
    /**
     * 品牌id
     */
    private String code;
    /**
     * 品牌名称
     */
    private String name;
    /**
     * 是否可见
     */
    private boolean visible = true;
    /**
     * logo路径
     */
    private String logopath;
        
    public Brand()
    {
        super();
        
    }
    
    public Brand(String name, String logopath)
    {
        super();
        this.name = name;
        this.logopath = logopath;
    }
    @Id @Column(length=36,nullable=false) 
    @SearchableProperty(index=Index.NO, store=Store.YES)//因为用户不会用品牌id来搜索产品,所以不用为品牌id建立索引,但要保存到索引字段
    public String getCode()
    {
        return code;
    }
    public void setCode(String code)
    {
        this.code = code;
    }
    @Column(nullable=false,length=40)
    @SearchableProperty(index=Index.ANALYZED, store=Store.YES,name="brandName")//不使用分词,保存,索引字段为brandName
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
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
    @Column(length=80,nullable=false)
    public String getLogopath()
    {
        return logopath;
    }
    public void setLogopath(String logopath)
    {
        this.logopath = logopath;
    }
    
    
}
