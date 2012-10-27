package com.jian.web.action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;
/**
 * 网页用户查询
 * @author JOJO
 * @date 2012-9-6
 */
@Controller("/control/user/query")
public class BuyerQueryAction extends Action
{

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        
        return mapping.findForward("queryUI");
    }
    
}
