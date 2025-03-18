package inventoryTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import genericUtilities.FileUtility;
import genericUtilities.SeleniumUtility;
import objectRepository.CartPage;
import objectRepository.InventoryItemPage;
import objectRepository.InventoryPage;
import objectRepository.LoginPage;

public class RevomeProductFromCartTestNGTest {

	@Test
	public void tc_003_RevomeProductFromCart() throws Throwable
	{

		// create Object of all Utilities
		SeleniumUtility sUtil = new SeleniumUtility();
		FileUtility fUtil = new FileUtility();

		// Read the Data from Files
		String URL = fUtil.readDataFromPropertyFile("url");
		String USERNAME = fUtil.readDataFromPropertyFile("username");
		String PASSWORD = fUtil.readDataFromPropertyFile("password");

		String PRODUCTNAME = fUtil.readDataFromExcelFile("products", 4, 2);

		// Launch the browser
		WebDriver driver = new ChromeDriver();
		sUtil.maximizeWindow(driver);
		sUtil.addImplicitlyWait(driver);

		// Load the app
		driver.get(URL);

		// Login to App
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);

		Thread.sleep(3000);
		// Click on Product
		InventoryPage ip = new InventoryPage(driver);
		String productAdded = ip.clickOnProduct(driver, PRODUCTNAME);

		Thread.sleep(3000);
		// Add Product To Cart
		InventoryItemPage iip = new InventoryItemPage(driver);
		iip.clickOnAddToCartBtn();

		Thread.sleep(3000);
		// Navigate to Cart
		ip.clickOnCartContainer();

		Thread.sleep(3000);
		// Validate in the Cart Page
		CartPage cp = new CartPage(driver);
		String productInCart = cp.getItemName();

		if (productInCart.equals(productAdded)) {

			System.out.println("Pass");
			System.out.println(productInCart);
		} else {

			System.out.println("FAIL");
		}

		Thread.sleep(3000);
		cp.clickOnRemoveBtn();
		System.out.println("Product remove successfully from Cart Page");

		Thread.sleep(3000);
		// Logout
		ip.logoutOfApp();
	}

}
