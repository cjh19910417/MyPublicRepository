package com.jian.web.action.shopping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import com.jian.bean.book.OrderDeliverInfo;
import com.jian.bean.shopping.BuyCart;
import com.jian.web.form.shopping.DeliverForm;
import com.jian.web.util.WebUtil;

@Controller("/customer/shopping/paymentway")
public class PaymentWayAction extends Action
{

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        DeliverForm formbean = (DeliverForm) form;
        BuyCart cart = WebUtil.getBuyCart(request);
        OrderDeliverInfo deliverInfo = cart.getDeliverInfo();
        if(deliverInfo==null)
        {
            //没有配送信息,就重定向到配送信息保存页面
            request.setAttribute("directUrl", "/customer/shopping/deliver.do");
            return mapping.findForward("directUrl");
        }
        if(cart != null)
        {
            /**
             * 配送方式回显
             */
            if(deliverInfo.getDeliverway()!=null)
            {
                formbean.setDeliverway(deliverInfo.getDeliverway());
            }
            /**
             * 支付方式回显
             */
            if(cart.getPaymentway()!=null)
            {
                formbean.setPaymentway(cart.getPaymentway());
            }
            /**
             * 送货时间回显
             */
            if(deliverInfo.getRequirement()!=null&&!"".equals(deliverInfo.getRequirement()))
            {
                formbean.setRequirement(deliverInfo.getRequirement());
            }
            /**
             * 特别说明回显
             */
            if(deliverInfo.getDelivernote()!=null&!"".equals(deliverInfo.getDelivernote()))
            {
                formbean.setDelivernote(deliverInfo.getDelivernote());
            }
            
        }
        return mapping.findForward("success");
    }
    
}
