package com.jian.web.action.user;

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

import com.jian.bean.user.Buyer;
import com.jian.service.base.QueryResult;
import com.jian.service.user.UserService;
import com.jian.web.bean.PageViewData;
import com.jian.web.form.user.BuyerForm;
/**
 * 用户管理列表
 * @author JOJO
 * @date 2012-9-6
 */
@Controller("/control/user/list")
public class BuyerListAction extends Action
{
    @Resource(name="userServiceImpl")
    UserService service;
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        BuyerForm formbean = (BuyerForm) form;
        // 最大一页显示多少条记录
        int maxResult = 12;
        
        // 得到当前要去的页码
        int currentPage = formbean.getPage();
        
        // 设置条目开始索引
        int startIndex = currentPage == 0 ? 0 : (currentPage - 1) * maxResult;
        
        // 设置排序方式
        LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
        orderby.put("regTime", "desc");//按注册时间倒序排序
        
        QueryResult<Buyer> qr = null;
        StringBuffer jpql = new StringBuffer();
        jpql.append(" 1 = 1 ");
        List<Object> queryParams = new ArrayList<Object>();
        
        // 来自查询页面
        
        if (formbean.getQuery() != null && "true".equals(formbean.getQuery()))
        {
            if(formbean.getUsername()!=null && !"".equals(formbean.getUsername()))
            {
                jpql.append(" and o.username like ?" + (queryParams.size() + 1));
                queryParams.add("%" + formbean.getUsername() + "%");
            }
            if(formbean.getRealname()!=null && !"".equals(formbean.getRealname()))
            {
                jpql.append(" and o.realname like ?" + (queryParams.size() + 1));
                queryParams.add("%" + formbean.getRealname() + "%");
            }
            if(formbean.getEmail()!=null && !"".equals(formbean.getEmail()))
            {
                jpql.append(" and o.email like ?" + (queryParams.size() + 1));
                queryParams.add("%" + formbean.getEmail() + "%");
            }
            qr = service.getScrollData(Buyer.class, startIndex, maxResult, jpql.toString(), queryParams.toArray(), orderby);
        }else{
            //不是来自查询页面
            qr = service.getScrollData(Buyer.class, startIndex, maxResult, orderby);
        }
        

        PageViewData<Buyer> pageViewData = new PageViewData<Buyer>(qr,
                currentPage, maxResult);

        request.setAttribute("pageViewData", pageViewData);
        return mapping.findForward("list");
    }
    
}
