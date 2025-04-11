package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class CategoriesPage  {
     WebDriver driver;
     WebDriverWait wait;

    // PageFactory Elements
    @FindBy(id = "cat")
    private WebElement categoriesSection;

    @FindBy(css = "a#itemc[onclick*='phone']")
    private WebElement phonesCategory;

    @FindBy(css = "a#itemc[onclick*='notebook']")
    private WebElement laptopsCategory;

    @FindBy(css = "a#itemc[onclick*='monitor']")
    private WebElement monitorsCategory;

    @FindBy(css = "div.card h4.card-title a")
    private List<WebElement> productNames;

    @FindBy(css = "div.card h5")
    private List<WebElement> productPrices;

    @FindBy(css = "div.card p.card-text")
    private List<WebElement> productDescriptions;

    @FindBy(css = "div.card img")
    private List<WebElement> productImages;

    @FindBy(id = "next2")
    private WebElement nextButton;

    @FindBy(id = "prev2")
    private WebElement previousButton;

    // Elements for Product Details Page
    @FindBy(css = "h2.name")
    private WebElement productDetailName;

    @FindBy(css = "h3.price-container")
    private WebElement productDetailPrice;

    @FindBy(css = "div#more-information p")
    private WebElement productDetailDescription;

    // Element for "No products found" message (if exists)
    @FindBy(css = "div#tbodyid:empty + div.alert.alert-info")
    private WebElement noProductsMessage;

    public CategoriesPage (WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void clickCategory(String categoryType) {
        WebElement categoryElement;
        switch (categoryType.toLowerCase()) {
            case "phones":
                categoryElement = phonesCategory;
                break;
            case "laptops":
                categoryElement = laptopsCategory;
                break;
            case "monitors":
                categoryElement = monitorsCategory;
                break;
            default:
                throw new IllegalArgumentException("Invalid category: " + categoryType);
        }
        wait.until(ExpectedConditions.elementToBeClickable(categoryElement)).click();
        waitForPageToLoad();
        waitForProductsToLoad();
    }

    public boolean clickCategoryByName(String category) {
        try {
            WebElement categoryElement = driver.findElement(
                    org.openqa.selenium.By.cssSelector("a#itemc[onclick*='" + category.toLowerCase() + "']"));
            wait.until(ExpectedConditions.elementToBeClickable(categoryElement)).click();
            waitForPageToLoad();
            waitForProductsToLoad();
            return true;
        } catch (Exception e) {
            System.out.println("Category '" + category + "' not found: " + e.getMessage());
            return false;
        }
    }

    public List<String> getProductNames() {
        int attempts = 0;
        while (attempts < 3) {
            try {
                wait.until(ExpectedConditions.visibilityOfAllElements(productNames));
                List<String> names = new ArrayList<>();
                for (WebElement element : productNames) {
                    names.add(element.getText().trim());
                }
                return names;
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                attempts++;
                System.out.println("StaleElementReferenceException caught in getProductNames (Attempt " + attempts + "): " + e.getMessage());
                waitForPageToLoad();
            }
        }
        throw new RuntimeException("Failed to get product names after 3 attempts due to StaleElementReferenceException");
    }

    public List<String> getProductPrices() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productPrices));
        List<String> prices = new ArrayList<>();
        for (WebElement element : productPrices) {
            prices.add(element.getText().trim());
        }
        return prices;
    }

    public List<String> getProductDescriptions() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productDescriptions));
        List<String> descriptions = new ArrayList<>();
        for (WebElement element : productDescriptions) {
            descriptions.add(element.getText().trim());
        }
        return descriptions;
    }

    public List<WebElement> getProductImages() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productImages));
        return productImages;
    }

    public int getProductListSize() {
        return getProductNames().size();
    }

    public boolean isCategoriesSectionDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(categoriesSection)).isDisplayed();
    }

    public void clickNextButton() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
        waitForPageToLoad();
        waitForProductsToLoad();
    }

    public void clickPreviousButton() {
        wait.until(ExpectedConditions.elementToBeClickable(previousButton)).click();
        waitForPageToLoad();
        waitForProductsToLoad();
    }

    public boolean isNextButtonEnabled() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(nextButton)).isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    // New Methods for Additional Test Cases
    public void clickFirstProduct() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productNames));
        if (!productNames.isEmpty()) {
            wait.until(ExpectedConditions.elementToBeClickable(productNames.get(0))).click();
            waitForPageToLoad();
        } else {
            throw new RuntimeException("No products available to click on");
        }
    }

    public String[] getProductDetails() {
        wait.until(ExpectedConditions.visibilityOf(productDetailName));
        wait.until(ExpectedConditions.visibilityOf(productDetailPrice));
        wait.until(ExpectedConditions.visibilityOf(productDetailDescription));
        String name = productDetailName.getText().trim();
        String price = productDetailPrice.getText().trim();
        String description = productDetailDescription.getText().trim();
        return new String[]{name, price, description};
    }

    public void refreshPage() {
        driver.navigate().refresh();
        waitForPageToLoad();
        waitForProductsToLoad();
    }

    public boolean isNoProductsMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(noProductsMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private void waitForProductsToLoad() {
        int attempts = 0;
        while (attempts < 3) {
            try {
                wait.until(ExpectedConditions.visibilityOfAllElements(productNames));
                return;
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                attempts++;
                System.out.println("StaleElementReferenceException caught in waitForProductsToLoad (Attempt " + attempts + "): " + e.getMessage());
                waitForPageToLoad();
            }
        }
        throw new RuntimeException("Failed to wait for products to load after 3 attempts due to StaleElementReferenceException");
    }

    private void waitForPageToLoad() {
        try {
            Thread.sleep(500); // تأخير صغير لضمان تحميل الصفحة
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        wait.until(driver -> ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("return document.readyState").equals("complete"));
    }
}