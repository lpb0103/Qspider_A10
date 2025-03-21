package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

import com.mysql.jdbc.Driver;

public class JDBCSampleProgram {
	
	@Test
	public void executeQuerySample() throws Throwable {
		
		//Fetch driver from database
		Driver dref = new Driver();//mysql
		
		//Register the driver
		DriverManager.registerDriver(dref);
		
		//Get connect with driver - give database name
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/customerdb","root","admin");
		
		//Issue create statement
		Statement stmt = con.createStatement();
		
		//Execute the query - table name
		ResultSet result = stmt.executeQuery("select * from customerinfo");
		while(result.next()) {
			
			System.out.println(result.getString(1)+"-"+result.getInt(2)+"-"+result.getString(3));
			
		}
		// Close database
		con.close();
		
		
	}

}
