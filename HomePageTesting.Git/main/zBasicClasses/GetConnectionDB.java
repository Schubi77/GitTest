package zBasicClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnectionDB
{
	public static Connection getConnection()
	{
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