package mod03_FunktTestReihe;

import java.net.URI;
import java.net.URL;
import javax.servlet.http.HttpServletRequest;
import org.testng.annotations.Parameters;
import mod00_TestFrame.ShowTestGUI;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class m03_Register
{
	@Given("^User auf Homepage$")
	@Parameters({"browser"})
	public String start_Browser(String browser) throws Throwable
	{
		
	}
	
	
	
	
	public String check_user_on_homepage(String browser) throws Throwable
	{
		String currUrl = webDriver.getCurrentUrl();
		
		//URL currUrl = new URL(request.getRequestURL().toString());

	    String host  = currUrl.getHost();
	    String userInfo = currUrl.getUserInfo();
	    String scheme = currUrl.getProtocol();
	    int port = currUrl.getPort();
	    String path = request.getAttribute("javax.servlet.forward.request_uri");
	    String query = request.getAttribute("javax.servlet.forward.query_string");
	    URI uri = new URI(scheme,userInfo,host,port,path,query,null);
	    return uri.toString();
		}
	}

	@When("^Chrome pruefen$")
	public void check_Button_Chrome() throws Throwable
	{
		System.out.println("vor Schritt 2");
		if (ShowTestGUI.butChrome.isSelected())
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
		 ShowTestGUI.listeTestablauf.add("J:/Java.Testing/TestNGxml/mod02_Start_Chrome.xml");
		 System.out.println("Schritt 3 -> Modul in Liste");
	}

}
