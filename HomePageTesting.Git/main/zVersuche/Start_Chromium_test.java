package zVersuche;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import com.thoughtworks.selenium.DefaultSelenium;
import zParameter.TestParameter;

public class Start_Chromium_test 

{

	static String testURL = TestParameter.testURL;
	
    public static void main (String[] args){

    //System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\IdeaProjects\\testselenium\\drivers\\chromedriver.exe");
    
    //System.setProperty("webdriver.chrome.driver", "c:/Internet/Chromium.733683/chrome.exe");
    System.setProperty("webdriver.chrome.driver","J:\\Java.Testing.Libraries\\Referenced Libraries.Packages\\WebDriver\\chromedriver_ver71-73.exe");

    ChromeOptions opt = new ChromeOptions();

    //opt.setBinary("C:\\Users\\User\\Downloads\\chrome-win\\chrome-win\\chrome.exe");
    
    opt.setBinary("c:/Internet/Chromium.733683/chrome.exe");

    WebDriver driver = new ChromeDriver(opt);

    //driver.get("http://rediff.com");
    driver.get(testURL);
    driver.manage().window().maximize();
    
  //chromeDriver.get(testURL);
//	//chromeDriver.manage().window().maximize();
    

    //System.out.println(driver.getTitle());

    }
}

//
//{
//	String testURL = TestParameter.testURL;
//	
//	//public static void main(String[] args) {
//	
//	@Test
//	public void start_Chromium()
//	{
//		
//		//ChromeOptions options = new ChromeOptions();
//		
//        String pathToChromeDriver = "c:/Internet/Chromium.733683/chrome.exe";
//        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);
//        
//        DefaultSelenium selenium = new DefaultSelenium("localhost", 4444, "c:/Internet/Chromium.733683/chrome.exe", testURL );
//
//        		selenium.start();
// 
////        ChromeDriver Chrom1 = new ChromeDriver();
//        
////        Chrom1.get("https://www.testinst.testing-board.com/");
////
////	    options..BinaryLocation = "c:/Internet/Chromium.733683/chrome.exe";
////	    
////	    using (var chrome = new ChromeDriver(options));
//		//System.setProperty("webdriver.chrome.driver","J:\\Java\\WebDriver\\chromedriver.exe");
//		System.setProperty("webdriver.chrome.driver","J:\\Java.Testing.Libraries\\Referenced Libraries.Packages\\WebDriver\\chromedriver_ver71-73.exe");
//			
//		//WebDriver chromeDriver = new ChromeDriver();
//		//chromeDriver.get("http://google.com");
//		//chromeDriver.get("http://rediff.com");
//		//chromeDriver.get(testURL);
//		//chromeDriver.manage().window().maximize();
//		
//		//System.out.println("Falls Fehler, Server starten");
//		//chromeDriver.findElement(arg0).  //findElement(By.xpath(xpathExpression))
//
//	}
//
//}
