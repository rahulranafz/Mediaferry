package steps;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import webpages.HomePage;
import webpages.ProjectsPage;
import webpages.LoginPage;

public class ProjectsSteps {

    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();
    ProjectsPage projectsPage = new ProjectsPage();

    public void login(WebDriver driver, SoftAssert softAssert, String username, String password) {
        loginPage.enterUsername(driver, username);
        loginPage.enterPassword(driver, password);
        loginPage.clickOnLoginButton(driver);
        loginPage.verifyLogin(driver, softAssert);
        loginPage.verifyCreateProjectBtn(driver, softAssert);
        loginPage.verifyActiveProjectLocator(driver, softAssert);
    }

    public void editProject(WebDriver driver, SoftAssert softAssert, String pName, String projectownernew, String position, String fpStyle, String target, String classificationNew, String orderValue,String uFilePath) throws InterruptedException, IOException {
        projectsPage.clickOnActiveProjects(driver);
        projectsPage.verifyProjectAndDisplay(driver, softAssert);
        projectsPage.enterProjectNametoFind(driver, pName);
        projectsPage.clickOnViewEditLink(driver);
        //Thread.sleep(3000);
        projectsPage.enterprojectOwner(driver, projectownernew);
        projectsPage.enterPosition(driver, position);
        projectsPage.enterFpStyle(driver, fpStyle);
        projectsPage.enterTarget(driver, target);
        projectsPage.enterClassificationNew(driver, classificationNew);
        projectsPage.selectProofPages(driver);
        projectsPage.selectClassification(driver);
        projectsPage.selectJobSubType(driver);
        projectsPage.enterOrderValue(driver,orderValue);
        projectsPage.clickOnSaveBtn(driver);
        projectsPage.fileUpload(driver, uFilePath);

    }
}

