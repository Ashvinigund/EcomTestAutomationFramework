package ecom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecom.base.Testbasepage;

public class LogoutProcess extends Testbasepage {
    
    @FindBy(xpath = "//a[@id='logout2']")
    private WebElement logoutBtn;

    // Constructor to initialize the PageFactory elements
    public LogoutProcess(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
  
    // Method to perform logout
    public void logout() {
        logoutBtn.click();
    }
}
