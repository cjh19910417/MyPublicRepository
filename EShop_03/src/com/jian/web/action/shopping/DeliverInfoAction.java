package com.jian.web.action.shopping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import com.jian.bean.book.OrderContactInfo;
import com.jian.bean.book.OrderDeliverInfo;
import com.jian.bean.shopping.BuyCart;
import com.jian.web.form.shopping.DeliverForm;
import com.jian.web.util.WebUtil;
/**
 * 配送信息界面
 * @author JOJO
 * @date 2012-9-7
 */
@Controller("/customer/shopping/deliver")
public class DeliverInfoAction extends Action
{

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        DeliverForm formbean = (DeliverForm) form;
        formbean.setEmail(WebUtil.getUserInSession(request).getEmail());
        BuyCart cart = WebUtil.getBuyCart(request);
        if(cart != null)
        {
            //收货人数据回显
            if(cart.getDeliverInfo()!=null)
            {
                OrderDeliverInfo deliverInfo = cart.getDeliverInfo();
                
                formbean.setAddress(deliverInfo.getAddress());
                formbean.setEmail(deliverInfo.getEmail());
                formbean.setRecipients(deliverInfo.getRecipients());
                formbean.setPostalcode(deliverInfo.getPostalcode());
                formbean.setTel(deliverInfo.getTel());
                formbean.setMobile(deliverInfo.getMobile());
                formbean.setGender(deliverInfo.getGender());
                formbean.setBuyerIsrecipients(cart.isBuyerIsrecipients());
            }
            if(!cart.isBuyerIsrecipients())
            {
                if(cart.getContactInfo()!=null)
                {
                    //购买者回显
                    OrderContactInfo contactInfo = cart.getContactInfo();
                    formbean.setBuyer(contactInfo.getBuyerName());
                    formbean.setBuyer_address(contactInfo.getAddress());
                    formbean.setBuyer_gender(contactInfo.getGender());
                    formbean.setBuyer_mobile(contactInfo.getMobile());
                    formbean.setBuyer_postalcode(contactInfo.getPostalcode());
                    formbean.setBuyer_tel(contactInfo.getTel());
                }
                
            }
        }
        return mapping.findForward("success");
    }
    
}
