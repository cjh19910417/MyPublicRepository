package com.jian.web.action.book;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.stereotype.Controller;

import com.jian.bean.book.Message;
import com.jian.bean.book.Order;
import com.jian.bean.book.OrderContactInfo;
import com.jian.bean.book.OrderDeliverInfo;
import com.jian.bean.book.OrderItem;
import com.jian.bean.shopping.DeliverWay;
import com.jian.bean.shopping.PaymentWay;
import com.jian.service.book.ContactInfoService;
import com.jian.service.book.DeliverService;
import com.jian.service.book.MessageService;
import com.jian.service.book.OrderItemService;
import com.jian.service.book.OrderService;
import com.jian.web.form.book.OrderForm;
import com.jian.web.util.UrlTools;
import com.jian.web.util.WebUtil;

/**
 * 
 * @author JOJO
 * @date 2012-9-13
 */
@Controller("/control/order/manage")
public class OrderManageAction extends DispatchAction
{
    @Resource(name = "deliverServiceImpl")
    DeliverService deliverService;
    @Resource(name = "contactInfoServiceImpl")
    ContactInfoService contactService;
    @Resource(name = "orderServiceImpl")
    OrderService orderService;
    @Resource(name = "orderItemServiceImpl")
    OrderItemService itemService;
    @Resource MessageService messageService;
    /**
     * 添加客服留言
     */
    public ActionForward addMessage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        OrderForm formbean = (OrderForm) form;
        
        Message message = new Message();
        message.setUsername(WebUtil.getEmployeeInSession(request).getUsername());
        message.setContent(formbean.getMessage());
        message.setOrder(new Order(formbean.getOrderid()));
        
