package ecom.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ecom.base.Testbasepage;
import ecom.pages.AddtoCartPage;
import ecom.pages.CheckoutProcess;
import ecom.pages.Loginpage;
import ecom.pages.ProductBrowsingPage;

public class CheckoutProcessTest extends Testbasepage {
    public WebDriver driver;
    private ProductBrowsingPage productBrowsingPage;
    private AddtoCartPage addtoCartPage;
    private CheckoutProcess checkoutProcess;

    @BeforeClass
    public void setUpClass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.yourwebsite.com"); // Replace with actual URL

        PageFactory.initElements(driver, Loginpage.class);
        productBrowsingPage = PageFactory.initElements(driver, ProductBrowsingPage.class);
        addtoCartPage = PageFactory.initElements(driver, AddtoCartPage.class);
        checkoutProcess = PageFactory.initElements(driver, CheckoutProcess.class);
    }

    @Test
    public void testCheckoutWithItemsInCart() {
        // Add items to cart
        productBrowsingPage.navigateToCategory("Phones"); // Replace with actual category
        addtoCartPage.addToCart(); // Add product to cart

        productBrowsingPage.navigateToCategory("Laptops"); // Replace with actual category
        addtoCartPage.addToCart(); // Add another product to cart
        
        // Navigate to cart and proceed to checkout
        checkoutProcess.clickPlaceOrder(); // Assuming this navigates to the checkout page

        // Fill out the checkout form
        checkoutProcess.fillCheckoutForm("John Doe", "USA", "New York", "1234567812345678", "July", "2024");
        
        // Click purchase
        checkoutProcess.clickPurchase();

        // Verify checkout success
        Assert.assertTrue(checkoutProcess.isOrderSuccessful(), "Checkout was not successful.");
    }

    @Test
    public void testCheckoutWithoutItemsInCart() {
        // Navigate to cart and attempt checkout
        checkoutProcess.clickPlaceOrder(); // Assuming this navigates to the checkout page

        // Verify empty cart error message
        String errorMessage = checkoutProcess.isEmptyCartErrorMessageDisplayed();
        Assert.assertNotNull(errorMessage, "Empty cart error message is not displayed.");
    }

    @AfterClass
    public void tearDownClass() {
        driver.quit();
    }
}
