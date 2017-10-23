package steps;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import webpages.ActivityFeedPage;
import webpages.HomePage;
import utilities.UtilityMethods;

public class UploadAssetSteps {

    HomePage homePage = new HomePage();
    ActivityFeedPage activityFeedPage=new ActivityFeedPage();
    UtilityMethods utilityMethods=new UtilityMethods();

    public void uploadFile(WebDriver driver, String filePath,SoftAssert softassert) {
        homePage.openFirstActivity(driver);
        activityFeedPage.mouseHoverOnActivityHeader(driver);
        activityFeedPage.clickOnViewOrEditLink(driver);
        activityFeedPage.uploadFiles(driver,filePath);
        //activityFeedPage.verifyUploadedFileByFeed(driver,softassert, "Test.pdf");
    }

    public void uploadFileByFeedActivity(WebDriver driver, String filePath, String projectName,SoftAssert softassert) throws InterruptedException {
        homePage.clickOnHomeMenu(driver);
        activityFeedPage.clickOnUploadBtnFromFeedActivity(driver,projectName);
        utilityMethods.swichToWindow(driver, 1);
        activityFeedPage.uploadFiles(driver,filePath);
        Thread.sleep(4000);
        activityFeedPage.clickOnCloseAssetWindow(driver);
        //activityFeedPage.verifyUploadedFileByFeed(driver, softassert, "Test.pdf");
    }
}