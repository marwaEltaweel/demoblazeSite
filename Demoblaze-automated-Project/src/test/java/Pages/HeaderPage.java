package Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderPage {
   WebDriver driver;
   public HeaderPage(WebDriver driver) {
       this.driver=driver;
   }

   By signUpLink=By.id("signin2");
   By signInLink=By.id("login2");

    public void clickSignUpLink() {
       driver.findElement(signUpLink).click();
    }
    public void clickSignInLink() {
        driver.findElement(signInLink).click();
    }



}
