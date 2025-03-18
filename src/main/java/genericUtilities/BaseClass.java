package genericUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import objectRepository.InventoryPage;
import objectRepository.LoginPage;

/**
 * This class consists of all basic Configuration annotations of TestNG
 */
public class BaseClass {

	public FileUtility fUtil = new FileUtility();
	public SeleniumUtility sUtil = new SeleniumUtility();
	public JavaUtility jUtil = new JavaUtility();
	public WebDriver driver;

	// for listeners
	public static WebDriver sdriver;

	@BeforeSuite(alwaysRun = true) // for always run without any group execution
//	@BeforeSuite(groups =  "SmokeSuite")
	public void bsConfig() {
		System.out.println("---Database Connection Successfully---");
	}

//	@Parameters("browser")
//	@BeforeTest // Parallel->testng_DistrubutedParallelExecution.xml

	@BeforeClass(alwaysRun = true) // for always run without any group execution
	public void bcConfig(/* String BValue */) throws Throwable {

		String URL = fUtil.readDataFromPropertyFile("url");
		driver = new ChromeDriver();
		
//		For cross Browser Execution
		/*
		 * if (BValue.equalsIgnoreCase("edge")) { driver = new EdgeDriver();
		 * System.out.println("Edge Browser Launch Successfully"); } else if
		 * (BValue.equalsIgnoreCase("firefox")) { driver = new FirefoxDriver();
		 * System.out.println("FireFox Browser Launch Successfully");
		 * 
		 * } else { driver = new ChromeDriver();
		 * System.out.println("Chrome Browser Launch Successfully");
		 * 
		 * }
		 */
		sUtil.maximizeWindow(driver);
		sUtil.addImplicitlyWait(driver);

		driver.get(URL);
		System.out.println("---Browser Launch Successfully---");

		// For Listeners
		sdriver = driver;
	}

	@BeforeMethod(groups = { "SmokeSuite", "RegressionSuite" }) // for both smoke and regression test_case run
	public void bmConfig() throws Throwable {

		String USERNAME = fUtil.readDataFromPropertyFile("username");
		String PASSWORD = fUtil.readDataFromPropertyFile("password");

		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);

		System.out.println("---- Login to App Successful ----");
	}

	@AfterMethod(alwaysRun = true)
	public void amConfig() {

		InventoryPage ip = new InventoryPage(driver);
		ip.logoutOfApp();

		System.out.println("---- Logout of App Successful ----");
	}

	@AfterTest // Parallel-testng_DistrubutedParallelExecution.xml
//  @AfterClass(groups = { "SmokeSuite","RegressionSuite"})
	public void acConfig() {
		driver.quit();
		System.out.println("---Browser Clouser Successfully---");
	}

	@AfterSuite(alwaysRun = true)
	public void asConfig() {

		System.out.println("---Database clouser Successfully---");
	}
}
