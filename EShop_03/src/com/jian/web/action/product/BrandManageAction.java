package com.jian.web.action.product;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.stereotype.Controller;

import com.jian.bean.product.Brand;
import com.jian.service.product.BrandService;
import com.jian.web.form.product.BrandForm;
import com.jian.web.util.UrlTools;
/**
 * 品牌管理     添加/修改/查询
 * @author JOJO
 * @date 2012-8-7
 */
@Controller("/control/product/brand/manage")
public class BrandManageAction extends DispatchAction
{
    @Resource(name="brandServiceImpl")
    BrandService brandService;
    public ActionForward addUI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        
        return mapping.findForward("addUI");
    }
    /**
     * 添加品牌
     */
    public ActionForward add(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        BrandForm brandForm = (BrandForm) form;
        //验证图片格式,不是有效格式就返回
        if(!brandForm.validateImageFileType("logofile")){
            request.setAttribute("message", "图片格式不对,请选择有效的图片格式!");
            request.setAttribute("urladdress", UrlTools.getUrlSite("back"));
            return mapping.findForward("message");
        }
        Brand brand = new Brand();
        //品牌名称
        String brandName = brandForm.getName();
        brand.setName(brandName);
        //品牌logo
        if(brandForm.getLogofile() != null && brandForm.getLogofile().getFileSize() > 0)
        {   
            //  /images/brand/2012/08/06/UUID.gif
            String logoPath = "";
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd/HH");
            String logoPathDir = "/images/brand/"+ dateFormat.format(new Date());
            //得到在硬盘上的真实路径
            String logoRealPathDir = request.getSession().getServletContext().getRealPath(logoPathDir);
            System.out.println("logoRealPathDir --> "+logoRealPathDir);
            //图片名称,用UUID生成
            String ext = brandForm.getLogofile().getFileName().substring(brandForm.getLogofile().getFileName().lastIndexOf('.'));
            String imageName = UUID.randomUUID().toString()+ext;
            //创建目录
            File logoSaveDir = new File(logoRealPathDir);
            if(!logoSaveDir.exists()) logoSaveDir.mkdirs();
           
            FileOutputStream out = new FileOutputStream(new File(logoRealPathDir, imageName));
            out.write(brandForm.getLogofile().getFileData());
            out.close();
            
            logoPath = logoPathDir+"/"+imageName;
            brand.setLogopath(logoPath);
            System.out.println(logoPath);
            
        }
        
        brandService.save(brand);
        request.setAttribute("message", "品牌添加成功!");
        request.setAttribute("urladdress", UrlTools.getUrlSite("product.brand.list"));
        return mapping.findForward("message");
    }
    public ActionForward editUI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        BrandForm brandForm = (BrandForm) form;
        Brand brand = brandService.find(Brand.class, brandForm.getCode());
        brandForm.setName(brand.getName());
        brandForm.setLogoImagepath(brand.getLogopath());
        
        return mapping.findForward("editUI");
    }
    /**
     * 修改
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        BrandForm brandForm = (BrandForm) form;
        //验证图片格式,不是有效格式就返回
        if(!brandForm.validateImageFileType("logofile")){
            request.setAttribute("message", "图片格式不对,请选择有效的图片格式!");
            request.setAttribute("urladdress", UrlTools.getUrlSite("back"));
            return mapping.findForward("message");
        }
        Brand brand = brandService.find(Brand.class, brandForm.getCode());
        //品牌名称
        String brandName = brandForm.getName();
        brand.setName(brandName);
        //品牌logo
        if(brandForm.getLogofile() != null && brandForm.getLogofile().getFileSize() > 0)
        {   
            //  /images/brand/2012/08/06/UUID.gif
            String logoPath = "";
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd/HH");
            String logoPathDir = "/images/brand/"+ dateFormat.format(new Date());
            //得到在硬盘上的真实路径
            String logoRealPathDir = request.getSession().getServletContext().getRealPath(logoPathDir);
            //图片名称,用UUID生成
            String ext = brandForm.getLogofile().getFileName().substring(brandForm.getLogofile().getFileName().lastIndexOf('.'));
            String imageName = UUID.randomUUID().toString()+ext;
            //创建目录
            File logoSaveDir = new File(logoRealPathDir);
            if(!logoSaveDir.exists()) logoSaveDir.mkdirs();
           
            FileOutputStream out = new FileOutputStream(new File(logoRealPathDir, imageName));
            out.write(brandForm.getLogofile().getFileData());
            out.close();
            
            logoPath = logoPathDir+"/"+imageName;
            brand.setLogopath(logoPath);
            System.out.println(logoPath);
            
        }
        
        brandService.update(brand);
        
        request.setAttribute("message", "修改品牌成功!");
        request.setAttribute("urladdress", UrlTools.getUrlSite("product.brand.list"));
        return mapping.findForward("message");
    }
    /**
     * 查询UI
     */
    public ActionForward queryUI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    { 
        return mapping.findForward("queryUI");
    }
    
}
