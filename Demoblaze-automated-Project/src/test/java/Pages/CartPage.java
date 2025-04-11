package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {
    WebDriver driver;
    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void reDirectToHomePage(){
        driver.findElement(By.xpath("//a[@href=\"index.html\"]"));
    }
    // List<WebElement>  cartItems=driver.findElements(By.xpath("//tbody/tr"));
   @FindBy(xpath = "//tbody/tr")
    List<WebElement> cartItems;
    @FindBy(xpath = "//td[2]") // Product names
    List<WebElement> productNames;
    @FindBy(xpath = "//td[3]") // Prices
    public  List<WebElement> productPrices;
    @FindBy(linkText = "Delete")
    List<WebElement> deleteLinks;

    By totalPrice=By.id("totalp");

    public int getCartItemCount() {
        return cartItems.size();
    }
    public String getTotalPrice() {
        return driver.findElement(totalPrice).getText();
    }
    public boolean isProductInCart(String productName) {
        for (WebElement name : productNames) {
            if (name.getText().equals(productName)) {
                return true;
            }
        }
        return false;
    }

    public void deleteProduct(String productName) {
        for (int i = 0; i < productNames.size(); i++) {
            if (productNames.get(i).getText().equals(productName)) {
                deleteLinks.get(i).click();
                break;
            }
        }
    }

    public void waitForCartToUpdate() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(cartItems.get(0)));
    }

}
