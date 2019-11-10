package au.com.skiddoo.shopping;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ProductRegister {

    public static final String APPLE_TV_SKU = "appletv";
    public static final String IPAD_SKU = "ipad";
    public static final String MAC_BOOK_PRO_SKU = "macbookpro";
    public static final String HDMI_ADAPTER_SKU = "hdmiadapter";

    public static Map productMap = new HashMap();

    static{

        Price ipadPrice = new Price( new BigDecimal("649.99"));
        Product ipad = new Product(IPAD_SKU,"Super iPad",ipadPrice);
        productMap.put(IPAD_SKU,ipad);


        Price macBookPrice = new Price( new BigDecimal("1499.99"));
        Product macBookPro = new Product(MAC_BOOK_PRO_SKU,"MacBook Pro",macBookPrice);
        productMap.put(MAC_BOOK_PRO_SKU,macBookPro);


        Price appleTvPrice = new Price( new BigDecimal("209.50"));
        Product appleTv = new Product(APPLE_TV_SKU,"Apple TV",appleTvPrice);
        productMap.put(APPLE_TV_SKU,appleTv);

        Price hdmiPrice = new Price( new BigDecimal("130.00"));
        Product hdmi = new Product(HDMI_ADAPTER_SKU,"HDMI adapter",hdmiPrice);
        productMap.put(HDMI_ADAPTER_SKU,hdmi);
    }



}
