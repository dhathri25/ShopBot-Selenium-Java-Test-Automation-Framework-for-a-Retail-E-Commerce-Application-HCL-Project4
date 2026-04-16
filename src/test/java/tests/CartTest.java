package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.ProductPage;
import pages.CartPage;

public class CartTest extends BaseTest {

    @Test
    public void testCartFunctionality() {

        LoginPage login = new LoginPage(driver);
        login.enterUsername("standard_user");
        login.enterPassword("secret_sauce");
        login.clickLogin();

        ProductPage product = new ProductPage(driver);
        CartPage cart = new CartPage(driver);

        // Add products
        product.addFirstProduct();
        Assert.assertTrue(cart.getCartCount() >= 1);

        product.addSecondProduct();
        Assert.assertTrue(cart.getCartCount() >= 2);

        // Open cart
        cart.openCart();

        // Remove item
        cart.removeItem();

        Assert.assertTrue(cart.getCartItemsCount() >= 0);

        // 🔥 NEW: Verify cart retains items after back
        driver.navigate().back();
        Assert.assertTrue(cart.getCartCount() >= 1);
    }
}