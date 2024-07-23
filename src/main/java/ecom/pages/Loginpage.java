package ecom.pages;
import org.openqa.selenium.interactions.Actions;

import ecom.base.Testbasepage;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Loginpage extends Testbasepage {

	     @FindBy(xpath = "//a[@id='login2']")
	 	   WebElement Loginbtn;
	     
	 	@FindBy(id = "loginusername")
		public
	 	 WebElement loginUsername;
	 	
	 	@FindBy(id = "loginpassword")
		public
	 	 WebElement Password;

	 	@FindBy(xpath = "//button[normalize-space()='Log in']")
		public
	     WebElement Loginbutton;

	 	
	 	 // Constructor to initialize the PageFactory elements and WebDriver instance
	    public Loginpage(WebDriver driver) {
	        this.driver = driver; // Initialize the WebDriver instance
	        PageFactory.initElements(driver, this); // Initialize WebElements using PageFactory
	    }

	    // Method to click on the login button and return the LoginPage instance
	    public Loginpage loginbtn() {
	        Loginbtn.click();
	        return this; // Return the current instance of Loginpage
	    }

	    // Method to perform mouse hover and click on the login button
	    public void Loginclick() {
	        Actions action = new Actions(driver);
	        action.moveToElement(Loginbtn).build().perform();
	        Loginbtn.click();
	    }

	    // Method to perform login with provided username and password
	    public void login(String username, String password) {
	        loginUsername.sendKeys(username);
	        Password.sendKeys(password);
	        Loginbutton.click();
	    }

		public String retrieveEmailPasswordNotMatchingWarningMessageText() {
			// TODO Auto-generated method stub
			return null;
		}
	}
