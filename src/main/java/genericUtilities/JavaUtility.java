package genericUtilities;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class consists of generic methods related to java
 */
public class JavaUtility {

	/**
	 * This method will capture the current system data and return to caller used to
	 * name screenshot and reports
	 * 
	 * @return
	 */

	public String getSystemDateInformat() {

		Date d = new Date();
		SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy_hh-mm-ss");
		String date = f.format(d);
		return date;
	}
}
