package com.jian.web.action.user;

import java.io.StringWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.stereotype.Controller;

import com.jian.bean.user.Buyer;
import com.jian.service.user.UserService;
import com.jian.web.form.user.BuyerForm;
import com.jian.web.util.EmailSender;
import com.jian.web.util.MD5;
import com.jian.web.util.UrlTools;
@Controller("/user/post")
public class BuyerFindPasswordAction extends DispatchAction
{
    @Resource UserService service;
    /**
     * 找回密码,第一步
     */
    public ActionForward getpassword(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        BuyerForm formbean = (BuyerForm) form;
        
        if(formbean.getUsername()!=null && service.isExist(formbean.getUsername().trim()))
        {
            Buyer buyer = service.find(Buyer.class, formbean.getUsername());
            Template template = Velocity.getTemplate("mailContent.html");
            VelocityContext context = new VelocityContext();
            context.put("username", formbean.getUsername());
            String validateCode = MD5.MD5Encode(formbean.getUsername()+buyer.getPassword());
            
            context.put("validateCode", validateCode);
            StringWriter writer = new StringWriter();
            template.merge(context, writer);
            EmailSender.send(buyer.getEmail(), "找回密码", writer.toString(), "text/html");
            return mapping.findForward("findpassword2");
        }
        request.setAttribute("message", "用户名有误!");
        return mapping.findForward("findpassword");
    }
    
    /**
     * 找回密码第二步,验证url,和返回修改密码的界面
     */
    public ActionForward update(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        BuyerForm formbean = (BuyerForm) form;
        
        if(formbean.getUsername()!=null && service.isExist(formbean.getUsername().trim()))
        {
            Buyer buyer = service.find(Buyer.class, formbean.getUsername());
            String validateCode = MD5.MD5Encode(formbean.getUsername()+buyer.getPassword());
            if(validateCode.equals(formbean.getValidateCode().trim()))
            {
                //验证通过
                return mapping.findForward("findpassword3");
            }
        }
        
        return mapping.findForward("error");
    }
    /**
     * 找回密码第三步,为了防止黑客之类的在查看提交路径后自己绕过之前的校验,就进行提交,所以在最后更改用户密码之前还要做最后一层的验证
     */
    public ActionForward changepassword(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        BuyerForm formbean = (BuyerForm) form;
        String password = formbean.getPassword();
        String confirm_password = formbean.getConfirm_password();
        
        if(password!=null&&confirm_password!=null&&!"".equals(password.trim())&&!"".equals(confirm_password.trim()))
        {
            if(formbean.getUsername()!=null && service.isExist(formbean.getUsername().trim()))
            {
                Buyer buyer = service.find(Buyer.class, formbean.getUsername());
                String validateCode = MD5.MD5Encode(formbean.getUsername()+buyer.getPassword());
                if(validateCode.equals(formbean.getValidateCode().trim()))
                {
                    buyer.setPassword(MD5.MD5Encode(password));
                    service.update(buyer);
                    
                    request.setAttribute(UrlTools.JUMP, UrlTools.getUrlSite("logonUI"));
                    request.setAttribute("message", "修改密码成功!");
                    return mapping.findForward("message");
                }
            }
            return mapping.findForward("error");
        }
        
        request.setAttribute(UrlTools.JUMP, UrlTools.getUrlSite("back"));
        request.setAttribute("message", "输入的密码有误!");
        return mapping.findForward("message");
    }
}
