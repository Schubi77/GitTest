package mod02_StartBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Start_IE {

	public static void main(String[] args) {

		System.setProperty("webdriver.ie.driver","C:\\Java\\WebDriver\\iedriver.exe"); //ist der ServerDriver32bit
		
		WebDriver ieDriver = new InternetExplorerDriver();
		ieDriver.get("http://google.com");
	}

}