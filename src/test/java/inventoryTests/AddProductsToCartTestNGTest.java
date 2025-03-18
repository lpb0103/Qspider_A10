package inventoryTests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import objectRepository.CartPage;
import objectRepository.InventoryItemPage;
import objectRepository.InventoryPage;

@Listeners(genericUtilities.ListnersImplementation.class)
public class AddProductsToCartTestNGTest extends BaseClass{
	
	@Test(groups = "SmokeSuite")
	public void tc_001_AddProductsToCart() throws Throwable
	 {
		
    //Read the Data from Files
	String PRODUCTNAME = fUtil.readDataFromExcelFile("products", 1, 2);

	//Click on Product
	InventoryPage ip = new InventoryPage(driver);
	String productAdded = ip.clickOnProduct(driver, PRODUCTNAME);
	
	//Assert.fail();//For Listner failure screen shot

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
	
	System.out.println(productInCart);
	/*
	 * if(productInCart.equals(productAdded)) {
	 * 
	 * System.out.println("Pass"); System.out.println(productInCart); }else {
	 * 
	 * System.out.println("FAIL"); }
	 */
	
	}	

}
