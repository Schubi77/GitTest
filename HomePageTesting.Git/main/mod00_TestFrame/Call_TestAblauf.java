package mod00_TestFrame;

import java.net.URI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import zParameter.TestParameter;

public class Call_TestAblauf
{
	static String testURL = TestParameter.testURL;
	private WebDriver webDriver;


	@Test
	@Parameters({ "wort1" })
	public void schreibe_Teil1(String wort1)
	{
		System.out.print(wort1);
	}


	@Test
	@Parameters({ "browser" })
	public void start_Chromium_73(String browser)
	{

		System.out.println(browser);

		//Browser Chrome
		ChromeOptions options = new ChromeOptions();
		//Pfad zum Browser
		options.setBinary("C:/Internet/Chromium.733683/chrome.exe");

		//Testtreiber Chromedriver
		System.setProperty("webdriver.chrome.driver", "J:/Java.Testing.Libraries/Referenced Libraries.Packages/WebDriver/chromedriver_ver71-73.exe");
		webDriver = new ChromeDriver(options);

		//driver.get("http://rediff.com");
		webDriver.get(testURL);
		//webDriver.manage().window().maximize();

	}

	@Test
	public void check_user_on_homepage() throws Throwable
	{
		String currURL = webDriver.getCurrentUrl();
		
		if (currURL.equals (testURL))
		{
			System.out.println("stimmen ueberein");
		}
		else
			System.out.println("URL ist verschieden" + currURL);

		//URL currUrl = new URL(request.getRequestURL().toString());

//		String host = currUrl.getHost();
//		String userInfo = currUrl.getUserInfo();
//		String scheme = currUrl.getProtocol();
//		int port = currUrl.getPort();
//		String path = request.getAttribute("javax.servlet.forward.request_uri");
//		String query = request.getAttribute("javax.servlet.forward.query_string");
//		URI uri = new URI(scheme, userInfo, host, port, path, query, null);
//		return uri.toString();
	}


	@Test
	@Parameters({ "wort2" })
	public void schreibe_Teil2(String wort2)
	{
		System.out.println(wort2);
	}


	@Test
	@Parameters({ "wort3" })
	public void schreibe_Teil3(String wort3)
	{
		System.out.println(wort3);
	}

}
