package mod02_StartBrowser;

import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class Start_Firefox {
	
	public static void main(String[] args) {
		
		//System.setProperty("webdriver.firefox.bin", "C:\\Internet\\MozillaFirefox.38esrPort\\firefox.exe");
		//System.setProperty("webdriver.firefox.bin", "C:\\Internet\\MozillaFirefox.33\\firefox.exe"); 
		System.setProperty("webdriver.firefox.bin", "C:\\Internet\\MozillaFirefox.33\\firefox.exe");
		
		FirefoxProfile profile = new FirefoxProfile(new File("C:\\Internet\\MozillaFirefox.33_Profile"));                  
		
		WebDriver ffDriver = new FirefoxDriver(profile);
		//ffDriver.get("https://www.google.de");
		//ffDriver.get("https://www.testing-board.com");
		ffDriver.get("http://rediff.com");
		//ffDriver.get("http://www.w3schools.com");
		//Endlich !!!
		
		ffDriver.manage().window().maximize();
		
		ffDriver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/div[1]/div[3]")).click();
		//ffDriver.findElement(By.id("/html/body/div[6]/div[1]/div[1]/div[1]/div[3]")).click();
		
		//vorheriges Element
		//ffDriver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/div[1]/div[3]/preceding-sibling::a")).click();
		
		//nachfolgendes Element
		//ffDriver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/div[1]/div[3]/following-sibling::a[1]")).click();
		
		//ffDriver.findElement(By.xpath(xpathExpression))
	}

}
