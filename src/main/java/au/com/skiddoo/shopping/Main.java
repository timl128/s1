package au.com.skiddoo.shopping;

import au.com.skiddoo.shopping.rules.RuleA;


import static au.com.skiddoo.shopping.ProductRegister.APPLE_TV_SKU;

public class Main{



    private static final String TOTAL_EXPECTED = "Total: %s";

    public static void main(String arg[]){

        CheckoutRegister co = new CheckoutRegisterMachine(new RuleA(),ProductRegister.productMap);
        co.read(APPLE_TV_SKU);
        Price total = co.total();


        System.out.println(String.format(TOTAL_EXPECTED,total));
    }

}