package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddProductToCart {

	public static void main(String[] args) {

		// Launch the browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		// Load the application
		driver.get("https://www.saucedemo.com/");

		// Login to application
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		
		// Click on a product - onesise
		String productToBeAdded = driver.findElement(By.id("item_2_title_link")).getText();
		driver.findElement(By.id("item_2_title_link")).click();
		
		// Add the product to cart
		driver.findElement(By.id("add-to-cart")).click();

		// Navigate to cart
		driver.findElement(By.id("shopping_cart_container")).click();
		
		// Validate for the product
		String productInCart = driver.findElement(By.id("item_2_title_link")).getText();
		if(productToBeAdded.equals(productInCart)) {
			
			System.out.println("PASS");
			System.out.println(productInCart);
		}else {
			
			System.out.println("FAILED");
		}
		// Logout the application
		driver.findElement(By.id("react-burger-menu-btn")).click();
		driver.findElement(By.id("logout_sidebar_link")).click();
	}
}
