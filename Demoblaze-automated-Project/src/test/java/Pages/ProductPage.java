package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
     WebDriver driver;
    public ProductPage(WebDriver driver) {
        this.driver = driver;
       // PageFactory.initElements(driver, this);
    }
     By addToCartButton= By.xpath("//a[text()=\"Add to cart\"]");
     By productTitle =By.xpath("//h2[@class=\"name\"]");
     By productPrice=By.xpath("//h3[@class='price-container']");



    public void addToCart() {
      driver.findElement(addToCartButton).click();
    }

    public String getProductTitle() {
        return driver.findElement(productTitle).getText();
    }

    public String getProductPrice() {
        return driver.findElement(productPrice).getText();
    }

    public void waitForAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
    }
}