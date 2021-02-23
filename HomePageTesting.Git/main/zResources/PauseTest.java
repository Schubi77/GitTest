package zResources;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PauseTest
{

	@Parameters("timer")
	@Test
	public void holdTest(String timer)
	{

		if (timer.equalsIgnoreCase("3000"))
		{
			try
			{
				Thread.sleep(3000);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	//@Parameters("pauseTimer")
	//@Test
	public void tryTest(String pauseTimer)
	{
		int value = Integer.parseInt(pauseTimer);
		//if (timer.equalsIgnoreCase("5000"))
		{
			try
			{
				Thread.sleep(value);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
