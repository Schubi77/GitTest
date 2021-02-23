package xDataIO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import zBasicClasses.GetConnectionDB;

public class ConnectionTest
{
	//Psvm(String arr[])
	{
		Connection dbConn=null;
		PreparedStatement stmt1,stmt2,stmt3=null;
		ResultSet rs1,rs2,rs3=null;

		try
		{
			dbConn=GetConnectionDB.getConnection();

			stmt1=dbConn.prepareStatement("select * from yourTable1");
			rs1 = stmt1.executeQuery();
			while(rs1.next())
			{
				System.out.println(rs1.getString(1));// 1 is first column in database’s table type varchar
			}
			stmt2=dbConn.prepareStatement("select * from yourTable2");
			rs2 = stmt2.executeQuery();
			while(rs2.next())
			{
				System.out.println(rs2.getString(2));// 2 is second column in database’s table type varchar
			}
			stmt3=dbConn.prepareStatement("select * from yourTable3");
			rs3 = stmt3.executeQuery();
			while(rs3.next())
			{
				System.out.println(rs3.getString(3));// 3 is 3rd column in database’s table type varchar
			}
			dbConn.close();
		}
		catch(Exception e){ System.out.println(e);
		}
	}
}
