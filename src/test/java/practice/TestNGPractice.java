package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNGPractice {

	@Test
	public void sampleFirst() {

		System.out.println("I'm TestNG sampleFirst()...");
	}

	@Test(priority = 1 , invocationCount = 2 , enabled = false)
	public void sampleSecond() {

		System.out.println("I'm TestNG sampleSecond()...");

	}

	@Test(priority = 2 , invocationCount = 3 , dependsOnMethods = "sampleFirst")

	public void sampleThird() {

		System.out.println("I'm TestNG sampleThird()...");

	}

	@Test(priority = 0 , invocationCount = 4 , dependsOnMethods = "sampleFive")

	public void sampleFourth() {

		System.out.println("I'm TestNG sampleFourth()...");

	}

	@Test(priority = -1 , invocationCount = 5 , enabled = true)

	public void sampleFive() {

		System.out.println("I'm TestNG sampleFifth()...");

	}
	@DataProvider (name = "cred")
	public String[][] sendData() {
		String data[][] = {
				{"Laxmi@gmail.com","laxmi@123"},
				{"Priya@gmail.com","priya@123"},
				{"Behera@gmail.com","behera@123"}
		};
		return data;
	}
	
	//DataDrivenProvider
	@Test(dataProvider = "cred")
	public void testCase(String userName, String Password) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.facebook.com/");
		driver.findElement(By.id("email")).sendKeys(userName);
		driver.findElement(By.id("pass")).sendKeys(Password);
	}

}
