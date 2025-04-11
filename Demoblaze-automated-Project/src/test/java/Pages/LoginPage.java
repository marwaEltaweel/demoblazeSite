package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    By usernameID= By.id("loginusername");
    By passwordID =By.id("loginpassword");
    By signinBtnPath=By.xpath("//button[text()='Log in']");
    By closeBtn=By.xpath("(//button[text()='Close'])[3]");
    By logOutID=By.id("logout2");
    By welcomeMsgID=By.id("nameofuser");

    public void enterUsername(String username) {
        driver.findElement(usernameID).clear();
        driver.findElement(usernameID).sendKeys(username);
    }
    public void enterPassword(String password) {
        driver.findElement(passwordID).clear();
        driver.findElement(passwordID).sendKeys(password);
    }
    public void clickLoginButton() {
        driver.findElement(signinBtnPath).click();
    }
    public void clickLogOut(){
        driver.findElement(logOutID).click();
    }
    public String getAlertText() {
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert().getText();
    }

    public void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }
    public String getWelcomeMessage(){
        return driver.findElement(By.id("nameofuser")).getText();


    }
}
