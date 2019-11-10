package au.com.skiddoo.shopping;

import au.com.skiddoo.shopping.rules.Rules;

import java.util.*;

public class CheckoutRegisterMachine implements CheckoutRegister{

    private Map<String,Product> productMap = new HashMap<>();

    public CheckoutRegisterMachine(Rules rules, Map<String,Product> allowedProduct){

        this.rules = rules;
        productMap = allowedProduct;


    }

    // rules should not be changed in the process
    private final Rules rules;
    private List<Product> productList = new LinkedList<>();

    @Override
    public void read(String sku) {
        productList.add(productMap.get(sku));
    }

    @Override
    public Price total() {
        return rules.process(productList,productMap);
    }
}
