package steps;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import webpages.LoginPage;
import webpages.SignupPage;

public class SignupSteps{

    SignupPage signupPage = new SignupPage();
    LoginPage loginPage = new LoginPage();

    public void verifyAllMandatoryFields(WebDriver driver, SoftAssert softAssert) {
        loginPage.clickOnTheCreateAnAccountLink(driver);
        signupPage.clickOnSignupButton(driver);
        softAssert.assertEquals(signupPage.getFullNameValidationMessage(driver), "Full name is required.");
        softAssert.assertEquals(signupPage.getCompanyNameValidationMessage(driver), "Company name is required.");
        softAssert.assertEquals(signupPage.getEmailidValidationMessage(driver), "Email ID is required.");
        softAssert.assertEquals(signupPage.getPasswordValidationMessage(driver), "Password is required.");
    }

    public void fillSignupDeatils(WebDriver driver, SoftAssert softAssert, String fullname, String companyName,
                                  String country, String email, String password) {
        signupPage.enterFullName(driver, fullname);
        signupPage.enterCompanyAndOrganization(driver, companyName);
        signupPage.selectCountry(driver, country);
        signupPage.enterEmail(driver, email);
        signupPage.enterPassword(driver, password);
        signupPage.clickOnSignupButton(driver);
        signupPage.isThanksMessageAfterRegisterDisplayed(driver,softAssert);
    }

    public void fillSignupDeatilsWithRegisterAccount(WebDriver driver, SoftAssert softAssert, String fullname, String companyName,
                                                     String country, String email, String password) {
        loginPage.clickOnTheCreateAnAccountLink(driver);
        signupPage.enterFullName(driver, fullname);
        signupPage.enterCompanyAndOrganization(driver, companyName);
        signupPage.selectCountry(driver, country);
        signupPage.enterEmail(driver, email);
        signupPage.enterPassword(driver, password);
        signupPage.clickOnSignupButton(driver);
        signupPage.verifyMessageAlreadyRegisterBythisEmail(driver,softAssert);
    }

}
