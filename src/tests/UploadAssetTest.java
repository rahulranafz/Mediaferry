package tests;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import BaseClasses.Driver;
import webpages.HomePage;
import steps.HomeSteps;
import steps.LoginSteps;
import steps.UploadAssetSteps;
import utilities.UtilityMethods;
public class UploadAssetTest {

    public class UploadTest {
        Driver driverObj = new Driver();
        WebDriver driver = null;
        LoginSteps loginSteps = new LoginSteps();
        UploadAssetSteps uploadAssetSteps = new UploadAssetSteps();
        SoftAssert softAssert = new SoftAssert();
        HomeSteps homeSteps = new HomeSteps();
        UtilityMethods utilityMethods = new UtilityMethods();
        String projectName = null;
        HomePage homePage=new HomePage();

        @BeforeTest
        public void start() throws IOException {
            driver = driverObj.createDriver();
            driver.get(driverObj.getUrl());
        }

        @Test(priority = 0)
        public void uploadFileByUploadBtnAtFeedActivity() throws Exception {
            loginSteps.login(driver, softAssert, driverObj.getUsername(), driverObj.getPassword());
            projectName = driverObj.getProjectName() + utilityMethods.randomStringGenrator(6);
            projectName = homeSteps.createNewProjectWithoutUpload(driver, softAssert, projectName, driverObj.getCampaign(),
                    driverObj.getBrandName(), driverObj.getCreativeLevel(), driverObj.getPriority(),
                    driverObj.getProjectOwner(), driverObj.getInstructions(), driverObj.getTeam(), driverObj.getWidth(),
                    driverObj.getHeight());
            System.out.println(projectName);
            uploadAssetSteps.uploadFileByFeedActivity(driver, driverObj.getFilePath(), projectName, softAssert);
            loginSteps.logout(driver, softAssert);
        }

        @Test(priority = 1)
        public void uploadFileByEditingActivityFeed() throws Exception {
            loginSteps.login(driver, softAssert, driverObj.getUsername(), driverObj.getPassword());
            homeSteps.createNewProject(driver, softAssert, driverObj.getProjectName(), driverObj.getCampaign(),
                    driverObj.getBrandName(), driverObj.getCreativeLevel(), driverObj.getFilePath(),
                    driverObj.getPriority(), driverObj.getProjectOwner(), driverObj.getInstructions(), driverObj.getTeam(),
                    driverObj.getWidth(), driverObj.getHeight());
            loginSteps.logout(driver, softAssert);
            uploadAssetSteps.uploadFile(driver, driverObj.getFilePath(), softAssert);
        }

        @AfterTest
        public void tearDown() {
            driver.quit();
            driver = null;
        }
    }

}
