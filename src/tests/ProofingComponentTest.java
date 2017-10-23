package tests;

import BaseClasses.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import steps.*;
import utilities.UtilityMethods;

import java.io.IOException;

public class ProofingComponentTest {

    Driver driverObj = new Driver();
    WebDriver driver = null;
    LoginSteps loginSteps = new LoginSteps();
    SoftAssert softAssert = new SoftAssert();
    UtilityMethods utilityMethods = new UtilityMethods();
    HomeSteps homeSteps = new HomeSteps();
    ActiveProjectsSteps activeProjectsSteps = new ActiveProjectsSteps();
    CreateNewProjectSteps createNewProjectSteps = new CreateNewProjectSteps();

    String projectName = null;
    int projectCountBefore;
    int projectCountAfter;

    @BeforeTest
    public void start() throws IOException {
        driver = driverObj.createDriver();
        driver.get(driverObj.getUrl());
    }

    @Test(priority = 1)
    public void creatingNewProject() throws Exception {
        loginSteps.login(driver, softAssert, driverObj.getUsername(), driverObj.getPassword());
        homeSteps.waitingForHeaderLnksSpinner(driver);
        //projectName = "My Test Project  795_2017-10-23";
        projectName = utilityMethods.createUniqueProjectName(driverObj.getProjectName());
        projectCountBefore = homeSteps.projectCountBefore(driver);
        homeSteps.creatingNewProject(driver);
        createNewProjectSteps.fillingDetails(driver, projectName, driverObj.getCampaign(), driverObj.getBrandName(), driverObj.getCreativeLevel(), driverObj.getFilePath(), driverObj.getPriority(), driverObj.getProjectOwner(), driverObj.getInstructions(), driverObj.getTeam(), driverObj.getWidth(), driverObj.getHeight());
        createNewProjectSteps.submittingJob(driver,softAssert, projectName);
        projectCountAfter = homeSteps.projectCountAfter(driver);
        homeSteps.verifyProjectCount(driver, softAssert, projectCountBefore+1, projectCountAfter);
    }

    @Test(priority = 2)
    public void editingProject() throws InterruptedException {
        /*homeSteps.goToActiveProjectsPage(driver);
        homeSteps.waitingForConstantLnksSpinner(driver);*/
        activeProjectsSteps.selectProjectForEdit(driver, softAssert, projectName);
        createNewProjectSteps.changeProductionStatus(driver);
        createNewProjectSteps.uploadFinishedArtwork(driver, softAssert, driverObj.getFilePath(), "The selected files has been uploaded successfully.");
        createNewProjectSteps.saveEditedDetails(driver);
    }

    @Test(priority = 3)
    public void openProofingComponents() throws InterruptedException {
        createNewProjectSteps.openProofWindowWhileEditJob(driver);
        homeSteps.goToActiveProjectsPage(driver);
        homeSteps.waitingForConstantLnksSpinner(driver);
        activeProjectsSteps.openProofingWindowSteps(driver, softAssert, projectName);
    }

    //@AfterTest
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}
