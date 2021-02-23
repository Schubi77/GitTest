package mod03_FunktTestReihe;


import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestKlasseFuerNG
{

	@Test
	@Parameters({ "modul" })
	public void testMethode(String modul)
	{

		System.out.println("TestModul: " + modul);

	}
}
