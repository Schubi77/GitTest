package xDataIO;

//import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class Form_IO_Browser {
	
	String testSeite = "http://mailing.dollartree.com/user/signup.jsp";
	
	WebDriver ffDriver = null;
	WebDriver webDriver = null;
	
	public Form_IO_Browser() {
		
	} //endmethod
	
	// Schreibt Daten in die Website funzt
	public void writeToForm(WebDriver webDriver) throws InterruptedException {

		//Testdaten
		String eMail = "schubert-gf@gmx.de";
		String Nachname = "Schubert";
		String Vorname = "Kurt";
		String PLZ = "68724";
		String Wohnort = "Plankstadt";

		Thread.sleep(2000);

		webDriver.findElement(By.xpath("//*[@id='emailAddress']")).clear();
		webDriver.findElement(By.xpath("//*[@id='emailAddress']")).sendKeys(eMail);

		Thread.sleep(1000);

		webDriver.findElement(By.xpath("//*[@id='zipCode']")).clear();
		webDriver.findElement(By.xpath("//*[@id='zipCode']")).sendKeys(PLZ);

		Thread.sleep(1000);

		webDriver.findElement(By.xpath("//*[@id='firstName']")).clear();
		webDriver.findElement(By.xpath("//*[@id='firstName']")).sendKeys(Vorname);

		Thread.sleep(1000);

		webDriver.findElement(By.xpath("//*[@id='lastName']")).clear();
		webDriver.findElement(By.xpath("//*[@id='lastName']")).sendKeys(Nachname);

	} //endmethod
	
	//Liest Daten von der Website funzt
	public void readFromForm(WebDriver webDriver) throws InterruptedException {

		String ReadeMail = "kein Wert vorhanden";
		String ReadNachname = "kein Wert vorhanden";
		String ReadVorname = "kein Wert vorhanden";
		String ReadPLZ = "kein Wert vorhanden";
		String ReadWohnort = "kein Wert vorhanden";

		ReadeMail = webDriver.findElement(By.xpath("//*[@id='emailAddress']")).getAttribute("value");
		// lange gesucht!!! warum hier G�nsef��chen???

		Thread.sleep(2000);

		ReadPLZ = webDriver.findElement(By.xpath("//*[@id='zipCode']")).getAttribute("value");

		Thread.sleep(2000);

		ReadVorname = webDriver.findElement(By.xpath("//*[@id='firstName']")).getAttribute("value");

		Thread.sleep(2000);

		ReadNachname = webDriver.findElement(By.xpath("//*[@id='lastName']")).getAttribute("value");

		Thread.sleep(1000);

		System.out.println(ReadeMail + ", " + ReadNachname + ", " + ReadVorname + ", " + ReadPLZ);

		Thread.sleep(1000);

	} //endmethod
}
