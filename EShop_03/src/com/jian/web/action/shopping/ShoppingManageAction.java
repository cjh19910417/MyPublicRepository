package com.jian.web.action.shopping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.stereotype.Controller;

import com.jian.bean.book.Order;
import com.jian.bean.book.OrderContactInfo;
import com.jian.bean.book.OrderDeliverInfo;
import com.jian.bean.shopping.BuyCart;
import com.jian.bean.shopping.DeliverWay;
import com.jian.bean.user.Buyer;
import com.jian.service.book.OrderService;
import com.jian.web.form.shopping.DeliverForm;
import com.jian.web.util.WebUtil;
/**
 * 购物管理
 * @author JOJO
 * @date 2012-9-7
 */
@Controller("/customer/shopping/manage")
public class ShoppingManageAction extends DispatchAction
{
    @Resource(name="orderServiceImpl")
    OrderService orderService;
    
    /**
     * 保存配货信息
     */
    public ActionForward saveDeliverInfo(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        DeliverForm formbean = (DeliverForm) form;
        
        BuyCart cart = WebUtil.getBuyCart(request);
        if(cart!=null)
        {
            //保存 配送信息
            OrderDeliverInfo deliverInfo;
            if(cart.getDeliverInfo()==null){
                deliverInfo = new OrderDeliverInfo();
            }else{
                deliverInfo = cart.getDeliverInfo();
            }
            
            deliverInfo.setAddress(formbean.getAddress());
            deliverInfo.setEmail(formbean.getEmail());
            deliverInfo.setGender(formbean.getGender());
            deliverInfo.setMobile(formbean.getMobile());
            deliverInfo.setPostalcode(formbean.getPostalcode());
            deliverInfo.setRecipients(formbean.getRecipients());
            deliverInfo.setTel(formbean.getTel());
            cart.setDeliverInfo(deliverInfo);
            
            cart.setBuyerIsrecipients(formbean.getBuyerIsrecipients());
            
            OrderContactInfo contactInfo;
            if(cart.getContactInfo()==null)
            {
                contactInfo = new OrderContactInfo();
            }else
            {
                contactInfo = cart.getContactInfo();
            }
            if(formbean.getBuyerIsrecipients())
            {
                //收货人和购买人是同一个人
                contactInfo.setAddress(formbean.getAddress());
                contactInfo.setEmail(formbean.getEmail());
                contactInfo.setGender(formbean.getGender());
                contactInfo.setMobile(formbean.getMobile());
                contactInfo.setPostalcode(formbean.getPostalcode());
                contactInfo.setBuyerName(formbean.getRecipients());
                contactInfo.setTel(formbean.getTel());
            }else
            {
                //不是同一个人
                contactInfo.setAddress(formbean.getBuyer_address());
                contactInfo.setEmail(formbean.getEmail());
                contactInfo.setGender(formbean.getBuyer_gender());
                contactInfo.setMobile(formbean.getBuyer_mobile());
                contactInfo.setPostalcode(formbean.getBuyer_postalcode());
                contactInfo.setBuyerName(formbean.getBuyer());
                contactInfo.setTel(formbean.getBuyer_tel());
            }
            cart.setContactInfo(contactInfo);
        }
        /*重定向到      支付方式    页面*/
        String url = "/customer/shopping/paymentway.do";
        if(formbean.getDirectUrl()!=null&&!"".equals(formbean.getDirectUrl()))
        {
            url = formbean.getDirectUrl();
        }
        request.setAttribute("directUrl",url );
        return mapping.findForward("directUrl");
    }
    /**
     * 保存配送和支付方式
     */
    public ActionForward savePaymentway(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        DeliverForm formbean = (DeliverForm) form;
        
        BuyCart cart = WebUtil.getBuyCart(request);
        if(cart!=null&&formbean.getDeliverway()!=null)
        {
            //设置配送方式和支付方式
            setDeliverPayway(formbean, cart);
        }
        request.setAttribute("directUrl", WebUtil.Base64encode("/customer/shopping/manage.do?method=savePaymentway"));
        return mapping.findForward("orderconfirm");
    }
    
    /**
     * 保存订单信息
     */
    public ActionForward saveorder(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        DeliverForm formbean = (DeliverForm) form;
        BuyCart cart = WebUtil.getBuyCart(request);
        Buyer user = WebUtil.getUserInSession(request);
        cart.setNote(formbean.getNote());
        //生成订单,并保存
        Order order = orderService.createOrder(cart, user.getUsername());
        request.setAttribute("order", order);
        //删掉session里面的购物车
        request.getSession().removeAttribute("buyCart");
        //重定向到/user/shopping/finish页面
        request.setAttribute("directUrl", "/user/shopping/finish.do?orderid="+order.getOrderid()+"&paymentway="+order.getPaymentWay().toString()+"&payablefee="+order.getPayablefee());
        return mapping.findForward("directUrl");
    }
    
    
    /**
     * 设置配送方式和支付方式
     * @param formbean
     * @param cart
     */
    private void setDeliverPayway(DeliverForm formbean, BuyCart cart)
    {
        OrderDeliverInfo deliverInfo = cart.getDeliverInfo();
        deliverInfo.setDeliverway(formbean.getDeliverway());
        //送货方式为快递或者为加急快递的话,有 时间要求
        if(formbean.getDeliverway() == DeliverWay.EXIGENCEEXPRESSDELIVERY || formbean.getDeliverway() == DeliverWay.EXPRESSDELIVERY)
        {
            if("other".equals(formbean.getRequirement()))
            {
                //特殊说明
                deliverInfo.setDelivernote(formbean.getDelivernote());
            }else
            {
                //时间要求
                deliverInfo.setRequirement(formbean.getRequirement());
            }
            
        }else{
            //送货方式为平邮或者EMS的话,没有时间要求
            deliverInfo.setRequirement(null);
            deliverInfo.setDelivernote(null);
        }
        
        cart.setPaymentway(formbean.getPaymentway());
        cart.setDeliverInfo(deliverInfo);
    }
    
}
