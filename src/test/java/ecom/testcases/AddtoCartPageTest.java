package ecom.testcases;

import ecom.pages.AddtoCartPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddtoCartPageTest {

    private WebDriver driver;
    private AddtoCartPage addtoCartPage;

    @BeforeClass
    public void setUpClass() {
        // Initialize the WebDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @BeforeMethod
    public void setUp() {
        // Initialize the AddtoCartPage using PageFactory
        addtoCartPage = PageFactory.initElements(driver, AddtoCartPage.class);
        driver.get("https://www.demoblaze.com/"); // Navigate to the base URL if required
    }

    @Test(priority = 1)
    public void testNavigateToLastPage() {
        // Test navigating to the last page
        addtoCartPage.navigateToLastPage();
        // Add assertions here to verify you are on the last page
       // Assert.assertTrue(((Object) addtoCartPage.getLastProduct()).isDisplayed(), "Failed to navigate to the last page.");
    }

    @Test(priority = 2)
    public void testAddToCart() {
        // Test adding a product to the cart
        addtoCartPage.addToCart();
        // Add assertions here to verify that the product was added to the cart
        // For example, checking the cart contents or a success message
    }

    @AfterClass
    public void tearDownClass() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
