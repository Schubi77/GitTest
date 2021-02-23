package xDataIO;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_IO_xssf
{
	//private Hashtable<String, Integer> dict = new Hashtable<String, Integer>();

	//Array zum Spaltendaten sammeln:
	private ArrayList<String> listColumnData = new ArrayList<String>();

	//The worksheet to read in Excel file
	private XSSFSheet worksheet;

	private int columnNumber;


	@SuppressWarnings("deprecation")
	public ArrayList<String> getDataColumn(String Datenkreis, String Datenart) throws IOException
	{
		// fileInputStream argument f�r myExcel.xlsx
		FileInputStream excelDataStream = new FileInputStream("J://Java.Testing_Installation_HowTo&Packages//Stammdaten_xssf.xlsx");

		XSSFWorkbook workbook = new XSSFWorkbook(excelDataStream);

		worksheet = workbook.getSheet(Datenkreis);

		Iterator<Row> rows = worksheet.iterator(); //Iterator -> java.util; Row -> usermodel

		//stellt den Iterator auf die erste Reihe, da er bei 0 beginnt
		Row firstrow = rows.next();

		//gehe durch alle Zellen der ersten Reihe
		Iterator<Cell> cellsFirstRow = firstrow.cellIterator(); //Cell -> usermodel

		int iteratorPosition = 0;
		columnNumber = 0;
		//im Moment steht er auf 0 und pr�ft, ob er eine erste Zelle hat
		while (cellsFirstRow.hasNext())
		{

			//stellt den Iterator auf die erste Zelle, da er bei 0 beginnt und liest deren Wert aus
			//iteriert anschlie�end durch die while-Schleife durch alle durch
			Cell actualCell = cellsFirstRow.next();
			String cellContent = actualCell.getStringCellValue();

			//wir holen uns die Zahl der Spalte mit der gesuchten Datenart
			if (cellContent.equalsIgnoreCase(Datenart))
			{

				columnNumber = iteratorPosition;

			}

			iteratorPosition++;

		}

		//Header in Excel weglassen, deshalb actualRow bei 1 beginnen
		for (int actualRow = 1; actualRow < worksheet.getLastRowNum(); actualRow++)
		{

			Cell actualCell = CellUtil.getCell(worksheet.getRow(actualRow), columnNumber);

			if (actualCell != null && actualCell.getCellTypeEnum() != CellType.FORMULA)

			{

				//Wert in die Liste schreiben
				listColumnData.add(actualCell.toString());
				//Kontrollausdruck
				System.out.print(worksheet.getRow(actualRow).getCell(columnNumber) + "\n");

				if (actualCell.getCellTypeEnum() == CellType.NUMERIC)
				{
					//waere auch noch moeglich...
					//NumberToTextConverter.toText(actualCell.getNumericCellValue());
					System.out.println("Achtung, Zellen mit numerischen Werten");
				}

			}

		}

		workbook.close();

		return listColumnData;

	}//method end

}//class end

