package com.jian.web.form.user;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.RequestUtils;

import com.jian.web.form.product.BaseForm;
import com.jian.web.util.WebUtil;
/**
 * 用户注册表单,表单校验
 * @author JOJO
 * @date 2012-9-6
 */
public class UserForm extends BaseForm
{
    private String username;
    private String password;
    private String password2;
    private String email;
    private String directUrl;
    
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getPassword2()
    {
        return password2;
    }
    public void setPassword2(String password2)
    {
        this.password2 = password2;
    }
    @Override
    public ActionErrors validate(ActionMapping mapping,
            HttpServletRequest request)
    {
        ActionErrors errors = new ActionErrors();
        //得到MessageResources对象
        MessageResources resources = (MessageResources) request.getAttribute(Globals.MESSAGES_KEY);
        //得到用户请求的locale
        Locale locale = RequestUtils.getUserLocale(request, null);
        //用户名为空
        if(getUsername()==null || "".equals(getUsername().trim())){
            String value = resources.getMessage(locale, "username");//得到国际化参数
            errors.add("username", new ActionMessage("error.required", value));
            
        }
        
        //密码为空
        if(getPassword() == null || "".equals(getPassword().trim())){
            String value = resources.getMessage(locale, "password");//得到国际化参数
            errors.add("password", new ActionMessage("error.required",value));
        }
        
        //确认密码为空
        if(this.getPassword2() == null || "".equals(this.getPassword2().trim())){
            String value = resources.getMessage(locale, "password2");//得到国际化参数
            errors.add("password2", new ActionMessage("error.required",value));
        }
        //电子邮箱为空
        if(this.getEmail() == null || "".equals(this.getEmail().trim())){
            errors.add("email", new ActionMessage("电子邮箱不能为空",false));
        }    
        
        //如果两个密码不相同
        if(getPassword() != null && !getPassword().equals(getPassword2())){
            errors.add("password2", new ActionMessage("error.password2.different"));
        }
        
        return errors;
    }
    public String getDirectUrl()
    {
        return directUrl;
    }
    public void setDirectUrl(String directUrl)
    {
        this.directUrl = WebUtil.Base64decode(directUrl);
    }  
}
