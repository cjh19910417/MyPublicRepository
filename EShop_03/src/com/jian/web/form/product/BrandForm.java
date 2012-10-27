package com.jian.web.form.product;


import org.apache.struts.upload.FormFile;


public class BrandForm extends BaseForm
{
    
    
    private static final long serialVersionUID = 3073361357175136737L;
    private String name;
    private FormFile logofile;
    private String code;
    private String logoImagepath;
    
    public String getLogoImagepath()
    {
        return logoImagepath;
    }
    public void setLogoImagepath(String imagepath)
    {
        this.logoImagepath = imagepath;
    }
    
    public String getCode()
    {
        return code;
    }
    public void setCode(String code)
    {
        this.code = code;
    }
    
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public FormFile getLogofile()
    {
        return logofile;
    }
    public void setLogofile(FormFile logofile)
    {
        this.logofile = logofile;
    }
   
    
}
