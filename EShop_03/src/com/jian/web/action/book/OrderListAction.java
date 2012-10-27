package com.jian.web.action.book;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import com.jian.bean.book.Order;
import com.jian.bean.book.OrderState;
import com.jian.service.base.QueryResult;
import com.jian.service.book.OrderService;
import com.jian.web.bean.PageViewData;
import com.jian.web.form.book.OrderForm;
/**
 * 后台显示订单
 * @author JOJO
 * @date 2012-9-13
 */
@Controller("/control/order/list")
public class OrderListAction extends Action
{
    @Resource(name="orderServiceImpl")
    OrderService orderService;
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        OrderForm formbean = (OrderForm) form;
        // 最大一页显示多少条记录
        int maxResult = 12;
        // 得到当前要去的页码
        int currentPage = formbean.getPage();
        // 设置条目开始索引
        int startIndex = currentPage == 0 ? 0 : (currentPage - 1) * maxResult;
        // 设置排序方式
        LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
        //  查询结果集
        QueryResult<Order> qr = null;
        //  条件语句字符串拼合
        StringBuffer jpql = new StringBuffer();
        //  查询条件注入参数list
        List<Object> queryParams = new ArrayList<Object>();
        
        orderby.put("createDate", "asc");//按订单创建时间的升序排序
        jpql.append(" o.state = ?1 ");
        if(formbean.getState()!=null)
        {
            queryParams.add(formbean.getState());
        }else
        {
            queryParams.add(OrderState.WAITCONFIRM);
        }
        
        
        // 来自查询页面
        if (formbean.getQuery() != null && "true".equals(formbean.getQuery()))
        {
            //订单id查询
            if(formbean.getOrderid()!=null && !"".equals(formbean.getOrderid()))
            {
                jpql.append(" and o.orderid = ?" + (queryParams.size() + 1));
                queryParams.add(formbean.getOrderid());
            }
            //订单所属用户名模糊查询
            if(formbean.getUsername()!=null && !"".equals(formbean.getUsername()))
            {
                jpql.append(" and o.buyer.username like ?" + (queryParams.size() + 1));
                queryParams.add("%" + formbean.getUsername() + "%");
            }
            //收货人名称模糊查询
            if(formbean.getRecipients()!=null && !"".equals(formbean.getRecipients()))
            {
                jpql.append(" and o.orderDeliverInfo.recipients like ?" + (queryParams.size() + 1));
                queryParams.add("%" + formbean.getRecipients() + "%");
            }
            //购买者名称模糊查询
            if(formbean.getBuyer()!=null && !"".equals(formbean.getBuyer()))
            {
                jpql.append(" and o.orderContactInfo.buyerName like ?" + (queryParams.size() + 1));
                queryParams.add("%" + formbean.getBuyer() + "%");
            }
            //订单状态查询
            if(formbean.getState()!=null && !"".equals(formbean.getState()))
            {
                jpql.append(" and o.state = ?" + (queryParams.size() + 1));
                queryParams.add(formbean.getState());
            }
            
        }
        qr = orderService.getScrollData(Order.class, startIndex, maxResult,
                                        jpql.toString(), queryParams.toArray(),
                                        orderby);

        PageViewData<Order> pageViewData = new PageViewData<Order>(qr,
                currentPage, maxResult);

        request.setAttribute("pageViewData", pageViewData);
        return mapping.findForward("list");
    }
    
}
