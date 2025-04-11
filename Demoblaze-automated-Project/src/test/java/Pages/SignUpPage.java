package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class SignUpPage {
    WebDriver driver;
    WebDriverWait wait;

    public SignUpPage(WebDriver driver) {
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    By usernameID= By.id("sign-username");
    By passwordID =By.id("sign-password");
    By signUpBtnPath=By.xpath("//button[text()='Sign up']");
    By closeBtn=By.xpath("(//button[text()='Close'])[2]");

    public void enterUsername(String username) {
        driver.findElement(usernameID).clear();
        driver.findElement(usernameID).sendKeys(username);
    }
    public void enterPassword(String password) {
        driver.findElement(passwordID).clear();
        driver.findElement(passwordID).sendKeys(password);
    }
    public void clickSignUpButton() {
        driver.findElement(signUpBtnPath).click();
    }
    public String getAlertText() {
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert().getText();
    }

    public void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }
    public void clickClose(){
        driver.findElement(closeBtn).click();
    }

}
