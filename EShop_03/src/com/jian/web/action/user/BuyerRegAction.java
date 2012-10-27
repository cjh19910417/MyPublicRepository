package com.jian.web.action.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.stereotype.Controller;

import com.jian.bean.user.Buyer;
import com.jian.service.user.UserService;
import com.jian.web.form.user.UserForm;
import com.jian.web.util.UrlTools;
import com.jian.web.util.WebUtil;

@Controller("/user/reg")
public class BuyerRegAction extends DispatchAction
{
    @Resource(name="userServiceImpl")
    UserService userService;
    /**
     * 注册用户
     */
    public ActionForward reg(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        UserForm formbean = (UserForm) form;

        if (!userService.isExist(formbean.getUsername()))
        {
            // 用户名不存在,
            Buyer buyer = new Buyer();
            buyer.setUsername(formbean.getUsername().trim());
            buyer.setPassword(formbean.getPassword().trim());
            buyer.setEmail(formbean.getEmail().trim());
            userService.save(buyer);
            
            request.setAttribute("message", "恭喜,注册成功!!");
            request.setAttribute(UrlTools.JUMP,"/user/logonUI.do?directUrl="+WebUtil.Base64encode(formbean.getDirectUrl()));
            return mapping.findForward("message");
        }
        request.setAttribute("isExist", "用户名已存在!!");
        return mapping.findForward("regUI");
    }
    
}
