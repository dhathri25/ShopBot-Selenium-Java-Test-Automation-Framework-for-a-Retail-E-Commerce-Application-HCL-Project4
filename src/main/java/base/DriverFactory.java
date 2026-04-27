package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.chrome.ChromeOptions;

import config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    public static WebDriver initDriver() {

        WebDriver driver;

        String browser = ConfigReader.getBrowser();

        if (browser.equalsIgnoreCase("chrome")) {
        	

            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();

            // 🔥 CRITICAL FIXES
            options.addArguments("--incognito");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-infobars");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-save-password-bubble");
            options.addArguments("--disable-blink-features=AutomationControlled");

            // 🔥 MOST IMPORTANT (password manager OFF)
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            prefs.put("profile.password_manager_leak_detection", false);

            options.setExperimentalOption("prefs", prefs);

            driver = new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("firefox")) {

            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();

        } else {
            throw new RuntimeException("Invalid browser");
        }

        driver.manage().window().maximize();
        driver.get(ConfigReader.getBaseUrl());

        return driver;
    }
}