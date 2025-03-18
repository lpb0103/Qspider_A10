package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryItemPage { // Rule 1 : Create pom class for every web page

	// Rule 2 : Identify the WebElement - Declaration
	
	 //Product add to Cart
     @FindBy(id = "add-to-cart") 
     private WebElement addTocartBtn;
     
	// Rule 3 : Initialization
    public InventoryItemPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	// Rule 4 : Utilization
    public WebElement getAddToCart() {
		return addTocartBtn;
	}
    
	//Business library
   /**
    * This method will click on add to cart button
    */
   public void clickOnAddToCartBtn() {
	   addTocartBtn.click();
   }
   
}

 