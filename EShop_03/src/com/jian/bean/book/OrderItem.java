package com.jian.bean.book;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
/**
 * 订单项
 *  
 *  hashCode();方法有重点:
 *      要自动由数据库生成id,又要用id来生成hashcode,id是在事务提交后才在数据库生成,
 *      可是保存到set里面是在事务提交前,所以在保存到set之前id都为null,所以有eclipse
 *      给我们自动生成的hashCode()方法会把两个不同的item对象当作同一个,导致实际上不同的
 *      item不能被插入到set集合,所以要更改下hashCode()的实现.
 *  
 *  思考:为什么这里不直接关联一个ProductInfo,而是再保存一个属性几乎一样的订单实体?
 *  -------------------------------------------------------------
 *  回答:因为ProductInfo为产品,产品有降价的可能,假如在用户当时下单产品价格为100元,可是
 *     隔天商城搞促销,该产品降为了90元,如果为关联的话,这时候订单会显示单价为90元,可是昨天
 *     用户付款时是100元,就会产生不必要的纠纷.
 *     ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
 *     
 *     您的订单: 状态:[已支付]            已支付金额:100元
 *     ------------------------------------------------
 *     产品名称                                 数量                                  单价
 *     ------------------------------------------------
 *     移动电源                                     1                 90元
 *     ------------------------------------------------
 *                                  订单总金额:90元
 * @author JOJO
 * @date 2012-9-10
 */
@Entity
public class OrderItem {
	private Integer itemid;
	/* 产品名称 */
	private String productName;
	/* 产品id */
	private Integer productid;
	/* 产品销售价 */
	private Float productPrice = 0f;
	/* 购买数量 */
	private Integer amount = 1;
	/* 产品样式 */
	private String styleName;	
	/* 产品样式ID */
	private Integer styleid;
	/* 所属订单 */
	private Order order;
	
	public OrderItem(){}
	
	public OrderItem(String productName, Integer productid, Float productPrice,
			Integer amount, String styleName, Integer styleid) {
		this.productName = productName;
		this.productid = productid;
		this.productPrice = productPrice;
		this.amount = amount;
		this.styleName = styleName;
		this.styleid = styleid;
	}
	@Id @GeneratedValue
	public Integer getItemid() {
		return itemid;
	}
	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}
	@Column(length=50,nullable=false)
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	@Column(nullable=false)
	public Integer getProductid() {
		return productid;
	}
	public void setProductid(Integer productid) {
		this.productid = productid;
	}
	@Column(nullable=false)
	public Float getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Float productPrice) {
		this.productPrice = productPrice;
	}
	@Column(nullable=false)
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	@Column(length=30,nullable=false)
	public String getStyleName() {
		return styleName;
	}
	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}
	@Column(nullable=false)
	public Integer getStyleid() {
		return styleid;
	}
	public void setStyleid(Integer styleid) {
		this.styleid = styleid;
	}
	@ManyToOne(cascade={CascadeType.MERGE, CascadeType.REFRESH}, optional=false)
	@JoinColumn(name="order_id")
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		//这一步很重要,保证了新new出来的orderitem在没有自动生成itemid时候,可以判断出两个item的不同
		result = prime * result + ((itemid == null) ? super.hashCode() : itemid.hashCode());
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
		final OrderItem other = (OrderItem) obj;
		if (itemid == null) {
			if (other.itemid != null)
				return false;
		} else if (!itemid.equals(other.itemid))
			return false;
		return true;
	}
	
}