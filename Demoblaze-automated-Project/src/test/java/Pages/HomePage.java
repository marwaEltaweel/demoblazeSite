package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;

   public HomePage(WebDriver driver) {
     this.driver = driver;
      //  PageFactory.initElements(driver, this);
    }

    By samsungGalaxyS6 = By.xpath("(//a[@href=\"prod.html?idp_=1\"])[2]");
    By nokiaLumia1520 = By.xpath("(//a[@href=\"prod.html?idp_=2\"])[2]");
    By nexus6 = By.xpath("(//a[@href=\"prod.html?idp_=3\"])[2]");
    By samsungGalaxyS7 = By.xpath("(//a[@href=\"prod.html?idp_=4\"])[2]");
    By iphone632gb = By.xpath("(//a[@href=\"prod.html?idp_=5\"])[2]");
    By sonyXperiaZ5 = By.xpath("(//a[@href=\"prod.html?idp_=6\"])[2]");
    By hTCOneM9 = By.xpath("(//a[@href=\"prod.html?idp_=7\"])[2]");
    By sonyVaiOi5 = By.xpath("(//a[@href=\"prod.html?idp_=8\"])[2]");
    By sonyVaiOi7 = By.xpath("(//a[@href=\"prod.html?idp_=9\"])[2]");

    By cartLink=By.id("cartur");
    public void clickSamsungGalaxyS6() {
        driver.findElement(samsungGalaxyS6).click();
    }
    public void clickNokiaLumia1520() {
        driver.findElement(nokiaLumia1520).click();
    }
    public void clickNexus6() {
        driver.findElement(nexus6).click();
    }
    public void clickSamsungGalaxyS7() {
        driver.findElement(samsungGalaxyS7).click();
    }
    public void clickIphone632gb() {
        driver.findElement(iphone632gb).click();
    }
    public void clickSonyXperiaZ5() {
        driver.findElement(sonyXperiaZ5).click();
    }
    public void clickHTCOneM9() {
        driver.findElement(hTCOneM9).click();
    }
    public void clickSonyVaiOi5() {
        driver.findElement(sonyVaiOi5).click();
    }
    public void clickSonyVaiOi7() {
        driver.findElement(sonyVaiOi7).click();
    }
    public void goToCart() {
        driver.findElement(cartLink).click();
    }

}
