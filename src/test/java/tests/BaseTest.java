package tests;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import base.DriverFactory;
import org.testng.annotations.Listeners;
import listeners.TestListener;

@Listeners(TestListener.class)

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.initDriver();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
    public WebDriver getDriver() {
        return driver;
    }
}