package tests;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import BaseClasses.Driver;
import steps.LoginSteps;
import steps.SignupSteps;
import utilities.MailReader;
import utilities.UtilityMethods;

public class SignupTests {
    Driver driverObj = new Driver();
    WebDriver driver = null;
    SignupSteps signupSteps = new SignupSteps();
    UtilityMethods utilityMethods = new UtilityMethods();
    LoginSteps loginSteps = new LoginSteps();
    SoftAssert softAssert = new SoftAssert();
    MailReader mailReader = new MailReader();
    private String userName = null;
    private String activationUrl = null;

    @BeforeTest
    public void start() throws IOException {
        driver = driverObj.createDriver();
        driver.get(driverObj.getUrl());
    }

    @Test(priority = 0)
    public void verifyMandatoryFieldsTest() {
        signupSteps.verifyAllMandatoryFields(driver, softAssert);
    }

    @Test(priority = 1)
    public void signupWithValidDeatils() throws InterruptedException {

        userName = "qa.gaurav239+" + utilityMethods.randomStringGenrator(6) + "@gmail.com";
        signupSteps.fillSignupDeatils(driver, softAssert, "test full name", "test company", "IN", userName,
                driverObj.getSignupPassword());
        Thread.sleep(10000);
        activationUrl = mailReader.FetchMails("imap.gmail.com", "qa.gaurav239@gmail.com", "result1029",
                "Welcome to MediaFerry!");
        driver.get(activationUrl);
        System.out.println("**********************************************");
        loginSteps.login(driver, softAssert, userName, driverObj.getSignupPassword());
        loginSteps.logout(driver, softAssert);
    }

    @Test(priority = 2)
    public void signupWithExistingAccount() {
        signupSteps.fillSignupDeatilsWithRegisterAccount(driver, softAssert, "test full name", "test company", "IN",
                userName, driverObj.getSignupPassword());
        softAssert.assertAll();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}
