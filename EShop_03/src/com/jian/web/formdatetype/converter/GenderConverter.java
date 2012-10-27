package com.jian.web.formdatetype.converter;

import org.apache.commons.beanutils.Converter;

import com.jian.bean.user.Gender;
/**
 * Gender 的类型转换器
 * @author JOJO
 * @date 2012-9-7
 */
public class GenderConverter implements Converter
{

    @Override
    public Object convert(Class clazz, Object value)
    {
        if(value instanceof Gender) return value;
        try{
            return Gender.valueOf(value.toString());
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }

}
