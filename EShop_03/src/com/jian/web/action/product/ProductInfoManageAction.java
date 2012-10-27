package com.jian.web.action.product;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
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
import com.jian.bean.product.ProductInfo;
import com.jian.bean.product.ProductStyle;
import com.jian.bean.product.ProductType;
import com.jian.bean.product.Sex;
import com.jian.bean.product.UploadFile;
import com.jian.service.base.QueryResult;
import com.jian.service.product.BrandService;
import com.jian.service.product.ProductInfoService;
import com.jian.service.product.ProductTypeService;
import com.jian.service.product.UploadFileService;
import com.jian.web.form.product.ProductForm;
import com.jian.web.util.ImageSizer;
import com.jian.web.util.UrlTools;

@Controller("/control/product/manage")
public class ProductInfoManageAction extends DispatchAction
{
    @Resource(name = "productInfoServiceImpl")
    ProductInfoService productInfoService;
    @Resource(name = "brandServiceImpl")
    BrandService brandService;
    @Resource(name = "productTypeServiceImpl")
    ProductTypeService productTypeService;
    @Resource UploadFileService uploadFileService;
    /**
     * 添加产品界面
     */
    public ActionForward addUI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        request.setAttribute("brands",
                             brandService.getScrollData(Brand.class).getResultlist());
        return mapping.findForward("addUI");
    }

    /**
     * 选择产品类别
     */
    public ActionForward selectUI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ProductForm productForm = (ProductForm) form;
        List<Object> queryParams = new ArrayList<Object>();
        LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
        orderby.put("typeId", "asc");
        QueryResult<ProductType> qr = null;
        /*
         * 设置选择界面的导航数据
         */
        List<ProductType> menutypes = new ArrayList<ProductType>();
        if (productForm.getTypeid() != null)
        {
            queryParams.add(new ProductType(productForm.getTypeid()));
            qr = productTypeService.getScrollData(ProductType.class, -1, -1,
                                                  " o.parent = ?1 ",
                                                  queryParams.toArray(),
                                                  orderby);
            ProductType type = productTypeService.find(ProductType.class,
                                                       productForm.getTypeid());
            ProductType parent = type.getParent();
            while (parent != null)
            {
                menutypes.add(0, parent);
                parent = parent.getParent();
            }

        }
        else
        {
            qr = productTypeService.getScrollData(ProductType.class, -1, -1,
                                                  " o.parent is null ",
                                                  queryParams.toArray(),
                                                  orderby);
        }
        List<ProductType> types = qr.getResultlist();

        request.setAttribute("menutypes", menutypes);
        request.setAttribute("types", types);
        return mapping.findForward("selectUI");
    }

    /**
     * 添加产品
     */
    public ActionForward add(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ProductForm productForm = (ProductForm) form;
        ProductInfo productInfo = new ProductInfo();
        productInfo.setBaseprice(productForm.getBaseprice());
        if (productForm.getBrandid() != null
                && !"".equals(productForm.getBrandid().trim()))
        {
            productInfo.setBrand(productInfoService.find(Brand.class,
                                                         productForm.getBrandid()));
        }

        productInfo.setBuyexplain(productForm.getBuyexplain());
        productInfo.setCode(productForm.getCode());
        productInfo.setDescription(productForm.getDescription());
        productInfo.setMarketprice(productForm.getMarketprice());
        productInfo.setModel(productForm.getModel());
        productInfo.setName(productForm.getName());
        productInfo.setWeight(productForm.getWeight());
        productInfo.setType(productInfoService.find(ProductType.class,
                                                    productForm.getTypeid()));
        productInfo.setSexrequest(Sex.valueOf(productForm.getSex()));
        productInfo.setSellprice(productForm.getSellprice());
        
        
        /*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~* 保存样式图片START ~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*/
        //[start]
        
        // 验证图片格式,不是有效格式就返回
        if (!productForm.validateImageFileType(productForm.getImagefile()))
        {
            request.setAttribute("message", "图片格式不对,请选择有效的图片格式!");
            request.setAttribute("urladdress", UrlTools.getUrlSite("back"));
            return mapping.findForward("message");
        }
        UploadFile uploadFile = new UploadFile();
        // 图片保存路径格式  --> /images/product/producttypeid/productid/UUID.gif
        String filePath = "";
        if (productForm.getImagefile() != null
                && productForm.getImagefile().getFileSize() > 0)
        {
            /*****************************保存原图START*******************************/
            // 图片名称,用UUID生成
            String ext = productForm.getImagefile().getFileName().substring(productForm.getImagefile().getFileName().lastIndexOf('.'));
            String imageName = UUID.randomUUID().toString() + ext;
            productInfo.addProductStyle(new ProductStyle(productForm.getStylename(), imageName));
            
            //使用重载后的save,可以生成productview的静态页面
            productInfoService.save(productInfo,request);
            
            String filePathDir = "/images/product/" + productForm.getTypeid() + "/" + productInfo.getId()+"/prototype";
            
            // 得到prototype在硬盘上的真实路径
            String fileRealPathDir = request.getSession().getServletContext().getRealPath(filePathDir);
            System.out.println("imagePrototypeRealPathDir --> " + fileRealPathDir);

            // 创建prototype目录
            File imagePrototypeSaveDir = new File(fileRealPathDir);
            if (!imagePrototypeSaveDir.exists())
                imagePrototypeSaveDir.mkdirs();
            
            //写入到prototype目录
            File protoFile = new File(fileRealPathDir, imageName);
            FileOutputStream out = new FileOutputStream(protoFile);
            out.write(productForm.getImagefile().getFileData());
            out.close();
            /*****************************保存原图E*N*D*******************************/
            
            
            /*****************************保存压缩图片start*****************************/
            //得到要保存的路径
            String filePathDir140x = "/images/product/" + productForm.getTypeid() + "/" + productInfo.getId()+"/140x";
            //得到140x在硬盘上的真实路径
            String fileRealPathDir140x = request.getSession().getServletContext().getRealPath(filePathDir140x);
            // 创建140x目录
            File image140xSaveDir = new File(fileRealPathDir140x);
            if(!image140xSaveDir.exists())
            {
                image140xSaveDir.mkdirs();
            }
            //写入到140x目录
            ImageSizer.resize(protoFile, new File(image140xSaveDir,imageName), 140, "jpg");
            
            /*****************************保存压缩图片e n d*****************************/
            
            /*上传文件管理*/
            filePath = filePathDir + "/" + imageName;
            uploadFile.setFilepath(filePath);
            uploadFile.setUploadtime(new Date());
            uploadFileService.save(uploadFile);
        }
        //[end]
        /*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~* 保存样式图片end ~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*/
        request.setAttribute(UrlTools.JUMP, UrlTools.getUrlSite("productlist"));
        request.setAttribute("message", "添加产品成功!!");
        return mapping.findForward("message");
    }

    /**
     * 修改产品界面
     */
    public ActionForward editUI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ProductForm productForm = (ProductForm) form;
        ProductInfo product = productInfoService.find(ProductInfo.class,
                                                      productForm.getProductid());

        productForm.setBaseprice(product.getBaseprice());
        if(product.getBrand()!=null)
        {
            productForm.setBrandid(product.getBrand().getCode());
        }
        
        productForm.setBuyexplain(product.getBuyexplain());
        productForm.setCode(product.getCode());
        productForm.setDescription(product.getDescription());
        productForm.setMarketprice(product.getMarketprice());
        productForm.setModel(product.getModel());
        productForm.setName(product.getName());
        productForm.setSellprice(product.getSellprice());
        productForm.setSex(product.getSexrequest().toString());
        productForm.setTypeid(product.getType().getTypeId());
        productForm.setWeight(product.getWeight());

        request.setAttribute("brands",
                             brandService.getScrollData(Brand.class).getResultlist());
        request.setAttribute("typename", product.getType().getName());
        return mapping.findForward("editUI");
    }

    /**
     * 修改产品
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ProductForm productForm = (ProductForm) form;
        ProductInfo productInfo = productInfoService.find(ProductInfo.class, productForm.getProductid());
        productInfo.setBaseprice(productForm.getBaseprice());
        if (productForm.getBrandid() != null
                && !"".equals(productForm.getBrandid().trim()))
        {
            productInfo.setBrand(productInfoService.find(Brand.class,
                                                         productForm.getBrandid()));
        }

        productInfo.setBuyexplain(productForm.getBuyexplain());
        productInfo.setCode(productForm.getCode());
        productInfo.setDescription(productForm.getDescription());
        productInfo.setMarketprice(productForm.getMarketprice());
        productInfo.setModel(productForm.getModel());
        productInfo.setName(productForm.getName());
        productInfo.setWeight(productForm.getWeight());
        productInfo.setType(productInfoService.find(ProductType.class,
                                                    productForm.getTypeid()));
        productInfo.setSexrequest(Sex.valueOf(productForm.getSex()));
        productInfo.setSellprice(productForm.getSellprice());

        productInfoService.update(productInfo,request);
        request.setAttribute("urladdress", UrlTools.getUrlSite("productlist"));
        request.setAttribute("message", "修改产品成功!!");
        return mapping.findForward("message");
    }
    /**
     * 产品查询界面
     */
    public ActionForward queryUI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        request.setAttribute("brands",
                             brandService.getScrollData(Brand.class).getResultlist());
        return mapping.findForward("queryUI");
    }
    /**
     * 设置产品上架
     */
    public ActionForward visible(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ProductForm productForm = (ProductForm) form;
        Integer[] productids = productForm.getProductids();
        productInfoService.setVisibleStatus(productids, true);
        request.setAttribute("message", "产品上架成功!");
        request.setAttribute("urladdress", UrlTools.getUrlSite("productlist"));
        return mapping.findForward("message");
    }
    /**
     * 设置产品下架
     */
    public ActionForward disable(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ProductForm productForm = (ProductForm) form;
        Integer[] productids = productForm.getProductids();
        productInfoService.setVisibleStatus(productids, false);
        request.setAttribute("message", "产品下架成功!");
        request.setAttribute("urladdress", UrlTools.getUrlSite("productlist"));
        return mapping.findForward("message");
    }/**
     * 设置产品推荐
     */
    public ActionForward commend(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ProductForm productForm = (ProductForm) form;
        Integer[] productids = productForm.getProductids();
        productInfoService.setCommendStatus(productids, true);
        request.setAttribute("message", "产品推荐成功!");
        request.setAttribute("urladdress", UrlTools.getUrlSite("productlist"));
        return mapping.findForward("message");
    }/**
     * 设置产品不推荐
     */
    public ActionForward uncommend(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ProductForm productForm = (ProductForm) form;
        Integer[] productids = productForm.getProductids();
        productInfoService.setCommendStatus(productids, false);
        request.setAttribute("message", "产品不推荐设置成功!");
        request.setAttribute("urladdress", UrlTools.getUrlSite("productlist"));
        return mapping.findForward("message");
    }
  
}
