// Copyright 2015 Skiddoo Pty Ltd

package au.com.skiddoo.shopping;

import au.com.skiddoo.shopping.rules.RuleA;
import org.junit.Test;

import java.math.BigDecimal;

import static au.com.skiddoo.shopping.ProductRegister.APPLE_TV_SKU;
import static org.junit.Assert.assertTrue;

/**
 * @author jaiew
 */
public class CheckoutRegisterTests {

    @Test
    public void test() {
       CheckoutRegister co = new CheckoutRegisterMachine(new RuleA(),ProductRegister.productMap);
       co.read(APPLE_TV_SKU);

       Price total = co.total();
       assertTrue(total.getAmount().compareTo(new BigDecimal("209.50")) == 0);
    }

}
