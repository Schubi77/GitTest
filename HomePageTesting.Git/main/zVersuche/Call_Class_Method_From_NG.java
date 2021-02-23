package zVersuche;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Call_Class_Method_From_NG
{

	@Test
	@Parameters({"wort1"})
	public void schreibe_Teil1(String wort1)
	{
		System.out.print("Halli" + wort1);
	}
	
	@Test
	@Parameters({"wort2"})
	public void schreibe_Teil2(String wort2)
	{
		System.out.println(wort2);
	}
	
}
