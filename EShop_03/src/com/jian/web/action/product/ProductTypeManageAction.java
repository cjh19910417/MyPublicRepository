package com.jian.web.action.product;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.stereotype.Controller;

import com.jian.bean.product.ProductType;
import com.jian.service.product.ProductTypeService;
import com.jian.web.form.product.ProductTypeForm;
import com.jian.web.util.UrlTools;
/**
 * 产品类别管理   添加,修改,查询
 * @author JOJO
 * @date 2012-8-7
 */
@Controller("/control/product/type/manage")
public class ProductTypeManageAction extends DispatchAction
{
    @Resource(name="productTypeServiceImpl")
    ProductTypeService productTypeService;
    /**
     *  添加界面
     */
    public ActionForward addUI(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception
    {
       return  mapping.findForward("addUI");
    }
    /**
     * 添加
     */
    public ActionForward add(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        
        ProductTypeForm productTypeForm = (ProductTypeForm) form;
        ProductType productType = new ProductType(productTypeForm.getName(),productTypeForm.getNote());
        
        if(productTypeForm.getParentid() != null && productTypeForm.getParentid() >0){
            productType.setParent(new ProductType(productTypeForm.getParentid()));
        }
        
        productTypeService.save(productType);
        request.setAttribute("urladdress", UrlTools.getUrlSite("product.type.list"));
        request.setAttribute("message", "添加成功");
        return mapping.findForward("message");
    }
    /**
     * 修改界面
     */
    public ActionForward editUI(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ProductTypeForm productTypeForm = (ProductTypeForm) form;
        ProductType productType = productTypeService.find(ProductType.class, productTypeForm.getTypeId());
        productTypeForm.setName(productType.getName());
        productTypeForm.setNote(productType.getNote());
        
        return mapping.findForward("editUI");
    }
    /**
     * 修改
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ProductTypeForm productTypeForm = (ProductTypeForm) form;
        ProductType productType = productTypeService.find(ProductType.class, productTypeForm.getTypeId());
        productType.setName(productTypeForm.getName());
        productType.setNote(productTypeForm.getNote());
        productTypeService.update(productType);
        request.setAttribute("urladdress", UrlTools.getUrlSite("product.type.list"));
        request.setAttribute("message", "修改成功!");
        return mapping.findForward("message");
    }
    
    /**
     * 查询页面
     */
    public ActionForward queryUI(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        
        
        return mapping.findForward("queryUI");
    }
}
