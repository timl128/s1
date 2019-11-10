package au.com.skiddoo.shopping.rules;

import au.com.skiddoo.shopping.Price;
import au.com.skiddoo.shopping.Product;

import java.util.List;
import java.util.Map;

public interface Rules {
    Price process(List<Product> productList, Map<String, Product> catalog);
}
