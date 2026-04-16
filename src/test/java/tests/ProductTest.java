package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.ProductPage;

public class ProductTest extends BaseTest {

    @Test
    public void testProductListingAndSorting() {

        LoginPage login = new LoginPage(driver);
        login.enterUsername("standard_user");
        login.enterPassword("secret_sauce");
        login.clickLogin();

        ProductPage product = new ProductPage(driver);

        // Verify product count
        Assert.assertTrue(product.getProductCount() > 0);

        // Sort A-Z
        product.sortBy("az");
        String firstName = product.getFirstProductName();
        Assert.assertNotNull(firstName);

        // Sort Low to High
        product.sortBy("lohi");
        double price = product.getFirstProductPrice();
        Assert.assertTrue(price >= 0);

        // 🔥 NEW: Verify product detail page
        String nameBefore = product.getFirstProductName();
        String priceBefore = String.valueOf(product.getFirstProductPrice());

        product.openFirstProduct();

        Assert.assertTrue(driver.getPageSource().contains(nameBefore));
        Assert.assertTrue(driver.getPageSource().contains(priceBefore));
    }
}