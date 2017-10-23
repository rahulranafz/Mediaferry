package webpages;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;

import utilities.JobIdManager;

import BaseClasses.BasePage;

public class ProjectsPage extends BasePage {


    private By searchProjectNLocator=By.id("f_project");
    private By filterButtonLocator = By.xpath("//button[contains(.,'Filter')]");
    private By linkLocator= By.xpath("//img[@src='https://devso.mediaferry.com/mf-s40qa/sitetheme_new/image/Overdue-Icon.gif']");
    private By viewEditLocator=By.linkText("View or Edit");
    private By positionLocator=By.id("metadata_1");
    private By fpStyleLocator= By.xpath("//input[@id='metadata_2']");
    private By targetLocator= By.xpath("//input[@id='metadata_8']");

    private By activeProjectsLocator=By.xpath("//div[@id='SuccessFul']//div[contains(@class, 'dashboard-stat') and contains(., 'Active projects')]");
    private By saveBtnLocator= By.id("save_view_edit");
    private By dropFileLocator=By.xpath(".//*[@id='file']");
    private By proofPagestLocator = By.xpath("//select[@id='metadata_13']");
    private By classificationLocator = By.xpath("//select[@id='account_WebSubTypeId']");
    private By jobSubTypeLocator= By.xpath("//select[@id='account_SizeDefinitionId']");
    private By orderValueLocator= By.xpath("//input[@id='metadata_14']");
    private By projectOwner = By.xpath("//*[@id='s2id_account_SalesRep']/a/span[1]");
    private By projectOwnerInput=By.xpath(".//*[@id='select2-drop']/div/input");
    private By projectOwnerResult=By.xpath("//*[@id='select2-drop']/ul/li[1]/div");

    public void verifyProjectAndDisplay(WebDriver driver,SoftAssert softAssert)
    {
        waitForElementVisibility(driver, searchProjectNLocator);
        softAssert.assertTrue(driver.findElement(searchProjectNLocator).isDisplayed());
    }

    public String enterProjectNametoFind(WebDriver driver, String pName) {

        HomePage homePage = new HomePage();
        System.out.println(JobIdManager.getInstance().findJobId(pName));
        String jobtoFind = JobIdManager.getInstance().findJobId(pName);
        waitForElementVisibility(driver, searchProjectNLocator);
        driver.findElement(searchProjectNLocator).sendKeys(jobtoFind);
        driver.findElement(searchProjectNLocator).sendKeys(Keys.RETURN);

        Boolean readyStateComplete = false;
        while (!readyStateComplete)
        {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("window.scrollTo(0, document.body.offsetHeight)");
            readyStateComplete = ((String) executor.executeScript("return document.readyState")).equals("complete");
            executor.executeScript("window.scrollTo(0, 0)");
        }

        return jobtoFind;
    }

    public void clickOnActiveProjects(WebDriver driver)
    {
        javaScriptClick(driver, activeProjectsLocator);

    }

    public void clickOnViewEditLink(WebDriver driver) {

        Actions action = new Actions(driver);
        WebElement we = driver.findElement(linkLocator);
        waitForElementVisibility(driver, linkLocator);
        action.moveToElement(we).build().perform();

        waitForElementVisibility(driver, viewEditLocator);
        driver.findElement(viewEditLocator).click();
        System.out.println("clicked link");

        Boolean readyStateComplete = false;
        while (!readyStateComplete)
        {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("window.scrollTo(0, document.body.offsetHeight)");
            readyStateComplete = ((String) executor.executeScript("return document.readyState")).equals("complete");
        }
    }

    public void enterPosition(WebDriver driver, String position) {
        scrollToElement(driver,positionLocator);
        driver.findElement(positionLocator).sendKeys(position);
    }

    public void enterFpStyle(WebDriver driver, String fpStyle) {
        scrollToElement(driver,fpStyleLocator);
        driver.findElement(fpStyleLocator).sendKeys(fpStyle);
    }

    public void enterTarget(WebDriver driver, String target) {
        scrollToElement(driver,targetLocator);
        driver.findElement(targetLocator).sendKeys(target);
    }

    public void enterClassificationNew(WebDriver driver, String classificationNew) {
        scrollToElement(driver,targetLocator);
        driver.findElement(targetLocator).sendKeys(classificationNew);
    }

    public void selectProofPages(WebDriver driver)
    {
        waitForElementVisibility(driver, proofPagestLocator);
        selectDropDownElement(driver,proofPagestLocator,"1","Text");
    }

    public void selectClassification(WebDriver driver)
    {
        waitForElementVisibility(driver, classificationLocator);
        selectDropDownElement(driver,classificationLocator,"1","Index");
    }

    public void selectJobSubType(WebDriver driver)
    {
        waitForElementVisibility(driver, jobSubTypeLocator);
        selectDropDownElement(driver,jobSubTypeLocator,"0","Index");
    }

    public void enterOrderValue(WebDriver driver, String orderValue) {
        scrollToElementWithoutWait(driver,orderValueLocator);
        driver.findElement(orderValueLocator).sendKeys(orderValue);
    }

    public void fileUpload(WebDriver driver, String ufilePath) throws InterruptedException, IOException
    {
        String fileUploaderPath = getWorkingDirectoryPath()+ufilePath;
        scrollToElementWithoutWait(driver,dropFileLocator);
        driver.findElement(dropFileLocator).sendKeys(fileUploaderPath);
    }

    public void clickOnSaveBtn(WebDriver driver) {
        javaScriptClick(driver,saveBtnLocator);
        //scrollToElement(driver,saveBtnLocator);
        //waitForElementVisibility(driver, saveBtnLocator);
        //	driver.findElement(saveBtnLocator).click();
    }

    public void enterprojectOwner(WebDriver driver, String projectowner) {
        waitForElementVisibility(driver, projectOwner);
        driver.findElement(projectOwner).click();
        waitForElementVisibility(driver, projectOwnerInput);
        driver.findElement(projectOwnerInput).sendKeys(projectowner);
        waitForElementVisibility(driver, projectOwnerResult);
        driver.findElement(projectOwnerResult).click();
    }

}
