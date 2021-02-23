package mod00_TestFrame;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.testng.collections.Lists;

public class List_Writer
{

	public static List<String> listeTestablauf = Lists.newArrayList();


	public static void fuelleListeFuerMain()
	{

		//Achtung! Testwerte nur fuer Duplikat main-Aufruf
		listeTestablauf.add("TestModul1");
		listeTestablauf.add("TestModul2");
		listeTestablauf.add("TestModul3");

	}


	public static void main(String[] args) throws Exception
	{
		//Achtung, nur in main !
		fuelleListeFuerMain();

		// Zusammenfassen der TestModule
		StringBuilder builder = new StringBuilder();
		for (String testModul : listeTestablauf)
		{
			builder.append(testModul.toString());
			builder.append(System.getProperty("line.separator"));
		}

		//vermutlich den letzten Strich entfernen
		builder.delete(builder.length() - System.getProperty("line.separator").length(), builder.length());

		// Speichern der ModulListe in eine .txt-Datei mit Datum

		Date date = new Date();
		//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd_HHmmss");

		//File file = new File(dateFormat.format(date) + ".tsv") ;
		File file = new File("J:/Java.Testing/HomePageTesting/ListeTestablauf_" + dateFormat.format(date) + ".txt");
		file.createNewFile();
		FileWriter fw = new FileWriter(file);
		fw.write(builder.toString());
		fw.flush();
		fw.close();

		System.out.println("ListeTestablauf erstellt.");

	}


	public static void SaveListIntoTxt(List<String> listeTestablauf) throws Exception
	{
		// Erstellen der ModulListe

		// Zusammenfassen der TestModule
		StringBuilder builder = new StringBuilder();
		for (String testModul : listeTestablauf)
		{
			builder.append(testModul.toString());
			builder.append(System.getProperty("line.separator"));
		}

		//vermutlich den letzten Strich entfernen
		builder.delete(builder.length() - System.getProperty("line.separator").length(), builder.length());

		// Speichern der ModulListe in eine .txt-Datei mit Datum

		Date date = new Date();
		//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd_HHmmss");

		//File file = new File(dateFormat.format(date) + ".tsv") ;
		File file = new File("j:/Java.Testing/HomePageTesting/ListeTestablauf_" + dateFormat.format(date) + ".txt");
		file.createNewFile();
		FileWriter fw = new FileWriter(file);
		fw.write(builder.toString());
		fw.flush();
		fw.close();

		System.out.println("ListeTestablauf erstellt.");

	}

}
