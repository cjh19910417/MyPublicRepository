package com.jian.web.action.privilege;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.stereotype.Controller;

import com.jian.bean.privilege.SystemPrivilege;
import com.jian.bean.privilege.SystemPrivilegeGroup;
import com.jian.bean.privilege.SystemPrivilegePK;
import com.jian.service.base.QueryResult;
import com.jian.service.privilege.PrivilegeGroupService;
import com.jian.service.privilege.PrivilegeService;
import com.jian.web.form.privilege.PrivilegeForm;
import com.jian.web.util.UrlTools;
/**
 * 权限组管理
 * @author JOJO
 * @date 2012-9-17
 */
@Controller("/control/privilegegroup/manage")
public class PrivilegeGroupManageAction extends DispatchAction
{
    @Resource PrivilegeService privilegeService;
    @Resource PrivilegeGroupService groupService;
    /**
     * 添加权限组界面
     */
    public ActionForward addUI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        
        QueryResult<SystemPrivilege> qr = privilegeService.getScrollData(SystemPrivilege.class);
        List<SystemPrivilege> privileges = qr.getResultlist();
        
        request.setAttribute("privileges", privileges);
        return mapping.findForward("addUI");
    }
    /**
     * 添加权限组
     */
    public ActionForward add(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        PrivilegeForm formbean = (PrivilegeForm) form;
        
        SystemPrivilegeGroup group = new SystemPrivilegeGroup();
        group.setName(formbean.getName());
        for(SystemPrivilegePK id : formbean.getPrivileges())
        {
            group.addSystemPrivilege(new SystemPrivilege(id));
        }
            
        groupService.save(group);
        request.setAttribute(UrlTools.JUMP, UrlTools.getUrlSite("privilege.group.list"));
        request.setAttribute("message", "添加权限组成功!");
        return mapping.findForward("message");
    }
    /**
     * 修改权限组界面
     */
    public ActionForward editUI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        PrivilegeForm formbean = (PrivilegeForm) form;
        
        SystemPrivilegeGroup group = groupService.find(SystemPrivilegeGroup.class, formbean.getGroupid());
        
        formbean.setName(group.getName());
        
        QueryResult<SystemPrivilege> qr = privilegeService.getScrollData(SystemPrivilege.class);
        List<SystemPrivilege> privileges = qr.getResultlist();
        request.setAttribute("selectprivileges", group.getPrivileges());
        request.setAttribute("privileges", privileges);
        return mapping.findForward("editUI");
    }
    /**
     * 修改权限组
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        PrivilegeForm formbean = (PrivilegeForm) form;
        
        SystemPrivilegeGroup group = groupService.find(SystemPrivilegeGroup.class, formbean.getGroupid());
        group.setName(formbean.getName());
        group.getPrivileges().clear();
        for(SystemPrivilegePK id : formbean.getPrivileges())
        {
            group.addSystemPrivilege(new SystemPrivilege(id));
        }
        
        groupService.update(group);
        request.setAttribute(UrlTools.JUMP, UrlTools.getUrlSite("privilege.group.list"));
        request.setAttribute("message", "修改权限组成功!");
        return mapping.findForward("message");
    }
    /**
     * 删除权限组
     */
    public ActionForward delete(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        PrivilegeForm formbean = (PrivilegeForm) form;
        
        groupService.delete(SystemPrivilegeGroup.class, formbean.getGroupid());
        
        request.setAttribute(UrlTools.JUMP, UrlTools.getUrlSite("privilege.group.list"));
        request.setAttribute("message", "删除权限组成功!");
        return mapping.findForward("message");
        
    }
    
}
