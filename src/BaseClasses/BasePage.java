package BaseClasses;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	public void waitForElementVisibility(WebDriver driver, By elem) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, 120);
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(elem));
	}

	public void javaScriptClick(WebDriver driver, By element) {
		scrollToElement(driver, element);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(element));
	}
	public void waitForElementPresence(WebDriver driver, By element)	{
		WebDriverWait webDriverWait =  new WebDriverWait(driver, 120);
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(element));	
	}
	

	public void scrollToElementWithoutWait(WebDriver driver, By elem) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(elem));
	}

	public String getWorkingDirectoryPath() {
		String workdingDirectoryPath = System.getProperty("user.dir");
		return workdingDirectoryPath;
	}

	public void waitForElementClickable(WebDriver driver, By elem) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, 120);
		webDriverWait.until(ExpectedConditions.elementToBeClickable(elem));
	}

	public void scrollToElement(WebDriver driver, By elem) {
		waitForElementVisibility(driver, elem);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(elem));
	}

	public void waitForElementInvisibility(WebDriver driver, By elem) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, 120);
		webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(elem));
	}

	/**
	 * <h1>This method is scroll to buttom</h1>
	 * <p>
	 * This method scroll to bottom of the any element
	 * 
	 * @param driver
	 *            driver of the browser
	 * @param elmentId
	 *            It is the element id
	 */
	public void scrollDownOfElement(WebDriver driver, String elmentId) {
		waitForElementVisibility(driver, (By.id(elmentId)));
		String myScript = "document.getElementById('" + elmentId + "').scrollTop = document.getElementById('" + elmentId
				+ "').scrollHeight;";
		((JavascriptExecutor) driver).executeScript(myScript);
	}

	public void scrollTop(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
	}

	public void selectDropDownElement(WebDriver driver, By elementId, String TextValueorIndex, String selectionType) {
		waitForElementVisibility(driver, (elementId));
		WebElement selectElement = driver.findElement(elementId);
		Select selectValue = new Select(selectElement);
		if (selectionType.equals("Text"))
			selectValue.selectByVisibleText(TextValueorIndex);
		else if (selectionType.equals("Value"))
			selectValue.selectByValue(TextValueorIndex);
		else
			selectValue.selectByIndex(Integer.parseInt(TextValueorIndex));
	}
	
	public void executeJSCommand(WebDriver driver, String command){
		((JavascriptExecutor) driver).executeScript(command);
	}

	public String getCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}

}
