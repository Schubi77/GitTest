package mod00_TestFrame;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Parameter_IO
{
	public Properties parameter = new Properties();
	
	String filePath = "J:/Java.Testing/HomePageTesting/main/mod00_PrepareTest/Parameter.properties";
	//FileInputStream fileInput;
	//FileOutputStream fileOutput;

	@BeforeTest
	public void readProperty() throws IOException
	{
        try (InputStream input = new FileInputStream(filePath))
        {

            //Properties prop = new Properties();

            // load the Parameter.properties file
            parameter.load(input);

            // get the property value and print it out
            System.out.println(parameter.getProperty("testURL"));
            System.out.println(parameter.getProperty("db.user"));
            System.out.println(parameter.getProperty("db.password"));
            System.out.println(parameter.getProperty("but.Chrome"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}
	
	@AfterTest
	public void correctProperty() throws IOException
	{
 
        try (OutputStream output = new FileOutputStream(filePath))
        {

            // set the properties value
            parameter.setProperty("testURL", "http://localhost:81/");
            parameter.setProperty("db.user", "myName");
            parameter.setProperty("db.password", "myPassword");

            // save Parameter.properties to given path
            parameter.store(output, null);

            System.out.println(parameter);

        } catch (IOException io) {
            io.printStackTrace();
        }
	}

}
