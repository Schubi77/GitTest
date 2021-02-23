package xDataIO;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import jxl.Sheet; //jxl.jar aus dem jexcelapi-Ordner aus dem ExcelTool_Testing_2_6_12.zip
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * This is a utility class created to read the excel test data file before
 * performing the test steps. This class loads the excel file and reads its
 * column entries.
 *
 */

public class Excel_IO_jxl
{

	//The worksheet to read in Excel file
	public Sheet wrksheet;

	//The Excel file to read
	public Workbook wrkbook = null;

	//Store the column data
	public Hashtable<String, Integer> dict = new Hashtable<String, Integer>();

	public String ExcelSheetPath = "J://Java.Testing_Installation_HowTo&Packages//Stammdaten_jxl.xls";


	//Constructor mit @param ExcelSheetPath,
	public Excel_IO_jxl(String Datenkreis, String Datenart) throws IOException, BiffException
	{

		// Initialize
		try
		{
			wrkbook = Workbook.getWorkbook(new File(ExcelSheetPath));
			wrksheet = wrkbook.getSheet(Datenkreis);
		} catch (IOException e)
		{
			throw new IOException();
		}
	}


	//Returns the Number of Rows
	public int RowCount()
	{
		System.out.println(wrksheet.getRows());
		return wrksheet.getRows();
	}


	//Returns the Cell value by taking row and Column values as argument
	public String ReadCell(int column, int row)
	{
		return wrksheet.getCell(column, row).getContents();
	}


	//Create Column Dictionary to hold all the Column Names
	public void ColumnDictionary()
	{
		// Iterate through all the columns in the Excel sheet and store the
		// value in Hashtable
		for (int col = 0; col < wrksheet.getColumns(); col++)
		{
			dict.put(ReadCell(col, 0), col);

		}

		System.out.println(dict.toString());
	}


	//Read Column Names
	public int GetCell(String colName)
	{
		try
		{
			int value;
			value = dict.get(colName).intValue();
			return value;
		} catch (NullPointerException e)
		{
			return (0);
		}
	}


	public void GetRowsAndColumns()
	{

		String dataString[][] = new String[wrksheet.getRows()][wrksheet.getColumns()];
		List liste = new List();

		for (int i = 0; i < wrksheet.getRows(); i++)
		{

			for (int j = 0; j < wrksheet.getColumns(); j++)
			{
				dataString[i][j] = wrksheet.getCell(j, i).getContents();

			}
		}

		//druckt den ganzen Inhalt Reihe f�r Reihe
		for (int row = 0; row < dataString.length; row++)
		{
			for (int column = 0; column < dataString[row].length; column++)
			{
				//druckt alles aus
				System.out.print(dataString[row][column]);

				//bringt keinen R�ckgabewert
				if (dataString[column].toString() == "Stra�e")
				{

					//System.out.print(" " + dataString[row][column]);

					System.out.print(" " + dataString[column].toString());

				}

			}
			System.out.println("");
		}

	}
}