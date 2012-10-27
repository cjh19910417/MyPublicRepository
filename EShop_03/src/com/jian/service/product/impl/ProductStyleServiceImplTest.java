package com.jian.service.product.impl;


import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jian.bean.product.ProductInfo;
import com.jian.service.base.QueryResult;
import com.jian.service.product.ProductQueryService;

public class ProductStyleServiceImplTest
{
    private static ProductQueryService productQueryService;
    private static ApplicationContext applicationContext;
    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
        applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        productQueryService = (ProductQueryService) applicationContext.getBean("productQueryServiceImpl");
    }

    @Test
    public void test()
    {
        QueryResult<ProductInfo> qr = productQueryService.find("四核", 0, 12);
        for(ProductInfo o :qr.getResultlist())
        {
            System.out.println(o.getName());
        }
        System.out.println(qr.getTotalrecord());
    }

}
