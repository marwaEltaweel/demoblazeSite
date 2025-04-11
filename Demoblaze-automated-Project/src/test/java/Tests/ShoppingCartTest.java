package Tests;

import Base.BaseTest;
import Pages.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class ShoppingCartTest extends BaseTest {
    HomePage homePage;
    ProductPage productPage;
    CartPage cartPage;

    @Test(priority = 1, description = "Verify adding a single product to cart")
    public void testAddSingleProductToCart() throws InterruptedException {
        homePage= new HomePage(driver);
        productPage=new ProductPage(driver);
        cartPage= new CartPage(driver);
        homePage.clickSamsungGalaxyS6();
        String productTitle = productPage.getProductTitle();

        productPage.addToCart();
        productPage.waitForAlert();
        driver.switchTo().alert().accept();

        homePage.goToCart();
        Thread.sleep(2000);
        assertEquals(cartPage.getCartItemCount(), 1);
        assertTrue(cartPage.isProductInCart(productTitle));

    }
    @Test(priority = 2, description = "Verify adding multiple products to cart")
    public void testAddMultipleProductsToCart() throws InterruptedException {
        homePage= new HomePage(driver);
        productPage=new ProductPage(driver);
        cartPage= new CartPage(driver);
        // Add first product
        homePage.clickSamsungGalaxyS6();
        String product1Title = productPage.getProductTitle();
        productPage.addToCart();
        productPage.waitForAlert();
        driver.switchTo().alert().accept();
        // Navigate back to home
        driver.navigate().back();
        driver.navigate().back();

        // Add second product
        homePage.clickNexus6();
        String product2Title = productPage.getProductTitle();
        productPage.addToCart();
        productPage.waitForAlert();
        driver.switchTo().alert().accept();

        homePage.goToCart();
        Thread.sleep(2000);
        assertEquals(cartPage.getCartItemCount(), 2);
        assertTrue(cartPage.isProductInCart(product1Title));
        assertTrue(cartPage.isProductInCart(product2Title));

    }
    @Test(priority = 3, description = "Verify removing a product from cart")
    public void testRemoveProductFromCart() {
        homePage= new HomePage(driver);
        productPage=new ProductPage(driver);
        cartPage= new CartPage(driver);
        // Add product first
        homePage.clickSamsungGalaxyS6();
        String productTitle = productPage.getProductTitle();
        productPage.addToCart();
        productPage.waitForAlert();
        driver.switchTo().alert().accept();

        homePage.goToCart();

        // Remove product
        cartPage.deleteProduct(productTitle);
        cartPage.waitForCartToUpdate();

        assertEquals(cartPage.getCartItemCount(), 0);
    }
    @Test(priority = 4, description = "Verify cart total calculation")
    public void testCartTotalCalculation() throws InterruptedException {
        homePage= new HomePage(driver);
        productPage=new ProductPage(driver);
        cartPage= new CartPage(driver);
        // Add first product
        homePage.clickSamsungGalaxyS6();
        String product1Price = productPage.getProductPrice().replaceAll("[^0-9]", "");
        productPage.addToCart();
        productPage.waitForAlert();
        driver.switchTo().alert().accept();

        // Navigate back to home
        driver.navigate().back();
        driver.navigate().back();

        // Add second product
        homePage.clickNexus6();
        String product2Price = productPage.getProductPrice().replaceAll("[^0-9]", "");
        productPage.addToCart();
        productPage.waitForAlert();
        driver.switchTo().alert().accept();
        homePage.goToCart();
        int expectedTotal = Integer.parseInt(product1Price) + Integer.parseInt(product2Price);
        Thread.sleep(2000);
        int actualTotal = Integer.parseInt(cartPage.getTotalPrice());

        assertEquals(actualTotal, expectedTotal);
    }
    @Test(priority = 5, description = "Verify product details in cart match product page")
    public void testProductDetailsInCart() {
        homePage= new HomePage(driver);
        productPage=new ProductPage(driver);
        cartPage= new CartPage(driver);
        homePage.clickSamsungGalaxyS6();
        String expectedTitle = productPage.getProductTitle();
        String expectedPrice = productPage.getProductPrice().replaceAll("[^0-9]", "");

        productPage.addToCart();
        productPage.waitForAlert();
        driver.switchTo().alert().accept();

        homePage.goToCart();

        assertTrue(cartPage.isProductInCart(expectedTitle));
        assertEquals(cartPage.productPrices.get(0).getText(), expectedPrice);
    }
    @Test(priority = 6, description = "Verify cart persists after page refresh")
    public void testCartPersistsAfterRefresh() {
        homePage= new HomePage(driver);
        productPage=new ProductPage(driver);
        cartPage= new CartPage(driver);
        homePage.clickSamsungGalaxyS6();
        String productTitle = productPage.getProductTitle();
        productPage.addToCart();
        productPage.waitForAlert();
        driver.switchTo().alert().accept();

        homePage.goToCart();
        driver.navigate().refresh();

        assertEquals(cartPage.getCartItemCount(), 1);
        assertTrue(cartPage.isProductInCart(productTitle));
    }
}
