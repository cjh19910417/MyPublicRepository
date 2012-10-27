package com.jian.web.formdatetype.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;
/**
 * 日期类型转换器
 * @author JOJO
 * @date 2012-9-7
 */
public class DateConverter implements Converter
{

    @Override
    public Object convert(Class clazz, Object value)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if(value instanceof String)
        {
            
            try
            {
                Date date = format.parse(value.toString());
                return date;
            }
            catch (ParseException e)
            {
                
                e.printStackTrace();
            }
        }
        if(value instanceof Date)
        {
            return format.format((Date)value);
        }
        return null;
    }

}
