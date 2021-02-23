package mod00_TestFrame;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import zParameter.TestParameter;

public class Test_Ablauf_Master
{

	static String testURL = TestParameter.testURL;
	
	public class Call_Class_Method_From_NG
	{

		@Test
		@Parameters({"wort1"})
		public void schreibe_Teil1(String wort1)
		{
			System.out.print(wort1);
		}
		
		@Test
		@Parameters({"browser"})
		public void start_Chromium_73(String browser)
		{

			System.out.println(browser);
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
		
		@Test
		@Parameters({"wort2"})
		public void schreibe_Teil2(String wort2)
		{
			System.out.println(wort2);
		}
		
	}

}
