package zResources;

import org.testng.ISuite;
import org.testng.ISuiteListener;

public class TestMethodsListener implements ISuiteListener
{
	@Override
	public void onStart(ISuite suite) {
		System.out.println("TestNG suite default output directory = "+suite.getOutputDirectory());
		}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("TestNG invoked methods = " +suite.getAllInvokedMethods());
	}
	
}
