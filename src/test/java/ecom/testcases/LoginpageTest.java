package ecom.testcases;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ecom.base.Testbasepage;
import ecom.pages.Loginpage;
import ecom.utils.ExcelDataUtil;

public class LoginpageTest extends Testbasepage {

    Loginpage loginPage;
    public WebDriver driver;

    @BeforeMethod
    public void setup() throws IOException, InterruptedException {
        driver = initializeBrowserAndOpenApplicationURL();
        loginPage = new Loginpage(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(priority = 1, dataProvider = "validCredentialsSupplier")
    public void verifyLoginWithValidCredentials(String username, String password) {
        loginPage.login(username, password);
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            Assert.assertEquals(alertText, "user logged in", "Alert text does not match");
            alert.accept();
        } catch (NoAlertPresentException e) {
            Assert.fail("Expected alert was not present");
        }
    }

    @DataProvider(name = "validCredentialsSupplier")
    public Object[][] supplyTestData() throws IOException {
        return ExcelDataUtil.getTestDataFromExcel("Sheet1");
    }

    @Test(priority = 2)
    public void verifyLoginWithInvalidCredentials() {
        loginPage.login(ExcelDataUtil.generateEmailWithTimeStamp(), dataProp.getProperty("invalidPassword"));
        Assert.assertTrue(
            loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")),
            "Expected warning message is not displayed"
        );
    }

    @Test(priority = 3)
    public void verifyLoginWithInvalidEmailAndValidPassword() {
        loginPage.login(ExcelDataUtil.generateEmailWithTimeStamp(), dataProp.getProperty("validPassword"));
        Assert.assertTrue(
            loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")),
            "Expected warning message is not displayed"
        );
    }

    @Test(priority = 4)
    public void verifyLoginWithValidEmailAndInvalidPassword() {
        loginPage.login(dataProp.getProperty("validEmail"), dataProp.getProperty("invalidPassword"));
        Assert.assertTrue(
            loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")),
            "Expected warning message is not displayed"
        );
    }

    @Test(priority = 5)
    public void verifyLoginWithoutProvidingCredentials() {
        loginPage.Loginclick();
        Assert.assertTrue(
            loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")),
            "Expected warning message is not displayed"
        );
    }
    @AfterClass
    public void afterClass() {
        extent.flush();
    }
}
