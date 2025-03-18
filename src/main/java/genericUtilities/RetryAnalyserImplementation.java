package genericUtilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyserImplementation implements IRetryAnalyzer{


	int count = 0;
	int retryCount = 3; // manual analysis
	
	public boolean retry(ITestResult result) {
		//0<3 1<3 2<3 3<NOT3-false
		while(count<retryCount) {
			
			count++;
			return true;// retry retry retry
			
		}
		return false;//stop retry
	}
	

}
