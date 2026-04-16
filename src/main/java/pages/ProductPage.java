package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;
import java.util.List;

import base.BasePage;

import java.util.List;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By productList = By.className("inventory_item");
    private By productNames = By.className("inventory_item_name");
    private By productPrices = By.className("inventory_item_price");
    private By sortDropdown = By.className("product_sort_container");
    private By firstItemAdd = By.id("add-to-cart-sauce-labs-backpack");
    private By menuBtn = By.id("react-burger-menu-btn");
    private By logoutLink = By.id("logout_sidebar_link");
    private By secondItemAdd = By.id("add-to-cart-sauce-labs-bike-light");

    public void addSecondProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(secondItemAdd)).click();
    }
    

    // Actions

    public int getProductCount() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productList)).size();
    }
    public void openFirstProduct() {
        driver.findElements(By.className("inventory_item_name")).get(0).click();
    }

    public void sortBy(String value) {
        Select select = new Select(driver.findElement(sortDropdown));
        select.selectByValue(value);
    }

    public String getFirstProductName() {
        List<WebElement> elements = driver.findElements(productNames);
        return elements.get(0).getText();
    }

    public double getFirstProductPrice() {
        List<WebElement> elements = driver.findElements(productPrices);
        String price = elements.get(0).getText().replace("$", "");
        return Double.parseDouble(price);
    }
    public void addFirstProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(firstItemAdd)).click();
    }
    public void openMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(menuBtn)).click();
    }

    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }
}