package com.jian.service.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import com.jian.bean.product.Brand;
import com.jian.bean.product.ProductInfo;
import com.jian.service.base.DAO;

@Transactional
public interface ProductInfoService extends DAO
{
    /**
     * 建议使用的product保存方法,能够创建productview的静态页面
     * 
     * @param product   需要保存的产品
     * @param request   当前的request
     */
    public void update(Object entity,HttpServletRequest request);
    
    /**
     * 不建议使用,该方法不会把产品的productview的静态页面缓存起来
     */
    @Deprecated
    public void update(Object entity);
    /**
     * 不建议使用,该方法不会把产品的productview的静态页面缓存起来
     */
    @Deprecated
    public void save(Object entity);
    /**
     * 批量设置产品的 上架或下架
     * @param productids
     * @param status
     */
    public void setVisibleStatus(Object[] productids ,boolean status);
    /**
     * 批量设置产品的 推荐或不推荐
     * @param productids
     * @param status
     */
    public void setCommendStatus(Object[] productids ,boolean status);
    /**
     * 得到type下所有产品所属的品牌
     * @param typeId
     * @return
     */
    public List<Brand> getBrandsByProductTypeid(Object[] typeIds);
    /**
     * 得到type下推荐的,销量最好的产品
     * @param typeIds
     * @param maxresult
     * @return
     */
    public List<ProductInfo> getTopSell(Integer typeId,int maxresult);
    /**
     * 得到10个浏览历史记录
     * @param productids
     * @return
     */
    public List<ProductInfo> getViewHistory(Integer[] productids);
    /**
     * 
     * 建议使用的product保存方法,能够创建productview的静态页面
     * 
     * @param product   需要保存的产品
     * @param request   当前的request
     */
    void save(ProductInfo product, HttpServletRequest request);
}
