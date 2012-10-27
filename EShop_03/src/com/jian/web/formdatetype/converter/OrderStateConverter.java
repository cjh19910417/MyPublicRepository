package com.jian.web.formdatetype.converter;

import org.apache.commons.beanutils.Converter;

import com.jian.bean.book.OrderState;

public class OrderStateConverter implements Converter
{

    @Override
    public Object convert(Class clazz, Object value)
    {
        if(value instanceof String)
        {
            if(!"".equals(value.toString()))
            return OrderState.valueOf(value.toString());
        }
        if(value instanceof OrderState)
        {
            return value.toString();
        }
        return null;
    }

}
