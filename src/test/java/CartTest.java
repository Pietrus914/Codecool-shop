
import com.codecool.shop.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    private Supplier supplier1 = new Supplier("supplierFirst", "desc");
    private Supplier supplier2 = new Supplier("supplierSecond", "desc");

    ProductCategory category = new ProductCategory("tablet", "departament", "descr");

    Product prodA = new Product("productA", 2.00f, "PLN", "description", category, supplier1 );
    Product prodB = new Product("productB", 4.00f, "PLN", "description", category,supplier2 );

    ProductLine testProductLine = new ProductLine(prodA);
    ProductLine testProductLine2 = new ProductLine(prodB);

    @Test
    void given2ProductsLineInCart_whenGetTotalPrice_ReturnSumOfPrices(){
        testProductLine2.setQuantity(2);
        Cart cart = new Cart();
        cart.add(testProductLine);
        cart.add(testProductLine2);

        float expectedTotalPrice = 10f;
        float returnedTotalPrice = cart.getTotalPrice();

        assertEquals(expectedTotalPrice,returnedTotalPrice);

    }

}
