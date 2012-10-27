package com.jian.web.formdatetype.converter;

import org.apache.commons.beanutils.Converter;

import com.jian.bean.privilege.SystemPrivilegePK;
/**
 * SystemPrivilegePK类型转换器
 * @author JOJO
 * @date 2012-9-18
 */
public class SystemPrivilegePKConverter implements Converter
{

    @Override
    public Object convert(Class clazz, Object value)
    {
        if(value instanceof SystemPrivilegePK)
        {
            if(clazz.equals(String.class))
            {
                SystemPrivilegePK key = (SystemPrivilegePK) value;
                return key.getModule()+","+key.getPrivilege();
            }else
            {
                return value;
            }
        }
        if(value instanceof String)
        {
            try
            {
                String key = value.toString();
                String keys[] = key.split(",");
                SystemPrivilegePK sp = new SystemPrivilegePK(keys[0], keys[1]);
                return sp;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        
        return null;
    }

}
