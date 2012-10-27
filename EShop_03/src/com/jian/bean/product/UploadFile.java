package com.jian.bean.product;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class UploadFile
{
    private int id;
    private String filepath;
    private Date uploadtime;
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    @Column(length=80,nullable=false)
    public String getFilepath()
    {
        return filepath;
    }
    public void setFilepath(String filepath)
    {
        this.filepath = filepath;
    }
    @Temporal(TemporalType.TIMESTAMP)//时间戳
    public Date getUploadtime()
    {
        return uploadtime;
    }
    public void setUploadtime(Date uploadtime)
    {
        this.uploadtime = uploadtime;
    }
    
    
}
