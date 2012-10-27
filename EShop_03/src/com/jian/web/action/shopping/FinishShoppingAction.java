package com.jian.web.action.shopping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import com.jian.bean.shopping.PaymentWay;
/**
 * 决定最终用户到哪个页面去支付订单金额
 * @author JOJO
 * @date 2012-9-12
 */
@Controller("/user/shopping/finish")
public class FinishShoppingAction extends Action
{

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        String target = "";
        String paymentwayValue = request.getParameter("paymentway");
        PaymentWay paymentWay = PaymentWay.valueOf(paymentwayValue);
        
        switch (paymentWay)
        {
            case NET://网上支付
                target = "net";
                break;
            case BANKREMITTANCE://银行电汇
                target = "bankremittance";
                break;
            case COD://货到付款
                target = "cod";
                break;
            case POSTOFFICEREMITTANCE://邮局汇款
                target = "postofficeremittance";
                break;
            default:
                target = "net";
                break;
        }
        
        return mapping.findForward(target);
    }
    
}
