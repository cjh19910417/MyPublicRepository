package com.jian.bean.product;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.compass.annotations.Index;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableProperty;
import org.compass.annotations.Store;

/**
 * "无限级" 类别,最好实现序列化接口
 * @author JoJo
 *
 */
@Entity @Searchable(root=false)//root=false明确指出这个类不是搜索的基类，只是Product类的附属，是个关联类而已
public class ProductType implements Serializable {

    private static final long serialVersionUID = -8840115190234155298L;

	/**
	 * 类别id
	 */
	private Integer typeId;

	/**
	 * 类别名称
	 */
	private String name;
	
	/**
	 * 备注,用于搜索引擎 
	 */
	private String note;
	/**
	 * 是否可见
	 */
	private boolean visible = true;
	/**
	 * 子类别
	 */
	private Set<ProductType> childtype = new HashSet<ProductType>();	
	/**
	 * 父类别
	 */
	private ProductType parent;
	/**
	 * 该产品类别下面的产品
	 */
	private Set<ProductInfo> products = new HashSet<ProductInfo>();
	
	@OneToMany(cascade={CascadeType.REMOVE},mappedBy="type")
	public Set<ProductInfo> getProducts()
    {
        return products;
    }

    public void setProducts(Set<ProductInfo> products)
    {
        this.products = products;
    }
	
    public ProductType()
    {
    }

    public ProductType(Integer typeId)
    {
        super();
        this.typeId = typeId;
    }

    public ProductType(String name, String note)
    {
        super();
        this.name = name;
        this.note = note;
    }

    /**
	 * 一对多,mappedBy指定被  "多方" 哪个属性维护
	 */
	@OneToMany(fetch=FetchType.LAZY,cascade={CascadeType.REFRESH,CascadeType.REMOVE},mappedBy="parent")
	public Set<ProductType> getChildtype() {
		return childtype;
	}
	
	public void setChildtype(Set<ProductType> childtype) {
		this.childtype = childtype;
	}
	
	@ManyToOne(cascade={CascadeType.REFRESH},optional=true)
	@JoinColumn(name="parentid")
	public ProductType getParent() {
		return parent;
	}

	public void setParent(ProductType parent) {
		this.parent = parent;
	}

	@Column(length=36,nullable=false) @SearchableProperty(index=Index.NOT_ANALYZED,store=Store.YES,name="typeName")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(length=200)
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	@Column(nullable=false)
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SearchableProperty(index=Index.NO,store=Store.YES)
	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((typeId == null) ? 0 : typeId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductType other = (ProductType) obj;
		if (typeId == null) {
			if (other.typeId != null)
				return false;
		} else if (!typeId.equals(other.typeId))
			return false;
		return true;
	}

	
}
