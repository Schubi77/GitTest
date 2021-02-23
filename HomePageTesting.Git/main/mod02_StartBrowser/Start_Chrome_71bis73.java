package mod02_StartBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import zParameter.TestParameter;

public class Start_Chrome_71bis73 

{
	String testURL = TestParameter.testURL;
	
	//public static void main(String[] args) {
	@Test
	public void start_Chrome()
	{
		
		//System.setProperty("webdriver.chrome.driver","J:\\Java\\WebDriver\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver","J:\\Java.Testing.Libraries\\Referenced Libraries.Packages\\WebDriver\\chromedriver_ver71-73.exe");
			
		WebDriver chromeDriver = new ChromeDriver();
		//chromeDriver.get("http://google.com");
		//chromeDriver.get("http://rediff.com");
		chromeDriver.get(testURL);
		chromeDriver.manage().window().maximize();
		
		System.out.println("Falls Fehler, Server starten");
		//chromeDriver.findElement(arg0).  //findElement(By.xpath(xpathExpression))

	}

}
