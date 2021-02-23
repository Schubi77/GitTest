package xDataIO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import org.testng.annotations.Test;

public class Property_IO_static
{
	public Properties property = new Properties();
	String filePath = (System.getProperty("user.dir") + "\\testData.properties");
	FileInputStream fileInput;
	FileOutputStream fileOutput;

	@Test
	public void readProperty() throws IOException
	{
		fileInput = new FileInputStream(filePath);

		//Properties property = new Properties();

		property.load(fileInput);

		System.out.println(property.getProperty("browser"));

	}

	@Test
	public void writeProperty(String key, String keyValue) throws IOException
	{
		fileOutput = new FileOutputStream(filePath);

		//Properties property = new Properties();

		//property.load(fileOutput);

		//property.put("browser", "Chrome");
		property.put(key, keyValue);

		System.out.println(property.getProperty("browser"));
	}

}
