package junit.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jian.bean.product.Brand;
import com.jian.bean.product.ProductInfo;
import com.jian.bean.product.ProductStyle;
import com.jian.bean.shopping.BuyItem;
import com.jian.service.product.BrandService;

public class BrandServiceImplTest
{
    
    @Test
    public void simpleTest(){
        List<BuyItem> items = new ArrayList<BuyItem>();
        ProductInfo pruduct = new ProductInfo();
        pruduct.setId(1);
        ProductStyle productStyle = new ProductStyle();
        productStyle.setId(9);
        ProductStyle productStyle2 = new ProductStyle();
        productStyle2.setId(9);
        BuyItem buyItem = new BuyItem(pruduct,productStyle , 2);
        items.add(buyItem);
        BuyItem buyItem2 = new BuyItem(pruduct, productStyle2, 3);
        
        System.out.println(items.contains(buyItem2));
    }
    

}
