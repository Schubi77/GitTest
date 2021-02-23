package stepDefinitions;

import mod00_TestFrame.Show_TestGUI;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDef_Browser_Chrome_Waehlen

{
	
	@Given("^Test nicht aktiv$")
	public void test_is_not_running() throws Throwable
	{
		System.out.println("vor Schritt 1");
		if (1==1) { //Test noch nicht gestartet

			System.out.println("Schritt 1");
			check_Button_Chrome();
		}
	}

	@When("^Chrome pruefen$")
	public void check_Button_Chrome() throws Throwable
	{
		System.out.println("vor Schritt 2");
		if (Show_TestGUI.butChrome.isSelected())
		{
			System.out.println("Schritt 2");
			modul_Start_URL_by_Chrome();
		}
	}

	@Then("^Link mit Chrome starten$")
	public void modul_Start_URL_by_Chrome() throws Throwable
	{
		System.out.println("vor Schritt 3");
		 //Pruefen, ob mod02 bereits in Liste
		 Show_TestGUI.listeTestablauf.add("J:/Java.Testing/TestNGxml/mod02_Start_Chrome.xml");
		 System.out.println("Schritt 3 -> Modul in Liste");
	}
}
