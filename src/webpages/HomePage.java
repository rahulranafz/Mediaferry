package webpages;

import BaseClasses.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import utilities.JobIdManager;

import java.io.IOException;
import java.util.Random;

public class HomePage extends BasePage{

  private By getCurrentProjectCount = By.id("tra");
  private By createNewProjectBtnLocator=By.xpath("//div[contains(@class, 'dashboard-stat') and contains(., ' Create new project')]");
  private By projectNameLocator=By.id("JobNumberId");
  private By selectCampaign=By.xpath("//div[@id='s2id_select_campaign']/a");
  private By campaignInput=By.xpath("//div[@id='select2-drop']/div/input");
  private By campaignResult=By.xpath("//span[text()='SFA2212178']");

  private By selectbrandName=By.xpath("//div[@id='s2id_account_Advertiser']/a");
  private By brandNameInput=By.xpath("//div[@id='select2-drop']/div/input");
  private By brandNameResult=By.xpath("//div[@id='select2-drop']/ul/li[2]/div");

  private By creativeLevelSelectLocator = By.xpath("//select[@id='mf_creative_level']");
  private By prioritySelectLocator = By.id("account_PriorityId");

  private By fileUploadLocator = By.id("file_attachedId1");

  private By projectOwner = By.xpath("//div[@id='s2id_account_SalesRep']/a");
  private By projectOwnerInput=By.xpath("//div[@id='select2-drop']/div/input");
  private By projectOwnerResult=By.xpath("//div[@id='select2-drop']/ul/li[1]/div");

  private By instructionsLocator=By.id("copyinstructions");
  private By teamInputLocator = By.id("metadata_2");
  private By jobTypeSelect = By.id("account_JobTypeId");
  private By projectSubTypeSelect = By.id("account_JobSubTypeId");
  private By jobCategorySelect = By.id("mf_Jobcategory");
  private By columnsSelect = By.id("mf_columns");
  private By width=By.id("coltypeid");
  private By height=By.id("HeightId");
  private By productionStatusSelect = By.id("mf_ProductionStatus");
  private By jobUploadedSuccessfully = By.id("successfulUpload");

  private By submit=By.id("btn-submit");
  private static Integer countBefore = null;
  private static Integer countAfter = null;
  private By searchProjectNLocator=By.id("f_project");
  private By createdFirstActivityFeedLocator = By.xpath("//div[@id='scroller']/ul/li[1]/div[1]/div/div[2]/div/span");
  private By homeMenuLocator = By.id("Home");

  public void openFirstActivity(WebDriver driver) {
    waitForElementClickable(driver, createdFirstActivityFeedLocator);
    driver.findElement(createdFirstActivityFeedLocator).click();
  }

  public void clickOnHomeMenu(WebDriver driver) {
    executeJSCommand(driver,"loadpageContentSecond('Home', '59', 'JP')");
  }

  public int getProjectCountBefore(WebDriver driver) {
    waitForElementVisibility(driver, getCurrentProjectCount);
    String projectCountBefore=driver.findElement(getCurrentProjectCount).getText();
    countBefore=Integer.parseInt(projectCountBefore);
    return countBefore;
  }

  public void clickOnCreateNewProject(WebDriver driver) throws Exception  {
    javaScriptClick(driver,createNewProjectBtnLocator);
  }

  public String enterProjectName(WebDriver driver, String pName) {

    waitForElementVisibility(driver, projectNameLocator);
    Random rand = new Random();
    int  projectSuffix = rand.nextInt(1000) + 1;
    String projectName = pName+ "  " + projectSuffix + "_"+ getCurrentDate();
    driver.findElement(projectNameLocator).sendKeys(projectName);
    // update the project name in singleton list
    JobIdManager.getInstance().putJobId(pName, projectName);
    return projectName;

  }

  public void enterCampaign(WebDriver driver, String campaign) {
    waitForElementVisibility(driver, selectCampaign);
    driver.findElement(selectCampaign).click();
    waitForElementVisibility(driver, campaignInput);
    driver.findElement(campaignInput).sendKeys(campaign);
    int searchCampaignResult = driver.findElements(By.xpath("//div[@id='select2-drop']/ul/li")).size();
    for(int i =0 ; i<100; i++) {
            if (searchCampaignResult <= 0)
                searchCampaignResult = driver.findElements(By.xpath("//div[@id='select2-drop']/ul/li")).size();
            else
                break;
    }
    driver.findElement(By.xpath("//div[@id='select2-drop']/ul/li[1]")).click();
  }

