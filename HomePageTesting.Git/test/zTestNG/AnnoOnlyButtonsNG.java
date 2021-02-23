package zTestNG;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class AnnoOnlyButtonsNG
{
	public static JTextField urlField = new JTextField();
	public static JTextField browserField = new JTextField();
	public static String setURL1 = "https://www.google.de";
	public static String setURL2 = "https://rediff.com";
	public static String setURL3 = "https://www.rahulshettyacademy.com/#/index";
	public static String setURL4 = "https://demo.guru99.com/V4/";
	public static String setURL5 = "for further URL";
	public int strTest = 0;
	public static ActionListener action = null;
	public static JButton startTestButton = new JButton("StartTest");

	public static String whichBrowser;
	public static String whichURL;
	public static String whichMode = "Test";

	@BeforeSuite (dependsOnMethods={"drawForm"})
	public static Boolean goSleep()
	{

		while (startTestButton.getText() == "StartTest")
		{

			try
			{
				Thread.sleep(1000); //Thread.sleep(604800000); //eine Woche
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}

	@BeforeSuite
	public static void drawForm()
	{

		JFrame frame = new JFrame("Teststeuerung");
		JPanel panel = new JPanel(new MigLayout("fillx"));

		// Row 0
		JLabel labURL = new JLabel("URL wählen");
		labURL.setFont(labURL.getFont().deriveFont(16.0f));
		labURL.setHorizontalAlignment(JLabel.CENTER);
		panel.add(labURL, "spanx, growx, wrap");
		// wrap indicates a new row

		// Row 1
		JButton butURL1 = new JButton(setURL1);
		panel.add(butURL1, "alignx trailing");
		panel.add(butURL1, "spanx, growx, wrap");
		//butURL1.addActionListener(e -> urlSetter(setURL1));
		butURL1.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				urlSetter(setURL1);
				//butURL1.setText("RunTest");
			}
		});

		// Row 2
		JButton butURL2 = new JButton(setURL2);
		panel.add(butURL2, "alignx trailing");
		panel.add(butURL2, "spanx, growx, wrap");
		//butURL2.addActionListener(e -> urlSetter(setURL2));
		butURL2.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				urlSetter(setURL2);
				//butURL2.setText("RunTest");
			}
		});

		// Row 3
		JButton butURL3 = new JButton(setURL3);
		panel.add(butURL3, "alignx trailing");
		panel.add(butURL3, "spanx, growx, wrap");
		//butURL3.addActionListener(e -> urlSetter(setURL3));
		butURL3.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				urlSetter(setURL3);
				//butURL3.setText("RunTest");
			}
		});

		// Row 6
		panel.add(urlField, "alignx trailing, wrap");
		panel.add(urlField, "spanx, growx, wrap");

		// Row 7
		JLabel labBrowser = new JLabel("Browser wählen");
		labBrowser.setFont(labBrowser.getFont().deriveFont(16.0f));
		labBrowser.setHorizontalAlignment(JLabel.CENTER);
		panel.add(labBrowser, "spanx, growx, wrap");
		// wrap indicates a new row

		// Row 8
		JButton chromeButton = new JButton("Start Chrome");
		panel.add(chromeButton, "alignx trailing");
		panel.add(chromeButton, "spanx, growx, wrap");
		//chromeButton.addActionListener(e -> callChrome());
		chromeButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				browserSetter("Chrome");
				//butURL5.setText("RunTest");
			}
		});

		// Row 11
		JButton ff66Button = new JButton("Start Firefox66");
		panel.add(ff66Button, "alignx trailing");
		panel.add(ff66Button, "spanx, growx, wrap");
		//ff66Button.addActionListener(e -> callFF66());
		ff66Button.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				browserSetter("Firefox");
				//butURL5.setText("RunTest");
			}
		});

		// Row 13
		JButton explorerButton = new JButton("Start Explorer");
		panel.add(explorerButton, "alignx trailing");
		panel.add(explorerButton, "spanx, growx, wrap");

		// Row 14
		panel.add(browserField, "alignx trailing, wrap");
		panel.add(browserField, "spanx, growx, wrap");

		// Row 15
		//JButton startTestButton = new JButton("StartTest");
		panel.add(startTestButton, "alignx trailing");
		panel.add(startTestButton, "spanx, growx, wrap");
		startTestButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				// delegate to event handler method
				startTestButton.setText("RunTest");
				//goSleep();
			}
		});

		frame.setContentPane(panel);
		// Resizes automatically
		frame.pack();
		// Centers automatically
		frame.setLocationRelativeTo(null);
		// Exit when the frame is closed
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setVisible(true);

	} //endmethod


	//---------------------------------------------------------------------------

	public static void urlSetter(String setURL)
	{

		whichURL = setURL;
		urlField.setText(setURL);
		urlField.setHorizontalAlignment(JTextField.CENTER);

	}


	public static void browserSetter(String setBrowser)
	{

		whichBrowser = setBrowser;
		browserField.setText(setBrowser);
		browserField.setHorizontalAlignment(JTextField.CENTER);

	}


	//---------------------------------------------------------------------------


	@BeforeTest
	public void getParameter()
	{
		System.out.println("getParameter: This method executes before all Testcases");
		System.out.println(whichBrowser + " - " + whichURL + " - " + whichMode);
	}

}
