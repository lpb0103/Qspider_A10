package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

	// Item in the Cart Container
	@FindBy(xpath = "//div[@class='inventory_item_name']")
	private WebElement itemInfo;
	
	//Item remove from Cart Container
	@FindBy(xpath="//button[.='Remove']")
	private WebElement removeBtn;

	// Rule 3 : Initialization
	public CartPage(WebDriver driver) {

		PageFactory.initElements(driver, this);
	}

	// Rule 4 : Utilization
	public WebElement getProductInCart() {
		return itemInfo;
	}
	public WebElement getRemoveBtn() {
		return removeBtn;
	}

	// Business library
	/**
	 * This method will capture the item name and return it to caller
	 * @return
	 */
	public String getItemName() {

		return itemInfo.getText();
	}
	/**
	 * This method will click on remove button
	 */
	public void clickOnRemoveBtn() {
		
		removeBtn.click();
	}

}
