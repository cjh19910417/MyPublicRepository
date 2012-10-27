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

import com.jian.bean.product.Brand;
import com.jian.service.base.QueryResult;
import com.jian.service.product.BrandService;
import com.jian.web.bean.PageViewData;
import com.jian.web.form.product.BrandForm;
/**
 * 品牌列表分页显示
 * @author JOJO
 * @date 2012-8-7
 */
@Controller("/control/product/brand/list")
public class BrandAction extends Action
{
    @Resource(name = "brandServiceImpl")
    BrandService brandService;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        BrandForm brandForm = (BrandForm) form;
        // 最大一页显示多少条记录
        int maxResult = 12;
        // 得到当前要去的页码
        int currentPage = brandForm.getPage();
        // 设置条目开始索引
        int startIndex = currentPage == 0 ? 0 : (currentPage - 1) * maxResult;
        // 设置排序方式
        LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
        orderby.put("name", "asc");
        QueryResult<Brand> qr = null;
        StringBuffer jpql = new StringBuffer();
        jpql.append(" o.visible = ?1 ");
        List<Object> queryParams = new ArrayList<Object>();
        queryParams.add(true);
        // 来自查询页面
        if (brandForm.getQuery() != null && "true".equals(brandForm.getQuery()))
        {
            jpql.append(" and o.name like ?" + (queryParams.size() + 1));
            queryParams.add("%" + brandForm.getName() + "%");
        }
        System.out.println(jpql + "-------" + brandForm.getName());
        qr = brandService.getScrollData(Brand.class, startIndex, maxResult,
                                        jpql.toString(), queryParams.toArray(),
                                        orderby);

        PageViewData<Brand> pageViewData = new PageViewData<Brand>(qr,
                currentPage, maxResult);

        request.setAttribute("pageViewData", pageViewData);
        System.out.println(pageViewData.getRecords().size());
        return mapping.findForward("list");
    }

}
