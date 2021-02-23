package cucumberOptions;

import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;

// hier muessen die Orte von features und StepDefinitions angegeben werden
// es werden alle ToDos der files.feature im Ordner: features abgearbeitet
@RunWith(Cucumber.class)
@CucumberOptions
		(
		features = "test/features", 
		glue = "stepDefinitions")
		// glue = "stepDefinitions/StepDef_Browser_Chrome_Waehlen")
		// stepNotifications = true)

//die Klasse TestRunner wird per ReMaKli Run As -> JUnit Test aufgerufen
public class TestRunner{

}
