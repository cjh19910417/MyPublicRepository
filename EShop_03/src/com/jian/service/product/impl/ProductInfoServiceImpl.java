package com.jian.service.product.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jian.bean.product.Brand;
import com.jian.bean.product.ProductInfo;
import com.jian.bean.product.ProductType;
import com.jian.service.base.DAOSupport;
import com.jian.service.product.ProductInfoService;
import com.jian.service.product.ProductTypeService;

@Service
@Transactional
public class ProductInfoServiceImpl extends DAOSupport implements
        ProductInfoService
{
    @Resource(name = "productTypeServiceImpl")
    ProductTypeService productTypeService;
    
    
    /**
     * 
     * 建议使用的product保存方法
     * 
     * @param product   需要保存的产品
     * @param request   当前的request
     */
    @Override
    public void save(ProductInfo product,HttpServletRequest request)
    {
        super.save(product);
        
     /* ******生成静态html代码一定能够要在save()下面,这样product才会有id****** */
        
        //保存路径为-->  /static/product/产品类别id
        File saveDir = new File(request.getSession().getServletContext().getRealPath("/static/product/"+product.getType().getTypeId()));
        //使用velocity技术生成静态页面
        BuildHtmlFile.createProductHtml(product, saveDir);
    }
    
    @Override
    @Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
    public List<ProductInfo> getTopSell(Integer typeId,int maxresult)
    {
        List<Integer> results = new ArrayList<Integer>();
        Object[] typeIds = getTypeids(results, typeId).toArray();
        StringBuffer param = new StringBuffer();
        for (int i = 0; i < typeIds.length; i++)
        {
            param.append("?").append(i).append(",");
        }
        param.deleteCharAt(param.length()-1);
        // 得到type下所有产品所属的品牌
        Query query = em.createQuery("select o from ProductInfo o where o.type.typeId in("+param+") and o.commend = true order by o.sellcount desc");
        
        for (int i = 0; i < typeIds.length; i++)
        {
            query.setParameter(i, typeIds[i]);
        }
        query.setFirstResult(0).setMaxResults(maxresult);
        return query.getResultList();
    }
    
    /**
     * 得到类别下所有typeId
     */
    private List<Integer> getTypeids(List<Integer> results , Integer typeid)
    {
        results.add(typeid);
        /*得到本层类别下的子类别*/
        List<ProductType> productTypes = productTypeService.getChildTypes(typeid);
        for(ProductType p : productTypes)
        {
            if(p.getChildtype() != null && p.getChildtype().size()>0)
            {
                results = getTypeids(results, p.getTypeId());
            }else{
                results.add(p.getTypeId());
            }
            
        }
        
        return results;
    }
    
    @Override
    public void setVisibleStatus(Object[] productids, boolean status)
    {
        StringBuffer jpql = new StringBuffer();
        for (int i = 0; i < productids.length; i++)
        {
            jpql.append("?" + (i + 2)).append(",");
        }
        jpql.deleteCharAt(jpql.length() - 1);
        Query query = em.createQuery("update ProductInfo o set o.visible = ?1 where o.id in("
                + jpql + ")");
        query.setParameter(1, status);
        for (int i = 0; i < productids.length; i++)
        {
            query.setParameter(i + 2, productids[i]);
        }
        query.executeUpdate();
    }

    @Override
    public void setCommendStatus(Object[] productids, boolean status)
    {
        StringBuffer jpql = new StringBuffer();
        for (int i = 0; i < productids.length; i++)
        {
            jpql.append("?" + (i + 2)).append(",");
        }
        jpql.deleteCharAt(jpql.length() - 1);
        Query query = em.createQuery("update ProductInfo o set o.commend = ?1 where o.id in("
                + jpql + ")");
        query.setParameter(1, status);
        for (int i = 0; i < productids.length; i++)
        {
            query.setParameter(i + 2, productids[i]);
        }
        query.executeUpdate();
    }

    
    @Override
    @Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
    public List<Brand> getBrandsByProductTypeid(Object[] typeIds)
    {
        StringBuffer param = new StringBuffer();
        for (int i = 0; i < typeIds.length; i++)
        {
            param.append("?").append(i).append(",");
        }
        param.deleteCharAt(param.length()-1);
        // 得到type下所有产品所属的品牌
        Query query = em.createQuery("select o from Brand o where o.code in(select p.brand.code from ProductInfo p where p.type.typeId in ("+param+") group by p.brand.code)");
        
        for (int i = 0; i < typeIds.length; i++)
        {
            query.setParameter(i, typeIds[i]);
        }

        return query.getResultList();

    }
    @Override
    @Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
    public List<ProductInfo> getViewHistory(Integer[] productids)
    {
        StringBuffer param = new StringBuffer();
        for (int i = 0; i < productids.length; i++)
        {
            param.append("?").append(i).append(",");
        }
        param.deleteCharAt(param.length()-1);
        Query query = em.createQuery("select o from ProductInfo o where o.id in ("+ param +")");
        for (int i = 0; i < productids.length; i++)
        {
            query.setParameter(i, productids[i]);
        }
        return query.getResultList();
    }
    
    @Override
    public void update(Object entity, HttpServletRequest request)
    {
        ProductInfo product = (ProductInfo) entity;
        
        //保存路径为-->  /static/product/产品类别id
        File saveDir = new File(request.getSession().getServletContext().getRealPath("/static/product/"+product.getType().getTypeId()));
        //使用velocity技术生成静态页面
        BuildHtmlFile.createProductHtml(product, saveDir);
        
        super.update(product);
    }

}
