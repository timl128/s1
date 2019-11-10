package au.com.skiddoo.shopping.rules;

import au.com.skiddoo.shopping.Price;
import au.com.skiddoo.shopping.Product;
import au.com.skiddoo.shopping.ProductRegister;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static au.com.skiddoo.shopping.ProductRegister.*;
import static org.junit.Assert.*;

public class RuleATest {

    private RuleA rule;
    private Map<String, Product> map = ProductRegister.productMap;
    private List<Product> basket;

    @Before
    public void setUp()  {
        rule = new RuleA();
        basket = new ArrayList<>();
    }

    @Test
    public void testThreeAppleTv() {

        basket.add(map.get(APPLE_TV_SKU));

        Price result = rule.process(basket,map);
        assertTrue(result.getAmount().compareTo(new BigDecimal("209.50")) == 0);

    }

    @Test
    public void testFourAppleTv() {

        for(int i = 0 ; i < 4 ;i++){
            basket.add(map.get(APPLE_TV_SKU));
        }

        Price result = rule.process(basket,map);
        assertTrue(result.getAmount().compareTo(new BigDecimal("209.50").multiply(new BigDecimal(3))) == 0);


    }



    @Test
    public void testOneMacOneHDMI() {

        basket.add(map.get(MAC_BOOK_PRO_SKU));
        basket.add(map.get(HDMI_ADAPTER_SKU));


        Price result = rule.process(basket,map);
        assertTrue(result.getAmount().compareTo(new BigDecimal("1499.99")) == 0);

    }


    @Test
    public void testScenario1() {

        for(int i = 0 ; i < 3 ;i++){
            basket.add(map.get(APPLE_TV_SKU));
        }

        basket.add(map.get(HDMI_ADAPTER_SKU));

        Price result = rule.process(basket,map);
        assertTrue(result.getAmount().compareTo(new BigDecimal("549")) == 0);

    }

    @Test
    public void testScenario2() {


        for(int i = 0 ; i < 2 ;i++){
            basket.add(map.get(APPLE_TV_SKU));
        }

        for(int i = 0 ; i < 5 ;i++){
            basket.add(map.get(IPAD_SKU));
        }


        Price result = rule.process(basket,map);
        assertTrue(result.getAmount().compareTo(new BigDecimal("2918.95")) == 0);

    }

    @Test
    public void testScenario3() {



        basket.add(map.get(MAC_BOOK_PRO_SKU));
        basket.add(map.get(HDMI_ADAPTER_SKU));
        basket.add(map.get(IPAD_SKU));

        Price result = rule.process(basket,map);
        assertTrue(result.getAmount().compareTo(new BigDecimal("2149.98")) == 0);

    }
}