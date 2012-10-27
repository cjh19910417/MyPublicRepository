package junit.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jian.bean.product.Brand;
import com.jian.bean.product.ProductInfo;
import com.jian.bean.product.ProductStyle;
import com.jian.bean.product.ProductType;
import com.jian.bean.product.Sex;
import com.jian.bean.user.Buyer;
import com.jian.bean.user.Gender;
import com.jian.service.product.ProductInfoService;
import com.jian.service.user.UserService;

public class ProductInfoServiceImplTest
{

    private static ApplicationContext applicationContext;
    private static UserService service;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
        try
        {
            applicationContext = new ClassPathXmlApplicationContext("beans.xml");
            service = (UserService) applicationContext.getBean("userServiceImpl");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    /**
     * 
     */
    @Test
    public void testSave()
    {
        Buyer buyer = new Buyer();
        
        for (int i = 0; i < 19; i++)
        {
            buyer.setUsername("abcdef"+i);
            buyer.setPassword("aaaaaa");
            buyer.setEmail("c.jian417@gmail.com");
            buyer.setRealname("叶发权");
            buyer.setGender(Gender.MAN);
            service.save(buyer);
        }
       
        
    }

}
