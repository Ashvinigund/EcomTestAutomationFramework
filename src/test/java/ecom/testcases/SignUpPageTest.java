package ecom.testcases;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import ecom.base.Testbasepage;
import ecom.pages.SignUpPage;

public class SignUpPageTest extends Testbasepage {

    private SignUpPage signUpPage;

    @BeforeMethod
    public void setUp() throws InterruptedException, IOException {
        // Log.startTestCase("SignUpTest");
        driver = initializeBrowserAndOpenApplicationURL();
        signUpPage = new SignUpPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(priority = 1)
    public void signUpWithValidDetailsTest() {
        signUpPage.clickSignupLink();
        signUpPage.enterUsername("Ashvini");
        signUpPage.enterPassword("@12345");
        signUpPage.clickSignupButton();

        // Add assertions here to verify successful signup
        // For example, you might want to check if a specific element appears or if a success message is shown
        // Assert.assertTrue(condition);
    }

    @Test(priority = 2)
    public void signUpWithInvalidDetailsTest() {
        signUpPage.clickSignupLink();
        signUpPage.enterUsername(""); // Invalid username
        signUpPage.enterPassword("12345"); // Invalid password
        signUpPage.clickSignupButton();

        // Add assertions here to verify signup failure
        // For example, you might want to check if an error message is shown
        // Assert.assertTrue(condition);

        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            Assert.assertEquals(alertText, "This user already exist", "Alert text does not match");
            logger.log(Status.PASS, "Alert text matches: " + alertText);
            alert.accept();
        } catch (Exception e) {
            logger.log(Status.FAIL, "Alert was not present or other error occurred: " + e.getMessage());
        }
    }
}
