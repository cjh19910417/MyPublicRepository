package com.jian.web.action.privilege;

import java.util.LinkedHashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import com.jian.bean.privilege.SystemPrivilegeGroup;
import com.jian.service.base.QueryResult;
import com.jian.service.privilege.PrivilegeGroupService;
import com.jian.web.bean.PageViewData;
import com.jian.web.form.privilege.PrivilegeForm;
/**
 * 权限组显示列表
 * @author JOJO
 * @date 2012-9-17
 */
@Controller("/control/privilegegroup/list")
public class PrivilegeGroupListAction extends Action
{
    @Resource PrivilegeGroupService groupService;
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        PrivilegeForm formbean = (PrivilegeForm) form;
        // 最大一页显示多少条记录
        int maxResult = 12;
        // 得到当前要去的页码
        int currentPage = formbean.getPage();
        // 设置条目开始索引
        int startIndex = currentPage == 0 ? 0 : (currentPage - 1) * maxResult;
        // 设置排序方式
        LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
        orderby.put("name", "asc");
        QueryResult<SystemPrivilegeGroup> qr = null;
        qr = groupService.getScrollData(SystemPrivilegeGroup.class, startIndex, maxResult, orderby);
        
        PageViewData<SystemPrivilegeGroup> pageViewData = new PageViewData<SystemPrivilegeGroup>(qr,
                currentPage, maxResult);
        
        request.setAttribute("pageViewData", pageViewData);
        return mapping.findForward("list");
    }
    
}
