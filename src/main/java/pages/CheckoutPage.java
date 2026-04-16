package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By checkoutBtn = By.id("checkout");
    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By postalCode = By.id("postal-code");
    private By continueBtn = By.id("continue");

    private By finishBtn = By.id("finish");
    private By confirmationMsg = By.className("complete-header");
    private By itemName = By.className("inventory_item_name");
    private By totalPrice = By.className("summary_total_label");

    public String getItemName() {
        return driver.findElement(itemName).getText();
    }

    public String getTotalPrice() {
        return driver.findElement(totalPrice).getText();
    }

    // Click checkout
    public void clickCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
    }

    // Enter details
    public void enterFirstName(String fname) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(fname);
    }

    public void enterLastName(String lname) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastName)).sendKeys(lname);
    }

    public void enterPostalCode(String code) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(postalCode)).sendKeys(code);
    }

    // Continue → go to Overview page
    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();

        // 🔥 IMPORTANT: wait until next page loads
        wait.until(ExpectedConditions.urlContains("checkout-step-two"));
    }

    // Finish order (FINAL FIX)
    public void clickFinish() {

        // wait until page loads
        wait.until(ExpectedConditions.urlContains("checkout-step-two"));

        // wait for element present
        wait.until(ExpectedConditions.presenceOfElementLocated(finishBtn));

        // 🔥 FORCE CLICK USING JAVASCRIPT
        org.openqa.selenium.JavascriptExecutor js =
            (org.openqa.selenium.JavascriptExecutor) driver;

        js.executeScript("arguments[0].click();", driver.findElement(finishBtn));
    }

    // Confirmation
    public String getConfirmationText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMsg));
        return driver.findElement(confirmationMsg).getText();
    }
}