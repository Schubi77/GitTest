package zVersuche;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Call_TestAblauf_sehr_gut_sich
{

	@Test
	@Parameters({"wort1"})
	public void schreibe_Teil1(String wort1)
	{
		System.out.print(wort1);
	}


	@Test
	@Parameters({"wort2"})
	public void schreibe_Teil2(String wort2)
	{
		System.out.println(wort2);
	}
	
	
	@Test
	@Parameters({"wort3"})
	public void schreibe_Teil3(String wort3)
	{
		System.out.println(wort3);
	}

	
}
