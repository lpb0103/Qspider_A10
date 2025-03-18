package practice;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ExcelAddProductToCartDDT {
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
		Sheet sh = wb.getSheet("products");

		// navigate to row
		Row row = sh.getRow(1);

		// navigate to cell
		Cell cell = row.getCell(2);

		// capture the data inside the cell
		String PRODUCTNAME = cell.getStringCellValue();
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

		// click on a product - dynamic xpath
		String ProductToBeAdded = driver.findElement(By.xpath("//div[.='" + PRODUCTNAME + "']")).getText();
		driver.findElement(By.xpath("//div[.='"+PRODUCTNAME+"']")).click();

		// Add the product to cart
		driver.findElement(By.id("add-to-cart")).click();

		// navigate to cart
		driver.findElement(By.id("shopping_cart_container")).click();

		// validate for the product
		String productInCart = driver.findElement(By.xpath("//div[@class='inventory_item_name']")).getText();
		if (ProductToBeAdded.equals(productInCart)) {

			System.out.println("PASS");
			System.out.println(productInCart);
		} else {

			System.out.println("FAILED");
		}
		// Logout the application
		driver.findElement(By.id("react-burger-menu-btn")).click();
		driver.findElement(By.id("logout_sidebar_link")).click();
	}
}
