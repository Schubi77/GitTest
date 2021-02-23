package xDataIO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import zBasicClasses.GetConnectionDB;

public class DataBase_IO {

	// StartDaten zum Schreiben in die DB

	//public ResultSet rsData = null;

	Connection conn = null;
	Statement statement;
	ResultSet dbData = null;

	public DataBase_IO(){

		conn=GetConnectionDB.getConnection();

		try {
			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} //end constructor

	public Statement createStatement() {

		try {
			statement = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	} //end method


	public void clearTable(String table){

		int datensatzeGeloescht = 0;

		String sqlClearTable = "DELETE FROM " + table;

		try {
			statement = conn.createStatement();
			datensatzeGeloescht = statement.executeUpdate(sqlClearTable);

			if (datensatzeGeloescht == 0) {
				System.out.println("Keine Datens�tze vorhanden");
			} else {
				System.out.println(datensatzeGeloescht + " Datensatz/Datensaetze geloescht");
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	} //end method


	public void resetTable() {

		int tableResetID = -1;

		String sqlResetID = "ALTER TABLE inputdata AUTO_INCREMENT = 1";

		try {

			statement = conn.createStatement();
			tableResetID = statement.executeUpdate(sqlResetID);

			if (tableResetID == 0) {
				System.out.println("UserID startet wieder bei 1");
			} else {
				System.out.println("UserID nicht resetted");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}// end try

	} //end method


	public void readDataFromTable() {

		String sqlGetData = "SELECT * FROM userdata ORDER BY UserID ASC";
		// r�ckw�rts -> String sqlQuery =
		// "Select * from userdata ORDER BY UserID DESC";
		// Trainer -> String sqlQuery =
		// "Select * from userdata ORDER BY UserID DESC LIMIT 1";

		try {

			Statement statement = conn.createStatement();

			dbData = statement.executeQuery(sqlGetData);

			for (int i = 1; i < 4; i++) {

				dbData.next();

				if (i > 1) {

					System.out.println("n�chster Datensatz:");
				}

				String read_eMail = dbData.getString("eMail");

				String read_Nachname = dbData.getString("Nachname");

				String read_Vorname = dbData.getString("Vorname");

				String read_PLZ = dbData.getString("PLZ");

				String read_Wohnort = dbData.getString("Wohnort");

				System.out.println(read_eMail + ", " + read_Nachname + ", " + read_Vorname + ", " + read_PLZ + ", " + read_Wohnort);
			}

			System.out.println("ok");

		} catch (Exception e) {

			System.out.println(e);

		}

	} //end method

	public void writeDataToTable() {

		String write_eMail = "schubert-gf@gmx.de";
		String write_Nachname = "Schubert";
		String write_Vorname = "Kurt";
		String write_PLZ = "68724";
		String write_Wohnort = "Plankstadt";

		String sqlInsertData = "INSERT INTO inputdata(eMail, Nachname, Vorname, PLZ) VALUES "
				+ "('"
				+ write_eMail
				+ "', '"
				+ write_Nachname
				+ "', '"
				+ write_Vorname 
				+ "', '" 
				+ write_Wohnort + "')";

		try {

			Statement statement = conn.createStatement();

			int dataFilled = statement.executeUpdate(sqlInsertData);

			if (dataFilled == 0) {
				System.out.println("Keine Datens�tze eingetragen");
			} else {
				System.out.println(dataFilled + " Datensatz/Datens�tze eingetragen");
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	} // end method

	public void closeConnection() {

		if (dbData != null) {
			try {
				dbData.close();
			} catch (SQLException e) { /* ignored */
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) { /* ignored */
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) { /* ignored */
			}

		}

		System.out.println("DBconnection closed");
	}
}
