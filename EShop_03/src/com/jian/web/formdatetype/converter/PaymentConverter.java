package com.jian.web.formdatetype.converter;

import org.apache.commons.beanutils.Converter;

import com.jian.bean.shopping.PaymentWay;
/**
 * 支付方式类型转换器
 * @author JOJO
 * @date 2012-9-8
 */
public class PaymentConverter implements Converter
{

    @Override
    public Object convert(Class clazz, Object value)
    {
        if(value instanceof PaymentWay) return value;
        try{
            return PaymentWay.valueOf(value.toString());
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }

}
