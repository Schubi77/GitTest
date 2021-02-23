package mod02_StartBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Start_Firefox48 {

	public static void main(String[] args) {
		
		//System.setProperty("webdriver.firefox.bin", "C:\\Internet\\MozillaFirefox.52esrPort\\firefox.exe");
		//System.setProperty("webdriver.firefox.driver", "C:\\Internet\\MozillaFirefox.38\\firefox.exe"); //gibt ein Problem
		System.setProperty("webdriver.firefox.driver", "C:\\Internet\\MozillaFirefox.52esrPort\\firefox.exe"); //gibt ein Problem
		
		WebDriver ffDriver = new  FirefoxDriver();
		//ffDriver.get("https://www.google.de");
		//ffDriver.get("https://www.testing-board.com");
		ffDriver.get("http://rediff.com");
		
		ffDriver.manage().window().maximize();
		
		//ffDriver.findElement(By.xpath(xpathExpression))
	}


}
