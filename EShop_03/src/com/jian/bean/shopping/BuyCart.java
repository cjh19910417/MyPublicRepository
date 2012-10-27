package com.jian.bean.shopping;

import java.util.ArrayList;
import java.util.List;

import com.jian.bean.book.OrderContactInfo;
import com.jian.bean.book.OrderDeliverInfo;

public class BuyCart
{
    /**
     * 购物项列表
     */
    private List<BuyItem> items = new ArrayList<BuyItem>();
    
    /**
     * 总价
     */
    private Float totalSellPrice = 0f;
    
    /*总省价*/
    @SuppressWarnings("unused")
    private Float totalSavePrice = 0f;
    
    /*配送信息*/
    private OrderDeliverInfo deliverInfo;
    
    /*购买人信息*/
    private OrderContactInfo contactInfo;
    
    /*购买者和收货人是否是同一个人*/
    private boolean buyerIsrecipients = true;
    
    /*支付方式信息*/
    private PaymentWay paymentway = PaymentWay.NET;//默认为网上支付
    
    /*配送费,默认为10元*/
    private Float deliveFee = 10f;
    
    /*订单总额=总价+配送费*/
    private Float orderTotalPrice;

    /*客服留言*/
    private String Note;
    
    public List<BuyItem> getItems()
    {
        return items;
    }

    public void setItems(List<BuyItem> items)
    {
        this.items = items;
    }

    /**
     * 清空购物车
     */
    public void removeAll()
    {
       items.clear(); 
    }
    /**
     * 删除购物车单个产品
     */
    public void removeItem(BuyItem item)
    {
        if(items.contains(item))
        {
            items.remove(item);
        }
    }
    /**
     * 删除购物车多个产品
     */
    public void removeItems(List<BuyItem> items)
    {
        for(BuyItem item : items )
        {
            for(BuyItem buyItem : this.items)
            {
                if(item.equals(buyItem))
                {
                    this.items.remove(buyItem);
                }
            }
            
        }
    }
    /**
     * 修改购物车多个产品数量
     */
    public void modifyItemsAmount(List<BuyItem> items)
    {
        for(BuyItem item : items )
        {
            for(BuyItem buyItem : this.items)
            {
                if(item.equals(buyItem))
                {
                    buyItem.setAmount(item.getAmount());
                }
            }
            
        }
    }
    /**
     * 添加产品
     * @param buyItem
     */
    public void addBuyItem(BuyItem buyItem)
    {
        if(!items.contains(buyItem))
        {
            items.add(buyItem);
        }else
        {
            for(BuyItem item : items )
            {
                if(item.equals(buyItem))
                {
                    item.setAmount(item.getAmount()+1);
                }
            }
        }
    }
    /**
     * 得到总金额
     * @return
     */
    public Float getTotalSellPrice()
    {
        Float total = 0f;
        for(BuyItem item : items)
        {
            total = total + item.getProduct().getSellprice()*item.getAmount();
        }
        this.totalSellPrice=total;
        return total;
    }

    public void setTotalSellPrice(Float totalSellPrice)
    {
        this.totalSellPrice = totalSellPrice;
    }
    /**
     * 得到总共可以省的金额
     * @return
     */
    public Float getTotalSavePrice()
    {
        Float total = 0f;
        for(BuyItem item : items)
        {
            total = total + item.getProduct().getSavedPrice()*item.getAmount();
        }
        return total;
    }

    public void setTotalSavePrice(Float totalSavePrice)
    {
        this.totalSavePrice = totalSavePrice;
    }

    public OrderDeliverInfo getDeliverInfo()
    {
        return deliverInfo;
    }

    public void setDeliverInfo(OrderDeliverInfo deliverInfo)
    {
        this.deliverInfo = deliverInfo;
    }

    public OrderContactInfo getContactInfo()
    {
        return contactInfo;
    }

    public void setContactInfo(OrderContactInfo contactInfo)
    {
        this.contactInfo = contactInfo;
    }

    public boolean isBuyerIsrecipients()
    {
        return buyerIsrecipients;
    }

    public void setBuyerIsrecipients(boolean buyerIsrecipients)
    {
        this.buyerIsrecipients = buyerIsrecipients;
    }

    public PaymentWay getPaymentway()
    {
        return paymentway;
    }

    public void setPaymentway(PaymentWay paymentway)
    {
        this.paymentway = paymentway;
    }

    public Float getDeliveFee()
    {
        return deliveFee;
    }

    public void setDeliveFee(Float deliveFee)
    {
        this.deliveFee = deliveFee;
    }

    public Float getOrderTotalPrice()
    {
        this.orderTotalPrice=totalSellPrice+deliveFee;
        return this.orderTotalPrice;
    }

    public void setOrderTotalPrice(Float orderTotalPrice)
    {
        this.orderTotalPrice = orderTotalPrice;
    }

    public String getNote()
    {
        return Note;
    }

    public void setNote(String note)
    {
        Note = note;
    }


}
