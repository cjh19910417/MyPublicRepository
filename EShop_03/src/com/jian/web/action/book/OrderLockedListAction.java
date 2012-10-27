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
import com.jian.service.base.QueryResult;
import com.jian.service.book.OrderService;
import com.jian.web.bean.PageViewData;
import com.jian.web.form.book.OrderForm;

@Controller("/control/lockedorder/list")
public class OrderLockedListAction extends Action
{
    @Resource OrderService orderService;
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
        orderby.put("createDate", "desc");
        QueryResult<Order> qr = null;
        StringBuffer jpql = new StringBuffer();
        jpql.append(" 1 = ?1 and o.lockuser is not null ");//显示加锁的
        List<Object> queryParams = new ArrayList<Object>();
        queryParams.add(1);
        
        qr = orderService.getScrollData(Order.class, startIndex, maxResult,
                                        jpql.toString(), queryParams.toArray(),
                                        orderby);

        PageViewData<Order> pageViewData = new PageViewData<Order>(qr,
                currentPage, maxResult);

        request.setAttribute("pageViewData", pageViewData);
        request.setAttribute("viewLockButton", true);//显示解锁订单的按钮
        return mapping.findForward("list");
    }
    
}
