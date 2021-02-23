package mod02_StartBrowser;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class Start_Firefox49 {

	public static void main(String[] args) {
		
		//System.setProperty("webdriver.firefox.bin", "C:\\Internet\\MozillaFirefox.52esrPort\\firefox.exe");
		//System.setProperty("webdriver.firefox.driver", "C:\\Internet\\MozillaFirefox.38\\firefox.exe"); //gibt ein Problem
		//System.setProperty("webdriver.firefox.driver", "C:\\Internet\\MozillaFirefox.52esrPort\\firefox.exe"); //gibt ein Problem
		
		System.setProperty("webdriver.gecko.driver", "C:\\Java\\Webdriver\\GeckoDriver.exe");
	    
		FirefoxProfile profile = new FirefoxProfile(new File("C:\\Internet\\MozillaFirefox.33_Profile"));		
		//FirefoxDriver ffDriver = new FirefoxDriver(profile);
		FirefoxDriver ffDriver = new FirefoxDriver();
		ffDriver.get("http://www.tutorialspoint.com");
		
		//FirefoxProfile profile = new FirefoxProfile(new File("C:\\Internet\\MozillaFirefox.33_Profile"));                  
		
		//WebDriver ffDriver = new FirefoxDriver(profile);
		
		//WebDriver ffDriver = new  FirefoxDriver();
		//ffDriver.get("https://www.google.de");
		//ffDriver.get("https://www.testing-board.com");
		//ffDriver.get("http://rediff.com");
		
		ffDriver.manage().window().maximize();
		
		//ffDriver.findElement(By.xpath(xpathExpression))
	}


}
