package genericUtilities;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class consists of generic methods related to selenium tool
 * 
 * @param driver
 */
public class SeleniumUtility {

	/**
	 * This method will maximize window
	 * 
	 * @param driver
	 */
	public void maximizeWindow(WebDriver driver) {

		driver.manage().window().maximize();
	}

	/**
	 * This method will minimize window
	 * 
	 * @param driver
	 */
	public void minimizeWindow(WebDriver driver) {

		driver.manage().window().minimize();
	}

	/**
	 * This method will add implicitly wait of 10 seconds
	 * 
	 * @param driver
	 */
	public void addImplicitlyWait(WebDriver driver) {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	/**
	 * This methods will wait for 10 seconds for element to be visible
	 * 
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeVisible(WebDriver driver, WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * this method will wait for 10 seconds for element to be clickable
	 * 
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeClickable(WebDriver driver, WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * This method will handle dropdown by index
	 * 
	 * @param element
	 * @param index
	 */
	public void handleDropDown(WebElement element, int index) {

		Select sel = new Select(element);
		sel.selectByIndex(index);
	}

	/**
	 * This method will handle dropdown by value
	 * 
	 * @param element
	 * @param value
	 */

	public void handleDropDown(WebElement element, String value) {

		Select sel = new Select(element);
		sel.selectByValue(value);
	}

	/**
	 * This method will handle dropdown by visible text
	 * 
	 * @param visibleText
	 * @param element
	 */

	public void handleDropDown(String visibleText, WebElement element) {

		Select sel = new Select(element);
		sel.selectByVisibleText(visibleText);
	}

	/**
	 * This method will perform mouse Hovering action on webElement
	 * 
	 * @param element
	 * @param driver
	 */
	public void mouseOverAction(WebElement element, WebDriver driver) {

		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}

	// Drag and Drop, Double Click and Context Click
	/**
	 * This method will drag and drop action on WebElement
	 * 
	 * @param source
	 * @param target
	 * @param driver
	 */

	public void dragAndDropAction(WebElement srcelement, WebElement tarelement, WebDriver driver) {

		Actions action = new Actions(driver);
		action.dragAndDrop(srcelement, tarelement).perform();
	}

	/**
	 * This method will perform double click action on WebElement
	 * 
	 * @param element
	 * @param driver
	 */

	public void doubleClickAction(WebElement element, WebDriver driver) {

		Actions act = new Actions(driver);
		act.doubleClick(element).perform();
	}

	/**
	 * This method will perform right click on WebElement
	 * 
	 * @param element
	 * @param driver
	 */
	public void rightClickAction(WebElement element, WebDriver driver) {

		Actions act = new Actions(driver);
		act.contextClick(element).perform();
	}

	/**
	 * This method will scroll to particular WebElement
	 * 
	 * @param element
	 * @param driver
	 */
	public void scrollToElementAction(WebDriver driver, WebElement element) {

		Actions action = new Actions(driver);
		action.scrollToElement(element).perform();
	}

    //Frame handling , window handling, Alert
	/**
	 * This method will switch to frame based in index
	 * 
	 * @param element
	 * @param driver
	 */
	public void handleFrame(WebDriver driver, int index) {

		driver.switchTo().frame(index);
	}

	/**
	 * This method will switch to frame based Name or ID
	 * 
	 * @param element
	 * @param driver
	 */
	public void handleFrame(WebDriver driver, String nameOrID) {

		driver.switchTo().frame(nameOrID);
	}

	/**
	 * This method will switch to frame based webElement
	 * 
	 * @param element
	 * @param driver
	 */
	public void handleFrame(WebElement element, WebDriver driver) {

		driver.switchTo().frame(element);
	}

	/**
	 * This method will switch the control to parent frame
	 * 
	 * @param element
	 * @param driver
	 */

	public void switchToParentframe(WebDriver driver) {

		driver.switchTo().parentFrame();
	}

	/**
	 * This method will switch to window
	 * 
	 * @param element
	 * @param driver
	 */

	public void switchToWindow(WebDriver driver, String windowID) {

		driver.switchTo().window(windowID);
	}

	/**
	 * This method will accept the alert popup
	 * 
	 * @param driver
	 */
	public void acceptAlert(WebDriver driver) {

		driver.switchTo().alert().accept();
	}

	/**
	 * This method will dismiss the alert popup
	 * 
	 * @param driver
	 */
	public void dissmissAlert(WebDriver driver) {

		driver.switchTo().alert().dismiss();
	}

	/**
	 * This method will get the alert text
	 * 
	 * @param driver
	 */
	public String getAlertText(WebDriver driver) {

		return driver.switchTo().alert().getText();

	}

	/**
	 * This method will enter the data to alert popup
	 * 
	 * @param driver
	 */
	public void enterDataToAlert(WebDriver driver, String data) {

		driver.switchTo().alert().sendKeys(data);

	}

	/**
	 * This method will capture the screenshot and return the path to caller
	 * @param driver
	 * @param screenshotName
	 * @return
	 * @throws Throwable
	 */
	public String captureScreenshot(WebDriver driver, String screenshotName) throws Throwable {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dst = new File("./Screenshots/" + screenshotName + ".png");
		FileHandler.copy(src, dst);

		return dst.getAbsolutePath();// Expert reports
		
		/*
		 * OutPut:
		 * addProductToCart - ts - addProductCart-22-02-2025-14-21-32.png;
		 * addProductToCart - ts - addProductCart-22-02-2025-14-45-32.png;
		 */


	}
}
