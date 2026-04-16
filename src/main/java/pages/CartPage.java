package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    // Stable Locators (IMPORTANT)
    private By firstItemAdd = By.id("add-to-cart-sauce-labs-backpack");
    private By secondItemAdd = By.id("add-to-cart-sauce-labs-bike-light");
    private By cartBadge = By.className("shopping_cart_badge");
    private By cartIcon = By.className("shopping_cart_link");
    private By removeBtn = By.xpath("//button[contains(text(),'Remove')]");
    private By cartItems = By.className("cart_item");

    // Add first item
    public void addFirstItem() {
        wait.until(ExpectedConditions.elementToBeClickable(firstItemAdd)).click();
    }

    // Add second item
    public void addSecondItem() {
        wait.until(ExpectedConditions.elementToBeClickable(secondItemAdd)).click();
    }

    // Get cart badge count
    public int getCartCount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge));
        return Integer.parseInt(driver.findElement(cartBadge).getText());
    }

    // Open cart
    public void openCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
    }

    // Remove item
    public void removeItem() {
        wait.until(ExpectedConditions.elementToBeClickable(removeBtn)).click();
    }

    // Count items in cart page
    public int getCartItemsCount() {
        return driver.findElements(cartItems).size();
    }
}