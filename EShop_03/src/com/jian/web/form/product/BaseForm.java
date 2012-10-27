package com.jian.web.form.product;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

/**
 * 基本form
 * 
 * @author JOJO
 * @date 2012-8-7
 */
public class BaseForm extends ActionForm
{
    // 读取能上传的文件格式
    // ★★★★★在struts1.X里面的formbean中引入复杂属性实体对象,一定要在formbean里初始化,不然会报错”No bean
    //          specified”没有bean被指定;struts2.x就不用;★★★★★
    private static Properties properties = new Properties();
    static
    {
        try
        {
            properties.load(BaseForm.class.getClassLoader().getResourceAsStream("allowuploadfiletype.properties"));
        }
        catch (IOException e)
        {

            e.printStackTrace();
        }
    }

    private static final long serialVersionUID = 908713456447743707L;
    private int page = 0;
    // 是否来自查询
    private String query;

    public String getQuery()
    {
        return query;
    }

    public void setQuery(String query)
    {
        this.query = query;
    }

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    /**
     * 验证上传文件格式
     */
    public boolean validateFileType(FormFile formfile)
    {
        // 上传文件的后缀名
        String ext = formfile.getFileName().substring(formfile.getFileName().lastIndexOf('.') + 1).toLowerCase();
        //System.out.println(ext);
        List<String> allowUploadFileTypes = new ArrayList<String>();
        if (properties.keySet().contains(ext))
        {// 有效后缀名
            for (Object key : properties.keySet())
            {
                String value = properties.get(key).toString();
                String[] values = value.split(",");
                for (String s : values)
                {
                    allowUploadFileTypes.add(s);// 把所有合法的文件类型从properties添加到list里面
                }
            }

            return allowUploadFileTypes.contains(formfile.getContentType().toLowerCase());
        }
        return false;
    }

    /**
     * 直接通过传FormFile本身来验证图片格式,较简单
     */
    public boolean validateImageFileType(FormFile formfile)
    {
        if (formfile != null && formfile.getFileSize() > 0)
        {
            List<String> allowType = Arrays.asList("image/bmp", "image/png",
                                                   "image/gif", "image/jpeg",
                                                   "image/tiff",
                                                   "image/x-icon",
                                                   "image/pjpeg");
            System.out.println(formfile.getContentType().toLowerCase()
                    + "--------------"
                    + allowType.contains(formfile.getContentType().toLowerCase()));
            return allowType.contains(formfile.getContentType().toLowerCase());
        }
        return false;
    }

    /**
     * 通过对FormFile名字的反射,验证上传图片文件是否为图片格式,较麻烦
     */
    public boolean validateImageFileType(String propertyName) throws Exception
    {
        // 1.通过反射得到FormFile 上传文件
        PropertyDescriptor[] propertyDescs = Introspector.getBeanInfo(this.getClass()).getPropertyDescriptors();
        boolean exsit = false;
        for (PropertyDescriptor propertyDesc : propertyDescs)
        {

            if (propertyDesc.getName().equals(propertyName))
            {
                exsit = true;
                Method method = propertyDesc.getReadMethod();
                if (method != null)
                {
                    FormFile formfile = (FormFile) method.invoke(this);
                    if (formfile != null && formfile.getFileSize() > 0)
                    {
                        List<String> allowType = Arrays.asList("image/bmp",
                                                               "image/png",
                                                               "image/gif",
                                                               "image/jpeg",
                                                               "image/tiff",
                                                               "image/x-icon",
                                                               "image/pjpeg");
                        System.out.println(formfile.getContentType().toLowerCase()
                                + "--------------"
                                + allowType.contains(formfile.getContentType().toLowerCase()));
                        return allowType.contains(formfile.getContentType().toLowerCase());
                    }
                }
                else
                {
                    throw new RuntimeException(propertyName + "属性的getter方法不存在!");
                }

            }
        }
        if (!exsit)
            throw new RuntimeException(propertyName + "属性不存在!");
        return true;
    }
}
