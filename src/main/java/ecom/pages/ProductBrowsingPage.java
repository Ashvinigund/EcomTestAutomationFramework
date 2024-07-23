package ecom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecom.base.Testbasepage;

import java.util.List;

public class ProductBrowsingPage extends Testbasepage {
    // Locators
    @FindBy(xpath = "//div[@class='col-lg-3']//a[2]")
    private List<WebElement> phones;

    @FindBy(xpath = "//div[@class='col-lg-3']//a[3]")
    private List<WebElement> laptops;

    @FindBy(xpath = "//div[@class='col-lg-3']//a[4]")
    private List<WebElement> monitors;

    @FindBy(xpath = "//a[@id='cat']")
    private List<WebElement> categories;

    public ProductBrowsingPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // Methods
    public boolean arePhonesProductsDisplayed() {
        return !phones.isEmpty();
    }

    public void navigateToCategory(String categoryName) {
        for (WebElement category : categories) {
            if (category.getText().equalsIgnoreCase(categoryName)) {
                category.click();
                break;
            }
        }
    }
}
