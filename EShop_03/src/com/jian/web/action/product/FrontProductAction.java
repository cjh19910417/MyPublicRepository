package com.jian.web.action.product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import com.jian.bean.product.Brand;
import com.jian.bean.product.ProductInfo;
import com.jian.bean.product.ProductStyle;
import com.jian.bean.product.ProductType;
import com.jian.bean.product.Sex;
import com.jian.service.base.QueryResult;
import com.jian.service.product.ProductInfoService;
import com.jian.service.product.ProductTypeService;
import com.jian.web.bean.PageViewData;
import com.jian.web.form.product.FrontProductForm;

@Controller("/product/list/display")
public class FrontProductAction extends Action
{
    @Resource(name = "productInfoServiceImpl")
    ProductInfoService productInfoService;
    @Resource(name = "productTypeServiceImpl")
    ProductTypeService productTypeService;
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        FrontProductForm formbean = (FrontProductForm) form;
        // 最大一页显示多少条记录
        int maxResult = 3;
        // 得到当前要去的页码
        int currentPage = formbean.getPage();
        // 设置条目开始索引
        int startIndex = currentPage == 0 ? 0 : (currentPage - 1) * maxResult;
        // 设置排序方式
        LinkedHashMap<String, String> orderby = bulidSort(formbean.getSort());
        
        QueryResult<ProductInfo> qr = null;
        //查询语句
        StringBuffer jpql = new StringBuffer();
        jpql.append("o.visible = ?1");
        //查询条件
        List<Object> params = new ArrayList<Object>();
        params.add(true);
        
        //品牌过滤
        if(formbean.getBrandid()!= null && !"".equals(formbean.getBrandid()))
        {
            jpql.append(" and o.brand = ?" + (params.size()+1));
            Brand brand = productInfoService.find(Brand.class, formbean.getBrandid());
            params.add(brand);
        }
        //性别过滤
        if(formbean.getSex()!= null && !"".equals(formbean.getSex()))
        {
            if("MAN".equals(formbean.getSex()))
            {
                jpql.append(" and o.sexrequest = ?" + (params.size()+1));
                params.add(Sex.MAN);
            }else if("WOMAN".equals(formbean.getSex()))
            {
                jpql.append(" and o.sexrequest = ?" + (params.size()+1));
                params.add(Sex.WOMAN);
            }else 
            {
                jpql.append(" and o.sexrequest = ?" + (params.size()+1));
                params.add(Sex.NONE);
            }
        }
        /*
         * 大类别下所有产品
         */
        List<Integer> typeids = new ArrayList<Integer>();
        if(formbean.getTypeId() != null && formbean.getTypeId()>0)
        {
           
            typeids = getTypeids(typeids, formbean.getTypeId());
            StringBuffer param = new StringBuffer();
            for (int i = 0; i < typeids.size(); i++)
            {
                param.append("?").append(params.size()+(i+1)).append(",");
            }
            param.deleteCharAt(param.lastIndexOf(","));
            jpql.append(" and o.type.typeId in ("+param+")");
            for(Integer typeid : typeids)
            {
                params.add(typeid);
            }
        }
        qr = productInfoService.getScrollData(ProductInfo.class, startIndex, maxResult, jpql.toString(),params.toArray(),orderby);
        
        /*处理一下样式图,只显示上架的产品图*/
        for(ProductInfo product : qr.getResultlist())
        {   
            for(ProductStyle productStyle :product.getProductStyles())
            {
                if(productStyle.isVisible())
                {
                    Set<ProductStyle> styles = new HashSet<ProductStyle>();
                    styles.add(productStyle);
                    product.setProductStyles(styles);
                    break;
                }
            }
            //设置图片版显示时候用的简单描述,要先对discription去一下html代码
           // product.setSimpleDescription(WebUtil.HtmltoText(product.getDescription()));
        }
        PageViewData<ProductInfo> pageViewData = new PageViewData<ProductInfo>(qr,
                currentPage, maxResult);
        
        /**
         * 得到产品轨迹导航
         */
        List<ProductType> typeTrace;
        typeTrace = getTypeTrace(formbean.getTypeId());
        /*for(int i = 0;i<typeTrace.size();i++)
        {
            System.out.print(typeTrace.get(i).getName()+">>");
        }*/
        /**
         * 得到产品品牌
         */
        request.setAttribute("brands",productInfoService.getBrandsByProductTypeid(typeids.toArray()));
        request.setAttribute("pageViewData", pageViewData);
        request.setAttribute("typeTrace", typeTrace);
        request.setAttribute("producttype", typeTrace.get(0));
        return mapping.findForward(getViewStyle(formbean.getStyle()));
    }
    /**
     * 得到用什么形式来显示   图片版?    图文版?
     * @param style
     * @return
     */
    private String getViewStyle(String style)
    {
        if("imagetext".equalsIgnoreCase(style)) return style;
        else
        return "image";
    }

    /**
     * 得到产品轨迹导航
     */
    private List<ProductType> getTypeTrace(Integer typeId)
    {
        ProductType type = productTypeService.find(ProductType.class, typeId);
        List<ProductType> typeTrace = new ArrayList<ProductType>();
        typeTrace.add(type);
        while(type.getParent()!=null)
        {
            typeTrace.add( type.getParent());
            type = type.getParent();
        }
        return typeTrace;
    }
    /**
     * 递归得到类别下所有typeId
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

    /**
     * 生成排序map
     */
    private LinkedHashMap<String, String> bulidSort(String orderfield)
    {

        LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
        if("sellpricedesc".equals(orderfield))
        {   //销售价降序
            orderby.put("sellprice", "desc");
        }else if("sellpriceasc".equals(orderfield))
        {   //销售价升序
            orderby.put("sellprice", "asc");
        }else if("selldesc".equals(orderfield))
        {   //销售量降序
            orderby.put("sellcount", "desc");
        }else
        {   //上架时间升序
            orderby.put("createdate", "asc");
        }
        return orderby;
    }
}
