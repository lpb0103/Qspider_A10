package inventoryTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import genericUtilities.FileUtility;
import genericUtilities.SeleniumUtility;
import objectRepository.CartPage;
import objectRepository.InventoryItemPage;
import objectRepository.InventoryPage;
import objectRepository.LoginPage;

public class AddLowestPriceproductToCartTest {

	public static void main(String[] args) throws Throwable {
		
	    //create Object of all Utilities
		SeleniumUtility sUtil = new SeleniumUtility();
		FileUtility fUtil = new FileUtility();
		
		//Read the Data from Files
		String URL = fUtil.readDataFromPropertyFile("url");
		String USERNAME = fUtil.readDataFromPropertyFile("username");
		String PASSWORD = fUtil.readDataFromPropertyFile("password");
		
		String PRODUCTNAME = fUtil.readDataFromExcelFile("products", 7, 3);
		String SORTOPTION = fUtil.readDataFromExcelFile("products", 7, 2);
		
		//Launch the browser
		WebDriver driver = new ChromeDriver();
		sUtil.maximizeWindow(driver);
		sUtil.addImplicitlyWait(driver);
		
		//Load the app
		driver.get(URL);
		
		//Login to App
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		Thread.sleep(3000);
		//Click on Product
		InventoryPage ip = new InventoryPage(driver);
		String productAdded = ip.clickOnSortedProduct(driver, PRODUCTNAME, SORTOPTION);
		
		Thread.sleep(3000);
		//Add Product To Cart
		InventoryItemPage iip = new InventoryItemPage(driver);
		iip.clickOnAddToCartBtn();
		
		Thread.sleep(3000);
		//Navigate to Cart
		ip.clickOnCartContainer();
		
		Thread.sleep(3000);
		//Validate in the Cart Page
		CartPage cp = new CartPage(driver);
		String productInCart = cp.getItemName();
		
		if(productInCart.equals(productAdded)) {
			
			System.out.println("Pass");
			System.out.println(productInCart);
		}else {
			
			System.out.println("FAIL");
		}
		//Logout
		ip.logoutOfApp();
	}
}
