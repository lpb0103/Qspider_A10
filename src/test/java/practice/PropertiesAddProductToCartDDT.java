package practice;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PropertiesAddProductToCartDDT {

	public static void main(String[] args) throws Throwable {

		// Read required Data
		// Property file - common Data

		// Open the doc in java readable format
		FileInputStream fis = new FileInputStream("./src/test/resources/CommonData.properties");

		// Create Object of properties class - java.util
		Properties pro = new Properties();

		// load the file input stream to properties
		pro.load(fis);

		// read the data using keys
		String URL = pro.getProperty("url");
		String USERNAME = pro.getProperty("username");
		String PASSWORD = pro.getProperty("password");
		
		// Launch the browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		// Load the application
		driver.get(URL);

		// Login to application
		driver.findElement(By.id("user-name")).sendKeys(USERNAME);
		driver.findElement(By.id("password")).sendKeys(PASSWORD);
		driver.findElement(By.id("login-button")).click();
	}
}
