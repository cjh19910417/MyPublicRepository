package com.jian.web.action.product;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import com.jian.bean.product.ProductInfo;
import com.jian.service.base.QueryResult;
import com.jian.service.product.ProductInfoService;
import com.jian.web.bean.PageViewData;
import com.jian.web.form.product.ProductForm;
@Controller("/control/product/list")
public class ProductAction extends Action
{
    @Resource(name = "productInfoServiceImpl")
    ProductInfoService productInfoService;
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        
        ProductForm productForm = (ProductForm) form;
        // 最大一页显示多少条记录
        int maxResult = 12;
        
        // 得到当前要去的页码
        int currentPage = productForm.getPage();
        
        // 设置条目开始索引
        int startIndex = currentPage == 0 ? 0 : (currentPage - 1) * maxResult;
        
        // 设置排序方式
        LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
        
        //根据id降序排序
        orderby.put("id", "desc");
        QueryResult<ProductInfo> qr = null;
        
        //查询语句
        StringBuffer jpql = new StringBuffer();
        jpql.append("o.visible = ?1");
        
        //查询条件
        List<Object> params = new ArrayList<Object>();
        params.add(true);
        
        if("true".equals(productForm.getQuery()))//来自查询页面
        {
            /*产品名称查询*/
            if(productForm.getName() != null && !"".equals(productForm.getName())){
                jpql.append(" and o.name like ?").append(params.size()+1);
                params.add("%"+productForm.getName()+"%");
            }
            /*产品类别查询*/
            if(productForm.getTypeid()!= null && productForm.getTypeid() > 0){
                jpql.append(" and o.type.typeId = ?").append(params.size()+1);
                params.add(productForm.getTypeid());
            }
            /*销售价格区间查询*/
            if(productForm.getStartsellprice() != null && productForm.getStartsellprice() > 0)
            {
                jpql.append(" and o.sellprice >= ?").append(params.size()+1);
                params.add(productForm.getStartsellprice());
            }
            if(productForm.getEndsellprice()!= null && productForm.getEndsellprice() > 0)
            {
                jpql.append(" and o.sellprice <= ?").append(params.size()+1);
                params.add(productForm.getEndsellprice());
            }
            
            /*采购价格区间查询*/
            if(productForm.getStartbaseprice() != null && productForm.getStartbaseprice() > 0)
            {
                jpql.append(" and o.baseprice >= ?").append(params.size()+1);
                params.add(productForm.getStartbaseprice());
            }
            if(productForm.getEndbaseprice()!= null && productForm.getEndbaseprice() > 0)
            {
                jpql.append(" and o.baseprice <= ?").append(params.size()+1);
                params.add(productForm.getEndbaseprice());
            }
            /*根据产品货号查询*/
            if(productForm.getCode()!= null && !"".equals(productForm.getCode())){
                jpql.append(" and o.code = ?").append(params.size()+1);
                params.add(productForm.getCode());
            }
            /*根据产品品牌查询*/
            if(productForm.getBrandid() != null && !"".equals(productForm.getBrandid())){
                jpql.append(" and o.brand.code = ?").append(params.size()+1);
                params.add(productForm.getBrandid());
            }
            qr = productInfoService.getScrollData(ProductInfo.class, startIndex, maxResult, jpql.toString(), params.toArray(), orderby);
        }else{
            //不是来自查询页面
            qr = productInfoService.getScrollData(ProductInfo.class, startIndex, maxResult, orderby);
        }
        

        PageViewData<ProductInfo> pageViewData = new PageViewData<ProductInfo>(qr,
                currentPage, maxResult);

        request.setAttribute("pageViewData", pageViewData);
        System.out.println(pageViewData.getRecords().size());
        return mapping.findForward("list");
    }
    
}
