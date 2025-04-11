package Tests;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.Objects;

public class ProductDetailsTest extends BaseTest {


    @Test(priority = 1, description = "Verify details page loads with required elements")
    public void testOpenProductPage() {
        // Click on a product (Nexus 6 in this case)
        driver.findElement(By.linkText("Nexus 6")).click();

        // Verify details page loads with required elements
        Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("idp_=")); //"URL should include product ID"
        Assert.assertTrue(driver.findElement(By.cssSelector(".name")).isDisplayed()); // "Product name is displayed"
        Assert.assertTrue(driver.findElement(By.cssSelector(".price-container")).isDisplayed()); //"Price is displayed"
        Assert.assertTrue(driver.findElement(By.id("more-information")).isDisplayed()); // "Description is displayed"
        Assert.assertTrue(driver.findElement(By.xpath("//img[@src=\"imgs/Nexus_6.jpg\"]")).isDisplayed());// "Image is displayed"
    }

    @Test(priority = 2, description = "Verify product name matches listing from category")
    public void testProductNameMatchesListing() {
        // Get product name from category view
        WebElement productInList = driver.findElement(By.linkText("Nexus 6"));
        String listName = productInList.getText();
        // Open product page
        productInList.click();
        // Get product name from details page
        String detailsName = driver.findElement(By.cssSelector(".name")).getText();
        // Verify names match
        Assert.assertEquals(detailsName, listName, "Product names should match");
    }

    @Test(priority = 3, description = "Verify price format (simple check for digits)")
    public void testPriceFormatting() {
        driver.findElement(By.linkText("Nexus 6")).click();
        String priceText = driver.findElement(By.cssSelector(".price-container")).getText();

        // Verify price format (simple check for digits)
        Assert.assertTrue(priceText.matches(".*\\d+.*"), "Price should contain numbers");
    }

    @Test(priority = 4, description = "Verify image loads successfully")
    public void testImageLoads() {
        driver.findElement(By.linkText("Nexus 6")).click();
        WebElement image = driver.findElement(By.xpath("//img[@src=\"imgs/Nexus_6.jpg\"]"));

        Assert.assertTrue(image.isDisplayed(), "Image should be displayed");
        // Check if image is loaded (naturalWidth > 0)
        Assert.assertTrue(Integer.parseInt(Objects.requireNonNull(image.getAttribute("naturalWidth"))) > 0);//"Image should load properly"
    }

    @Test(priority = 5, description = "Verify that button 'Add to cart' is clickable")
    public void testAddToCart() throws InterruptedException {
        driver.findElement(By.linkText("Nexus 6")).click();
        driver.findElement(By.linkText("Add to cart")).click();
        Thread.sleep(2000);
        // Handle alert
        String alertText = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();

        Assert.assertEquals(alertText, "Product added", "Alert text should match");
    }

    @Test(priority = 6, description = "Verify 'Description' field content is visible")
    public void testDescriptionContent() {
        driver.findElement(By.linkText("Nexus 6")).click();
        String description = driver.findElement(By.id("more-information")).getText();

        Assert.assertFalse(description.isEmpty(), "Description should not be empty");
        Assert.assertFalse(description.contains("Lorem Ipsum"), "Description should not contain placeholder text");
    }

    @Test(priority = 7, description = "Navigate back to categories (Home Page)")
    public void testNavigateBackToCategories() {
        driver.findElement(By.linkText("Nexus 6")).click();
        driver.findElement(By.cssSelector(".nav-link[href='index.html']")).click();
        String BASE_URL = "https://www.demoblaze.com";

        Assert.assertEquals(driver.getCurrentUrl(), BASE_URL + "/index.html", "Should return to home page");
    }
}