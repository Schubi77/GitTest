package stepDefinitions_weg;

import mod00_TestFrame.Show_TestGUI;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class stepDef_Warenlager
{
//	Szenario 1: Rückgegebene Ware kommt wieder ins Lager
//	Gegeben ist, dass ein Kunde eine schwarze Hose gekauft hat
//	Und wir daraufhin 3 schwarze Hosen im Lager hatten,
//	Wenn er die Hose zurückgibt und dafür einen Gutschein erhält,
//	Dann werden wir 4 schwarze Hosen im Lager haben.
//
//	Szenario 2: Umgetauschte Ware kommt wieder ins Lager
//	Gegeben ist, dass ein Kunde einen blauen Rock gekauft hat
//	Und wir daraufhin 2 blaue Röcke
//	Und 3 schwarze Röcke im Lager hatten,
//	Wenn er den blauen Rock gegen einen schwarzen Rock tauscht,
//	Dann werden wir 3 blaue Röcke
//	Und 2 schwarze Röcke auf Lager haben.
	
	@Given("^User is on NetBanking landing page$")
	public void User_is_on_NetBanking_landingPage()
	{
		Show_TestGUI.listeTestablauf.add("J:/Java.Testing/TestNG/3_registerTestR.xml");
	}

	@When("^User login into application with username and password$")
	public void User_Login()
	{

	}

	@Then("^Home page is populated$")
	public void Home_page()
	{

	}

	@And("^Cards are displayed$")
	public void Cards_are_displayed()
	{

	}

}
