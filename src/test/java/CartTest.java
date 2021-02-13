
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

    @Test
    void givenProductName_whenDecrease_ReturnedDecreasedQuantityInProperProductLine(){
        testProductLine2.setQuantity(2);
        Cart cart = new Cart();
        cart.add(testProductLine);
        cart.add(testProductLine2);

        int expectedQuantityOfProductLine2 = 1;

        cart.decreaseQuantity("productB");
        int returnedQuantity = cart.getProductLines().get(testProductLine2.getProduct().getName()).getQuantity();

        assertEquals(expectedQuantityOfProductLine2, returnedQuantity);

        cart.decreaseQuantity("productB");
        int expectedCartSize = 1;
        int returnedCartSize = cart.getSize();

        assertEquals(expectedCartSize,returnedCartSize);

    }


    @Test
    void givenProductName_whenRemove_ReturnedCartSizeDecreasedBy1(){
        testProductLine2.setQuantity(2);
        Cart cart = new Cart();
        cart.add(testProductLine);
        cart.add(testProductLine2);

        int expectedSize = 1;

        cart.remove("productB");
        int returnedSize = cart.getSize();

        assertEquals(expectedSize, returnedSize);

        cart.remove("productA");
        expectedSize = 0;
        returnedSize = cart.getSize();

        assertEquals(expectedSize,returnedSize);

    }

    @Test
    void givenProductName_whenIncrease_ReturnedCartSizeIncreasedBy1(){
        testProductLine2.setQuantity(2);
        Cart cart = new Cart();
        cart.add(testProductLine);
        cart.add(testProductLine2);

        int expectedQuantityOfTestProductLine2 = 3;

        cart.increaseQuantity("productB");
        int returnedQuantity = cart.getProductLines().get("productB").getQuantity();

        assertEquals(expectedQuantityOfTestProductLine2, returnedQuantity);

        cart.increaseQuantity("productA");
        int expectedQuantityOfTestProductLine = 2;
        returnedQuantity = cart.getProductLines().get("productA").getQuantity();

        assertEquals(expectedQuantityOfTestProductLine,returnedQuantity);

    }

}
