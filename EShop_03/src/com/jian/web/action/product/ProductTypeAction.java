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

import com.jian.bean.product.ProductType;
import com.jian.service.base.QueryResult;
import com.jian.service.product.ProductTypeService;
import com.jian.web.bean.PageViewData;
import com.jian.web.form.product.ProductTypeForm;
/**
 * 产品类别列表分页显示
 * @author JOJO
 * @date 2012-8-7
 */
@Controller("/control/product/type/list")
public class ProductTypeAction extends Action
{
   
    @Resource(name = "productTypeServiceImpl")// spring注入
    ProductTypeService productTypeService;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception
    {   
        ProductTypeForm productTypeForm = (ProductTypeForm) form;
        //最大一页显示多少条记录
        int maxResult = 15;
        //得到当前要去的页码
        int currentPage = productTypeForm.getPage();
        //设置条目开始索引
        int startIndex = currentPage == 0? 0 :(currentPage-1)* maxResult;
        //设置排序方式
        LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
        orderby.put("typeId", "asc");
        QueryResult<ProductType> qr = null;
        StringBuffer jpql = new StringBuffer();
        jpql.append(" o.visible = ?1 ");
        List<Object> queryParams = new ArrayList<Object>();
        queryParams.add(true);
        
        //不是来自查询页面
        if(!"true".equals(productTypeForm.getQuery())){
          //子页面显示
            if(productTypeForm.getParentid() != null && productTypeForm.getParentid() >0){
                jpql.append(" and o.parent = ?" +(queryParams.size()+1));
                queryParams.add(new ProductType(productTypeForm.getParentid()));
            }else{
                jpql.append(" and o.parent is null ");
            }
        }else{
            //来自查询页面,搜索
            jpql.append(" and o.name like ?" +(queryParams.size()+1));
            queryParams.add("%"+productTypeForm.getName()+"%");
        }
        
        System.out.println(jpql);
        qr= productTypeService.getScrollData(ProductType.class
                                             , startIndex
                                             , maxResult
                                             , jpql.toString()
                                             , queryParams.toArray()
                                             , orderby );
        //创建页面分页显示数据
        PageViewData<ProductType> pageViewData = new PageViewData<ProductType>(qr, currentPage,maxResult);
        
        request.setAttribute("pageViewData", pageViewData);
        if( qr.getResultlist().size() != 0 && qr.getResultlist().get(0).getParent() != null ){
            Object parentid = qr.getResultlist().get(0).getParent().getTypeId();
            request.setAttribute("parentid", parentid );
        }
         
        
        return mapping.findForward("list");
    }
}
