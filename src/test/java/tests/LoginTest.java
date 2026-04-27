package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;

public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] getData() {
        return new Object[][] {
            {"standard_user", "secret_sauce"},
            {"locked_out_user", "secret_sauce"},
            {"", ""}
        };
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String user, String pass) {

        LoginPage login = new LoginPage(driver);
        

        login.enterUsername(user);
        login.enterPassword(pass);
        login.clickLogin();

        if (user.equals("standard_user")) {
            Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
        } else {
            Assert.assertTrue(login.getErrorMessage().length() > 0);
        }
    }
    @Test
    public void testInvalidLoginScreenshot() {

        LoginPage login = new LoginPage(driver);

        login.enterUsername("invalid_user");
        login.enterPassword("wrong_pass");
        login.clickLogin();

        // Force validation
        String error = login.getErrorMessage();

        // ❌ Intentionally fail if error not matching
        Assert.assertEquals(error, "Wrong error message");
    }
}