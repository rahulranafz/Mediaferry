package steps;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import webpages.HomePage;
import webpages.LoginPage;

public class HomeSteps {

    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();

    public void login(WebDriver driver, SoftAssert softAssert, String username, String password) {
        loginPage.enterUsername(driver, username);
        loginPage.enterPassword(driver, password);
        loginPage.clickOnLoginButton(driver);
        loginPage.verifyLogin(driver, softAssert);
    }

    public void createNewProject(WebDriver driver, SoftAssert softAssert, String pName, String campaign, String brandname, String creativeLevel, String filePath, String priority, String projectowner, String instructions, String team, String projectwidth, String projectheight) throws Exception {
        homePage.getProjectCountBefore(driver);
        homePage.clickOnCreateNewProject(driver);
        homePage.enterProjectName(driver, pName);
        homePage.enterCampaign(driver, campaign);
        homePage.enterbrandName(driver, brandname);
        homePage.selectCreativeLevel(driver, creativeLevel);
        homePage.fileUpload(driver, filePath);
        homePage.selectPriority(driver, priority);
        homePage.enterprojectOwner(driver, projectowner);
        homePage.enterInstructions(driver, instructions);
        homePage.enterTeam(driver, team);
        homePage.selectJobType(driver);
        homePage.selectProjectSubType(driver);
        homePage.selectJobCategory(driver);
        homePage.selectColumns(driver);
        homePage.enterWidth(driver, projectwidth);
        homePage.enterHeight(driver, projectheight);
        homePage.selectProductionStatus(driver);
        homePage.clickOnSubmitButton(driver);
        homePage.verifyJobUploadedSuccessfully(driver, softAssert);
        homePage.getProjectCountAfter(driver);
        homePage.verifyProjectCount(driver, softAssert);
    }

    public String createNewProjectWithoutUpload(WebDriver driver, SoftAssert softAssert, String projectName, String campaign, String brandname, String creativeLevel, String priority, String projectowner, String instructions, String team, String projectwidth, String projectheight) throws Exception {
        homePage.getProjectCountBefore(driver);
        homePage.clickOnCreateNewProject(driver);
        projectName = homePage.enterProjectName(driver, projectName);
        homePage.enterCampaign(driver, campaign);
        homePage.enterbrandName(driver, brandname);
        homePage.selectCreativeLevel(driver, creativeLevel);
        homePage.selectPriority(driver, priority);
        homePage.enterprojectOwner(driver, projectowner);
        homePage.enterInstructions(driver, instructions);
        homePage.enterTeam(driver, team);
        homePage.selectJobType(driver);
        homePage.selectProjectSubType(driver);
        homePage.selectJobCategory(driver);
        homePage.selectColumns(driver);
        homePage.enterWidth(driver, projectwidth);
        homePage.enterHeight(driver, projectheight);
        homePage.selectProductionStatus(driver);
        homePage.clickOnSubmitButton(driver);
        homePage.verifyJobUploadedSuccessfully(driver, softAssert);
        homePage.getProjectCountAfter(driver);
        homePage.verifyProjectCount(driver, softAssert);
        return projectName;
    }


}

