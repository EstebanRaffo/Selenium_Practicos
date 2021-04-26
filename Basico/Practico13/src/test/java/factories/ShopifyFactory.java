package factories;

import org.testng.annotations.Factory;
import tests.ShopifyTest;

public class ShopifyFactory {

    @Factory
    public Object[] shopifyTest(){
        return new Object[]{
                new ShopifyTest(),
                new ShopifyTest(1),
                new ShopifyTest(2),
                new ShopifyTest(3),
                new ShopifyTest(4),
                new ShopifyTest(5)
        };
    }
}
