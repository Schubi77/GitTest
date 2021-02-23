package stepDefinitions_weg;

import mod00_TestFrame.Show_TestGUI;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class stepDef_LogIn
{
	@Given("^User is on NetBanking landing page$")
	public void User_is_on_NetBanking_landingPage()
	{
		Show_TestGUI.listeTestablauf.add("J:/Java.Testing/TestNGxml/3_registerTestR.xml");
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
