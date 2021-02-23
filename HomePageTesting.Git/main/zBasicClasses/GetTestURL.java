package zBasicClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//muss noch umgestrickt werden
public class GetTestURL
{

	public static Connection getConnection()
	{
		String driverPath = "C:\\Java\\WebDriver\\geckodriver_ver0191_64bit.exe";
		String testURL = "http://127.0.0.1/wordpress/de/";
		String testUID = "mgr123";
		String testPW = "mgr!23";
		String testTitle = "guru99 bank";

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Connection con=DriverManager.getConnection(“url”,”user”,”password”);

		java.sql.Connection sqlConn = null;

		try
		{
			sqlConn = DriverManager.getConnection("jdbc:mysql://localhost/bitnami_wordpress", "root", "basben71");
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sqlConn;
	}
}