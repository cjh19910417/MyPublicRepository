package com.jian.service.product.impl;

import java.util.LinkedList;
import java.util.List;

import org.compass.core.CompassCallback;
import org.compass.core.CompassException;
import org.compass.core.CompassHits;
import org.compass.core.CompassSession;

import com.jian.bean.product.ProductInfo;
import com.jian.service.base.QueryResult;
/**
 * 分页检索
 * @author JOJO
 * @date 2012-9-22
 */
public class QueryCallback implements CompassCallback<QueryResult<ProductInfo>>
{
    private String keyword;
    private int firstIndex;
    private int maxResult;
    public QueryCallback(String keyword, int firstIndex, int maxResult)
    {
        this.keyword = keyword;
        this.firstIndex = firstIndex;
        this.maxResult = maxResult;
    }

    @Override
    public QueryResult<ProductInfo> doInCompass(CompassSession session) throws CompassException
    {
        CompassHits hits = session.find(keyword);
        QueryResult<ProductInfo> qr = new QueryResult<ProductInfo>();
        qr.setTotalrecord(hits.length());//设置命中的总数
        int lastIndex = firstIndex + maxResult;
        if(lastIndex > hits.length()) lastIndex = hits.length();
        List<ProductInfo> results = new LinkedList<ProductInfo>();
        for(int i=firstIndex ; i<lastIndex ; i++)
        {
            ProductInfo productInfo = (ProductInfo) hits.data(i);//真正的得到检索出来的product
            
            /*设置高亮部分*/
            if(hits.highlighter(i).fragment("productName")!=null)
                productInfo.setName(hits.highlighter(i).fragment("productName"));
            if(hits.highlighter(i).fragment("simpleDescription")!=null)
                productInfo.setSimpleDescription(hits.highlighter(i).fragment("simpleDescription"));
            //添加到结果集
            results.add(productInfo);
        }
        
        qr.setResultlist(results);
        
        return qr;
    }
    
}
