package com.jian.web.form.product;

import org.apache.struts.upload.FormFile;

public class UploadFileForm extends BaseForm
{

    private static final long serialVersionUID = 5672988161983025500L;
    /**
     * 要上传的文件
     */
    private FormFile uploadfile;
    /**
     * 要删除的id
     */
    private Integer[] fileids;

    public Integer[] getFileids()
    {
        return fileids;
    }

    public void setFileids(Integer[] fileids)
    {
        this.fileids = fileids;
    }

    public FormFile getUploadfile()
    {
        return uploadfile;
    }

    public void setUploadfile(FormFile uploadfile)
    {
        this.uploadfile = uploadfile;
    }

}
