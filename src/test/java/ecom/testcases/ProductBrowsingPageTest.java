package ecom.testcases;

import ecom.base.Testbasepage;
import ecom.pages.ProductBrowsingPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class ProductBrowsingPageTest extends Testbasepage {

    private WebDriver driver;
    private ProductBrowsingPage productBrowsingPage;

    @BeforeClass
    public void setUpClass() throws IOException, InterruptedException {
        // Initialize WebDriver and open the application URL
        driver = initializeBrowserAndOpenApplicationURL();
    }

    @BeforeMethod
    public void setUp() {
        // Initialize ProductBrowsingPage using the constructor
        productBrowsingPage = new ProductBrowsingPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser after each test method
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(priority = 1)
    public void testAreProductsDisplayed() {
        // Validate if the products are displayed
        Assert.assertTrue(productBrowsingPage.arePhonesProductsDisplayed(), "Phones are not displayed.");
    }

    @Test(priority = 2)
    public void testNavigateToCategory() {
        // Navigate to the "Laptops" category and validate
        productBrowsingPage.navigateToCategory("Laptops");
        // Add an assertion or validation to check if the navigation was successful
        Assert.assertTrue(driver.getCurrentUrl().contains("laptops"), "Failed to navigate to Laptops category.");
    }

    @AfterClass
    public void tearDownClass() {
        // Clean up resources after all tests
        if (driver != null) {
            driver.quit();
        }
    }
}