package xDataIO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import zBasicClasses.GetConnectionDB;

class BN_woco_sessions_Main
{
	//static Connection conn = null;
	//static Statement statement;
	//static ResultSet dbResult = null;

	public static void main(String args[])
	{
		try
		{
			//This line will load the JDBC
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn=GetConnectionDB.getConnection();

			System.out.print("Database is connected ! \n");

			//String sqlGetData = "SELECT * FROM wp_woocommerce_sessions WHERE session_id = 6"; 
			String sqlGetData = "SELECT * FROM wp_woocommerce_sessions";

			Statement statement = conn.createStatement();

			System.out.print("Statement is fired ! \n");

			ResultSet dbResult = statement.executeQuery(sqlGetData);

			System.out.print("There is a result. \n");

			// iterate through the java resultset
			while (dbResult.next())
			{
				int id = dbResult.getInt("session_id");
				int sessionKey = dbResult.getInt("session_key");
				String sessionValue = dbResult.getString("session_value");
				Date sessionExpiry = dbResult.getDate("session_expiry");

				System.out.println(id);
				System.out.println(sessionKey);
				System.out.println(sessionValue);
				System.out.println(sessionExpiry);
			}

			conn.close();

		} catch (Exception e)
		{
			System.out.print("We got an Error:" + e);
		}
	}
}