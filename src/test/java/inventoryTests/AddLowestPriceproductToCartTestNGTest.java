package inventoryTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import objectRepository.CartPage;
import objectRepository.InventoryItemPage;
import objectRepository.InventoryPage;

public class AddLowestPriceproductToCartTestNGTest extends BaseClass{

	@Test(groups =  "RegressionSuite")
	public void tc_002_AddLowestPriceproductToCart() throws Throwable
	 {
		
	   	String PRODUCTNAME = fUtil.readDataFromExcelFile("products", 7, 3);
		String SORTOPTION = fUtil.readDataFromExcelFile("products", 7, 2);
		
		//Click on Product
		InventoryPage ip = new InventoryPage(driver);
		String productAdded = ip.clickOnSortedProduct(driver, PRODUCTNAME, SORTOPTION);
		
		//Add Product To Cart
		InventoryItemPage iip = new InventoryItemPage(driver);
		iip.clickOnAddToCartBtn();
		
		//Navigate to Cart
		ip.clickOnCartContainer();
		
		//Validate in the Cart Page
		CartPage cp = new CartPage(driver);
		String productInCart = cp.getItemName();
		
		Assert.assertEquals(productAdded, productInCart);
		Assert.assertTrue(productInCart.equals(productAdded));
		/*
		 * if(productInCart.equals(productAdded)) {
		 * 
		 * System.out.println("Pass"); System.out.println(productInCart); }else {
		 * 
		 * System.out.println("FAIL"); }
		 */
		
	}
	@Test
	public void sample() {
		
		System.out.println("I'm from sample method(AddLowestPriceproductToCartTestNGTest)");
	}
}
