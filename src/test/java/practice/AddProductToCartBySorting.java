package practice;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class AddProductToCartBySorting {

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

		// Read test data from Excel File
		// Open the document in java readable format
		FileInputStream fise = new FileInputStream("./src/test/resources/TestData.xlsx");

		// Create a WorkBook
		Workbook wb = WorkbookFactory.create(fise);

		// navigate to sheet
		 String PRODUCTNAME = wb.getSheet("products").getRow(7).getCell(3).getStringCellValue();
		System.out.println(PRODUCTNAME);

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

		// to handel dropdown
		WebElement dropDown = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
		Select selectFrom = new Select(dropDown);
		selectFrom.selectByVisibleText("Price (low to high)");

		String firstElement = driver.findElement(By.xpath("//div[.='Sauce Labs Onesie']")).getText();
		if (firstElement.equals(PRODUCTNAME)) {
			
			System.out.println("PASS");
			System.out.println(firstElement);

		}else {
			System.out.println("FAIL");
		}

	}

}
