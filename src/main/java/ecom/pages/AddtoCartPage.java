package ecom.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecom.base.Testbasepage;

public class AddtoCartPage extends Testbasepage {

    // Locators
    @FindBy(xpath="//a[normalize-space()='Samsung galaxy s6']")
    private WebElement mobile;

    @FindBy(xpath="//a[normalize-space()='Sony vaio i5']")
    private WebElement laptop;
    
    @FindBy(xpath="//a[normalize-space()='Add to cart']")
    private WebElement addToCartBtn;
    
    @FindBy(id = "next2")
    private WebElement nextButton;
    
    @FindBy(xpath = "//a[normalize-space()='MacBook Pro']")
    private WebElement lastProduct;

    // Constructor to initialize Page Objects
    public AddtoCartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // Method to navigate through pages to reach the last page
    public void navigateToLastPage() {
        while (true) {
            try {
                if (nextButton.isDisplayed()) {
                    nextButton.click();
                } else {
                    break; // No more "Next" button to click
                }
            } catch (Exception e) {
                break; // Handle the case where the "Next" button is not clickable
            }
        }
    }
    
    // Method to add an item to the cart and handle the alert
    public void addToCart() {
        addToCartBtn.click();
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (Exception e) {
            System.err.println("No alert present or unable to handle alert: " + e.getMessage());
        }
    }

	public Object getLastProduct() {
		// TODO Auto-generated method stub
		return null;
	}
}
