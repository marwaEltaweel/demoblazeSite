package Tests;

import Base.BaseTesttt;
import Pages.HeaderPage;
import Pages.SignUpPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignUpTest extends BaseTesttt {
    HeaderPage homePage;
    SignUpPage signupPage;

    @BeforeMethod
    public void beforeMethod() {
        // Initialize page objects for each test
        homePage = new HeaderPage(driver);
        signupPage = new SignUpPage(driver);
       // homePage.clickSignUpLink();
    }

  //  @Test(priority = 1, description = "Validate that User can Sign up with valid credentials")
    public void testValidSignup() throws InterruptedException {
        homePage.clickSignUpLink();
        signupPage.enterUsername("Omayiah");
        signupPage.enterPassword("Oyaiee111");
        signupPage.clickSignUpButton();
        Thread.sleep(1200);
        String alertText = signupPage.getAlertText();
        Assert.assertEquals(alertText, "Sign up successful.");
        signupPage.acceptAlert();
        Thread.sleep(1500);
    }
    @Test(priority = 2, description = "Validate error message when sign up with blank User name")
    public void testSignupWithBlankUsername() throws InterruptedException {
        homePage.clickSignUpLink();
        signupPage.enterUsername("");
        signupPage.enterPassword("123456");
        signupPage.clickSignUpButton();
        Thread.sleep(1200);
        String alertText = signupPage.getAlertText();
        Assert.assertEquals(alertText, "Please fill out Username and Password.");
        signupPage.acceptAlert();
    }
    @Test(priority = 3, description = "Validate error message when sign up with blank Password")
    public void testSignupWithBlankPassword() throws InterruptedException {
        signupPage.enterUsername("TestUser");
        signupPage.enterPassword("");
        signupPage.clickSignUpButton();
        Thread.sleep(1200);
        String alertText = signupPage.getAlertText();
        Assert.assertEquals(alertText, "Please fill out Username and Password.");
        signupPage.acceptAlert();
        Thread.sleep(1200);
    }
    @Test(priority = 4, description = "Validate error message when sign up with blank User name and Password")
    public void testSignupWithBlankUsernameAndPassword() throws InterruptedException {
        signupPage.enterUsername("");
        signupPage.enterPassword("");
        signupPage.clickSignUpButton();
        Thread.sleep(1200);
        String alertText = signupPage.getAlertText();
        Assert.assertEquals(alertText, "Please fill out Username and Password.");
        signupPage.acceptAlert();
        Thread.sleep(1200);

    }
   @Test(priority = 5, description = "Validate existing user signup")
    public void testSignupWithExistingUser() throws InterruptedException {
            signupPage.enterUsername("Mimo");
            signupPage.enterPassword("Mimo111181");
            signupPage.clickSignUpButton();
            Thread.sleep(1200);
            String alertText = signupPage.getAlertText();
            Assert.assertEquals(alertText, "This user already exist.");
            signupPage.acceptAlert();
            signupPage.clickClose();
    }


}
