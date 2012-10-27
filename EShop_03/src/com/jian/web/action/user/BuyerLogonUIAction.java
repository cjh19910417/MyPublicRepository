package com.jian.web.action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * 用户登录界面
 * @author JOJO
 * @date 2012-9-6
 */
import org.springframework.stereotype.Controller;
@Controller("/user/logonUI")
public class BuyerLogonUIAction extends Action
{

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        
        return mapping.findForward("success");
    }
    
}
