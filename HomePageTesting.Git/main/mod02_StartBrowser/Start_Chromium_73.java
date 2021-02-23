package mod02_StartBrowser;

import java.io.FileWriter;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import zParameter.TestParameter;

public class Start_Chromium_73

{
	static String testURL = TestParameter.testURL;


	public static void main(String[] args)
	{

		//Browsertreiber
		ChromeOptions options = new ChromeOptions();
		//Pfad zum Browser
		options.setBinary("C:/Internet/Chromium.733683/chrome.exe");

		//Testtreiber Chromedriver
		System.setProperty("webdriver.chrome.driver", "J:/Java.Testing.Libraries/Referenced Libraries.Packages/WebDriver/chromedriver_ver71-73.exe");
		WebDriver chromeDriver = new ChromeDriver(options);

		//driver.get("http://rediff.com");
		chromeDriver.get(testURL);
		chromeDriver.manage().window().maximize();

		
	}


	@Test
	public void start_Chromium_73()
	{

		//Browser Chrome
		ChromeOptions options = new ChromeOptions();
		//Pfad zum Browser
		options.setBinary("C:/Internet/Chromium.733683/chrome.exe");

		//Testtreiber Chromedriver
		System.setProperty("webdriver.chrome.driver", "J:/Java.Testing.Libraries/Referenced Libraries.Packages/WebDriver/chromedriver_ver71-73.exe");
		WebDriver chromeDriver = new ChromeDriver(options);

		//driver.get("http://rediff.com");
		chromeDriver.get(testURL);
		chromeDriver.manage().window().maximize();
		
	}
}

//Gson gson = new Gson();
//String json = gson.toJson(chromeDriver);
//
//try
//{
//	gson.toJson(json, new FileWriter("J:/Java.Testing/HomePageTesting/browserDriver.json"));
//} catch (JsonIOException | IOException e)
//{
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//}
