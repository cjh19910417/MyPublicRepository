package com.jian.service.product.impl;

import javax.annotation.Resource;

import org.compass.core.Compass;
import org.compass.core.CompassTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jian.bean.product.ProductInfo;
import com.jian.service.base.QueryResult;
import com.jian.service.product.ProductQueryService;
/**
 * 前台产品检索业务bean
 * @author JOJO
 * @date 2012-9-22
 */
@Service @Transactional
public class ProductQueryServiceImpl implements ProductQueryService
{
    private CompassTemplate compassTemplate;
    
    @SuppressWarnings("unused")
    @Resource   //注入compass
    private void setCompass(Compass compass)
    {
        this.compassTemplate = new CompassTemplate(compass);
    }
    /* (non-Javadoc)
     * @see com.jian.service.product.impl.ProductQueryService#find(java.lang.String, int, int)
     */
    @Override
    public QueryResult<ProductInfo> find(String keyword,int firstIndex,int maxResult)
    {
        return compassTemplate.execute(new QueryCallback(keyword,firstIndex,maxResult));
    }
}
