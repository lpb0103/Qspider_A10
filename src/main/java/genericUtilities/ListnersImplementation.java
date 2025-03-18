package genericUtilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * This class provides implementation to ITestListners interface of TestNG
 */
public class ListnersImplementation implements ITestListener {

	ExtentReports report;
	ExtentTest test;
	
	public void onTestStart(ITestResult result) {

		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname + "-@ Test is execution Started");
		
		//Intimate the start of @Test to Extent Reports
		test = report.createTest(methodname);
	}

	public void onTestSuccess(ITestResult result) {

		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname + "-@ Test is execution PASS");
		
		//Intimate Extent Reports @Test is PASS - log the status
		test.log(Status.PASS, methodname+"- @Test is execution PASS");

	}

	public void onTestFailure(ITestResult result) {

		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname + "- @Test is execution FAIL");

		// Capture the exception
		System.out.println(result.getThrowable());
		
		// Log the Status as Fail in Extent Reports
		test.log(Status.FAIL, methodname +"- @Test is execution FAIL");
		
		// Log the exception in Extent Reports
		test.log(Status.WARNING, result.getThrowable());
		
		//Capture Screenshot
		SeleniumUtility s = new SeleniumUtility();
		JavaUtility j = new JavaUtility();
		
		//Configure screenshot name
		String screenShotName = methodname+"-"+j.getSystemDateInformat();
		
		//For abstract methods we are not using exception throw to JVM
		//For capture the Exception we are using try and catch block
		try {
			String path = s.captureScreenshot(BaseClass.sdriver, screenShotName);
			
			// Attach the screenshot to Extent Report
			test.addScreenCaptureFromPath(path);
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {

		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname + "-@ Test is execution SKIP");

		// Capture the exception
		System.out.println(result.getThrowable());
		
		//Log the status as SKIP in Extent Reports
		test.log(Status.SKIP, methodname + " - @Test is execution SKIP");
		
		//Log the exception in Extent Reports
		test.log(Status.WARNING, result.getThrowable());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onTestFailedWithTimeout(ITestResult result) {

	}

	public void onStart(ITestContext context) {

		System.out.println("Suite is execution Strated");
		
		//Configuration of Extent Reports
		ExtentSparkReporter esr = new ExtentSparkReporter(".\\ExtentReports\\Reports -"+ new JavaUtility().getSystemDateInformat()+".html"); 
		esr.config().setDocumentTitle("SauceLabs Execution Report");
		esr.config().setReportName("Execution");
		esr.config().setTheme(Theme.DARK);
		
		//Load the basic configuration to extent reports class
	    report = new ExtentReports();
		report.attachReporter(esr);
		report.setSystemInfo("Base Browser", "Microsoft Edge");
		report.setSystemInfo("Base Platform", "Windows");
		report.setSystemInfo("Base URL", "Testing Environment");
		report.setSystemInfo("Reporter name", "Laxmi");
	}

	public void onFinish(ITestContext context) {

		System.out.println("Suite is execution Finished");
		
		//Flush the Report
		report.flush();
	}
}
