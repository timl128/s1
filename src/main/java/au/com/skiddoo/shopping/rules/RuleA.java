package au.com.skiddoo.shopping.rules;

import au.com.skiddoo.shopping.Price;
import au.com.skiddoo.shopping.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static au.com.skiddoo.shopping.ProductRegister.*;

public class RuleA implements Rules {


    private static final long APPLE_TV_DEAL_X = 3;
    private static final long APPLE_TV_DEAL_Y = 2;
    private static final long SUPER_IPAD_DEAL  = 4;
    private static final BigDecimal SUPER_IPAD_PRICE  = new BigDecimal("499.99");

    /**
     * @param productList
     * @return
     */
    @Override
    public Price process(List<Product> productList, Map<String, Product> catalog) {

        Price total = new Price();
        Map<String,Long> countMap = countProduct(productList);

        total.add(new Price(applyAppleTvBuy3For2(countMap,catalog)));
        total.add(new Price(applySuperIPadDeal(countMap,catalog)));
        total.add(new Price(applyAdapter(countMap,catalog)));
        total.add(new Price(remainingItems(countMap,catalog)));

        return total;
    }

    /**
     * apple tv deal
     * @param countMap
     * @param catalog
     * @return
     */
    private BigDecimal applyAppleTvBuy3For2(Map<String,Long> countMap,
                                       Map<String, Product> catalog){

        Long count = countMap.get(APPLE_TV_SKU);
        if(count == null || count < APPLE_TV_DEAL_X)
            return BigDecimal.ZERO;

        Long groups = count / APPLE_TV_DEAL_X;
        Long remaining = count % APPLE_TV_DEAL_X;
        Long toBePaidTv = groups * APPLE_TV_DEAL_Y + remaining;

        countMap.remove(APPLE_TV_SKU);

        return catalog.get(APPLE_TV_SKU).getPrice().getAmount().multiply(new BigDecimal(toBePaidTv));
    }

    /**
     * apply super ipad deal
     * @param countMap
     * @param catalog
     * @return
     */
    private BigDecimal applySuperIPadDeal(Map<String,Long> countMap,
                                          Map<String, Product> catalog){

        Long count = countMap.get(IPAD_SKU);
        if(count == null)
            return BigDecimal.ZERO;

        BigDecimal price = catalog.get(IPAD_SKU).getPrice().getAmount();

        if(count > SUPER_IPAD_DEAL)
            price = SUPER_IPAD_PRICE;

        countMap.remove(IPAD_SKU);

        return price.multiply(new BigDecimal(count));
    }


    /**
     * hdmi adapter deal
     * @param countMap
     * @param catalog
     * @return
     */
    private BigDecimal applyAdapter(Map<String,Long> countMap,
                                    Map<String, Product> catalog){

        Long count = countMap.get(MAC_BOOK_PRO_SKU);
        if(count == null)
            return BigDecimal.ZERO;

        Long countAdapter = countMap.get(HDMI_ADAPTER_SKU);

        if(countAdapter != null){
            if(count >= countAdapter)
                countAdapter = 0L;
            else
                countAdapter -= count;

            countMap.put(HDMI_ADAPTER_SKU,countAdapter);
        }

        countMap.remove(MAC_BOOK_PRO_SKU);
        BigDecimal price = catalog.get(MAC_BOOK_PRO_SKU).getPrice().getAmount();
        return price.multiply(new BigDecimal(count));

    }


    /**
     * calculate remaining  item
     * @param countMap
     * @param catalog
     * @return
     */
    private BigDecimal remainingItems(Map<String,Long> countMap,
                                      Map<String, Product> catalog)  {

        BigDecimal amount = BigDecimal.ZERO;
        for( Map.Entry<String,Long> record :countMap.entrySet()){


            Product product = catalog.getOrDefault(record.getKey(),null);

            if(product != null){
                amount = product.getPrice().getAmount().multiply(new BigDecimal(record.getValue()));
            }
        }
        countMap.clear();
        return  amount;
    }


    /**
     * count product by type
     * @param productList
     * @return
     */
    private Map<String,Long> countProduct(List<Product> productList){

        Map<String,Long> productCount =
                productList.stream().collect(Collectors.groupingBy(Product::getSku,
                        Collectors.counting()));
        return productCount;
    }
}
