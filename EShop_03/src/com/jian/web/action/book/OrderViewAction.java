package com.jian.web.action.book;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import com.jian.bean.book.Order;
import com.jian.service.book.OrderService;
import com.jian.web.form.book.OrderForm;
import com.jian.web.util.UrlTools;
import com.jian.web.util.WebUtil;
/**
 * 订单详情
 * @author JOJO
 * @date 2012-9-13
 */
@Controller("/control/order/view")
public class OrderViewAction extends Action
{
    @Resource(name="orderServiceImpl")
    OrderService orderService;
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        
        OrderForm formbean = (OrderForm) form;
        String id = formbean.getOrderid();
        
        String username = WebUtil.getEmployeeInSession(request).getUsername();
        
        //给订单加锁
        Order order = orderService.addUserLock(username,id);
        
        if(order.getLockuser()!=null && username.equals(order.getLockuser()))
        {
            //不为空,而且加锁id为自己的id,可以加载订单
            request.setAttribute("order", order);
            return mapping.findForward("view");
        }
        request.setAttribute(UrlTools.JUMP, UrlTools.getUrlSite("back"));
        request.setAttribute("message", "订单正在被"+order.getLockuser()+"操作中!");
        return mapping.findForward("message");
        
    }
    
}
