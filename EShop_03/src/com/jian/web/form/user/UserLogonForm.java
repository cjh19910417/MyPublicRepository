package com.jian.web.form.user;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.RequestUtils;

import com.jian.web.form.product.BaseForm;
import com.jian.web.util.WebUtil;
/**
 * 用户登录form
 * @author JOJO
 * @date 2012-9-6
 */
public class UserLogonForm extends BaseForm
{
    //帐号
    private String username;
    //密码
    private String password;
    //登录成功后自动跳回的页面
    private String directUrl;
    //是否自动登录
    private String isAutoLogon;
    
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
    @Override
    public ActionErrors validate(ActionMapping mapping,
            HttpServletRequest request)
    {
        ActionErrors errors = new ActionErrors();
        
        MessageResources resources = (MessageResources) request.getAttribute(ActionErrors.GLOBAL_MESSAGE);
        Locale locale = RequestUtils.getUserLocale(request, null);
        if(this.getUsername()==null || "".equals(getUsername()))
        {   //用户名不能为空
            String value = resources.getMessage(locale,"username");
            errors.add(username, new ActionMessage("error.required", value));
        }
        if(this.getPassword()==null || "".equals(getPassword()))
        {
            //密码不能为空!
            String value = resources.getMessage(locale, "password");
            errors.add("password", new ActionMessage("error.required", value));
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
    public String getIsAutoLogon()
    {
        return isAutoLogon;
    }
    public void setIsAutoLogon(String isAutoLogon)
    {
        this.isAutoLogon = isAutoLogon;
    }
     
    
}
