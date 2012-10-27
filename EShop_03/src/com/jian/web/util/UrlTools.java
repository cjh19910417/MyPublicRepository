package com.jian.web.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UrlTools
{
    public static String JUMP= "urladdress";
    public static String urlSite = "";
    static Properties properties = null;
    static{
        InputStream in = UrlTools.class.getClassLoader().getResourceAsStream("urlsite.properties");
        properties = new Properties();
        try
        {
            properties.load(in);
        }
        catch (IOException e)
        {
            
            e.printStackTrace();
        }
    }
    public static String getUrlSite(String key)
    {
        return urlSite = properties.getProperty(key);
    }
    
    
}
