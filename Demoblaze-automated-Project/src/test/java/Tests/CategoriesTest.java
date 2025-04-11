package Tests;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import Pages.CategoriesPage;

public class CategoriesTest  extends BaseTest {


    @Test
    public void testCategoriesPageLoadsCorrectly() { // TC_01: Verify that the CategoriesPage loads correctly
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "STORE", "CategoriesPage title is incorrect");
    }

    @Test
    public void testCategoriesSectionIsDisplayed() { // TC_02: Verify that the categories section is displayed
        CategoriesPage catPage = new CategoriesPage(driver);
        Assert.assertTrue(catPage.isCategoriesSectionDisplayed(), "Categories section is not displayed on the CategoriesPage");
    }

    @Test
    public void testPhonesCategoryShowsPhoneProducts() { // TC_03: Verify that the Phones category shows phone products
        CategoriesPage catPage = new CategoriesPage(driver);
        catPage.clickCategory("phones");
        int productCount = catPage.getProductListSize();
        Assert.assertTrue(productCount > 0, "No products displayed in Phones category");

        List<String> productNames = catPage.getProductNames();
        boolean allPhones = productNames.stream()
                .allMatch(name -> name.toLowerCase().contains("phone") ||
                        name.toLowerCase().contains("nexus") ||
                        name.toLowerCase().contains("galaxy") ||
                        name.toLowerCase().contains("iphone") ||
                        name.toLowerCase().contains("samsung") ||
                        name.toLowerCase().contains("nokia") ||
                        name.toLowerCase().contains("sony") ||
                        name.toLowerCase().contains("htc"));
        Assert.assertTrue(allPhones, "Products in Phones category do not seem to be phones: " + productNames);
    }

    @Test
    public void testLaptopsCategoryShowsLaptopProducts() { // TC_04: Verify that the Laptops category shows laptop products
        CategoriesPage catPage = new CategoriesPage(driver);
        catPage.clickCategory("laptops");
        int productCount = catPage.getProductListSize();
        Assert.assertTrue(productCount > 0, "No products displayed in Laptops category");

        List<String> productNames = catPage.getProductNames();
        boolean allLaptops = productNames.stream()
                .allMatch(name -> name.toLowerCase().contains("laptop") ||
                        name.toLowerCase().contains("macbook") ||
                        name.toLowerCase().contains("dell") ||
                        name.toLowerCase().contains("sony") ||
                        name.toLowerCase().contains("hp") ||
                        name.toLowerCase().contains("lenovo"));
        Assert.assertTrue(allLaptops, "Products in Laptops category do not seem to be laptops: " + productNames);
    }

    @Test
    public void testMonitorsCategoryShowsMonitorProducts() { // TC_05: Verify that the Monitors category shows monitor products
        CategoriesPage CategoriesPage = new CategoriesPage(driver);
        CategoriesPage.clickCategory("monitors");
        int productCount = CategoriesPage.getProductListSize();
        Assert.assertTrue(productCount > 0, "No products displayed in Monitors category");

        List<String> productNames = CategoriesPage.getProductNames();
        boolean allMonitors = productNames.stream()
                .allMatch(name -> name.toLowerCase().contains("monitor") ||
                        name.toLowerCase().contains("apple") ||
                        name.toLowerCase().contains("asus") ||
                        name.toLowerCase().contains("dell") ||
                        name.toLowerCase().contains("lg"));
        Assert.assertTrue(allMonitors, "Products in Monitors category do not seem to be monitors: " + productNames);
    }

    @Test
    public void testDefaultStateShowsMixedProducts() { // TC_06: Verify that the default state shows mixed products
        CategoriesPage CategoriesPage = new CategoriesPage(driver);
        int productCount = CategoriesPage.getProductListSize();
        Assert.assertTrue(productCount > 0, "No products displayed in default state");

        List<String> productNames = CategoriesPage.getProductNames();
        boolean hasPhone = productNames.stream()
                .anyMatch(name -> name.toLowerCase().contains("phone") ||
                        name.toLowerCase().contains("nexus") ||
                        name.toLowerCase().contains("galaxy") ||
                        name.toLowerCase().contains("iphone") ||
                        name.toLowerCase().contains("samsung") ||
                        name.toLowerCase().contains("nokia") ||
                        name.toLowerCase().contains("sony") ||
                        name.toLowerCase().contains("htc"));
        boolean hasLaptop = productNames.stream()
                .anyMatch(name -> name.toLowerCase().contains("laptop") ||
                        name.toLowerCase().contains("macbook") ||
                        name.toLowerCase().contains("dell") ||
                        name.toLowerCase().contains("sony") ||
                        name.toLowerCase().contains("hp") ||
                        name.toLowerCase().contains("lenovo"));
        boolean hasMonitor = productNames.stream()
                .anyMatch(name -> name.toLowerCase().contains("monitor") ||
                        name.toLowerCase().contains("apple") ||
                        name.toLowerCase().contains("asus") ||
                        name.toLowerCase().contains("dell") ||
                        name.toLowerCase().contains("lg"));
        int categoriesPresent = 0;
        if (hasPhone) categoriesPresent++;
        if (hasLaptop) categoriesPresent++;
        if (hasMonitor) categoriesPresent++;
        Assert.assertTrue(categoriesPresent >= 2, "Default state should contain products from at least two categories. Found: " + productNames);
    }

    @Test
    public void testSwitchingBetweenCategories() { // TC_07: Verify that switching between categories works correctly
        CategoriesPage CategoriesPage = new CategoriesPage(driver);
        CategoriesPage.clickCategory("phones");
        int phoneProductCount = CategoriesPage.getProductListSize();
        CategoriesPage.clickCategory("laptops");
        int laptopProductCount = CategoriesPage.getProductListSize();
        Assert.assertNotEquals(laptopProductCount, phoneProductCount, "Product count did not change after switching to Laptops category");

        CategoriesPage.clickCategory("monitors");
        int monitorProductCount = CategoriesPage.getProductListSize();
        Assert.assertNotEquals(monitorProductCount, laptopProductCount, "Product count did not change after switching to Monitors category");
    }

    // @Test
    public void testCategoryLoadingTime() { // TC_08: Verify that category loading time is within acceptable limits
        CategoriesPage CategoriesPage = new CategoriesPage(driver);
        long startTime = System.currentTimeMillis();
        CategoriesPage.clickCategory("phones");
        long loadTime = System.currentTimeMillis() - startTime;
        Assert.assertTrue(loadTime < 5000, "Phones category took too long to load: " + loadTime + " ms");
    }

    @Test
    public void testProductImagesDisplayed() { // TC_09: Verify that product images are displayed
        CategoriesPage CategoriesPage = new CategoriesPage(driver);
        List<WebElement> images = CategoriesPage.getProductImages();
        Assert.assertTrue(images.size() > 0, "No product images found on the CategoriesPage");
        for (WebElement image : images) {
            Assert.assertTrue(image.isDisplayed(), "Product image is not displayed");
        }
    }

    @Test
    public void testSelectingInvalidCategory() { // TC_11: Verify that selecting an invalid category does not change products
        CategoriesPage CategoriesPage = new CategoriesPage(driver);
        int initialProductCount = CategoriesPage.getProductListSize();
        boolean categoryClicked = CategoriesPage.clickCategoryByName("Tablets");
        Assert.assertFalse(categoryClicked, "Invalid category 'Tablets' was clicked, but it should not exist");
        int productCountAfter = CategoriesPage.getProductListSize();
        Assert.assertEquals(productCountAfter, initialProductCount, "Product count changed after selecting invalid category");
    }

    @Test
    public void testProductDetailsDisplayedCorrectly() { // TC_19: Verify that product details are displayed correctly
        CategoriesPage CategoriesPage = new CategoriesPage(driver);
        List<String> productNames = CategoriesPage.getProductNames();
        List<String> productPrices = CategoriesPage.getProductPrices();
        List<String> productDescriptions = CategoriesPage.getProductDescriptions();
        Assert.assertFalse(productNames.isEmpty(), "No product names displayed");
        Assert.assertFalse(productPrices.isEmpty(), "No product prices displayed");
        Assert.assertFalse(productDescriptions.isEmpty(), "No product descriptions displayed");
        Assert.assertEquals(productNames.size(), productPrices.size(), "Mismatch between product names and prices");
        Assert.assertEquals(productNames.size(), productDescriptions.size(), "Mismatch between product names and descriptions");
    }

    @Test
    public void testNextButtonFunctionality() { // TC_24: Verify that the Next button changes the products
        CategoriesPage CategoriesPage = new CategoriesPage(driver);
        int initialProductCount = CategoriesPage.getProductListSize();
        if (CategoriesPage.isNextButtonEnabled()) {
            CategoriesPage.clickNextButton();
            int newProductCount = CategoriesPage.getProductListSize();
            Assert.assertNotEquals(newProductCount, initialProductCount, "Product count did not change after clicking Next");
        } else {
            System.out.println("Next button is not enabled, skipping test.");
        }
    }

    @Test
    public void testPreviousButtonFunctionality() { // TC_25: Verify that the Previous button returns to initial products
        CategoriesPage CategoriesPage = new CategoriesPage(driver);
        int initialProductCount = CategoriesPage.getProductListSize();
        if (CategoriesPage.isNextButtonEnabled()) {
            CategoriesPage.clickNextButton();
            CategoriesPage.clickPreviousButton();
            int finalProductCount = CategoriesPage.getProductListSize();
            Assert.assertEquals(finalProductCount, initialProductCount, "Product count did not return to initial state after clicking Previous");
        } else {
            System.out.println("Next button is not enabled, skipping test.");
        }
    }

    // New Test Cases
    @Test
    public void testClickingProductOpensDetailsPage() { // TC_26: Verify that clicking a product opens its details page
        CategoriesPage CategoriesPage = new CategoriesPage(driver);
        String firstProductName = CategoriesPage.getProductNames().get(0);
        CategoriesPage.clickFirstProduct();
        String[] productDetails = CategoriesPage.getProductDetails();
        Assert.assertEquals(productDetails[0], firstProductName, "Product name on details page does not match the clicked product");
        Assert.assertFalse(productDetails[1].isEmpty(), "Product price is not displayed on details page");
        Assert.assertFalse(productDetails[2].isEmpty(), "Product description is not displayed on details page");
    }

    // @Test
    public void testCategorySelectionPersistenceAfterRefresh() { // TC_27: Verify that category selection does not persist after refresh
        CategoriesPage CategoriesPage = new CategoriesPage(driver);
        CategoriesPage.clickCategory("phones");
        int phoneProductCount = CategoriesPage.getProductListSize();
        CategoriesPage.refreshPage();
        int productCountAfterRefresh = CategoriesPage.getProductListSize();
        Assert.assertNotEquals(productCountAfterRefresh, phoneProductCount, "Product count did not change after page refresh, category selection persisted");
    }

    //  @Test
    public void testProductLinksAreClickable() { // TC_28: Verify that product links are clickable
        CategoriesPage CategoriesPage = new CategoriesPage(driver);
        List<WebElement> productLinks = new ArrayList<>();
        for (String name : CategoriesPage.getProductNames()) {
            WebElement link = driver.findElement(org.openqa.selenium.By.linkText(name));
            productLinks.add(link);
        }
        Assert.assertTrue(productLinks.size() > 0, "No product links found on the CategoriesPage");
        for (WebElement link : productLinks) {
            Assert.assertTrue(link.isEnabled(), "Product link is not clickable: " + link.getText());
        }
    }
}