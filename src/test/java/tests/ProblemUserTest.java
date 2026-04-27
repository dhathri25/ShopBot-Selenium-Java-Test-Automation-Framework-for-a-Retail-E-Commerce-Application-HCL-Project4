package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.ProductPage;

public class ProblemUserTest extends BaseTest {

    @Test
    public void testProblemUser() {

        LoginPage login = new LoginPage(driver);
        login.enterUsername("problem_user");
        login.enterPassword("secret_sauce");
        login.clickLogin();

        ProductPage product = new ProductPage(driver);

        // Verify page loads
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));

        // 🔥 Attempt add product
        product.addFirstProduct();
        

        // 🔥 Verify still functioning (UI buggy but works)
        Assert.assertTrue(driver.getPageSource().contains("inventory"));
    }
}