package com.jian.web.formdatetype.converter;

import org.apache.commons.beanutils.Converter;
import com.jian.bean.shopping.DeliverWay;
/**
 * 配送方式转换器
 * @author JOJO
 * @date 2012-9-8
 */
public class DeliverConverter implements Converter
{

    @Override
    public Object convert(Class clazz, Object value)
    {
        if(value instanceof DeliverWay) return value;
        try{
            return DeliverWay.valueOf(value.toString());
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }

}
