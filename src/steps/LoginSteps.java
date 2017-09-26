package steps;

import BaseClasses.BasePage;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import webpages.LoginPage;

public class LoginSteps extends BasePage {

    LoginPage loginPage = new LoginPage();

    public void login(WebDriver driver, SoftAssert softAssert, String username, String password) {
        loginPage.enterUsername(driver, username);
        loginPage.enterPassword(driver, password);
        loginPage.clickOnLoginButton(driver);
        loginPage.verifyLogin(driver, softAssert);
    }

    public void logout(WebDriver driver, SoftAssert softAssert) {
        loginPage.mouseHoverOnProfileName(driver);
        //loginPage.clickOnProfileAdjacentDownIcon(driver);
        loginPage.clickOnLogout(driver);
        loginPage.verifyLogout(driver, softAssert);
    }

    public void invalidLogin(WebDriver driver, SoftAssert softAssert, String invalidUsername, String password) {
        loginPage.enterUsername(driver, invalidUsername);
        loginPage.enterPassword(driver, password);
        loginPage.clickOnLoginButton(driver);
        loginPage.verifyInvalidLogin(driver, softAssert);
    }

}
