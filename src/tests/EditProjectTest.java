package tests;

import BaseClasses.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import steps.HomeSteps;
import steps.LoginSteps;
import steps.ProjectsSteps;

import java.io.IOException;

public class EditProjectTest {

    Driver driverObj = new Driver();
    WebDriver driver = null;
    LoginSteps loginSteps = new LoginSteps();
    ProjectsSteps projectsSteps = new ProjectsSteps();
    SoftAssert softAssert = new SoftAssert();

    @BeforeTest
    public void start() throws IOException {
        driver = driverObj.createDriver();
        driver.get(driverObj.getUrl());
    }

    @Test(priority = 0)
    public void createNewProject() throws Exception {
        loginSteps.login(driver, softAssert, driverObj.getUsername(), driverObj.getPassword());
        projectsSteps.editProject(driver, softAssert, driverObj.getProjectName(), driverObj.getProjectOwnerNew(),
                driverObj.getPosition(), driverObj.getfpStyle(), driverObj.getTarget(),
                driverObj.getClassificationNew(), driverObj.getOrderValue(), driverObj.getUFilePath());
        loginSteps.logout(driver, softAssert);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
        driver = null;
    }

}
