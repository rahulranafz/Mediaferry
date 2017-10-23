package steps;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import pages.ActivityFeedPage;
import pages.CreateNewProjectPage;
import utilities.UtilityMethods;

public class UploadDownloadAssetsSteps {

    CreateNewProjectPage createNewProjectPage = new CreateNewProjectPage();
    CreateNewProjectSteps createNewProjectSteps = new CreateNewProjectSteps();
    ActivityFeedPage activityFeedPage=new ActivityFeedPage();
    ActivityFeedSteps activityFeedSteps = new ActivityFeedSteps();
    UtilityMethods utilityMethods=new UtilityMethods();
    HomeSteps homeSteps = new HomeSteps();

    public void uploadFile(WebDriver driver, String filePath,SoftAssert softassert) {
        activityFeedPage.uploadFilesOnSameWindow(driver,softassert, filePath);
        createNewProjectPage.clickOnSaveBtn(driver);
    }

    public void uploadingAssetQueryWindow(WebDriver driver, String filePath, SoftAssert softAssert) throws InterruptedException {
        activityFeedPage.uploadFilesOnQueryWindow(driver, softAssert, filePath);
    }

    public void uploadFileOnProofingWindow(WebDriver driver, String filePath,SoftAssert softAssert) throws InterruptedException {
        activityFeedPage.uploadFilesWhileEditing(driver,softAssert, filePath);
        createNewProjectSteps.acceptingUpload(driver);
        createNewProjectPage.clickOnSaveBtn(driver);
        createNewProjectPage.clickOnProofingButton(driver);
        utilityMethods.swichToWindow(driver, 1);
        createNewProjectSteps.draggingStickyNotesOnUploadedAsset(driver);
        utilityMethods.swichToWindow(driver, 2);
        activityFeedPage.uploadFilesOnNewWindow(driver,softAssert,filePath);
        utilityMethods.swichToWindow(driver, 1);
        createNewProjectSteps.closePopingWindow(driver);
        utilityMethods.swichToWindow(driver,0);
    }

    public void uploadFileByFeedActivity(WebDriver driver, String filePath, String projectName,SoftAssert softAssert) throws InterruptedException {
        homeSteps.goToHomeLnkPage(driver);
        activityFeedSteps.uploadAssetsFromUploadFeedActivity(driver,projectName);
        utilityMethods.swichToWindow(driver, 1);
        activityFeedPage.uploadFilesOnNewWindow(driver,softAssert, filePath);
        utilityMethods.swichToWindow(driver,0);
    }

    public void uploadFileFromActivityFeedPage(WebDriver driver,SoftAssert softAssert, String filePath) throws InterruptedException {
        utilityMethods.swichToWindow(driver, 1);
        activityFeedPage.uploadFilesOnNewWindow(driver,softAssert,filePath);
        utilityMethods.swichToWindow(driver,0);
    }
}
