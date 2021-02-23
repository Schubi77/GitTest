package zParameter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Parameter_IO_gut
{
	public Properties prop = new Properties();
	//String filePath = "j:\\Java.Testing\\HomePageTesting\\main\\mod00_PrepareTest\\Parameter.properties";
	//String filePath = "j:/Java.Testing/HomePageTesting/main/mod00_PrepareTest/Parameter.properties";
	//String filePath = (System.getProperty("user.dir") + "\\Parameter.properties");
	//FileInputStream fileInput;
	//FileOutputStream fileOutput;

//	@BeforeTest
//	public void writeProperty() throws IOException
//	{
//        //try (OutputStream output = new FileOutputStream("j:\\Java.Testing\\HomePageTesting\\main\\mod00_PrepareTest\\Parameter.properties")) 
//        try (OutputStream output = new FileOutputStream("j:/Java.Testing/HomePageTesting/main/mod00_PrepareTest/Parameter.properties"))
//        {
//
//            //Properties prop = new Properties();
//
//            // set the properties value
//            prop.setProperty("db.url", "google");
//            prop.setProperty("db.user", "anyName");
//            prop.setProperty("db.password", "anyPassword");
//
//            // save properties to project root folder
//            prop.store(output, null);
//
//            System.out.println(prop);
//
//        } catch (IOException io) {
//            io.printStackTrace();
//        }
//
//	}

	@Test
	public void readProperty() throws IOException
	{
        //try (InputStream input = new FileInputStream("path/to/config.properties")) 
        try (InputStream input = new FileInputStream("j:/Java.Testing/HomePageTesting/main/mod00_PrepareTest/Parameter.properties"))
        {

            //Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            System.out.println(prop.getProperty("db.url"));
            System.out.println(prop.getProperty("db.user"));
            System.out.println(prop.getProperty("db.password"));
            System.out.println(prop.getProperty("but.Chrome"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}
	
	@AfterTest
	public void correctProperty() throws IOException
	{
        //try (OutputStream output = new FileOutputStream("j:\\Java.Testing\\HomePageTesting\\main\\mod00_PrepareTest\\Parameter.properties")) 
        try (OutputStream output = new FileOutputStream("j:/Java.Testing/HomePageTesting/main/mod00_PrepareTest/Parameter.properties"))
        {

            //Properties prop = new Properties();

            // set the properties value
            prop.setProperty("db.url", "localhost");
            prop.setProperty("db.user", "myName");
            prop.setProperty("db.password", "myPassword");

            // save properties to project root folder
            prop.store(output, null);

            System.out.println(prop);

        } catch (IOException io) {
            io.printStackTrace();
        }
	}

}