  public void enterbrandName(WebDriver driver, String brandname) {
    waitForElementVisibility(driver, selectbrandName);
    driver.findElement(selectbrandName).click();
    waitForElementVisibility(driver, brandNameInput);
    driver.findElement(brandNameInput).sendKeys(brandname);
    //waitForElementVisibility(driver, brandNameResult);
        int countBrandNameResult = driver.findElements(By.xpath("//div[@id='select2-drop']/ul/li")).size();
        for(int i =0 ; i<100; i++) {
            if (countBrandNameResult <= 0)
                countBrandNameResult = driver.findElements(By.xpath("//div[@id='select2-drop']/ul/li")).size();
            else
                break;
        }
        driver.findElement(brandNameResult).click();
    }

  public void fileUpload(WebDriver driver, String filePath) throws InterruptedException, IOException
  {
    String fileUploaderPath = getWorkingDirectoryPath()+filePath;
    scrollToElement(driver,fileUploadLocator);
    driver.findElement(fileUploadLocator).sendKeys(fileUploaderPath);
  }

  public void selectCreativeLevel(WebDriver driver,String selectText)
  {
    selectDropDownElement(driver,creativeLevelSelectLocator,selectText,"Text");
  }

  public void selectPriority(WebDriver driver,String selectText)
  {
    selectDropDownElement(driver,prioritySelectLocator,selectText,"Text");
  }

  public void enterprojectOwner(WebDriver driver, String projectowner) {
    waitForElementVisibility(driver, projectOwner);
    driver.findElement(projectOwner).click();
    waitForElementVisibility(driver, projectOwnerInput);
    driver.findElement(projectOwnerInput).sendKeys(projectowner);
    //waitForElementVisibility(driver, projectOwnerResult);
        int countProjectOwnerResult = driver.findElements(By.xpath("//div[@id='select2-drop']/ul/li")).size();
        for(int i =0 ; i<100; i++) {
            if (countProjectOwnerResult <= 0)
                countProjectOwnerResult = driver.findElements(By.xpath("//div[@id='select2-drop']/ul/li")).size();
            else
                break;
        }
        driver.findElement(projectOwnerResult).click();
    }


  public void enterInstructions(WebDriver driver, String instructions) {
    waitForElementVisibility(driver, instructionsLocator);
    driver.findElement(instructionsLocator).sendKeys(instructions);
  }

  public void enterTeam(WebDriver driver, String team)
  {
    waitForElementVisibility(driver, teamInputLocator);
    driver.findElement(teamInputLocator).sendKeys(team);
  }
  public void selectJobType(WebDriver driver)
  {
    scrollToElement(driver,jobTypeSelect);
    selectDropDownElement(driver,jobTypeSelect,"1","Index");
  }

  public void selectProjectSubType(WebDriver driver)
  {
    waitForElementVisibility(driver, projectSubTypeSelect);
    selectDropDownElement(driver,projectSubTypeSelect,"1","Index");
  }

  public void selectJobCategory(WebDriver driver)
  {
    waitForElementVisibility(driver, jobCategorySelect);
    selectDropDownElement(driver,jobCategorySelect,"1","Index");
  }

  public void selectColumns(WebDriver driver)
  {
    waitForElementVisibility(driver, columnsSelect);
    selectDropDownElement(driver,columnsSelect,"1","Index");
  }


  public void enterWidth(WebDriver driver, String projectwidth) {
    waitForElementVisibility(driver, width);
    driver.findElement(width).sendKeys(projectwidth);
  }

  public void enterHeight(WebDriver driver, String projectheight) {
    waitForElementVisibility(driver, height);
    driver.findElement(height).sendKeys(projectheight);
  }

  public void selectProductionStatus(WebDriver driver)
  {
    selectDropDownElement(driver,productionStatusSelect,"1","Index");
  }
  public void clickOnSubmitButton(WebDriver driver) {
    driver.findElement(submit).click();
    Boolean readyStateComplete = false;
        while (!readyStateComplete)
        {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("window.scrollTo(0, document.body.offsetHeight)");
            readyStateComplete = ((String) executor.executeScript("return document.readyState")).equals("complete");
        }

  }

  public void verifyJobUploadedSuccessfully(WebDriver driver,SoftAssert softAssert)
  {
    waitForElementPresence(driver, jobUploadedSuccessfully);
    softAssert.assertTrue(driver.findElement(jobUploadedSuccessfully).isDisplayed());
  }

  public void verifyProjectNdisplay(WebDriver driver,SoftAssert softAssert)
  {
    waitForElementPresence(driver, searchProjectNLocator);
    softAssert.assertTrue(driver.findElement(searchProjectNLocator).isDisplayed());
  }


  public int getProjectCountAfter(WebDriver driver) {
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {

      e.printStackTrace();
    }
    String projectCountAfter=driver.findElement(getCurrentProjectCount).getText();
    countAfter = Integer.parseInt(projectCountAfter);
    return countAfter;
  }

  public void verifyProjectCount(WebDriver driver, SoftAssert softAssert) {
    softAssert.assertTrue(countAfter==(countBefore+1));
  }

}
