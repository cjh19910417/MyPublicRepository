package junit.test;

import java.util.LinkedHashMap;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jian.bean.product.Brand;
import com.jian.bean.product.ProductType;
import com.jian.service.base.QueryResult;
import com.jian.service.product.ProductTypeService;

/**
 * 
 * @author JOJO
 * @date 2012-8-2
 */
public class ProductTest
{
    private static ApplicationContext applicationContext;
    private static ProductTypeService productService;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
        try
        {
            applicationContext = new ClassPathXmlApplicationContext("beans.xml");
            productService = (ProductTypeService) applicationContext.getBean("productTypeServiceImpl");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void testSave()
    {
       /* for (int i = 0; i < 15; i++)
        {
            ProductType productType1 = new ProductType();
            productType1.setName("iphone" + i);
            productType1.setNote("好产品");
            productType1.setParent(new ProductType(3));
            productService.save(productType1);
        }*/
        System.out.println(productService.getCount(Brand.class));
    }

    @Test
    public void testUpdate()
    {
        ProductType productType1 = productService.find(ProductType.class, 2);
        productType1.setName("PC产品");
        productService.update(productType1);
    }

    @Test
    public void testFind()
    {
        System.out.println(productService.find(ProductType.class, 2).getName());
    }

    @Test
    public void testdelete()
    {
        productService.delete(ProductType.class, 2);
    }

    @Test
    public void testScrollData()
    {

        String whereJpql = "o.visible = ?1";
        Object[] queryParams = new Object[] { true };
        LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
        orderby.put("typeId", "desc");
        QueryResult<ProductType> qr = productService.getScrollData(ProductType.class,
                                                                   0, 5,
                                                                   whereJpql,
                                                                   queryParams,
                                                                   orderby);
        for (ProductType p : qr.getResultlist())
        {
            System.out.println(p.getName());
        }

        System.out.println("==================================");

        qr = productService.getScrollData(ProductType.class);
        for (ProductType p : qr.getResultlist())
        {
            System.out.println(p.getName());
        }
    }
    @Test
    public void test1()
    {
        String ext = "";
        String originalName = "sadfasdf.exe";
        ext = originalName.substring(originalName.lastIndexOf('.'));
        System.out.println(ext);
    }
}
