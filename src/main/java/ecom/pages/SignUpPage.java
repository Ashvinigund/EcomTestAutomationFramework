package ecom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ecom.base.Testbasepage;

public class SignUpPage extends Testbasepage {

    private WebDriver driver;

    @FindBy(xpath = "//a[@id='signin2']")
    private WebElement signupLink;

    @FindBy(id = "sign-username")
    private WebElement usernameField;

    @FindBy(id = "sign-password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[normalize-space()='Sign up']")
    private WebElement signupButton;

    // Initializing the Page Objects
    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickSignupLink() {
        Actions action = new Actions(driver);
        action.moveToElement(signupLink).click().build().perform();
    }

    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickSignupButton() {
        signupButton.click();
    }

    public void signUp(String username, String password) {
        clickSignupLink();
        enterUsername(username);
        enterPassword(password);
        clickSignupButton();
    }
}
