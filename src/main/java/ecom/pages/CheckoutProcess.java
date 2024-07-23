package ecom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecom.base.Testbasepage;

public class CheckoutProcess extends Testbasepage {
    
    // Locators
    @FindBy(id = "cartur")
    private WebElement cartBtn;

    @FindBy(xpath = "//button[normalize-space()='Place Order']")
    private WebElement placeOrderBtn;

    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "country")
    private WebElement countryField;

    @FindBy(id = "city")
    private WebElement cityField;

    @FindBy(id = "card")
    private WebElement cardField;

    @FindBy(id = "month")
    private WebElement monthField;

    @FindBy(id = "year")
    private WebElement yearField;

    @FindBy(xpath = "//button[normalize-space()='Purchase']")
    private WebElement purchaseBtn;

    @FindBy(id = "successMessage")
    private WebElement successMessage;

    // Constructor
    public CheckoutProcess(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // Fill out the checkout form
    public void fillCheckoutForm(String name, String country, String city, String creditCard, String month, String year) {
        nameField.sendKeys(name);
        countryField.sendKeys(country);
        cityField.sendKeys(city);
        cardField.sendKeys(creditCard);
        monthField.sendKeys(month);
        yearField.sendKeys(year);
    }

    // Click the Place Order button
    public void clickPlaceOrder() {
        placeOrderBtn.click();
    }

    // Click the Purchase button
    public void clickPurchase() {
        purchaseBtn.click();
    }

    // Verify if the order was successful
    public boolean isOrderSuccessful() {
        try {
            return successMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

	public String isEmptyCartErrorMessageDisplayed() {
		// TODO Auto-generated method stub
		return null;
	}
}
