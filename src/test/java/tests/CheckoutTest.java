package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.ProductPage;
import pages.CartPage;
import pages.CheckoutPage;

public class CheckoutTest extends BaseTest {

    @Test
    public void testCheckoutFlow() {

        LoginPage login = new LoginPage(driver);
        login.enterUsername("standard_user");
        login.enterPassword("secret_sauce");
        login.clickLogin();

        ProductPage product = new ProductPage(driver);
        product.addFirstProduct();

        CartPage cart = new CartPage(driver);
        cart.openCart();

        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.clickCheckout();

        checkout.enterFirstName("Hema");
        checkout.enterLastName("K");
        checkout.enterPostalCode("522201");

        checkout.clickContinue();

        // 🔥 NEW: Verify summary
        String item = checkout.getItemName();
        String total = checkout.getTotalPrice();

        Assert.assertTrue(item.length() > 0);
        Assert.assertTrue(total.contains("$"));

        checkout.clickFinish();

        String msg = checkout.getConfirmationText();
        Assert.assertTrue(msg.contains("Thank you"));
    }
}