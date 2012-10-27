package com.jian.web.form.user;

import com.jian.web.form.product.BaseForm;
/**
 * 后台用户管理form
 * @author JOJO
 * @date 2012-9-6
 */
public class BuyerForm extends BaseForm
{
    
    private String username;
    private String email;
    private String realname;
    private String[] usernames;
    private String validateCode;
    private String password;
    private String confirm_password;
    
    
    public String[] getUsernames()
    {
        return usernames;
    }
    public void setUsernames(String[] usernames)
    {
        this.usernames = usernames;
    }
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getRealname()
    {
        return realname;
    }
    public void setRealname(String realname)
    {
        this.realname = realname;
    }
    public String getValidateCode()
    {
        return validateCode;
    }
    public void setValidateCode(String validateCode)
    {
        this.validateCode = validateCode;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getConfirm_password()
    {
        return confirm_password;
    }
    public void setConfirm_password(String confirm_password)
    {
        this.confirm_password = confirm_password;
    }
    
}
