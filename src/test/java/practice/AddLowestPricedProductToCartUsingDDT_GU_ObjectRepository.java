package practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import genericUtilities.FileUtility;
import genericUtilities.SeleniumUtility;
import objectRepository.CartPage;
import objectRepository.InventoryItemPage;
import objectRepository.InventoryPage;
import objectRepository.LoginPage;

public class AddLowestPricedProductToCartUsingDDT_GU_ObjectRepository {

	public static void main(String[] args) throws Throwable {

		// Create Object of All Utilities
		FileUtility fUtil = new FileUtility();
		SeleniumUtility sUtil = new SeleniumUtility();
//		JavaUtility jUtil = new JavaUtility();

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

		/*
		 * // Login to Application USING POM Class LoginPage lp = new LoginPage(driver);
		 * lp.getUsernameEdt().sendKeys(USERNAME);
		 * lp.getPasswordEdt().sendKeys(PASSWORD); lp.getLoginBtn().click();
		 */

		// Login to Application USING POM Class Rule 4 : Business Library
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);

		// Sort the page for lowest price
		InventoryPage ip = new InventoryPage(driver);
		WebElement prodsort = ip.getProdSort();
		String sortItem = prodsort.getText();
		System.out.println(sortItem);
		ip.clickOnProduct(driver, PRODUCTNAME);
    	ip.clickOnSortedProduct(driver, PRODUCTNAME, SORTOPTION);
		System.out.println("Hello");

//		sUtil.handleDropDown(SORTOPTION, prodsort);

		// Click on the Lowest Price Product
		InventoryItemPage iip = new InventoryItemPage(driver);
		iip.clickOnAddToCartBtn();
//		WebElement product = ip.getProduct();
//		String productToBeAdded = product.getText();
//		product.click();

		// Navigate To Cart and Validate
		CartPage cp = new CartPage(driver);
		String itemName = cp.getItemName();
//		ip.getShoppingCart().click();

		// validate
//		InventoryItemPage iip = new InventoryItemPage(driver);
//		String productInCart = iip.getAddToCart().getText();
		

		if (sortItem.equals(itemName)) {

			System.out.println("PASS");
			System.out.println(itemName);
		} else {

			System.out.println("FAILED");
		}

		// Logout the application
		ip.logoutOfApp();
		System.out.println("##### Hello I'm from AddLowestPricedProductToCartUsingDDT_GU_ObjectRepository #####");

	}
}
