package zTestNG;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AnnoOwnClassNG
{

	//---------------------------------------------------------------------------

	@BeforeMethod
	public void checkConnection()
	{
		System.out.println("checkConnection: This method executes before each Testmethod");
	}


	@Test
	public void normalTest()
	{
		System.out.println("Testteil ohne ParameterInjection");
	}


	@Test(dataProvider = "getData")
	public void paramTest(String username, String password, String id)
	{
		System.out.println("Testteil mit Parametern: " + username + " " + password + " " + id);
	}


	@Test
	@Parameters({ "userID" })
	public void flightBookingVIP(String userID)
	{
		System.out.println("flightBookingVIP: " + userID);
	}


	@AfterTest
	public void endTest()
	{
		System.out.println("Testende: This method executes after all Testcases");
	}


	@DataProvider
	public Object[][] getData()
	{
		//i = number of Tests to run
		//j = number of Parameters to inject
		//Object[][] data 0 new Object[i][j];
		Object[][] data = new Object[3][3];

		data[0][0] = "mike";
		data[0][1] = "123#";
		data[0][2] = "id08";

		//		data[0][0] = whichBrowser;
		//		data[0][1] = whichURL;
		//		data[0][2] = whichMode;

		data[1][0] = "karl";
		data[1][1] = "haz!";
		data[1][2] = "id25";

		data[2][0] = "jane";
		data[2][1] = "pip5";
		data[2][2] = "id17";

		return data;

	}

}
