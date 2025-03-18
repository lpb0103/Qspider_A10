package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.SeleniumUtility;

public class InventoryPage extends SeleniumUtility{ // Rule 1 : Create pom class for every web page

	// Rule 2 : Identify the WebElement - Declaration
	
	//Product sorting
	@FindBy(xpath = "//select[@class='product_sort_container']")
	private WebElement sortDropDown;

	//shopping Cart container
	@FindBy(id = "shopping_cart_container")
	private WebElement cartContainerBtn;
	
	//Logout Menu
	@FindBy(id = "react-burger-menu-btn")
	private WebElement logoutMenu;
	
	//Logout link
	@FindBy(id = "logout_sidebar_link")
	private WebElement logoutLink;
	
	// Rule 3 : Initialization
    public InventoryPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}

	// Rule 4 : Utilization
    
	public WebElement getProdSort() {
		return sortDropDown;
	}

	public WebElement getShoppingCart() {
		return cartContainerBtn;
	}
	public WebElement getLogoutMenu() {
		return logoutMenu;
	}

	public WebElement getLogoutLink() {
		return logoutLink;
	}
	//Business Library
	
	/**
	 * This method will click on product read from excel file 
	 * and return the details to caller
	 * @param driver
	 * @param PRODUCTNAME
	 */
	public String clickOnProduct(WebDriver driver,String PRODUCTNAME) { //runtime data will fetch from excel file
		
		WebElement prod = driver.findElement(By.xpath("//div[.='"+PRODUCTNAME+"']")); 
		String productDetails = prod.getText();
	    System.out.println("InventoryPage:"+productDetails);
		prod.click();
		return productDetails;
	}
	/**
	 * This method will click on cart container button
	 */
	public void clickOnCartContainer() {
		cartContainerBtn.click();
	}
	/**
	 * This method will perform logout operation
	 */
	public void logoutOfApp() {
		logoutMenu.click();
		logoutLink.click();
	}
	/**
	 * This method will sort the product and then click on required product
	 * and return the details to caller
	 * @param driver
	 * @param PRODUCTNAME
	 * @param SORTOPTION
	 * @return
	 */
	public String clickOnSortedProduct(WebDriver driver, String PRODUCTNAME, String SORTOPTION) {
	
		handleDropDown(SORTOPTION, sortDropDown);// SeleniumUtility.java class method
		WebElement prod = driver.findElement(By.xpath("//div[.='"+PRODUCTNAME+"']"));
	    String productDetails = prod.getText();
	    System.out.println("InventoryPage:"+productDetails);
	    prod.click();
	    return productDetails;
	}

}
/*
 * //Sorting Product name
 * 
 * @FindBy(xpath = "//div[.='Sauce Labs Onesie']") 
 * private WebElement product;
 * 
 * public WebElement getProduct() {
		return product;
	}

	
 */