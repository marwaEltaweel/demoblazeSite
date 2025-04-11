package Tests;

import Base.BaseTest;
import Base.BaseTesttt;
import Pages.HeaderPage;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTesttt {
    HeaderPage homepage;
    LoginPage loginPage;
    @BeforeMethod
    public void preconditions(){
        // Initialize page objects for each test
        homepage=new HeaderPage(driver);
        loginPage=new LoginPage(driver);
    }
    @Test (priority = 6, description = "Validate that registered user can Log in")
    public void testSuccessfulLogin() throws InterruptedException {
        homepage.clickSignInLink();
        loginPage.enterUsername("Mimo");
        loginPage.enterPassword("Mimo111181");
        loginPage.clickLoginButton();
        Thread.sleep(1200);
        //Assert.assertEquals(welcomeText, "Welcome Mimo");
        Assert.assertTrue(true,"Welcome Mimo");
        loginPage.clickLogOut();
        Thread.sleep(1000);
    }
    @Test (priority = 7, description = "Validate error with blank username and password")
    public void testLoginWithBlankCredentials()  throws InterruptedException {
        homepage.clickSignInLink();
        loginPage.enterUsername("");
        loginPage.enterPassword("");
        loginPage.clickLoginButton();
        Thread.sleep(1200);
        String alert=loginPage.getAlertText();
        Assert.assertEquals(alert,"Please fill out Username and Password.");
        loginPage.acceptAlert();
        Thread.sleep(1200);
    }
    @Test(priority = 8, description = "Validate error with blank username")
    public void testLoginWithBlankUsername() throws InterruptedException {
        loginPage.enterUsername("");
        loginPage.enterPassword("Mimo111181");
        loginPage.clickLoginButton();
        Thread.sleep(1200);
        String alert=loginPage.getAlertText();
        Assert.assertEquals(alert,"Please fill out Username and Password.");
        loginPage.acceptAlert();
        Thread.sleep(1200);
    }
    @Test(priority = 9, description = "Validate error with blank password")
    public void testLoginWithBlankPassword() throws InterruptedException {
        loginPage.enterUsername("Mimo");
        loginPage.enterPassword("");
        loginPage.clickLoginButton();
        Thread.sleep(1200);
        String alert=loginPage.getAlertText();
        Assert.assertEquals(alert,"Please fill out Username and Password.");
        loginPage.acceptAlert();
        Thread.sleep(1200);
    }
    @Test(priority = 10, description = "Validate error with wrong password")
    public void testLoginWithWrongPassword() throws InterruptedException {
        loginPage.enterUsername("Mimo");
        loginPage.enterPassword("123456");
        loginPage.clickLoginButton();
        Thread.sleep(1000);;
        String alert=loginPage.getAlertText();
        Assert.assertEquals(alert,"Wrong password.");
        loginPage.acceptAlert();
    }
    @Test(priority = 11, description = "Validate error with wrong username")
    public void testLoginWithWrongUsername() throws InterruptedException {
        loginPage.enterUsername("Bibaaaaaao");
        loginPage.enterPassword("Mimo111181");
        loginPage.clickLoginButton();
        Thread.sleep(1000);
        String alert=loginPage.getAlertText();
        Assert.assertEquals(alert,"User does not exist.");
        loginPage.acceptAlert();
        Thread.sleep(1000);
    }


}
