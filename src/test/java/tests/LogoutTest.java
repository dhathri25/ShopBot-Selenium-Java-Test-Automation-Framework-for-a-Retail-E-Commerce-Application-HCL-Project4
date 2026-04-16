package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.ProductPage;

public class LogoutTest extends BaseTest {

    @Test
    public void testLogout() {

        LoginPage login = new LoginPage(driver);
        login.enterUsername("standard_user");
        login.enterPassword("secret_sauce");
        login.clickLogin();

        ProductPage product = new ProductPage(driver);
        product.openMenu();
        product.clickLogout();

        Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo"));
    }
}