        messageService.save(message);
        request.setAttribute(UrlTools.JUMP, "/control/order/view.do?orderid="+formbean.getOrderid());
        request.setAttribute("message", "添加留言成功!");
        return mapping.findForward("message");
    }

    /**
     * 客服留言界面
     */
    public ActionForward addMessageUI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {

        return mapping.findForward("addMessageUI");
    }

    /**
     * 强行解锁订单
     */
    public ActionForward allUnLock(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        OrderForm formbean = (OrderForm) form;

        orderService.unlockuser(formbean.getOrderids());

        request.setAttribute("directUrl", "/control/lockedorder/list.do");
        return mapping.findForward("directUrl");
    }

    /**
     * 解锁订单
     */
    public ActionForward employeeUnlockOrder(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        OrderForm formbean = (OrderForm) form;

        orderService.unlockuser(formbean.getOrderid());

        request.setAttribute("directUrl", "/control/order/list.do");
        return mapping.findForward("directUrl");
    }

    /**
     * 更改订单配送信息
     */
    public ActionForward modifyDeliverInfo(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        OrderForm formbean = (OrderForm) form;
        int id = formbean.getDeliverid();
        OrderDeliverInfo deliverInfo = deliverService.find(OrderDeliverInfo.class,
                                                           id);
        deliverInfo.setAddress(formbean.getAddress());
        deliverInfo.setEmail(formbean.getEmail());
        deliverInfo.setMobile(formbean.getMobile());
        deliverInfo.setPostalcode(formbean.getPostalcode());
        deliverInfo.setRecipients(formbean.getRecipients());
        deliverInfo.setTel(formbean.getTel());
        deliverInfo.setGender(formbean.getGender());
        deliverService.update(deliverInfo);

        request.setAttribute("message", "修改配送信息成功!");
        request.setAttribute(UrlTools.JUMP, UrlTools.getUrlSite("orderview")
                + "?orderid=" + deliverInfo.getOrder().getOrderid());
        return mapping.findForward("message");
    }

    /**
     * 更改订单配送信息界面
     */
    public ActionForward modifyDeliverInfoUI(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        OrderForm formbean = (OrderForm) form;
        int id = formbean.getDeliverid();
        OrderDeliverInfo deliverInfo = deliverService.find(OrderDeliverInfo.class,
                                                           id);

        formbean.setRecipients(deliverInfo.getRecipients());
        formbean.setAddress(deliverInfo.getAddress());
        formbean.setEmail(deliverInfo.getEmail());
        formbean.setMobile(deliverInfo.getMobile());
        formbean.setPostalcode(deliverInfo.getPostalcode());
        formbean.setTel(deliverInfo.getTel());
        formbean.setGender(deliverInfo.getGender());

        return mapping.findForward("modifyDeliverInfoUI");
    }

    /**
     * 联系人信息修改界面
     */
    public ActionForward modifyContactInfoUI(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        OrderForm formbean = (OrderForm) form;
        OrderContactInfo contactInfo = contactService.find(OrderContactInfo.class,
                                                           formbean.getContactid());

        formbean.setBuyer(contactInfo.getBuyerName());
        formbean.setBuyer_address(contactInfo.getAddress());
        formbean.setBuyer_gender(contactInfo.getGender());
        formbean.setBuyer_mobile(contactInfo.getMobile());
        formbean.setBuyer_postalcode(contactInfo.getPostalcode());
        formbean.setBuyer_tel(contactInfo.getTel());

        return mapping.findForward("modifyContactInfoUI");
    }

    /**
     * 订购者信息修改
     */
    public ActionForward modifyContactInfo(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        OrderForm formbean = (OrderForm) form;
        OrderContactInfo contactInfo = contactService.find(OrderContactInfo.class,
                                                           formbean.getContactid());

        contactInfo.setBuyerName(formbean.getBuyer());
        contactInfo.setAddress(formbean.getBuyer_address());
        contactInfo.setGender(formbean.getBuyer_gender());
        contactInfo.setMobile(formbean.getBuyer_mobile());
        contactInfo.setPostalcode(formbean.getBuyer_postalcode());
        contactInfo.setTel(formbean.getTel());

        contactService.update(contactInfo);
        request.setAttribute("message", "修改订购者信息成功!");
        request.setAttribute(UrlTools.JUMP, UrlTools.getUrlSite("orderview")
                + "?orderid=" + contactInfo.getOrder().getOrderid());
        return mapping.findForward("message");
    }

    /**
     * 修改支付方式界面
     */
    public ActionForward modifyPaymentWayUI(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        OrderForm formbean = (OrderForm) form;
        PaymentWay paymentWay = orderService.queryPaymentWay(formbean.getOrderid());
        DeliverWay deliverWay = orderService.queryDeliverWay(formbean.getOrderid());
        formbean.setPaymentWay(paymentWay);
        formbean.setDeliverWay(deliverWay);
        return mapping.findForward("modifyPaymentWayUI");
    }

    /**
     * 修改支付方式
     */
    public ActionForward modifyPaymentWay(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        OrderForm formbean = (OrderForm) form;
        orderService.updatePaymentWay(formbean.getOrderid(),
                                      formbean.getPaymentWay());

        request.setAttribute("message", "修改支付方式成功!");
        request.setAttribute(UrlTools.JUMP, UrlTools.getUrlSite("orderview")
                + "?orderid=" + formbean.getOrderid());
        return mapping.findForward("message");
    }

    /**
     * 修改配送方式界面
     */
    public ActionForward modifyDeliverWayUI(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        OrderForm formbean = (OrderForm) form;
        PaymentWay paymentWay = orderService.queryPaymentWay(formbean.getOrderid());
        DeliverWay deliverWay = orderService.queryDeliverWay(formbean.getOrderid());
        formbean.setPaymentWay(paymentWay);
        formbean.setDeliverWay(deliverWay);
        return mapping.findForward("modifyDeliverWayUI");
    }

    /**
     * 修改配送方式
     */
    public ActionForward modifyDeliverWay(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        OrderForm formbean = (OrderForm) form;
        Order order = orderService.find(Order.class, formbean.getOrderid());
        OrderDeliverInfo deliverInfo = deliverService.find(OrderDeliverInfo.class,
                                                           order.getOrderDeliverInfo().getDeliverid());
        deliverInfo.setDeliverway(formbean.getDeliverWay());
        deliverService.update(deliverInfo);

        request.setAttribute("message", "修改配送方式成功!");
        request.setAttribute(UrlTools.JUMP, UrlTools.getUrlSite("orderview")
                + "?orderid=" + formbean.getOrderid());
        return mapping.findForward("message");
    }

    /**
     * modifyProductAmountUI修改产品数量界面
     */
    public ActionForward modifyProductAmountUI(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        OrderForm formbean = (OrderForm) form;
        OrderItem item = itemService.find(OrderItem.class,
                                          formbean.getOrderitemid());
        formbean.setAmount(item.getAmount());
        return mapping.findForward("modifyProductAmountUI");
    }

    /**
     * 修改产品数量
     */
    public ActionForward modifyProductAmount(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        OrderForm formbean = (OrderForm) form;
        // 修改相应id的产品数量
        itemService.updateAmount(formbean.getOrderitemid(),
                                 formbean.getAmount(), formbean.getOrderid());

        request.setAttribute("message", "修改产品数量成功!");
        request.setAttribute(UrlTools.JUMP, UrlTools.getUrlSite("orderview")
                + "?orderid=" + formbean.getOrderid());
        return mapping.findForward("message");
    }

    /**
     * 修改配送费用界面
     */
    public ActionForward modifyDeliverFeeUI(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        OrderForm formbean = (OrderForm) form;
        Float deliverFee = orderService.queryDeliverFee(formbean.getOrderid());
        formbean.setDeliverFee(deliverFee);
        return mapping.findForward("modifyDeliverFeeUI");
    }

    /**
     * 修改配送费
     */
    public ActionForward modifyDeliverFee(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        OrderForm formbean = (OrderForm) form;
        orderService.updateDeliverFee(formbean.getOrderid(),
                                      formbean.getDeliverFee());

        request.setAttribute("message", "修改订单配送费成功!");
        request.setAttribute(UrlTools.JUMP, UrlTools.getUrlSite("orderview")
                + "?orderid=" + formbean.getOrderid());
        return mapping.findForward("message");
    }

    /**
     * 删除订单项
     */
    public ActionForward deleteOrderItem(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        OrderForm formbean = (OrderForm) form;

        itemService.deleteItem(formbean.getOrderitemid(), formbean.getOrderid());

        request.setAttribute("message", "删除订单项成功!");
        request.setAttribute(UrlTools.JUMP, UrlTools.getUrlSite("orderview")
                + "?orderid=" + formbean.getOrderid());
        return mapping.findForward("message");
    }

    /**
     * 取消订单
     */
    public ActionForward cancelOrder(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        OrderForm formbean = (OrderForm) form;
        orderService.cancelOrder(formbean.getOrderid());

        request.setAttribute(UrlTools.JUMP, "/control/order/list.do");
        request.setAttribute("message", "成功取消订单!");
        return mapping.findForward("message");
    }

    /**
     * 打印订单
     */

    public ActionForward printOrder(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        OrderForm formbean = (OrderForm) form;
        Order order = orderService.find(Order.class, formbean.getOrderid());
        request.setAttribute("order", order);
        return mapping.findForward("print");
    }

    /**
     * 订单审核通过
     */
    public ActionForward confirmOrder(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        OrderForm formbean = (OrderForm) form;

        orderService.confirmOrder(formbean.getOrderid());

        request.setAttribute(UrlTools.JUMP, "/control/order/list.do");
        request.setAttribute("message", "订单审核通过!");
        return mapping.findForward("message");
    }

    /**
     * 财务确认付款
     */
    public ActionForward confirmPayment(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        OrderForm formbean = (OrderForm) form;

        orderService.confirmPayment(formbean.getOrderid());

        request.setAttribute(UrlTools.JUMP,
                             "/control/order/list.do?state=WAITPAYMENT");
        request.setAttribute("message", "设置成功!");
        return mapping.findForward("message");
    }

    /**
     * 等待发货
     */
    public ActionForward turnWaitdeliver(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        OrderForm formbean = (OrderForm) form;

        orderService.turnWaitdeliver(formbean.getOrderid());

        request.setAttribute(UrlTools.JUMP,
                             "/control/order/list.do?state=ADMEASUREPRODUCT");
        request.setAttribute("message", "设置成功!");
        return mapping.findForward("message");
    }

    /**
     * 已发货
     */
    public ActionForward turnDelivered(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        OrderForm formbean = (OrderForm) form;

        orderService.turnDelivered(formbean.getOrderid());

        request.setAttribute(UrlTools.JUMP,
                             "/control/order/list.do?state=WAITDELIVER");
        request.setAttribute("message", "设置成功!");
        return mapping.findForward("message");
    }

    /**
     * 已收货
     */
    public ActionForward turnReceived(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        OrderForm formbean = (OrderForm) form;

        orderService.turnReceived(formbean.getOrderid());

        request.setAttribute(UrlTools.JUMP,
                             "/control/order/list.do?state=DELIVERED");
        request.setAttribute("message", "设置成功!");
        return mapping.findForward("message");
    }
}
