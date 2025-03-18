package practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import genericUtilities.FileUtility;
import genericUtilities.SeleniumUtility;

public class AddLowestPricedProductToCartUsingDDT_GU {

	public static void main(String[] args) throws Throwable {

		// Create Object of All Utilities
		FileUtility fUtil = new FileUtility();
		SeleniumUtility sUtil = new SeleniumUtility();

		// Read required Data
		// Property file - common Data
		String URL = fUtil.readDataFromPropertyFile("url");
		String USERNAME = fUtil.readDataFromPropertyFile("username");
		String PASSWORD = fUtil.readDataFromPropertyFile("password");

		// Read Test Data From Excel File
		String SORTOPTION = fUtil.readDataFromExcelFile("products", 7, 2);
		String PRODUCTNAME = fUtil.readDataFromExcelFile("products", 7, 3);
		System.out.println(PRODUCTNAME);

		// LAUNCH THE BROWSER
		WebDriver driver = new ChromeDriver();
		sUtil.maximizeWindow(driver);
		sUtil.addImplicitlyWait(driver);

		// Load the Application
		driver.get(URL);

		// Login to Application USING traditional way
		driver.findElement(By.id("user-name")).sendKeys(USERNAME);
		driver.findElement(By.id("password")).sendKeys(PASSWORD);
		driver.findElement(By.id("login-button")).click();

		// Sort the page for lowest price
		WebElement prodSort = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
		sUtil.handleDropDown(SORTOPTION, prodSort);

		Thread.sleep(1000);

		// Click on the Lowest Price Product
		WebElement Product = driver.findElement(By.xpath("//div[.='" + PRODUCTNAME + "']"));
		String productToBeAdded = Product.getText();
		Product.click();

		// Add the product To Cart
		driver.findElement(By.id("add-to-cart")).click();

		// Navigate To Cart and Validate
		driver.findElement(By.id("shopping_cart_container")).click();

		// validate
		String productInCart = driver.findElement(By.xpath("//div[@class='inventory_item_name']")).getText();
		if (productToBeAdded.equals(productInCart)) {

			System.out.println("PASS");
			System.out.println(productInCart);
		} else {

			System.out.println("FAILED");
		}
		// Logout the application
		driver.findElement(By.id("react-burger-menu-btn")).click();
		driver.findElement(By.id("logout_sidebar_link")).click();
	}
}
