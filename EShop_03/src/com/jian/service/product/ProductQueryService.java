package com.jian.service.product;

import com.jian.bean.product.ProductInfo;
import com.jian.service.base.QueryResult;
/**
 * 前台产品搜索service
 * @author JOJO
 * @date 2012-9-22
 */
public interface ProductQueryService
{

    /**
     * @param keyword   搜索关键字
     * @param firstIndex    开始的检索的索引
     * @param maxResult     一页最大显示数
     * @return
     */
    public abstract QueryResult<ProductInfo> find(String keyword,
            int firstIndex, int maxResult);

}
