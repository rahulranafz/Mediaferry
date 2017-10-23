package webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;
import BaseClasses.BasePage;

public class ActivityFeedPage extends BasePage {
    private By activityHeadingLocator = By.xpath("//div[@id='1']/ul/li/div[1]/div[1]/div[2]/div/span[1]");
    private By viewOrEditLinkLocator = By.xpath("//a[text()='View or Edit']");
    private By fileUploadFormLocator = By.id("fileupload");
    private By fileUploadLocator = By.xpath("//form[@id='fileupload']//input[@id='file']");
    private By fileUploadListLocator = By.id("filesContainer");
    private By uploadBtnFieldLocator = By.xpath("//strong[text()='test050917a2']//../..//a[text()='Upload']");
    private By spinnerLocator=By.id("loadOverlay");
    private By assetNameFieldLocator = By.className("name padding alerthover-1");
    private By closeAssetWindowLocator = By.className("topHdrCrossBtn");
    private By noAssetLblLocator = By.xpath("//span[text()='No asset found!']");

    public void mouseHoverOnActivityHeader(WebDriver driver) {
        Actions action = new Actions(driver);
        waitForElementVisibility(driver, activityHeadingLocator);
        action.moveToElement(driver.findElement(activityHeadingLocator)).build().perform();
    }

    public void clickOnViewOrEditLink(WebDriver driver) {
        waitForElementVisibility(driver, viewOrEditLinkLocator);
        driver.findElement(viewOrEditLinkLocator).click();
    }

    public void uploadFiles(WebDriver driver, String filePath) {
        String fileUploaderPath = getWorkingDirectoryPath()+filePath;
        scrollToElement(driver, fileUploadFormLocator);
        driver.findElement(fileUploadLocator).sendKeys(fileUploaderPath);
    }

    public void verifyUploadedFile(WebDriver driver, SoftAssert softassert) {
        waitForElementVisibility(driver, fileUploadListLocator);
        softassert.assertTrue(driver.findElement(fileUploadListLocator).isDisplayed());
    }

    public void verifyUploadedFileByFeed(WebDriver driver, SoftAssert softassert, String expectedAssetName){
        waitForElementVisibility(driver, assetNameFieldLocator);
        String actualAssetName = driver.findElement(assetNameFieldLocator).getText();
        softassert.assertEquals(actualAssetName, expectedAssetName);

    }

    public void clickOnUploadBtnFromFeedActivity(WebDriver driver, String projectName) {
        System.out.println(projectName);
        String Locator = "//strong[text()='"+ projectName +"']//../..//a[text()='Upload']";
        waitForElementVisibility(driver, spinnerLocator);
        waitForElementInvisibility(driver, spinnerLocator);
        waitForElementVisibility(driver, By.xpath(Locator));
        driver.findElement(By.xpath(Locator)).click();
    }

    public void clickOnCloseAssetWindow(WebDriver driver){
        waitForElementVisibility(driver, closeAssetWindowLocator);
        driver.findElement(closeAssetWindowLocator).click();
    }

}
