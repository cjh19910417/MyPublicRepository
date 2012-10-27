package com.jian.web.form.product;

public class ProductTypeForm extends BaseForm
{

    private static final long serialVersionUID = 2764763957542340319L;
    private Integer parentid;
    private String name;
    private String note;
    private Integer typeId;
   
    
    public Integer getTypeId()
    {
        return typeId;
    }

    public void setTypeId(Integer typeId)
    {
        this.typeId = typeId;
    }
    
    public Integer getParentid()
    {
        return parentid;
    }

    public void setParentid(Integer parentid)
    {
        this.parentid = parentid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

}