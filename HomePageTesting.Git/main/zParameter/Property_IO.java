package zParameter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Property_IO
{
	public Properties Parameter = new Properties();
	String filePath = "j:\\Java.Testing\\HomePageTesting\\main\\mod00_PrepareTest\\Parameter.properties";
	//String filePath = (System.getProperty("user.dir") + "\\Parameter.properties");
	FileInputStream fileInput;
	FileOutputStream fileOutput;

	@BeforeTest
	public void readProperty() throws IOException
	{
		fileInput = new FileInputStream(filePath);

		//Properties property = new Properties();

		Parameter.load(fileInput);

		System.out.println(Parameter.getProperty("browser"));

	}

	@Test
	public void writeProperty(String key, String keyValue) throws IOException
	{
		fileOutput = new FileOutputStream(filePath);

		//Properties property = new Properties();

		//property.load(fileOutput);

		//property.put("browser", "Chrome");
		Parameter.put(key, keyValue);

		System.out.println(Parameter.getProperty("browser"));
	}

}
