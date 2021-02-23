package mod00_TestFrame;

// Hinweise: 
// Test: Cookies
// Bei den Cookies wird die Anzahl der Aufrufe in das Cookie eingetragen und 
// bei Testende die Zahl ausgelesen und mit der tatsächlichen Anzahl verglichen.
// Test: DSGVO
// Es wird geprüft, ob beim Aufruf der Sete der Bestätigungshaken in der CheckBox
// nicht gesetzt ist, also gesetzt werden muss -> Opt In
// Es wird geprüft, ob eine Bestätigungs eMail verschickt wird, eine eineindeutige Bestätigungsseite
// generiert wird und das Anklicken dieses Bestätigungsbuttons auf der eigens erzeugten
// URL funktioniert und in die Userdaten (DB) eingetragen wird -> Double Opt In

// Prinzipieller Ablauf: Durch die Buttons werden die jeweiligen Tests als XML-file zu einer
// Liste hinzu addiert (oder entfernt). Diese Liste wird dann durch "Testablauf starten" 
// an ein TestNG Objekt per testNG.setTestSuites(listeTestablauf) uebergeben und dann per 
// testNG.run() ausgefuehrt

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import net.miginfocom.swing.MigLayout;
import org.testng.SkipException;
import org.testng.TestNG;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.collections.Lists;
//import xDataIO.DataBase_IO;
//import xDataIO.Excel_IO_jxl;
//import xDataIO.Excel_IO_xssf;
//import zBrowser.StartBrowser;

public class Show_TestGUI
{
	//Area Intro	

	static JTextField appTextField = new JTextField();
	static JTextField browserTextField = new JTextField();
	static String setApp1 = "https://www.google.de";
	static String setApp2 = "https://rediff.com";
	static String setApp3 = "https://www.rahulshettyacademy.com/#/index";
	static String setApp4 = "https://demo.guru99.com/V4/";
	static String setApp5 = "for further App";

	static String whichBrowser;
	static String whichApp;
	static String whichMode;

	// MainPanel
	static JPanel panGesamt = new JPanel(new MigLayout("filly")); // again, we want to fill the vertical space so the 2 pans will have the same height
	// MainLab
	static JLabel labGesamtHead = new JLabel();

	// DataFeed
	static JPanel panDataFeedGrey = new JPanel(new MigLayout("fillx"));
	static JLabel labDataFeedGrey = new JLabel("Testdaten");

	static JPanel panLadeXML = new JPanel(new MigLayout("fillx"));
	static JLabel labLadeXML = new JLabel("aus XML");
	static JToggleButton butLadeXML = new JToggleButton("XML laden");
	static JTextField tfLadeXML = new JTextField("Testdaten.xml");
	static JPanel panLadeExcel_jxl = new JPanel(new MigLayout("fillx"));
	static JLabel labLadeExcel_jxl = new JLabel("aus Excel_jxl");
	static JToggleButton butLadeExcel_jxl = new JToggleButton("Excel_jxl laden");
	static JTextField tfLadeExcel_jxl = new JTextField("Testdaten.jxl");
	static JPanel panLadeExcel_xssf = new JPanel(new MigLayout("fillx"));
	static JLabel labLadeExcel_xssf = new JLabel("aus Excel_xssf");
	static JToggleButton butLadeExcel_xssf = new JToggleButton("Excel_xssf laden");
	static JTextField tfLadeExcel_xssf = new JTextField("Testdaten.xssf");
	static JPanel panLadeTabelle = new JPanel(new MigLayout("fillx"));
	static JLabel labLadeTabelle = new JLabel("aus Tabelle");
	static JToggleButton butLadeTabelle = new JToggleButton("Tabelle laden");
	static JTextField tfLadeTabelle = new JTextField("SELECT Testdaten");
	static int countButtonsLadeDaten = 0;
	static boolean bolpanLadeXMLBlue = false;
	static boolean bolpanLadeExcel_jxlBlue = false;
	static boolean bolpanLadeExcel_xssfBlue = false;
	static boolean bolpanLadeTabelleBlue = false;

	// Browser
	static JPanel panBrowserGrey = new JPanel(new MigLayout("fillx"));
	static JLabel labBrowserGrey = new JLabel();
	static JPanel panBrowser = new JPanel(new MigLayout("fillx"));
	static JLabel labBrowser = new JLabel();
	static int countButtonsBrowser = 0;
	static int countButtonsBrowserAlt = 0;
	static boolean bolpanBrowserBlue = false;

	public static JToggleButton butChrome = new JToggleButton("Chrome");
	static JToggleButton butEdge = new JToggleButton("Edge");
	static JToggleButton butExplorer = new JToggleButton("Explorer");
	static JToggleButton butFf54 = new JToggleButton("Firefox54");
	static JToggleButton butFf66 = new JToggleButton("Firefox66");
	static JToggleButton butFf80 = new JToggleButton("Firefox80");
	static JToggleButton butOpera = new JToggleButton("Opera");
	static JToggleButton butWf55 = new JToggleButton("Waterfox");

	// TestVarianten
	static JPanel panTestVariantenGrey = new JPanel(new MigLayout("fillx"));
	static JLabel labTestVariantenGrey = new JLabel();
	static JLabel labTestVariantenUntenGrey = new JLabel();
	static JLabel labTestVariantenBlack = new JLabel();

	// Tests in Reihe
	static JPanel panTestsInReiheBlack = new JPanel(new MigLayout("fillx"));
	static JLabel labTestsInReiheBlack = new JLabel();
	static int countButtonsTestInReihe = 0;
	static int countButtonsTestInReiheAlt = 0;
	static boolean bolPanTestInReiheBlack = false;
	static boolean bolPanTestsInReiheBlue = false;

	static JPanel panTestsInReiheResultBlack = new JPanel(new MigLayout("fillx"));
	static JLabel labTestsInReiheResultBlack = new JLabel();
	static String strResultNewUserR = "";
	static String strResultProdVarR = "";
	static String strResultRegisterR = "";
	static String strResultLoginR = "";
	static String strResultLagerR = "";
	static String strResultWaKoR = "";
	static String strResultRabattR = "";
	static String strResultMwStR = "";
	static String strResultBezahlungR = "";
	static String strResultBestellungR = "";
	static String strResultVersandR = "";
	static String strResultValutaR = "";
	static String strResultMahnungR = "";
	static String strResultCookiesR = "";
	static String strResultDatSchuR = "";

	// Tests Parallel
	static JPanel panTestsParallelBlack = new JPanel(new MigLayout("fillx"));
	static JLabel labTestsParallelBlack = new JLabel();
	static int countButtonsTestParallel = 0;
	static int countButtonsTestParallelAlt = 0;
	static boolean bolPanTestParallelBlack = false;
	static boolean bolPanTestsParallelBlue = false;

	// Tests Parallel Slider
	static JPanel panParallelQuantityBlack = new JPanel(new MigLayout("fillx"));
	static JLabel labParallelQuantityBlack = new JLabel();

	static JSlider sliderNewUser = new JSlider(); //default is (0, 100, 50)
	static JSlider sliderProdVar = new JSlider();
	static JSlider sliderRegister = new JSlider();
	static JSlider sliderLogin = new JSlider();
	static JSlider sliderLager = new JSlider();
	static JSlider sliderWaKo = new JSlider();
	static JSlider sliderRabatt = new JSlider();
	static JSlider sliderMwSt = new JSlider();
	static JSlider sliderBezahlung = new JSlider();
	static JSlider sliderBestellung = new JSlider();
	static JSlider sliderVersand = new JSlider();
	static JSlider sliderValuta = new JSlider();
	static JSlider sliderMahnung = new JSlider();
	static JSlider sliderCookies = new JSlider();
	static JSlider sliderDatSchu = new JSlider();

	static JLabel labNewUser = new JLabel("Neuer User: 1x");
	static JLabel labProdVar = new JLabel("Prod.Varianten: 1x");
	static JLabel labRegister = new JLabel("Registrierung: 1x");
	static JLabel labLogin = new JLabel("Login: 1x");
	static JLabel labLager = new JLabel("Lagerpruefung: 1x");
	static JLabel labWaKo = new JLabel("Warenkorb: 1x");
	static JLabel labRabatt = new JLabel("Rabatt: 1x");
	static JLabel labMwSt = new JLabel("MwSt: 1x");
	static JLabel labBezahlung = new JLabel("Bezahlung: 1x");
	static JLabel labBestell = new JLabel("Bestellung: 1x");
	static JLabel labVersand = new JLabel("Versand: 1x");
	static JLabel labValuta = new JLabel("Valuta: 1x");
	static JLabel labMahnung = new JLabel("Mahnung: 1x");
	static JLabel labCookies = new JLabel("Cookies: 1x");
	static JLabel labDatSchu = new JLabel("Datenschutz: 1x");

	// Tests ParaPara
	static JPanel panTestsParaParaBlack = new JPanel(new MigLayout("fillx"));
	static JLabel labTestsParaParaBlack = new JLabel();
	static int countCheckBoxParaPara = 0;
	static int countCheckBoxParaParaAlt = 0;
	static boolean bolPanTestsParaParaBlack = false;
	static boolean bolPanTestsParaParaBlue = false;

	static Border secondBorder = new LineBorder(Color.orange, 8); //CheckBox Border
	static String strParaResultNewUserP = "";
	static String strParaResultProdVarP = "";
	static String strParaResultRegisterP = "";
	static String strParaResultLoginP = "";
	static String strParaResultLagerP = "";
	static String strParaResultWaKoP = "";
	static String strParaResultRabattP = "";
	static String strParaResultMwStP = "";
	static String strParaResultBezahlungP = "";
	static String strParaResultBestellungP = "";
	static String strParaResultVersandP = "";
	static String strParaResultValutaP = "";
	static String strParaResultMahnungP = "";
	static String strParaResultCookiesP = "";
	static String strParaResultDatSchuP = "";

	// Tests Parallel Result
	static JPanel panTestsParallelResultBlack = new JPanel(new MigLayout("fillx"));
	static JLabel labTestsParallelResultBlack = new JLabel();
	static String strResultNewUserP = "";
	static String strResultProdVarP = "";
	static String strResultRegisterP = "";
	static String strResultLoginP = "";
	static String strResultLagerP = "";
	static String strResultWaKoP = "";
	static String strResultRabattP = "";
	static String strResultMwStP = "";
	static String strResultBezahlungP = "";
	static String strResultBestellungP = "";
	static String strResultVersandP = "";
	static String strResultValutaP = "";
	static String strResultMahnungP = "";
	static String strResultCookiesP = "";
	static String strResultDatSchuP = "";

	// Tests Error Provocation
	static JPanel panTestsErrorProvBlack = new JPanel(new MigLayout("fillx"));
	static JLabel labTestsErrorProvBlack = new JLabel();
	static int countButtonsErrorProv = 0;
	static int countButtonsErrorProvAlt = 0;
	static boolean bolPanErrorProvBlack = false;
	static boolean bolpanErrorProvBlue = false;

	static JPanel panTestsErrorProvResultBlack = new JPanel(new MigLayout("fillx"));
	static JLabel labTestsErrorProvResultBlack = new JLabel();
	static String strResultFehlbedienung = "";
	static String strResultDatenFehler = "";
	static String strResultBezahlFehler = "";
	static String strResultConnBroken = "";
	static String strResultDBfehler = "";
	static String strResultArtikelMinus = "";
	static String strResultWaKoFehler = "";

	// Teststeuerung (hat noch 3 Unterpanels)
	static JPanel panTestSteuerungGrey = new JPanel(new MigLayout("fillx"));
	static JLabel labTestSteuerungGrey = new JLabel();

	static Border lineBlack = BorderFactory.createLineBorder(Color.black);
	static Border lineGreen = BorderFactory.createLineBorder(Color.green);
	static Border checkBoxBorder = new LineBorder(Color.BLUE, 6);

	//static JButton butLoescheTest = new JButton();
	static JToggleButton butLoescheTest = new JToggleButton();
	static JToggleButton butStarteTest = new JToggleButton();
	static JToggleButton butStartZeitTest = new JToggleButton();
	static JToggleButton butTestAbbrechen = new JToggleButton();

	static boolean bolPanTestStarten = false;
	static boolean bolPanTestGestartet = false;
	static boolean bolPanTestBeendet = false;

	static TestNG testNG = new TestNG();
	public static Properties prop = new Properties();
	public static List<String> listeTestablauf = Lists.newArrayList();
	public static List<String> listeTestablaufAlt = Lists.newArrayList();


	public static void main(String[] args) throws InterruptedException
	{

		System.out.println("Achtung, falls benoetigt, DB einschalten !" + "\n");

		// Methodenaufrufe
		try
		{
			writeProperty();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		drawForm();

	} //endmethod


	public static void writeProperty() throws IOException
	{
		//try (OutputStream output = new FileOutputStream("j:\\Java.Testing\\HomePageTesting\\main\\mod00_PrepareTest\\Parameter.properties")) 
		try (OutputStream output = new FileOutputStream("j:/Java.Testing/HomePageTesting/main/mod00_PrepareTest/Parameter.properties"))
		{

			//Properties prop = new Properties();

			// set the properties value
			prop.setProperty("db.url", "google");
			prop.setProperty("db.user", "anyName");
			prop.setProperty("db.password", "anyPassword");

			// save properties to project root folder
			prop.store(output, null);

			System.out.println(prop);

		} catch (IOException io)
		{
			io.printStackTrace();
		}

	}


	@BeforeSuite(dependsOnMethods = { "drawForm" })
	public static Boolean goSleep()
	{

		//if (startTestButton.getText() == "Hallo")

		//while (strTest == 0)

		while (butStarteTest.getText() == "StartTest")
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
	} //endmethod goSleep()


	@BeforeSuite
	public static void drawForm()
	{

		JFrame frame = new JFrame(); //("Teststeuerung");
		frame.setTitle("GUI Teststeuerung (Basisversion)");
		JLabel labFrame = new JLabel("Test");
		labFrame.setForeground(Color.lightGray);
		labFrame.setHorizontalAlignment(JLabel.CENTER);
		labFrame.setBackground(Color.lightGray);
		labFrame.setBorder(lineBlack);
		frame.add(labFrame);

		//endArea Intro

		//-------------------------------------------------------------------------------

		//Area DataFeed

		//JPanel panDataFeedGrey = new JPanel(new MigLayout("fillx"));
		panDataFeedGrey.setBackground(Color.lightGray);

		//Headline
		//JLabel labDataFeedGrey = new JLabel("Testdaten");
		labDataFeedGrey.setFont(labDataFeedGrey.getFont().deriveFont(16.0f));
		labDataFeedGrey.setHorizontalAlignment(JLabel.CENTER);
		panDataFeedGrey.add(labDataFeedGrey, "spanx, growx, wrap");
		//blau machen

		// Lade XML
		//JPanel panLadeXML = new JPanel(new MigLayout("fillx"));
		panLadeXML.setBackground(Color.darkGray);

		// Label
		// JLabel labLadeXML = new JLabel("aus XML");
		labLadeXML.setPreferredSize(new Dimension(112, 32));
		labLadeXML.setFont(labLadeXML.getFont().deriveFont(16.0f));
		labLadeXML.setForeground(Color.lightGray);
		labLadeXML.setHorizontalAlignment(JLabel.CENTER);
		labLadeXML.setBackground(Color.lightGray);
		panLadeXML.add(labLadeXML, "alignx trailing");
		panLadeXML.add(labLadeXML, "spanx, growx, wrap");
		labLadeXML.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (bolpanLadeXMLBlue == false)
				{
					panLadeXML.setBackground(Color.blue);
					labLadeXML.setText("XML verwendet");
					labLadeXML.setForeground(Color.white);
					butLadeXML.setText("aus Datei");
					bolpanLadeXMLBlue = true;

				} else
				{
					panLadeXML.setBackground(Color.darkGray);
					labLadeXML.setText("XML laden");
					labLadeXML.setForeground(Color.lightGray);
					butLadeXML.setText("XML laden");
					bolpanLadeXMLBlue = false;
				}
			}

		});

		// Button
		// JToggleButton butLadeXML = new JToggleButton("XML laden");
		butLadeXML.setPreferredSize(new Dimension(112, 32));
		panLadeXML.add(butLadeXML, "alignx trailing");
		panLadeXML.add(butLadeXML, "spanx, growx, wrap");

		// Textfeld
		// JTextField tfLadeXML = new JTextField("Testdaten.xml");
		tfLadeXML.setPreferredSize(new Dimension(112, 32));
		tfLadeXML.setFont(tfLadeXML.getFont().deriveFont(12.0f));
		tfLadeXML.setHorizontalAlignment(JLabel.CENTER);
		tfLadeXML.setBackground(Color.lightGray);
		tfLadeXML.setOpaque(true);
		panLadeXML.add(tfLadeXML, "alignx trailing, wrap");
		panLadeXML.add(tfLadeXML, "spanx, growx, wrap");

		butLadeXML.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butLadeXML.isSelected())
				{
					activateDatenXML();
					deactivateDatenExcel_jxl();
					deactivateDatenExcel_xssf();
					deactivateDatenTabelle();

					if (countButtonsLadeDaten < 1)
					{
						countButtonsLadeDaten++;
					}
					checkAnyPanelButtonChanged();

				} else
				{
					deactivateDatenXML();

					if (countButtonsLadeDaten > 0)
					{
						countButtonsLadeDaten--;
					}
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Lade Excel_jxl
		//JPanel panLadeExcel_jxl = new JPanel(new MigLayout("fillx"));
		panLadeExcel_jxl.setBackground(Color.darkGray);

		// Label
		//JLabel labLadeExcel_jxl = new JLabel("aus Excel_jxl");
		labLadeExcel_jxl.setPreferredSize(new Dimension(112, 32));
		labLadeExcel_jxl.setFont(labLadeExcel_jxl.getFont().deriveFont(16.0f));
		labLadeExcel_jxl.setForeground(Color.lightGray);
		labLadeExcel_jxl.setHorizontalAlignment(JLabel.CENTER);
		labLadeExcel_jxl.setBackground(Color.lightGray);
		panLadeExcel_jxl.add(labLadeExcel_jxl, "alignx trailing");
		panLadeExcel_jxl.add(labLadeExcel_jxl, "spanx, growx, wrap");
		labLadeExcel_jxl.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (bolpanLadeExcel_jxlBlue == false)
				{
					panLadeExcel_jxl.setBackground(Color.blue);
					labLadeExcel_jxl.setText("Excel verwendet");
					labLadeExcel_jxl.setForeground(Color.white);
					butLadeExcel_jxl.setText("aus Datei");
					bolpanLadeExcel_jxlBlue = true;

				} else
				{
					panLadeExcel_jxl.setBackground(Color.darkGray);
					labLadeExcel_jxl.setText("Excel_jxl laden");
					labLadeExcel_jxl.setForeground(Color.lightGray);
					butLadeExcel_jxl.setText("Excel_jxl laden");
					bolpanLadeExcel_jxlBlue = false;
				}
			}

		});

		// Button
		// JToggleButton butLadeExcel_jxl = new JToggleButton("Excel_jxl laden");
		butLadeExcel_jxl.setPreferredSize(new Dimension(112, 32));
		panLadeExcel_jxl.add(butLadeExcel_jxl, "alignx trailing");
		panLadeExcel_jxl.add(butLadeExcel_jxl, "spanx, growx, wrap");

		// Textfeld
		// JTextField tfLadeExcel_jxl = new JTextField("Testdaten.xml");
		tfLadeExcel_jxl.setPreferredSize(new Dimension(112, 32));
		tfLadeExcel_jxl.setFont(tfLadeExcel_jxl.getFont().deriveFont(12.0f));
		tfLadeExcel_jxl.setHorizontalAlignment(JLabel.CENTER);
		tfLadeExcel_jxl.setBackground(Color.lightGray);
		tfLadeExcel_jxl.setOpaque(true);
		panLadeExcel_jxl.add(tfLadeExcel_jxl, "alignx trailing, wrap");
		panLadeExcel_jxl.add(tfLadeExcel_jxl, "spanx, growx, wrap");

		butLadeExcel_jxl.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butLadeExcel_jxl.isSelected())
				{
					activateDatenExcel_jxl();
					deactivateDatenXML();
					deactivateDatenExcel_xssf();
					deactivateDatenTabelle();

					if (countButtonsLadeDaten < 1)
					{
						countButtonsLadeDaten++;
					}
					checkAnyPanelButtonChanged();

				} else
				{
					deactivateDatenExcel_jxl();

					if (countButtonsLadeDaten > 0)
					{
						countButtonsLadeDaten--;
					}
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Lade Excel_xssf
		//JPanel panLadeExcel_xssf = new JPanel(new MigLayout("fillx"));
		panLadeExcel_xssf.setBackground(Color.darkGray);

		// Label
		//JLabel labLadeExcel_xssf = new JLabel("aus Excel_xssf");
		labLadeExcel_xssf.setPreferredSize(new Dimension(112, 32));
		labLadeExcel_xssf.setFont(labLadeExcel_xssf.getFont().deriveFont(16.0f));
		labLadeExcel_xssf.setForeground(Color.lightGray);
		labLadeExcel_xssf.setHorizontalAlignment(JLabel.CENTER);
		labLadeExcel_xssf.setBackground(Color.lightGray);
		panLadeExcel_xssf.add(labLadeExcel_xssf, "alignx trailing");
		panLadeExcel_xssf.add(labLadeExcel_xssf, "spanx, growx, wrap");
		labLadeExcel_xssf.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (bolpanLadeExcel_xssfBlue == false)
				{
					panLadeExcel_xssf.setBackground(Color.blue);
					labLadeExcel_xssf.setText("Excel verwendet");
					labLadeExcel_xssf.setForeground(Color.white);
					butLadeExcel_xssf.setText("aus Datei");
					bolpanLadeExcel_xssfBlue = true;

				} else
				{
					panLadeExcel_xssf.setBackground(Color.darkGray);
					labLadeExcel_xssf.setText("Excel_xssf laden");
					labLadeExcel_xssf.setForeground(Color.lightGray);
					butLadeExcel_xssf.setText("Excel_xssf laden");
					bolpanLadeExcel_xssfBlue = false;
				}
			}
		});

		// Button
		// JToggleButton butLadeExcel_xssf = new JToggleButton("Excel_xssf laden");
		butLadeExcel_xssf.setPreferredSize(new Dimension(112, 32));
		panLadeExcel_xssf.add(butLadeExcel_xssf, "alignx trailing");
		panLadeExcel_xssf.add(butLadeExcel_xssf, "spanx, growx, wrap");

		// Textfeld
		// JTextField tfLadeExcel_xssf = new JTextField("Testdaten.xml");
		tfLadeExcel_xssf.setPreferredSize(new Dimension(112, 32));
		tfLadeExcel_xssf.setFont(tfLadeExcel_xssf.getFont().deriveFont(12.0f));
		tfLadeExcel_xssf.setHorizontalAlignment(JLabel.CENTER);
		tfLadeExcel_xssf.setBackground(Color.lightGray);
		tfLadeExcel_xssf.setOpaque(true);
		panLadeExcel_xssf.add(tfLadeExcel_xssf, "alignx trailing, wrap");
		panLadeExcel_xssf.add(tfLadeExcel_xssf, "spanx, growx, wrap");

		butLadeExcel_xssf.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butLadeExcel_xssf.isSelected())
				{
					activateDatenExcel_xssf();
					deactivateDatenExcel_jxl();
					deactivateDatenXML();
					deactivateDatenTabelle();

					if (countButtonsLadeDaten < 1)
					{
						countButtonsLadeDaten++;
					}
					checkAnyPanelButtonChanged();

				} else
				{
					deactivateDatenExcel_xssf();

					if (countButtonsLadeDaten > 0)
					{
						countButtonsLadeDaten--;
					}
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Lade Tabelle
		//JPanel panLadeTabelle = new JPanel(new MigLayout("fillx"));
		panLadeTabelle.setBackground(Color.darkGray);

		// Label
		//JLabel labLadeTabelle = new JLabel("aus Tabelle");
		labLadeTabelle.setPreferredSize(new Dimension(112, 32));
		labLadeTabelle.setFont(labLadeTabelle.getFont().deriveFont(16.0f));
		labLadeTabelle.setForeground(Color.lightGray);
		labLadeTabelle.setHorizontalAlignment(JLabel.CENTER);
		labLadeTabelle.setBackground(Color.lightGray);
		panLadeTabelle.add(labLadeTabelle, "alignx trailing");
		panLadeTabelle.add(labLadeTabelle, "spanx, growx, wrap");
		labLadeTabelle.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (bolpanLadeTabelleBlue == false)
				{
					panLadeTabelle.setBackground(Color.blue);
					labLadeTabelle.setText("Tabelle verwendet");
					labLadeTabelle.setForeground(Color.white);
					butLadeTabelle.setText("aus Datenbank");
					bolpanLadeTabelleBlue = true;

				} else
				{
					panLadeTabelle.setBackground(Color.darkGray);
					labLadeTabelle.setText("Tabelle laden");
					labLadeTabelle.setForeground(Color.lightGray);
					butLadeTabelle.setText("Tabelle laden");
					bolpanLadeTabelleBlue = false;
				}
			}

		});

		// Button
		// JToggleButton butLadeTabelle = new JToggleButton("Tabelle laden");
		butLadeTabelle.setPreferredSize(new Dimension(112, 32));
		panLadeTabelle.add(butLadeTabelle, "alignx trailing");
		panLadeTabelle.add(butLadeTabelle, "spanx, growx, wrap");

		// Textfeld
		// JTextField tfLadeTabelle = new JTextField("Testdaten.xml");
		tfLadeTabelle.setPreferredSize(new Dimension(112, 32));
		tfLadeTabelle.setFont(tfLadeTabelle.getFont().deriveFont(12.0f));
		tfLadeTabelle.setHorizontalAlignment(JLabel.CENTER);
		tfLadeTabelle.setBackground(Color.lightGray);
		tfLadeTabelle.setOpaque(true);
		panLadeTabelle.add(tfLadeTabelle, "alignx trailing, wrap");
		panLadeTabelle.add(tfLadeTabelle, "spanx, growx, wrap");

		butLadeTabelle.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butLadeTabelle.isSelected())
				{
					activateDatenTabelle();
					deactivateDatenExcel_jxl();
					deactivateDatenExcel_xssf();
					deactivateDatenXML();

					if (countButtonsLadeDaten < 1)
					{
						countButtonsLadeDaten++;
					}
					checkAnyPanelButtonChanged();

				} else
				{
					deactivateDatenTabelle();

					if (countButtonsLadeDaten > 0)
					{
						countButtonsLadeDaten--;
					}
					checkAnyPanelButtonChanged();
				}
			}
		});

		panDataFeedGrey.add(panLadeXML, "spanx, growx, wrap");
		panDataFeedGrey.add(panLadeExcel_jxl, "spanx, growx, wrap");
		panDataFeedGrey.add(panLadeExcel_xssf, "spanx, growx, wrap");
		panDataFeedGrey.add(panLadeTabelle, "spanx, growx, wrap");

		//endArea DataFeed

		//-------------------------------------------------------------------------------

		//Area Browser

		//JPanel panBrowser = new JPanel(new MigLayout("wrap 1"));
		panBrowserGrey.setBackground(Color.lightGray);
		panBrowserGrey.add(labBrowserGrey, "spanx, growx, wrap");
		panBrowserGrey.add(labBrowser, "spanx, growx, wrap");
		panBrowserGrey.add(panBrowser, "spanx, growx, wrap");
		panBrowser.setBackground(Color.darkGray);

		//Headline
		labBrowserGrey.setText(" Browser ");
		labBrowserGrey.setFont(labBrowser.getFont().deriveFont(16.0f));
		labBrowserGrey.setForeground(Color.black);
		labBrowserGrey.setHorizontalAlignment(JLabel.CENTER);
		panBrowserGrey.add(labBrowser, "spanx, growx, wrap");
		labBrowser.setText(" Browser wählen ");
		labBrowser.setFont(labBrowser.getFont().deriveFont(16.0f));
		labBrowser.setForeground(Color.lightGray);
		labBrowser.setHorizontalAlignment(JLabel.CENTER);
		panBrowser.add(labBrowser, "spanx, growx, wrap");
		labBrowser.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (bolpanBrowserBlue == false)
				{
					//labBrowserGrey.setText("Als Browser");
					//panBrowser.setBackground(Color.blue);
					labBrowserGrey.setText("Tests wurden");
					panBrowser.setBackground(Color.blue);
					labBrowser.setText("durchgeführt:");
					labBrowser.setForeground(Color.white);
					butLadeTabelle.setText("aus Datei");
					bolpanBrowserBlue = true;

				} else
				{
					labBrowserGrey.setText(" Browser ");
					panBrowser.setBackground(Color.darkGray);
					labBrowser.setText("Browser wählen");
					labBrowser.setForeground(Color.lightGray);
					butLadeTabelle.setText("Tabelle laden");
					bolpanBrowserBlue = false;
				}
			}

		});

		//butChrome
		//JToggleButton butChrome = new JToggleButton("Chrome");
		butChrome.setPreferredSize(new Dimension(112, 32));
		panBrowser.add(butChrome, "alignx trailing");
		panBrowser.add(butChrome, "spanx, growx, wrap");
		butChrome.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butChrome.isSelected())
				{
					butChrome.setText("mit Chrome");
					butChrome.setForeground(Color.blue);
					countButtonsBrowser++;
					checkAnyPanelButtonChanged();
					browserSetter("Chrome");
					//listeTestablauf.add("J:/Java.Testing/HomePageTesting/TestNG.xml/mod01_Start_Chromium73.xml");
					//zu Testzwecken:
					listeTestablauf.add("J:/Java.Testing/HomePageTesting/TestNG.xml/mod00_Call_TestAblauf_1400a.xml");
					try (OutputStream output = new FileOutputStream("j:/Java.Testing/HomePageTesting/main/mod00_PrepareTest/Parameter.properties"))
					{
						prop.setProperty("but.Chrome", "On");
						prop.store(output, null);
						System.out.println(prop);

					} catch (IOException io)
					{
						io.printStackTrace();
					}

				} else
				{
					butChrome.setText("Chrome");
					butChrome.setForeground(Color.black);
					countButtonsBrowser--;
					checkAnyPanelButtonChanged();
					//listeTestablauf.remove("J:/Java.Testing/HomePageTesting/TestNG.xml/mod01_Start_Chromium73.xml.xml");
					//zu Testzwecken:
					listeTestablauf.remove("J:/Java.Testing/HomePageTesting/TestNG.xml/mod00_Call_TestAblauf_1400a.xml");

					try (OutputStream output = new FileOutputStream("j:/Java.Testing/HomePageTesting/main/mod00_PrepareTest/Parameter.properties"))
					{
						prop.setProperty("but.Chrome", "Off");
						prop.store(output, null);
						System.out.println(prop);

					} catch (IOException io)
					{
						io.printStackTrace();
					}
				}
			}
		});

		//butEdge
		//JToggleButton butEdge = new JToggleButton("Edge");
		butEdge.setPreferredSize(new Dimension(112, 32));
		panBrowser.add(butEdge, "alignx trailing");
		panBrowser.add(butEdge, "spanx, growx, wrap");
		butEdge.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butEdge.isSelected())
				{
					butEdge.setText("mit Edge");
					butEdge.setForeground(Color.blue);
					countButtonsBrowser++;
					checkAnyPanelButtonChanged();
					browserSetter("Edge");

				} else
				{
					butEdge.setText("Edge");
					butEdge.setForeground(Color.black);
					countButtonsBrowser--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		//butExplorer
		//JToggleButton butExplorer = new JToggleButton("Explorer");
		butExplorer.setPreferredSize(new Dimension(112, 32));
		panBrowser.add(butExplorer, "alignx trailing");
		panBrowser.add(butExplorer, "spanx, growx, wrap");
		butExplorer.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butExplorer.isSelected())
				{
					butExplorer.setText("mit Explorer");
					butExplorer.setForeground(Color.blue);
					countButtonsBrowser++;
					checkAnyPanelButtonChanged();
					browserSetter("Explorer");

				} else
				{
					butExplorer.setText("Explorer");
					butExplorer.setForeground(Color.black);
					countButtonsBrowser--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		//butFf54
		//JToggleButton butFf54 = new JToggleButton("Firefox54");
		butFf54.setPreferredSize(new Dimension(112, 32));
		panBrowser.add(butFf54, "alignx trailing");
		panBrowser.add(butFf54, "spanx, growx, wrap");
		butFf54.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butFf54.isSelected())
				{
					butFf54.setText("mit Firefox54");
					butFf54.setForeground(Color.blue);
					countButtonsBrowser++;
					checkAnyPanelButtonChanged();
					browserSetter("Ff54");

				} else
				{
					butFf54.setText("Firefox54");
					butFf54.setForeground(Color.black);
					countButtonsBrowser--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		//butFf66
		//JToggleButton butFf66 = new JToggleButton("Firefox66");
		butFf66.setPreferredSize(new Dimension(112, 32));
		panBrowser.add(butFf66, "alignx trailing");
		panBrowser.add(butFf66, "spanx, growx, wrap");
		butFf66.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butFf66.isSelected())
				{
					butFf66.setText("mit Firefox66");
					butFf66.setForeground(Color.blue);
					countButtonsBrowser++;
					checkAnyPanelButtonChanged();
					browserSetter("Ff66");

				} else
				{
					butFf66.setText("Firefox66");
					butFf66.setForeground(Color.black);
					countButtonsBrowser--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		//butFf80
		//JToggleButton butFf80 = new JToggleButton("Firefox80");
		butFf80.setPreferredSize(new Dimension(112, 32));
		panBrowser.add(butFf80, "alignx trailing");
		panBrowser.add(butFf80, "spanx, growx, wrap");
		butFf80.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butFf80.isSelected())
				{
					butFf80.setText("mit Firefox80");
					butFf80.setForeground(Color.blue);
					countButtonsBrowser++;
					checkAnyPanelButtonChanged();
					browserSetter("Ff80");

				} else
				{
					butFf80.setText("Firefox80");
					butFf80.setForeground(Color.black);
					countButtonsBrowser--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		//butOpera
		//JToggleButton butOpera = new JToggleButton("Opera");
		butOpera.setPreferredSize(new Dimension(112, 32));
		panBrowser.add(butOpera, "alignx trailing");
		panBrowser.add(butOpera, "spanx, growx, wrap");
		butOpera.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butOpera.isSelected())
				{
					butOpera.setText("mit Opera");
					butOpera.setForeground(Color.blue);
					countButtonsBrowser++;
					checkAnyPanelButtonChanged();
					browserSetter("Opera");

				} else
				{
					butOpera.setText("Opera");
					butOpera.setForeground(Color.black);
					countButtonsBrowser--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		//butWf55
		//JToggleButton butWf55 = new JToggleButton("Waterfox55");
		butWf55.setPreferredSize(new Dimension(112, 32));
		panBrowser.add(butWf55, "alignx trailing");
		panBrowser.add(butWf55, "spanx, growx, wrap");
		butWf55.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butWf55.isSelected())

				{
					butWf55.setText("mit Waterfox55");
					butWf55.setForeground(Color.blue);
					countButtonsBrowser++;
					checkAnyPanelButtonChanged();
					browserSetter("Wf55");

				} else
				{
					butWf55.setText("Waterfox55");
					butWf55.setForeground(Color.black);
					countButtonsBrowser--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		//endArea Browser

		//-------------------------------------------------------------------------------

		//Area AppsReihe

		//JPanel panAppReihe = new JPanel(new MigLayout("fillx"));
		panTestsInReiheBlack.setBackground(Color.darkGray);

		//Header
		//JLabel labTestsInReihe = new JLabel("Tests nacheinander");
		labTestsInReiheBlack.setText("Funktionstest(s) wählen");
		labTestsInReiheBlack.setFont(labTestsInReiheBlack.getFont().deriveFont(16.0f));
		labTestsInReiheBlack.setForeground(Color.lightGray);
		labTestsInReiheBlack.setHorizontalAlignment(JLabel.CENTER);
		panTestsInReiheBlack.add(labTestsInReiheBlack, "spanx, growx, wrap");
		//blau machen
		labTestsInReiheBlack.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (bolPanTestsInReiheBlue == false)
				{
					panTestsInReiheBlack.setBackground(Color.blue);
					labTestsInReiheBlack.setText("Funktionstest(s) beendet");
					labTestsInReiheBlack.setForeground(Color.white);
					panTestsInReiheResultBlack.setBackground(Color.blue);
					labTestsInReiheResultBlack.setText("Erg.");
					labTestsInReiheResultBlack.setForeground(Color.white);
					bolPanTestBeendet = true;
					switchPanTestBeendetColor();
					bolPanTestsInReiheBlue = true;

				} else
				{
					panTestsInReiheBlack.setBackground(Color.darkGray);
					labTestsInReiheBlack.setText("Funktionstest(s) wählen");
					labTestsInReiheBlack.setForeground(Color.lightGray);
					panTestsInReiheResultBlack.setBackground(Color.darkGray);
					labTestsInReiheResultBlack.setText("Erg.");
					labTestsInReiheResultBlack.setForeground(Color.lightGray);
					bolPanTestBeendet = false;
					switchPanTestBeendetColor();
					bolPanTestsInReiheBlue = false;
				}
			}

		});
		// wrap indicates a new row

		// Neuer User
		JToggleButton butAppNewUserR = new JToggleButton("Neuer User");
		butAppNewUserR.setPreferredSize(new Dimension(112, 32));
		panTestsInReiheBlack.add(butAppNewUserR, "alignx trailing");
		panTestsInReiheBlack.add(butAppNewUserR, "spanx, growx, wrap");
		butAppNewUserR.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butAppNewUserR.isSelected())
				{
					butAppNewUserR.setText("Test: Neuer User");
					butAppNewUserR.setForeground(Color.blue);
					countButtonsTestInReihe++;
					checkAnyPanelButtonChanged();
				} else
				{
					butAppNewUserR.setText("Neuer User");
					butAppNewUserR.setForeground(Color.black);
					countButtonsTestInReihe--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Produktvarianten
		JToggleButton butAppProdVarR = new JToggleButton("Produktvarianten");
		butAppProdVarR.setPreferredSize(new Dimension(112, 32));
		panTestsInReiheBlack.add(butAppProdVarR, "alignx trailing");
		panTestsInReiheBlack.add(butAppProdVarR, "spanx, growx, wrap");
		butAppProdVarR.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butAppProdVarR.isSelected())
				{
					butAppProdVarR.setText("Test: Produktvarianten");
					butAppProdVarR.setForeground(Color.blue);
					countButtonsTestInReihe++;
					checkAnyPanelButtonChanged();
				} else
				{
					butAppProdVarR.setText("Produktvarianten");
					butAppProdVarR.setForeground(Color.black);
					countButtonsTestInReihe--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Registrierung
		JToggleButton butAppRegisterR = new JToggleButton("Registrierung");
		butAppRegisterR.setPreferredSize(new Dimension(112, 32));
		panTestsInReiheBlack.add(butAppRegisterR, "alignx trailing");
		panTestsInReiheBlack.add(butAppRegisterR, "spanx, growx, wrap");
		butAppRegisterR.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butAppRegisterR.isSelected())
				{
					butAppRegisterR.setText("Test: Registrierung");
					butAppRegisterR.setForeground(Color.blue);
					countButtonsTestInReihe++;
					checkAnyPanelButtonChanged();
					//listeTestablauf.add("J:/Java.Testing/HomePageTesting/TestNG.xml/mod03_TestR_Registrierung.xml");
					//zu Testzwecken
					listeTestablauf.add("J:/Java.Testing/HomePageTesting/TestNG.xml/mod00_Call_TestAblauf_1400b.xml");
				} else
				{
					butAppRegisterR.setText("Registrierung");
					butAppRegisterR.setForeground(Color.black);
					countButtonsTestInReihe--;
					checkAnyPanelButtonChanged();
					//listeTestablauf.remove("J:/Java.Testing/HomePageTesting/TestNG.xml/mod03_TestR_Registrierung.xml");
					//zu Testzwecken
					listeTestablauf.remove("J:/Java.Testing/HomePageTesting/TestNG.xml/mod00_Call_TestAblauf_1400b.xml");
				}
			}
		});

		// Login
		JToggleButton butLoginR = new JToggleButton("Login");
		butLoginR.setPreferredSize(new Dimension(112, 32));
		panTestsInReiheBlack.add(butLoginR, "alignx trailing");
		panTestsInReiheBlack.add(butLoginR, "spanx, growx, wrap");
		butLoginR.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butLoginR.isSelected())
				{
					butLoginR.setText("Test: Login");
					butLoginR.setForeground(Color.blue);
					countButtonsTestInReihe++;
					checkAnyPanelButtonChanged();
					//listeTestablauf.add("J:/Java.Testing/TestNG/4_loginTestsR.xml");
				} else
				{
					butLoginR.setText("Login");
					butLoginR.setForeground(Color.black);
					countButtonsTestInReihe--;
					checkAnyPanelButtonChanged();
					//listeTestablauf.remove("J:/Java.Testing/TestNG/4_loginTestsR.xml");
				}
			}
		});

		// Lagerpruefung
		JToggleButton butLagerR = new JToggleButton("Lagerpruefung");
		butLagerR.setPreferredSize(new Dimension(112, 32));
		panTestsInReiheBlack.add(butLagerR, "alignx trailing");
		panTestsInReiheBlack.add(butLagerR, "spanx, growx, wrap");
		butLagerR.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butLagerR.isSelected())
				{
					butLagerR.setText("Test: Lagerpruefung");
					butLagerR.setForeground(Color.blue);
					countButtonsTestInReihe++;
					checkAnyPanelButtonChanged();
				} else
				{
					butLagerR.setText("Lagerpruefung");
					butLagerR.setForeground(Color.black);
					countButtonsTestInReihe--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Warenkorb
		JToggleButton butWaKoR = new JToggleButton("Warenkorb");
		butWaKoR.setPreferredSize(new Dimension(112, 32));
		panTestsInReiheBlack.add(butWaKoR, "alignx trailing");
		panTestsInReiheBlack.add(butWaKoR, "spanx, growx, wrap");
		butWaKoR.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butWaKoR.isSelected())
				{
					butWaKoR.setText("Test: Warenkorb");
					butWaKoR.setForeground(Color.blue);
					countButtonsTestInReihe++;
					checkAnyPanelButtonChanged();
				} else
				{
					butWaKoR.setText("Warenkorb");
					butWaKoR.setForeground(Color.black);
					countButtonsTestInReihe--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Rabatt
		JToggleButton butAppRabattR = new JToggleButton("Rabatt");
		butAppRabattR.setPreferredSize(new Dimension(112, 32));
		panTestsInReiheBlack.add(butAppRabattR, "alignx trailing");
		panTestsInReiheBlack.add(butAppRabattR, "spanx, growx, wrap");
		butAppRabattR.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butAppRabattR.isSelected())
				{
					butAppRabattR.setText("Test: Rabatt");
					butAppRabattR.setForeground(Color.blue);
					countButtonsTestInReihe++;
					checkAnyPanelButtonChanged();
				} else
				{
					butAppRabattR.setText("Rabatt");
					butAppRabattR.setForeground(Color.black);
					countButtonsTestInReihe--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// MwSt
		JToggleButton butAppMwStR = new JToggleButton("MwSt");
		butAppMwStR.setPreferredSize(new Dimension(112, 32));
		panTestsInReiheBlack.add(butAppMwStR, "alignx trailing");
		panTestsInReiheBlack.add(butAppMwStR, "spanx, growx, wrap");
		butAppMwStR.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butAppMwStR.isSelected())
				{
					butAppMwStR.setText("Test: MwSt");
					butAppMwStR.setForeground(Color.blue);
					countButtonsTestInReihe++;
					checkAnyPanelButtonChanged();
				} else
				{
					butAppMwStR.setText("MwSt");
					butAppMwStR.setForeground(Color.black);
					countButtonsTestInReihe--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Bezahlung
		JToggleButton butAppBezahlungR = new JToggleButton("Bezahlung");
		butAppBezahlungR.setPreferredSize(new Dimension(112, 32));
		panTestsInReiheBlack.add(butAppBezahlungR, "alignx trailing");
		panTestsInReiheBlack.add(butAppBezahlungR, "spanx, growx, wrap");
		butAppBezahlungR.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butAppBezahlungR.isSelected())
				{
					butAppBezahlungR.setText("Test: Bezahlung");
					butAppBezahlungR.setForeground(Color.blue);
					countButtonsTestInReihe++;
					checkAnyPanelButtonChanged();
				} else
				{
					butAppBezahlungR.setText("Bezahlung");
					butAppBezahlungR.setForeground(Color.black);
					countButtonsTestInReihe--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Bestellung
		JToggleButton butAppBestellR = new JToggleButton("Bestellung");
		butAppBestellR.setPreferredSize(new Dimension(112, 32));
		panTestsInReiheBlack.add(butAppBestellR, "alignx trailing");
		panTestsInReiheBlack.add(butAppBestellR, "spanx, growx, wrap");
		butAppBestellR.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butAppBestellR.isSelected())
				{
					butAppBestellR.setText("Test: Bestellung");
					butAppBestellR.setForeground(Color.blue);
					countButtonsTestInReihe++;
					checkAnyPanelButtonChanged();
				} else
				{
					butAppBestellR.setText("Bestellung");
					butAppBestellR.setForeground(Color.black);
					countButtonsTestInReihe--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Versand
		JToggleButton butAppVersandR = new JToggleButton("Versand");
		butAppVersandR.setPreferredSize(new Dimension(112, 32));
		panTestsInReiheBlack.add(butAppVersandR, "alignx trailing");
		panTestsInReiheBlack.add(butAppVersandR, "spanx, growx, wrap");
		butAppVersandR.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butAppVersandR.isSelected())
				{
					butAppVersandR.setText("Test: Versand");
					butAppVersandR.setForeground(Color.blue);
					countButtonsTestInReihe++;
					checkAnyPanelButtonChanged();
				} else
				{
					butAppVersandR.setText("Versand");
					butAppVersandR.setForeground(Color.black);
					countButtonsTestInReihe--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Valuta
		JToggleButton butAppValutaR = new JToggleButton("Valuta");
		butAppValutaR.setPreferredSize(new Dimension(112, 32));
		panTestsInReiheBlack.add(butAppValutaR, "alignx trailing");
		panTestsInReiheBlack.add(butAppValutaR, "spanx, growx, wrap");
		butAppValutaR.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butAppValutaR.isSelected())
				{
					butAppValutaR.setText("Test: Valuta");
					butAppValutaR.setForeground(Color.blue);
					countButtonsTestInReihe++;
					checkAnyPanelButtonChanged();
				} else
				{
					butAppValutaR.setText("Valuta");
					butAppValutaR.setForeground(Color.black);
					countButtonsTestInReihe--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Mahnung
		JToggleButton butAppMahnR = new JToggleButton("Mahnung");
		butAppMahnR.setPreferredSize(new Dimension(112, 32));
		panTestsInReiheBlack.add(butAppMahnR, "alignx trailing");
		panTestsInReiheBlack.add(butAppMahnR, "spanx, growx, wrap");
		butAppMahnR.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butAppMahnR.isSelected())
				{
					butAppMahnR.setText("Test: Mahnung");
					butAppMahnR.setForeground(Color.blue);
					countButtonsTestInReihe++;
					checkAnyPanelButtonChanged();
				} else
				{
					butAppMahnR.setText("Test: Mahnung");
					butAppMahnR.setForeground(Color.black);
					countButtonsTestInReihe--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Cookies
		JToggleButton butAppCookiesR = new JToggleButton("Cookies");
		butAppCookiesR.setPreferredSize(new Dimension(112, 32));
		panTestsInReiheBlack.add(butAppCookiesR, "alignx trailing");
		panTestsInReiheBlack.add(butAppCookiesR, "spanx, growx, wrap");
		butAppCookiesR.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butAppCookiesR.isSelected())
				{
					butAppCookiesR.setText("Test: Cookies");
					butAppCookiesR.setForeground(Color.blue);
					countButtonsTestInReihe++;
					checkAnyPanelButtonChanged();
				} else
				{
					butAppCookiesR.setText("Cookies");
					butAppCookiesR.setForeground(Color.black);
					countButtonsTestInReihe--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Datenschutz
		JToggleButton butAppDatSchuR = new JToggleButton("Datenschutz");
		butAppDatSchuR.setPreferredSize(new Dimension(112, 32));
		panTestsInReiheBlack.add(butAppDatSchuR, "alignx trailing");
		panTestsInReiheBlack.add(butAppDatSchuR, "spanx, growx, wrap");
		butAppDatSchuR.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butAppDatSchuR.isSelected())
				{
					butAppDatSchuR.setText("Test: Datenschutz");
					butAppDatSchuR.setForeground(Color.blue);
					countButtonsTestInReihe++;
					checkAnyPanelButtonChanged();
				} else
				{
					butAppDatSchuR.setText("Datenschutz");
					butAppDatSchuR.setForeground(Color.black);
					countButtonsTestInReihe--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		//endArea AppsReihe

		//-------------------------------------------------------------------------------

		//Area AppsReiheResult

		//JPanel panAppReiheResult = new JPanel(new MigLayout("fillx"));
		panTestsInReiheResultBlack.setBackground(Color.darkGray);

		// Header
		labTestsInReiheResultBlack.setText("oder");
		labTestsInReiheResultBlack.setFont(labTestsInReiheResultBlack.getFont().deriveFont(16.0f));
		labTestsInReiheResultBlack.setForeground(Color.lightGray);
		labTestsInReiheResultBlack.setHorizontalAlignment(JLabel.CENTER);
		panTestsInReiheResultBlack.add(labTestsInReiheResultBlack, "spanx, growx, wrap");

		// NewUser
		JLabel labResultNewUserR = new JLabel("");
		labResultNewUserR.setPreferredSize(new Dimension(40, 32));
		labResultNewUserR.setOpaque(true);
		labResultNewUserR.setBorder(lineBlack);
		panTestsInReiheResultBlack.add(labResultNewUserR, "w 40!, align center, spanx, growx, wrap");
		// Color test = new JLabel().getBackground(); sehr gut !
		labResultNewUserR.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switch (strResultNewUserR)
				{
				case "":
					labResultNewUserR.setBackground(Color.green);
					strResultNewUserR = "green";
					break;
				case "green":
					labResultNewUserR.setBackground(Color.red);
					strResultNewUserR = "red";
					break;
				case "red":
					labResultNewUserR.setBackground(new Color(238, 238, 238));
					strResultNewUserR = "";
					break;
				default:
					break;
				}
			}
		});

		// Produktvarianten
		JLabel labResultProdVarR = new JLabel("");
		labResultProdVarR.setPreferredSize(new Dimension(40, 32));
		labResultProdVarR.setOpaque(true);
		labResultProdVarR.setBorder(lineBlack);
		panTestsInReiheResultBlack.add(labResultProdVarR, "w 40!, align center, spanx, growx, wrap");
		labResultProdVarR.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switch (strResultProdVarR)
				{
				case "":
					labResultProdVarR.setBackground(Color.green);
					strResultProdVarR = "green";
					break;
				case "green":
					labResultProdVarR.setBackground(Color.red);
					strResultProdVarR = "red";
					break;
				case "red":
					labResultProdVarR.setBackground(new Color(238, 238, 238));
					strResultProdVarR = "";
					break;
				default:
					break;
				}
			}
		});

		// Registrierung
		JLabel labResultRegisterR = new JLabel("");
		labResultRegisterR.setPreferredSize(new Dimension(40, 32));
		labResultRegisterR.setOpaque(true);
		labResultRegisterR.setBorder(lineBlack);
		panTestsInReiheResultBlack.add(labResultRegisterR, "w 40!, align center, spanx, growx, wrap");
		labResultRegisterR.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switch (strResultRegisterR)
				{
				case "":
					labResultRegisterR.setBackground(Color.green);
					strResultRegisterR = "green";
					break;
				case "green":
					labResultRegisterR.setBackground(Color.red);
					strResultRegisterR = "red";
					break;
				case "red":
					labResultRegisterR.setBackground(new Color(238, 238, 238));
					strResultRegisterR = "";
					break;
				default:
					break;
				}
			}
		});

		// Login
		JLabel labResultLoginR = new JLabel("");
		labResultLoginR.setPreferredSize(new Dimension(40, 32));
		labResultLoginR.setOpaque(true);
		labResultLoginR.setBorder(lineBlack);
		panTestsInReiheResultBlack.add(labResultLoginR, "w 40!, align center, spanx, growx, wrap");
		labResultLoginR.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switch (strResultLoginR)
				{
				case "":
					labResultLoginR.setBackground(Color.green);
					strResultLoginR = "green";
					break;
				case "green":
					labResultLoginR.setBackground(Color.red);
					strResultLoginR = "red";
					break;
				case "red":
					labResultLoginR.setBackground(new Color(238, 238, 238));
					strResultLoginR = "";
					break;
				default:
					break;
				}
			}
		});

		// Lager
		JLabel labResultLagerR = new JLabel("");
		labResultLagerR.setPreferredSize(new Dimension(40, 32));
		labResultLagerR.setOpaque(true);
		labResultLagerR.setBorder(lineBlack);
		panTestsInReiheResultBlack.add(labResultLagerR, "w 40!, align center, spanx, growx, wrap");
		labResultLagerR.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switch (strResultLagerR)
				{
				case "":
					labResultLagerR.setBackground(Color.green);
					strResultLagerR = "green";
					break;
				case "green":
					labResultLagerR.setBackground(Color.red);
					strResultLagerR = "red";
					break;
				case "red":
					labResultLagerR.setBackground(new Color(238, 238, 238));
					strResultLagerR = "";
					break;
				default:
					break;
				}
			}
		});

		// Warenkorb
		JLabel labResultWaKoR = new JLabel("");
		labResultWaKoR.setPreferredSize(new Dimension(40, 32));
		labResultWaKoR.setOpaque(true);
		labResultWaKoR.setBorder(lineBlack);
		panTestsInReiheResultBlack.add(labResultWaKoR, "w 40!, align center, spanx, growx, wrap");
		labResultWaKoR.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switch (strResultWaKoR)
				{
				case "":
					labResultWaKoR.setBackground(Color.green);
					strResultWaKoR = "green";
					break;
				case "green":
					labResultWaKoR.setBackground(Color.red);
					strResultWaKoR = "red";
					break;
				case "red":
					labResultWaKoR.setBackground(new Color(238, 238, 238));
					strResultWaKoR = "";
					break;
				default:
					break;
				}
			}
		});

		// Rabatt
		JLabel labResultRabattR = new JLabel("");
		labResultRabattR.setPreferredSize(new Dimension(40, 32));
		labResultRabattR.setOpaque(true);
		labResultRabattR.setBorder(lineBlack);
		panTestsInReiheResultBlack.add(labResultRabattR, "w 40!, align center, spanx, growx, wrap");
		labResultRabattR.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switch (strResultRabattR)
				{
				case "":
					labResultRabattR.setBackground(Color.green);
					strResultRabattR = "green";
					break;
				case "green":
					labResultRabattR.setBackground(Color.red);
					strResultRabattR = "red";
					break;
				case "red":
					labResultRabattR.setBackground(new Color(238, 238, 238));
					strResultRabattR = "";
					break;
				default:
					break;
				}
			}
		});

		// MwSt
		JLabel labResultMwStR = new JLabel("");
		labResultMwStR.setPreferredSize(new Dimension(40, 32));
		labResultMwStR.setOpaque(true);
		labResultMwStR.setBorder(lineBlack);
		panTestsInReiheResultBlack.add(labResultMwStR, "w 40!, align center, spanx, growx, wrap");
		labResultMwStR.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switch (strResultMwStR)
				{
				case "":
					labResultMwStR.setBackground(Color.green);
					strResultMwStR = "green";
					break;
				case "green":
					labResultMwStR.setBackground(Color.red);
					strResultMwStR = "red";
					break;
				case "red":
					labResultMwStR.setBackground(new Color(238, 238, 238));
					strResultMwStR = "";
					break;
				default:
					break;
				}
			}
		});

		// Bezahlung
		JLabel labResultBezahlungR = new JLabel("");
		labResultBezahlungR.setPreferredSize(new Dimension(40, 32));
		labResultBezahlungR.setOpaque(true);
		labResultBezahlungR.setBorder(lineBlack);
		panTestsInReiheResultBlack.add(labResultBezahlungR, "w 40!, align center, spanx, growx, wrap");
		labResultBezahlungR.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switch (strResultBezahlungR)
				{
				case "":
					labResultBezahlungR.setBackground(Color.green);
					strResultBezahlungR = "green";
					break;
				case "green":
					labResultBezahlungR.setBackground(Color.red);
					strResultBezahlungR = "red";
					break;
				case "red":
					labResultBezahlungR.setBackground(new Color(238, 238, 238));
					strResultBezahlungR = "";
					break;
				default:
					break;
				}
			}
		});

		// Bestellung
		JLabel labResultBestellungR = new JLabel("");
		labResultBestellungR.setPreferredSize(new Dimension(40, 32));
		labResultBestellungR.setOpaque(true);
		labResultBestellungR.setBorder(lineBlack);
		panTestsInReiheResultBlack.add(labResultBestellungR, "w 40!, align center, spanx, growx, wrap");
		labResultBestellungR.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switch (strResultBestellungR)
				{
				case "":
					labResultBestellungR.setBackground(Color.green);
					strResultBestellungR = "green";
					break;
				case "green":
					labResultBestellungR.setBackground(Color.red);
					strResultBestellungR = "red";
					break;
				case "red":
					labResultBestellungR.setBackground(new Color(238, 238, 238));
					strResultBestellungR = "";
					break;
				default:
					break;
				}
			}
		});

		// Versand
		JLabel labResultVersandR = new JLabel("");
		labResultVersandR.setPreferredSize(new Dimension(40, 32));
		labResultVersandR.setOpaque(true);
		labResultVersandR.setBorder(lineBlack);
		panTestsInReiheResultBlack.add(labResultVersandR, "w 40!, align center, spanx, growx, wrap");
		labResultVersandR.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switch (strResultVersandR)
				{
				case "":
					labResultVersandR.setBackground(Color.green);
					strResultVersandR = "green";
					break;
				case "green":
					labResultVersandR.setBackground(Color.red);
					strResultVersandR = "red";
					break;
				case "red":
					labResultVersandR.setBackground(new Color(238, 238, 238));
					strResultVersandR = "";
					break;
				default:
					break;
				}
			}
		});

		// Valuta
		JLabel labResultValutaR = new JLabel("");
		labResultValutaR.setPreferredSize(new Dimension(40, 32));
		labResultValutaR.setOpaque(true);
		labResultValutaR.setBorder(lineBlack);
		panTestsInReiheResultBlack.add(labResultValutaR, "w 40!, align center, spanx, growx, wrap");
		labResultValutaR.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switch (strResultValutaR)
				{
				case "":
					labResultValutaR.setBackground(Color.green);
					strResultValutaR = "green";
					break;
				case "green":
					labResultValutaR.setBackground(Color.red);
					strResultValutaR = "red";
					break;
				case "red":
					labResultValutaR.setBackground(new Color(238, 238, 238));
					strResultValutaR = "";
					break;
				default:
					break;
				}
			}
		});

		// Mahnung
		JLabel labResultMahnungR = new JLabel("");
		labResultMahnungR.setPreferredSize(new Dimension(40, 32));
		labResultMahnungR.setOpaque(true);
		labResultMahnungR.setBorder(lineBlack);
		panTestsInReiheResultBlack.add(labResultMahnungR, "w 40!, align center, spanx, growx, wrap");
		labResultMahnungR.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switch (strResultMahnungR)
				{
				case "":
					labResultMahnungR.setBackground(Color.green);
					strResultMahnungR = "green";
					break;
				case "green":
					labResultMahnungR.setBackground(Color.red);
					strResultMahnungR = "red";
					break;
				case "red":
					labResultMahnungR.setBackground(new Color(238, 238, 238));
					strResultMahnungR = "";
					break;
				default:
					break;
				}
			}
		});

		// Cookies
		JLabel labResultCookiesR = new JLabel("");
		labResultCookiesR.setPreferredSize(new Dimension(40, 32));
		labResultCookiesR.setOpaque(true);
		labResultCookiesR.setBorder(lineBlack);
		panTestsInReiheResultBlack.add(labResultCookiesR, "w 40!, align center, spanx, growx, wrap");
		labResultCookiesR.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switch (strResultCookiesR)
				{
				case "":
					labResultCookiesR.setBackground(Color.green);
					strResultCookiesR = "green";
					break;
				case "green":
					labResultCookiesR.setBackground(Color.red);
					strResultCookiesR = "red";
					break;
				case "red":
					labResultCookiesR.setBackground(new Color(238, 238, 238));
					strResultCookiesR = "";
					break;
				default:
					break;
				}
			}
		});

		// Datenschutz
		JLabel labResultDatSchuR = new JLabel("");
		labResultDatSchuR.setPreferredSize(new Dimension(40, 32));
		labResultDatSchuR.setOpaque(true);
		labResultDatSchuR.setBorder(lineBlack);
		panTestsInReiheResultBlack.add(labResultDatSchuR, "w 40!, align center, spanx, growx, wrap");
		labResultDatSchuR.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switch (strResultDatSchuR)
				{
				case "":
					labResultDatSchuR.setBackground(Color.green);
					strResultDatSchuR = "green";
					break;
				case "green":
					labResultDatSchuR.setBackground(Color.red);
					strResultDatSchuR = "red";
					break;
				case "red":
					labResultDatSchuR.setBackground(new Color(238, 238, 238));
					strResultDatSchuR = "";
					break;
				default:
					break;
				}
			}
		});

		//endArea AppsReiheResult

		//-------------------------------------------------------------------------------

		//Area AppsParallel

		//JPanel panTestsParallel = new JPanel(new MigLayout("fillx"));
		panTestsParallelBlack.setBackground(Color.darkGray);

		// Header
		//JLabel labTestsParallel = new JLabel("Tests parallel");
		labTestsParallelBlack.setText(" Vielfachtest(s) wählen ");
		labTestsParallelBlack.setFont(labTestsParallelBlack.getFont().deriveFont(16.0f));
		labTestsParallelBlack.setForeground(Color.lightGray);
		labTestsParallelBlack.setHorizontalAlignment(JLabel.CENTER);
		panTestsParallelBlack.add(labTestsParallelBlack, "spanx, growx, wrap");
		//blau machen
		labTestsParallelBlack.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (bolPanTestsParallelBlue == false)
				{
					panTestsParallelBlack.setBackground(Color.blue);
					labTestsParallelBlack.setText("Vielfachtest(s) beendet");
					labTestsParallelBlack.setForeground(Color.white);
					panParallelQuantityBlack.setBackground(Color.blue);
					labParallelQuantityBlack.setText("mit getesteter Anzahl");
					labParallelQuantityBlack.setForeground(Color.white);
					panTestsParallelResultBlack.setBackground(Color.blue);
					labTestsParallelResultBlack.setText("Erg.");
					labTestsParallelResultBlack.setForeground(Color.white);
					bolPanTestBeendet = true;
					switchPanTestBeendetColor();
					bolPanTestsParallelBlue = true;
				} else
				{
					panTestsParallelBlack.setBackground(Color.darkGray);
					labTestsParallelBlack.setText(" Vielfachtest(s) wählen ");
					labTestsParallelBlack.setForeground(Color.lightGray);
					panParallelQuantityBlack.setBackground(Color.darkGray);
					labParallelQuantityBlack.setText("Anzahl 1x oder mehr");
					labParallelQuantityBlack.setForeground(Color.lightGray);
					panTestsParallelResultBlack.setBackground(Color.darkGray);
					labTestsParallelResultBlack.setText("Erg.");
					labTestsParallelResultBlack.setForeground(Color.lightGray);
					bolPanTestBeendet = false;
					switchPanTestBeendetColor();
					bolPanTestsParallelBlue = false;
				}

			}
		});
		// wrap indicates a new row

		// Neuer User
		JToggleButton butAppNewUserP = new JToggleButton("Neuer User");
		butAppNewUserP.setPreferredSize(new Dimension(112, 32));
		panTestsParallelBlack.add(butAppNewUserP, "alignx trailing");
		panTestsParallelBlack.add(butAppNewUserP, "spanx, growx, wrap");
		butAppNewUserP.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butAppNewUserP.isSelected())
				{
					butAppNewUserP.setText("Test: Neuer User");
					butAppNewUserP.setForeground(Color.blue);
					sliderNewUser.setBackground(Color.blue);
					labNewUser.setForeground(Color.blue);

					countButtonsTestParallel++;
					checkAnyPanelButtonChanged();

				} else
				{
					butAppNewUserP.setText("Neuer User");
					butAppNewUserP.setForeground(Color.black);
					sliderNewUser.setBackground(null);
					labNewUser.setForeground(Color.black);

					countButtonsTestParallel--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Produktvarianten
		JToggleButton butAppProdVarP = new JToggleButton("Produktvarianten");
		butAppProdVarP.setPreferredSize(new Dimension(112, 32));
		panTestsParallelBlack.add(butAppProdVarP, "alignx trailing");
		panTestsParallelBlack.add(butAppProdVarP, "spanx, growx, wrap");
		butAppProdVarP.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butAppProdVarP.isSelected())
				{
					butAppProdVarP.setText("Test: Produktvarianten");
					butAppProdVarP.setForeground(Color.blue);
					sliderProdVar.setBackground(Color.blue);
					labProdVar.setForeground(Color.blue);

					countButtonsTestParallel++;
					checkAnyPanelButtonChanged();

				} else
				{
					butAppProdVarP.setText("Produktvarianten");
					butAppProdVarP.setForeground(Color.black);
					sliderProdVar.setBackground(null);
					labProdVar.setForeground(Color.black);

					countButtonsTestParallel--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Registrierung
		JToggleButton butAppRegisterP = new JToggleButton("Registrierung");
		butAppRegisterP.setPreferredSize(new Dimension(112, 32));
		panTestsParallelBlack.add(butAppRegisterP, "alignx trailing");
		panTestsParallelBlack.add(butAppRegisterP, "spanx, growx, wrap");
		butAppRegisterP.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butAppRegisterP.isSelected())
				{
					butAppRegisterP.setText("Test: Registrierung");
					butAppRegisterP.setForeground(Color.blue);
					sliderRegister.setBackground(Color.blue);
					labRegister.setForeground(Color.blue);

					countButtonsTestParallel++;
					checkAnyPanelButtonChanged();

				} else
				{
					butAppRegisterP.setText("Registrierung");
					butAppRegisterP.setForeground(Color.black);
					sliderRegister.setBackground(null);
					labRegister.setForeground(Color.black);

					countButtonsTestParallel--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Login
		JToggleButton butLoginP = new JToggleButton("Login");
		butLoginP.setPreferredSize(new Dimension(112, 32));
		panTestsParallelBlack.add(butLoginP, "alignx trailing");
		panTestsParallelBlack.add(butLoginP, "spanx, growx, wrap");
		butLoginP.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butLoginP.isSelected())
				{
					butLoginP.setText("Test: Login");
					butLoginP.setForeground(Color.blue);
					sliderLogin.setBackground(Color.blue);
					labLogin.setForeground(Color.blue);

					countButtonsTestParallel++;
					checkAnyPanelButtonChanged();

				} else
				{
					butLoginP.setText("Login");
					butLoginP.setForeground(Color.black);
					sliderLogin.setBackground(null);
					labLogin.setForeground(Color.black);

					countButtonsTestParallel--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Lagerpruefung
		JToggleButton butLagerP = new JToggleButton("Lagerpruefung");
		butLagerP.setPreferredSize(new Dimension(112, 32));
		panTestsParallelBlack.add(butLagerP, "alignx trailing");
		panTestsParallelBlack.add(butLagerP, "spanx, growx, wrap");
		butLagerP.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butLagerP.isSelected())
				{
					butLagerP.setText("Test: Lagerpruefung");
					butLagerP.setForeground(Color.blue);
					sliderLager.setBackground(Color.blue);
					labLager.setForeground(Color.blue);

					countButtonsTestParallel++;
					checkAnyPanelButtonChanged();

				} else
				{
					butLagerP.setText("Lagerpruefung");
					butLagerP.setForeground(Color.black);
					sliderLager.setBackground(null);
					labLager.setForeground(Color.black);

					countButtonsTestParallel--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Warenkorb
		JToggleButton butWaKoP = new JToggleButton("Warenkorb");
		butWaKoP.setPreferredSize(new Dimension(112, 32));
		panTestsParallelBlack.add(butWaKoP, "alignx trailing");
		panTestsParallelBlack.add(butWaKoP, "spanx, growx, wrap");
		butWaKoP.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butWaKoP.isSelected())
				{
					butWaKoP.setText("Test: Warenkorb");
					butWaKoP.setForeground(Color.blue);
					sliderWaKo.setBackground(Color.blue);
					labWaKo.setForeground(Color.blue);

					countButtonsTestParallel++;
					checkAnyPanelButtonChanged();

				} else
				{
					butWaKoP.setText("Warenkorb");
					butWaKoP.setForeground(Color.black);
					sliderWaKo.setBackground(null);
					labWaKo.setForeground(Color.black);

					countButtonsTestParallel--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Rabatt
		JToggleButton butAppRabattP = new JToggleButton("Rabatt");
		butAppRabattP.setPreferredSize(new Dimension(112, 32));
		panTestsParallelBlack.add(butAppRabattP, "alignx trailing");
		panTestsParallelBlack.add(butAppRabattP, "spanx, growx, wrap");
		butAppRabattP.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butAppRabattP.isSelected())
				{
					butAppRabattP.setText("Test: Rabatt");
					butAppRabattP.setForeground(Color.blue);
					sliderRabatt.setBackground(Color.blue);
					labRabatt.setForeground(Color.blue);

					countButtonsTestParallel++;
					checkAnyPanelButtonChanged();

				} else
				{
					butAppRabattP.setText("Rabatt");
					butAppRabattP.setForeground(Color.black);
					sliderRabatt.setBackground(null);
					labRabatt.setForeground(Color.black);

					countButtonsTestParallel--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// MwSt
		JToggleButton butAppMwStP = new JToggleButton("MwSt");
		butAppMwStP.setPreferredSize(new Dimension(112, 32));
		panTestsParallelBlack.add(butAppMwStP, "alignx trailing");
		panTestsParallelBlack.add(butAppMwStP, "spanx, growx, wrap");
		butAppMwStP.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butAppMwStP.isSelected())
				{
					butAppMwStP.setText("Test: MwSt");
					butAppMwStP.setForeground(Color.blue);
					sliderMwSt.setBackground(Color.blue);
					labMwSt.setForeground(Color.blue);

					countButtonsTestParallel++;
					checkAnyPanelButtonChanged();

				} else
				{
					butAppMwStP.setText("MwSt");
					butAppMwStP.setForeground(Color.black);
					sliderMwSt.setBackground(null);
					labMwSt.setForeground(Color.black);

					countButtonsTestParallel--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Bezahlung
		JToggleButton butAppBezahlungP = new JToggleButton("Bezahlung");
		butAppBezahlungP.setPreferredSize(new Dimension(112, 32));
		panTestsParallelBlack.add(butAppBezahlungP, "alignx trailing");
		panTestsParallelBlack.add(butAppBezahlungP, "spanx, growx, wrap");
		butAppBezahlungP.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butAppBezahlungP.isSelected())
				{
					butAppBezahlungP.setText("Test: Bezahlung");
					butAppBezahlungP.setForeground(Color.blue);
					sliderBezahlung.setBackground(Color.blue);
					labBezahlung.setForeground(Color.blue);

					countButtonsTestParallel++;
					checkAnyPanelButtonChanged();

				} else
				{
					butAppBezahlungP.setText("Bezahlung");
					butAppBezahlungP.setForeground(Color.black);
					sliderBezahlung.setBackground(null);
					labBezahlung.setForeground(Color.black);

					countButtonsTestParallel--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Bestellung
		JToggleButton butAppBestellP = new JToggleButton("Bestellung");
		butAppBestellP.setPreferredSize(new Dimension(112, 32));
		panTestsParallelBlack.add(butAppBestellP, "alignx trailing");
		panTestsParallelBlack.add(butAppBestellP, "spanx, growx, wrap");
		butAppBestellP.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butAppBestellP.isSelected())
				{
					butAppBestellP.setText("Test: Bestellung");
					butAppBestellP.setForeground(Color.blue);
					sliderBestellung.setBackground(Color.blue);
					labBestell.setForeground(Color.blue);

					countButtonsTestParallel++;
					checkAnyPanelButtonChanged();

				} else
				{
					butAppBestellP.setText("Bestellung");
					butAppBestellP.setForeground(Color.black);
					sliderBestellung.setBackground(null);
					labBestell.setForeground(Color.black);

					countButtonsTestParallel--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Versand
		JToggleButton butAppVersandP = new JToggleButton("Versand");
		butAppVersandP.setPreferredSize(new Dimension(112, 32));
		panTestsParallelBlack.add(butAppVersandP, "alignx trailing");
		panTestsParallelBlack.add(butAppVersandP, "spanx, growx, wrap");
		butAppVersandP.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butAppVersandP.isSelected())
				{
					butAppVersandP.setText("Test: Versand");
					butAppVersandP.setForeground(Color.blue);
					sliderVersand.setBackground(Color.blue);
					labVersand.setForeground(Color.blue);
					countButtonsTestParallel++;
					checkAnyPanelButtonChanged();
					//listeTestablauf.add("J:/Java.Testing/HomePageTesting/TestNG.xml/mod04_TestP_Versand.xml");
					
				} else
				{
					butAppVersandP.setText("Versand");
					butAppVersandP.setForeground(Color.black);
					sliderVersand.setBackground(null);
					labVersand.setForeground(Color.black);
					countButtonsTestParallel--;
					checkAnyPanelButtonChanged();
					//listeTestablauf.remove("J:/Java.Testing/HomePageTesting/TestNG.xml/mod04_TestP_Versand.xml");
				}
			}
		});

		// Valuta
		JToggleButton butAppValutaP = new JToggleButton("Valuta");
		butAppValutaP.setPreferredSize(new Dimension(112, 32));
		panTestsParallelBlack.add(butAppValutaP, "alignx trailing");
		panTestsParallelBlack.add(butAppValutaP, "spanx, growx, wrap");
		butAppValutaP.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butAppValutaP.isSelected())
				{
					butAppValutaP.setText("Test: Valuta");
					butAppValutaP.setForeground(Color.blue);
					sliderValuta.setBackground(Color.blue);
					labValuta.setForeground(Color.blue);

					countButtonsTestParallel++;
					checkAnyPanelButtonChanged();

				} else
				{
					butAppValutaP.setText("Valuta");
					butAppValutaP.setForeground(Color.black);
					sliderValuta.setBackground(null);
					labValuta.setForeground(Color.black);

					countButtonsTestParallel--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Mahnung
		JToggleButton butAppMahnP = new JToggleButton("Mahnung");
		butAppMahnP.setPreferredSize(new Dimension(112, 32));
		panTestsParallelBlack.add(butAppMahnP, "alignx trailing");
		panTestsParallelBlack.add(butAppMahnP, "spanx, growx, wrap");
		butAppMahnP.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butAppMahnP.isSelected())
				{
					butAppMahnP.setText("Test: Mahnung");
					butAppMahnP.setForeground(Color.blue);
					sliderMahnung.setBackground(Color.blue);
					labMahnung.setForeground(Color.blue);

					countButtonsTestParallel++;
					checkAnyPanelButtonChanged();

				} else
				{
					butAppMahnP.setText("Mahnung");
					butAppMahnP.setForeground(Color.black);
					sliderMahnung.setBackground(null);
					labMahnung.setForeground(Color.black);

					countButtonsTestParallel--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Cookies
		JToggleButton butAppCookiesP = new JToggleButton("Cookies");
		butAppCookiesP.setPreferredSize(new Dimension(112, 32));
		panTestsParallelBlack.add(butAppCookiesP, "alignx trailing");
		panTestsParallelBlack.add(butAppCookiesP, "spanx, growx, wrap");
		butAppCookiesP.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butAppCookiesP.isSelected())
				{
					butAppCookiesP.setText("Test: Cookies");
					butAppCookiesP.setForeground(Color.blue);
					sliderCookies.setBackground(Color.blue);
					labCookies.setForeground(Color.blue);

					countButtonsTestParallel++;
					checkAnyPanelButtonChanged();

				} else
				{
					butAppCookiesP.setText("Cookies");
					butAppCookiesP.setForeground(Color.black);
					sliderCookies.setBackground(null);
					labCookies.setForeground(Color.black);

					countButtonsTestParallel--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Datenschutz
		JToggleButton butAppDatSchuP = new JToggleButton("Datenschutz");
		butAppDatSchuP.setPreferredSize(new Dimension(112, 32));
		panTestsParallelBlack.add(butAppDatSchuP, "alignx trailing");
		panTestsParallelBlack.add(butAppDatSchuP, "spanx, growx, wrap");
		butAppDatSchuP.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butAppDatSchuP.isSelected())
				{
					butAppDatSchuP.setText("Test: Datenschutz");
					butAppDatSchuP.setForeground(Color.blue);
					sliderDatSchu.setBackground(Color.blue);
					labDatSchu.setForeground(Color.blue);

					countButtonsTestParallel++;
					checkAnyPanelButtonChanged();

				} else
				{
					butAppDatSchuP.setText("Datenschutz");
					butAppDatSchuP.setForeground(Color.black);
					sliderDatSchu.setBackground(null);
					labDatSchu.setForeground(Color.black);

					countButtonsTestParallel--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		//endArea AppsParallel

		//-------------------------------------------------------------------------------

		// Area ParaQuantity

		//JPanel panParallelQuantity = new JPanel(new MigLayout("fillx"));
		panParallelQuantityBlack.setBackground(Color.darkGray);

		// Headline
		labParallelQuantityBlack.setText("Anzahl 1x oder mehr");
		labParallelQuantityBlack.setFont(labParallelQuantityBlack.getFont().deriveFont(16.0f));
		labParallelQuantityBlack.setForeground(Color.lightGray);
		labParallelQuantityBlack.setHorizontalAlignment(JLabel.CENTER);
		panParallelQuantityBlack.add(labParallelQuantityBlack, "spanx, growx, wrap");
		// wrap indicates a new row

		// NewUser
		//JSlider sliderNewUser = new JSlider();
		sliderNewUser.setValue(0); // Schiebebalken wird auf den Wert 0 gesetzt
		//JLabel labNewUser = new JLabel("Neuer User: 1x");
		labNewUser.setHorizontalAlignment(JLabel.CENTER);
		JPanel panFrameNewUser = new JPanel(new MigLayout("fillx"));
		panFrameNewUser.add(sliderNewUser);
		panFrameNewUser.add(labNewUser, BorderLayout.SOUTH);

		panParallelQuantityBlack.add(panFrameNewUser, "spanx, growx, wrap");
		sliderNewUser.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				int value = calculateSliderValue(sliderNewUser.getValue());
				labNewUser.setText("Neuer User: " + Integer.toString(value) + "x");
			}
		});

		// Produktvarianten
		// JSlider sliderProdVar = new JSlider();
		sliderProdVar.setValue(0); // Schiebebalken wird auf den Wert 0 gesetzt
		// JLabel labProdVar = new JLabel("Prod.Varianten: 1x");
		labProdVar.setHorizontalAlignment(JLabel.CENTER);
		JPanel panFrameProdVar = new JPanel(new MigLayout("fillx"));
		panFrameProdVar.add(sliderProdVar);
		panFrameProdVar.add(labProdVar, BorderLayout.SOUTH);

		panParallelQuantityBlack.add(panFrameProdVar, "spanx, growx, wrap");
		sliderProdVar.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				int value = calculateSliderValue(sliderProdVar.getValue());
				labProdVar.setText("Prod.Varianten: " + Integer.toString(value) + "x");
			}
		});

		// Registrierung
		// JSlider sliderRegister = new JSlider();
		sliderRegister.setValue(0); // Schiebebalken wird auf den Wert 0 gesetzt
		// JLabel labRegistr = new JLabel("Registrierung: 1x");
		labRegister.setHorizontalAlignment(JLabel.CENTER);
		JPanel panFrameRegistr = new JPanel(new MigLayout("fillx"));
		panFrameRegistr.add(sliderRegister);
		panFrameRegistr.add(labRegister, BorderLayout.SOUTH);

		panParallelQuantityBlack.add(panFrameRegistr, "spanx, growx, wrap");
		sliderRegister.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				int value = calculateSliderValue(sliderRegister.getValue());
				labRegister.setText("Registrierung: " + Integer.toString(value) + "x");
			}
		});

		// Login
		// JSlider sliderLogin = new JSlider();
		sliderLogin.setValue(0); // Schiebebalken wird auf den Wert 0 gesetzt
		// JLabel labLogin = new JLabel("Login: 1x");
		labLogin.setHorizontalAlignment(JLabel.CENTER);
		JPanel panFrameLogin = new JPanel(new MigLayout("fillx"));
		panFrameLogin.add(sliderLogin);
		panFrameLogin.add(labLogin, BorderLayout.SOUTH);

		panParallelQuantityBlack.add(panFrameLogin, "spanx, growx, wrap");
		sliderLogin.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				int value = calculateSliderValue(sliderLogin.getValue());
				labLogin.setText("Login: " + Integer.toString(value) + "x");
			}
		});

		// Lagerpruefung
		// JSlider sliderLager = new JSlider();
		sliderLager.setValue(0); // Schiebebalken wird auf den Wert 0 gesetzt
		// JLabel labLager = new JLabel("Lagerpruefung: 1x");
		labLager.setHorizontalAlignment(JLabel.CENTER);
		JPanel panFrameLager = new JPanel(new MigLayout("fillx"));
		panFrameLager.add(sliderLager);
		panFrameLager.add(labLager, BorderLayout.SOUTH);

		panParallelQuantityBlack.add(panFrameLager, "spanx, growx, wrap");
		sliderLager.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				int value = calculateSliderValue(sliderLager.getValue());
				labLager.setText("Lagerpruefung: " + Integer.toString(value) + "x");
			}
		});

		// Warenkorb
		// JSlider sliderWarKo = new JSlider();
		sliderWaKo.setValue(0); // Schiebebalken wird auf den Wert 0 gesetzt
		// JLabel labWarKo = new JLabel("Warenkorb: 1x");
		labWaKo.setHorizontalAlignment(JLabel.CENTER);
		JPanel panFrameWarKo = new JPanel(new MigLayout("fillx"));
		panFrameWarKo.add(sliderWaKo);
		panFrameWarKo.add(labWaKo, BorderLayout.SOUTH);

		panParallelQuantityBlack.add(panFrameWarKo, "spanx, growx, wrap");
		sliderWaKo.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				int value = calculateSliderValue(sliderWaKo.getValue());
				labWaKo.setText("Warenkorb: " + Integer.toString(value) + "x");
			}
		});

		// Rabatt
		// JSlider sliderRabatt = new JSlider();
		sliderRabatt.setValue(0); // Schiebebalken wird auf den Wert 0 gesetzt
		// JLabel labRabatt = new JLabel("Rabatt: 1x");
		labRabatt.setHorizontalAlignment(JLabel.CENTER);
		JPanel panFrameRabatt = new JPanel(new MigLayout("fillx"));
		panFrameRabatt.add(sliderRabatt);
		panFrameRabatt.add(labRabatt, BorderLayout.SOUTH);

		panParallelQuantityBlack.add(panFrameRabatt, "spanx, growx, wrap");
		sliderRabatt.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				int value = calculateSliderValue(sliderRabatt.getValue());
				labRabatt.setText("Rabatt: " + Integer.toString(value) + "x");
			}
		});

		// MwSt
		// JSlider sliderMwSt = new JSlider();
		sliderMwSt.setValue(0); // Schiebebalken wird auf den Wert 0 gesetzt
		// JLabel labMwSt = new JLabel("MwSt: 1x");
		labMwSt.setHorizontalAlignment(JLabel.CENTER);
		JPanel panFrameMwSt = new JPanel(new MigLayout("fillx"));
		panFrameMwSt.add(sliderMwSt);
		panFrameMwSt.add(labMwSt, BorderLayout.SOUTH);

		panParallelQuantityBlack.add(panFrameMwSt, "spanx, growx, wrap");
		sliderMwSt.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				int value = calculateSliderValue(sliderMwSt.getValue());
				labMwSt.setText("MwSt: " + Integer.toString(value) + "x");
			}
		});

		// Bezahlung
		// JSlider sliderBezahlung = new JSlider();
		sliderBezahlung.setValue(0); // Schiebebalken wird auf den Wert 0 gesetzt
		// JLabel labBezahlung = new JLabel("Bezahlung: 1x");
		labBezahlung.setHorizontalAlignment(JLabel.CENTER);
		JPanel panFrameBezahlung = new JPanel(new MigLayout("fillx"));
		panFrameBezahlung.add(sliderBezahlung);
		panFrameBezahlung.add(labBezahlung, BorderLayout.SOUTH);

		panParallelQuantityBlack.add(panFrameBezahlung, "spanx, growx, wrap");
		sliderBezahlung.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				int value = calculateSliderValue(sliderBezahlung.getValue());
				labBezahlung.setText("Bezahlung: " + Integer.toString(value) + "x");
			}
		});

		// Bestellung
		// JSlider sliderBestell = new JSlider();
		sliderBestellung.setValue(0); // Schiebebalken wird auf den Wert 0 gesetzt
		// JLabel labBestell = new JLabel("Bestellung: 1x");
		labBestell.setHorizontalAlignment(JLabel.CENTER);
		JPanel panFrameBestell = new JPanel(new MigLayout("fillx"));
		panFrameBestell.add(sliderBestellung);
		panFrameBestell.add(labBestell, BorderLayout.SOUTH);

		panParallelQuantityBlack.add(panFrameBestell, "spanx, growx, wrap");
		sliderBestellung.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				int value = calculateSliderValue(sliderBestellung.getValue());
				labBestell.setText("Bestellung: " + Integer.toString(value) + "x");
			}
		});

		// Versand
		// JSlider sliderVersand = new JSlider();
		sliderVersand.setValue(0); // Schiebebalken wird auf den Wert 0 gesetzt
		// JLabel labVersand = new JLabel("Versand: 1x");
		labVersand.setHorizontalAlignment(JLabel.CENTER);
		JPanel panFrameVersand = new JPanel(new MigLayout("fillx"));
		panFrameVersand.add(sliderVersand);
		panFrameVersand.add(labVersand, BorderLayout.SOUTH);

		panParallelQuantityBlack.add(panFrameVersand, "spanx, growx, wrap");
		sliderVersand.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				int value = calculateSliderValue(sliderVersand.getValue());
				labVersand.setText("Versand: " + Integer.toString(value) + "x");
			}
		});

		// Valuta
		// JSlider sliderValuta = new JSlider();
		sliderValuta.setValue(0); // Schiebebalken wird auf den Wert 0 gesetzt
		// JLabel labValuta = new JLabel("Valuta: 1x");
		labValuta.setHorizontalAlignment(JLabel.CENTER);
		JPanel panFrameValuta = new JPanel(new MigLayout("fillx"));
		panFrameValuta.add(sliderValuta);
		panFrameValuta.add(labValuta, BorderLayout.SOUTH);

		panParallelQuantityBlack.add(panFrameValuta, "spanx, growx, wrap");
		sliderValuta.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				int value = calculateSliderValue(sliderValuta.getValue());
				labValuta.setText("Valuta: " + Integer.toString(value) + "x");
			}
		});

		// Mahnung
		// JSlider sliderMahnung = new JSlider();
		sliderMahnung.setValue(0); // Schiebebalken wird auf den Wert 0 gesetzt
		// JLabel labMahnung = new JLabel("Mahnung: 1x");
		labMahnung.setHorizontalAlignment(JLabel.CENTER);
		JPanel panFrameMahnung = new JPanel(new MigLayout("fillx"));
		panFrameMahnung.add(sliderMahnung);
		panFrameMahnung.add(labMahnung, BorderLayout.SOUTH);

		panParallelQuantityBlack.add(panFrameMahnung, "spanx, growx, wrap");
		sliderMahnung.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				int value = calculateSliderValue(sliderMahnung.getValue());
				labMahnung.setText("Mahnung: " + Integer.toString(value) + "x");
			}
		});

		// Cookies
		// JSlider sliderCookies = new JSlider();
		sliderCookies.setValue(0); // Schiebebalken wird auf den Wert 0 gesetzt
		// JLabel labCookies = new JLabel("Cookies: 1x");
		labCookies.setHorizontalAlignment(JLabel.CENTER);
		JPanel panFrameCookies = new JPanel(new MigLayout("fillx"));
		panFrameCookies.add(sliderCookies);
		panFrameCookies.add(labCookies, BorderLayout.SOUTH);

		panParallelQuantityBlack.add(panFrameCookies, "spanx, growx, wrap");
		sliderCookies.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				int value = calculateSliderValue(sliderCookies.getValue());
				labCookies.setText("Cookies: " + Integer.toString(value) + "x");
			}
		});

		// Datenschutz
		// JSlider sliderDatSchu = new JSlider();
		sliderDatSchu.setValue(0); // Schiebebalken wird auf den Wert 0 gesetzt
		// JLabel labDatSchu = new JLabel("Datenschutz: 1x");
		labDatSchu.setHorizontalAlignment(JLabel.CENTER);
		JPanel panFrameDatSchu = new JPanel(new MigLayout("fillx"));
		panFrameDatSchu.add(sliderDatSchu);
		panFrameDatSchu.add(labDatSchu, BorderLayout.SOUTH);

		panParallelQuantityBlack.add(panFrameDatSchu, "spanx, growx, wrap");
		sliderDatSchu.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				int value = calculateSliderValue(sliderDatSchu.getValue());
				labDatSchu.setText("Datenschutz: " + Integer.toString(value) + "x");
			}
		});

		//endArea ParaQuantity

		//-------------------------------------------------------------------------------

		//Area AppsParaPara

		//JPanel panAppParaPara = new JPanel(new MigLayout("fillx"));
		panTestsParaParaBlack.setBackground(Color.darkGray);

		// Header
		labTestsParaParaBlack.setText("LT");
		labTestsParaParaBlack.setFont(labTestsParaParaBlack.getFont().deriveFont(16.0f));
		labTestsParaParaBlack.setForeground(Color.lightGray);
		labTestsParaParaBlack.setHorizontalAlignment(JLabel.CENTER);
		panTestsParaParaBlack.add(labTestsParaParaBlack, "spanx, growx, wrap");
		//blau machen
		labTestsParaParaBlack.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (bolPanTestsParaParaBlue == false)
				{
					panTestsParaParaBlack.setBackground(Color.blue);
					labTestsParaParaBlack.setText("LT");
					labTestsParaParaBlack.setForeground(Color.white);
					bolPanTestsParaParaBlue = true;
				} else
				{
					panTestsParaParaBlack.setBackground(Color.darkGray);
					labTestsParaParaBlack.setText("LT");
					labTestsParaParaBlack.setForeground(Color.lightGray);
					bolPanTestsParaParaBlue = false;
				}

			}
		});

		// NewUser
		JCheckBox checkNewUser = new JCheckBox("");
		checkNewUser.setPreferredSize(new Dimension(32, 32));
		checkNewUser.setOpaque(true);
		checkNewUser.setSize(new Dimension(50, 50));
		checkNewUser.setBorder(secondBorder);
		checkNewUser.setBorderPainted(false);
		panTestsParaParaBlack.add(checkNewUser, "w 30!, align center, spanx, growx, wrap");
		checkNewUser.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (checkNewUser.isSelected())
				{
					checkNewUser.setBorderPainted(true);
					countCheckBoxParaPara++;
					checkAnyPanelButtonChanged();
				} else
				{
					checkNewUser.setBorderPainted(false);
					countCheckBoxParaPara--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Produktvarianten
		JCheckBox checkProdVar = new JCheckBox("");
		checkProdVar.setPreferredSize(new Dimension(32, 32));
		checkProdVar.setOpaque(true);
		checkProdVar.setSize(new Dimension(50, 50));
		checkProdVar.setBorder(secondBorder);
		checkProdVar.setBorderPainted(false);
		panTestsParaParaBlack.add(checkProdVar, "w 30!, align center, spanx, growx, wrap");
		checkProdVar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (checkProdVar.isSelected())
				{
					checkProdVar.setBorderPainted(true);
					countCheckBoxParaPara++;
					checkAnyPanelButtonChanged();
				} else
				{
					checkProdVar.setBorderPainted(false);
					countCheckBoxParaPara--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Registrierung
		JCheckBox checkRegister = new JCheckBox("");
		checkRegister.setPreferredSize(new Dimension(32, 32));
		checkRegister.setOpaque(true);
		checkRegister.setSize(new Dimension(50, 50));
		checkRegister.setBorder(secondBorder);
		checkRegister.setBorderPainted(false);
		panTestsParaParaBlack.add(checkRegister, "w 30!, align center, spanx, growx, wrap");
		checkRegister.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (checkRegister.isSelected())
				{
					checkRegister.setBorderPainted(true);
					countCheckBoxParaPara++;
					checkAnyPanelButtonChanged();
				} else
				{
					checkRegister.setBorderPainted(false);
					countCheckBoxParaPara--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Login
		JCheckBox checkLogin = new JCheckBox("");
		checkLogin.setPreferredSize(new Dimension(32, 32));
		checkLogin.setOpaque(true);
		checkLogin.setSize(new Dimension(50, 50));
		checkLogin.setBorder(secondBorder);
		checkLogin.setBorderPainted(false);
		panTestsParaParaBlack.add(checkLogin, "w 30!, align center, spanx, growx, wrap");
		checkLogin.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (checkLogin.isSelected())
				{
					checkLogin.setBorderPainted(true);
					countCheckBoxParaPara++;
					checkAnyPanelButtonChanged();
				} else
				{
					checkLogin.setBorderPainted(false);
					countCheckBoxParaPara--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Lager
		JCheckBox checkLager = new JCheckBox("");
		checkLager.setPreferredSize(new Dimension(32, 32));
		checkLager.setOpaque(true);
		checkLager.setSize(new Dimension(50, 50));
		checkLager.setBorder(secondBorder);
		checkLager.setBorderPainted(false);
		panTestsParaParaBlack.add(checkLager, "w 30!, align center, spanx, growx, wrap");
		checkLager.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (checkLager.isSelected())
				{
					checkLager.setBorderPainted(true);
					countCheckBoxParaPara++;
					checkAnyPanelButtonChanged();
				} else
				{
					checkLager.setBorderPainted(false);
					countCheckBoxParaPara--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Warenkorb
		JCheckBox checkWaKo = new JCheckBox("");
		checkWaKo.setPreferredSize(new Dimension(32, 32));
		checkWaKo.setOpaque(true);
		checkWaKo.setSize(new Dimension(50, 50));
		checkWaKo.setBorder(secondBorder);
		checkWaKo.setBorderPainted(false);
		panTestsParaParaBlack.add(checkWaKo, "w 30!, align center, spanx, growx, wrap");
		checkWaKo.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (checkWaKo.isSelected())
				{
					checkWaKo.setBorderPainted(true);
					countCheckBoxParaPara++;
					checkAnyPanelButtonChanged();
				} else
				{
					checkWaKo.setBorderPainted(false);
					countCheckBoxParaPara--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Rabatt
		JCheckBox checkRabatt = new JCheckBox("");
		checkRabatt.setPreferredSize(new Dimension(32, 32));
		checkRabatt.setOpaque(true);
		checkRabatt.setSize(new Dimension(50, 50));
		checkRabatt.setBorder(secondBorder);
		checkRabatt.setBorderPainted(false);
		panTestsParaParaBlack.add(checkRabatt, "w 30!, align center, spanx, growx, wrap");
		checkRabatt.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (checkRabatt.isSelected())
				{
					checkRabatt.setBorderPainted(true);
					countCheckBoxParaPara++;
					checkAnyPanelButtonChanged();
				} else
				{
					checkRabatt.setBorderPainted(false);
					countCheckBoxParaPara--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// MwSt
		JCheckBox checkMwSt = new JCheckBox("");
		checkMwSt.setPreferredSize(new Dimension(32, 32));
		checkMwSt.setOpaque(true);
		checkMwSt.setSize(new Dimension(50, 50));
		checkMwSt.setBorder(secondBorder);
		checkMwSt.setBorderPainted(false);
		panTestsParaParaBlack.add(checkMwSt, "w 30!, align center, spanx, growx, wrap");
		checkMwSt.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (checkMwSt.isSelected())
				{
					checkMwSt.setBorderPainted(true);
					countCheckBoxParaPara++;
					checkAnyPanelButtonChanged();
				} else
				{
					checkMwSt.setBorderPainted(false);
					countCheckBoxParaPara--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Bezahlung
		JCheckBox checkBezahlung = new JCheckBox("");
		checkBezahlung.setPreferredSize(new Dimension(32, 32));
		checkBezahlung.setOpaque(true);
		checkBezahlung.setSize(new Dimension(50, 50));
		checkBezahlung.setBorder(secondBorder);
		checkBezahlung.setBorderPainted(false);
		panTestsParaParaBlack.add(checkBezahlung, "w 30!, align center, spanx, growx, wrap");
		checkBezahlung.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (checkBezahlung.isSelected())
				{
					checkBezahlung.setBorderPainted(true);
					countCheckBoxParaPara++;
					checkAnyPanelButtonChanged();
				} else
				{
					checkBezahlung.setBorderPainted(false);
					countCheckBoxParaPara--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Bestellung
		JCheckBox checkBestellung = new JCheckBox("");
		checkBestellung.setPreferredSize(new Dimension(32, 32));
		checkBestellung.setOpaque(true);
		checkBestellung.setSize(new Dimension(50, 50));
		checkBestellung.setBorder(secondBorder);
		checkBestellung.setBorderPainted(false);
		panTestsParaParaBlack.add(checkBestellung, "w 30!, align center, spanx, growx, wrap");
		checkBestellung.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (checkBestellung.isSelected())
				{
					checkBestellung.setBorderPainted(true);
					countCheckBoxParaPara++;
					checkAnyPanelButtonChanged();
				} else
				{
					checkBestellung.setBorderPainted(false);
					countCheckBoxParaPara--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Versand
		JCheckBox checkVersand = new JCheckBox("");
		checkVersand.setPreferredSize(new Dimension(32, 32));
		checkVersand.setOpaque(true);
		checkVersand.setSize(new Dimension(50, 50));
		checkVersand.setBorder(secondBorder);
		checkVersand.setBorderPainted(false);
		panTestsParaParaBlack.add(checkVersand, "w 30!, align center, spanx, growx, wrap");
		checkVersand.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (checkVersand.isSelected())
				{
					checkVersand.setBorderPainted(true);
					countCheckBoxParaPara++;
					checkAnyPanelButtonChanged();
					//listeTestablauf.add("J:/Java.Testing/HomePageTesting/TestNG.xml/mod06_TestPP_Bestellung.xml");
				} else
				{
					checkVersand.setBorderPainted(false);
					countCheckBoxParaPara--;
					checkAnyPanelButtonChanged();
					//listeTestablauf.remove("J:/Java.Testing/HomePageTesting/TestNG.xml/mod06_TestPP_Bestellung.xml");
				}
			}
		});

		// Valuta
		JCheckBox checkValuta = new JCheckBox("");
		checkValuta.setPreferredSize(new Dimension(32, 32));
		checkValuta.setOpaque(true);
		checkValuta.setSize(new Dimension(50, 50));
		checkValuta.setBorder(secondBorder);
		checkValuta.setBorderPainted(false);
		panTestsParaParaBlack.add(checkValuta, "w 30!, align center, spanx, growx, wrap");
		checkValuta.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (checkValuta.isSelected())
				{
					checkValuta.setBorderPainted(true);
					countCheckBoxParaPara++;
					checkAnyPanelButtonChanged();
				} else
				{
					checkValuta.setBorderPainted(false);
					countCheckBoxParaPara--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Mahnung
		JCheckBox checkMahnung = new JCheckBox("");
		checkMahnung.setPreferredSize(new Dimension(32, 32));
		checkMahnung.setOpaque(true);
		checkMahnung.setSize(new Dimension(50, 50));
		checkMahnung.setBorder(secondBorder);
		checkMahnung.setBorderPainted(false);
		panTestsParaParaBlack.add(checkMahnung, "w 30!, align center, spanx, growx, wrap");
		checkMahnung.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (checkMahnung.isSelected())
				{
					checkMahnung.setBorderPainted(true);
					countCheckBoxParaPara++;
					checkAnyPanelButtonChanged();
				} else
				{
					checkMahnung.setBorderPainted(false);
					countCheckBoxParaPara--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Cookies
		JCheckBox checkCookies = new JCheckBox("");
		checkCookies.setPreferredSize(new Dimension(32, 32));
		checkCookies.setOpaque(true);
		checkCookies.setSize(new Dimension(50, 50));
		checkCookies.setBorder(secondBorder);
		checkCookies.setBorderPainted(false);
		panTestsParaParaBlack.add(checkCookies, "w 30!, align center, spanx, growx, wrap");
		checkCookies.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (checkCookies.isSelected())
				{
					checkCookies.setBorderPainted(true);
					countCheckBoxParaPara++;
					checkAnyPanelButtonChanged();
				} else
				{
					checkCookies.setBorderPainted(false);
					countCheckBoxParaPara--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Datenschutz
		JCheckBox checkDatSchu = new JCheckBox("");
		checkDatSchu.setPreferredSize(new Dimension(32, 32));
		checkDatSchu.setOpaque(true);
		checkDatSchu.setSize(new Dimension(50, 50));
		checkDatSchu.setBorder(secondBorder);
		checkDatSchu.setBorderPainted(false);
		panTestsParaParaBlack.add(checkDatSchu, "w 30!, align center, spanx, growx, wrap");
		checkDatSchu.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (checkDatSchu.isSelected())
				{
					checkDatSchu.setBorderPainted(true);
					countCheckBoxParaPara++;
					checkAnyPanelButtonChanged();
				} else
				{
					checkDatSchu.setBorderPainted(false);
					countCheckBoxParaPara--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		//endArea AppsParaPara

		//-------------------------------------------------------------------------------

		//Area AppsParaResult

		//JPanel panAppParaResult = new JPanel(new MigLayout("fillx"));
		panTestsParallelResultBlack.setBackground(Color.darkGray);

		// Header
		labTestsParallelResultBlack.setText("oder");
		labTestsParallelResultBlack.setFont(labTestsParallelResultBlack.getFont().deriveFont(16.0f));
		labTestsParallelResultBlack.setForeground(Color.lightGray);
		labTestsParallelResultBlack.setHorizontalAlignment(JLabel.CENTER);
		panTestsParallelResultBlack.add(labTestsParallelResultBlack, "spanx, growx, wrap");

		// NewUser
		JLabel labResultNewUserP = new JLabel("");
		labResultNewUserP.setPreferredSize(new Dimension(40, 32));
		labResultNewUserP.setOpaque(true);
		labResultNewUserP.setBorder(lineBlack);
		panTestsParallelResultBlack.add(labResultNewUserP, "w 40!, align center, spanx, growx, wrap");
		// Color test = new JLabel().getBackground(); sehr gut !
		labResultNewUserP.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switch (strResultNewUserP)
				{
				case "":
					labResultNewUserP.setBackground(Color.green);
					strResultNewUserP = "green";
					break;
				case "green":
					labResultNewUserP.setBackground(Color.red);
					strResultNewUserP = "red";
					break;
				case "red":
					labResultNewUserP.setBackground(new Color(238, 238, 238));
					strResultNewUserP = "";
					break;
				default:
					break;
				}
			}
		});

		// Produktvarianten
		JLabel labResultProdVarP = new JLabel("");
		labResultProdVarP.setPreferredSize(new Dimension(40, 32));
		labResultProdVarP.setOpaque(true);
		labResultProdVarP.setBorder(lineBlack);
		panTestsParallelResultBlack.add(labResultProdVarP, "w 40!, align center, spanx, growx, wrap");
		labResultProdVarP.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switch (strResultProdVarP)
				{
				case "":
					labResultProdVarP.setBackground(Color.green);
					strResultProdVarP = "green";
					break;
				case "green":
					labResultProdVarP.setBackground(Color.red);
					strResultProdVarP = "red";
					break;
				case "red":
					labResultProdVarP.setBackground(new Color(238, 238, 238));
					strResultProdVarP = "";
					break;
				default:
					break;
				}
			}
		});

		// Pegistrierung
		JLabel labResultRegisterP = new JLabel("");
		labResultRegisterP.setPreferredSize(new Dimension(40, 32));
		labResultRegisterP.setOpaque(true);
		labResultRegisterP.setBorder(lineBlack);
		panTestsParallelResultBlack.add(labResultRegisterP, "w 40!, align center, spanx, growx, wrap");
		labResultRegisterP.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switch (strResultRegisterP)
				{
				case "":
					labResultRegisterP.setBackground(Color.green);
					strResultRegisterP = "green";
					break;
				case "green":
					labResultRegisterP.setBackground(Color.red);
					strResultRegisterP = "red";
					break;
				case "red":
					labResultRegisterP.setBackground(new Color(238, 238, 238));
					strResultRegisterP = "";
					break;
				default:
					break;
				}
			}
		});

		// Login
		JLabel labResultLoginP = new JLabel("");
		labResultLoginP.setPreferredSize(new Dimension(40, 32));
		labResultLoginP.setOpaque(true);
		labResultLoginP.setBorder(lineBlack);
		panTestsParallelResultBlack.add(labResultLoginP, "w 40!, align center, spanx, growx, wrap");
		labResultLoginP.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switch (strResultLoginP)
				{
				case "":
					labResultLoginP.setBackground(Color.green);
					strResultLoginP = "green";
					break;
				case "green":
					labResultLoginP.setBackground(Color.red);
					strResultLoginP = "red";
					break;
				case "red":
					labResultLoginP.setBackground(new Color(238, 238, 238));
					strResultLoginP = "";
					break;
				default:
					break;
				}
			}
		});

		// Lager
		JLabel labResultLagerP = new JLabel("");
		labResultLagerP.setPreferredSize(new Dimension(40, 32));
		labResultLagerP.setOpaque(true);
		labResultLagerP.setBorder(lineBlack);
		panTestsParallelResultBlack.add(labResultLagerP, "w 40!, align center, spanx, growx, wrap");
		labResultLagerP.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switch (strResultLagerP)
				{
				case "":
					labResultLagerP.setBackground(Color.green);
					strResultLagerP = "green";
					break;
				case "green":
					labResultLagerP.setBackground(Color.red);
					strResultLagerP = "red";
					break;
				case "red":
					labResultLagerP.setBackground(new Color(238, 238, 238));
					strResultLagerP = "";
					break;
				default:
					break;
				}
			}
		});

		// Warenkorb
		JLabel labResultWaKoP = new JLabel("");
		labResultWaKoP.setPreferredSize(new Dimension(40, 32));
		labResultWaKoP.setOpaque(true);
		labResultWaKoP.setBorder(lineBlack);
		panTestsParallelResultBlack.add(labResultWaKoP, "w 40!, align center, spanx, growx, wrap");
		labResultWaKoP.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switch (strResultWaKoP)
				{
				case "":
					labResultWaKoP.setBackground(Color.green);
					strResultWaKoP = "green";
					break;
				case "green":
					labResultWaKoP.setBackground(Color.red);
					strResultWaKoP = "red";
					break;
				case "red":
					labResultWaKoP.setBackground(new Color(238, 238, 238));
					strResultWaKoP = "";
					break;
				default:
					break;
				}
			}
		});

		// Rabatt
		JLabel labResultRabattP = new JLabel("");
		labResultRabattP.setPreferredSize(new Dimension(40, 32));
		labResultRabattP.setOpaque(true);
		labResultRabattP.setBorder(lineBlack);
		panTestsParallelResultBlack.add(labResultRabattP, "w 40!, align center, spanx, growx, wrap");
		labResultRabattP.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switch (strResultRabattP)
				{
				case "":
					labResultRabattP.setBackground(Color.green);
					strResultRabattP = "green";
					break;
				case "green":
					labResultRabattP.setBackground(Color.red);
					strResultRabattP = "red";
					break;
				case "red":
					labResultRabattP.setBackground(new Color(238, 238, 238));
					strResultRabattP = "";
					break;
				default:
					break;
				}
			}
		});

		// MwSt
		JLabel labResultMwStP = new JLabel("");
		labResultMwStP.setPreferredSize(new Dimension(40, 32));
		labResultMwStP.setOpaque(true);
		labResultMwStP.setBorder(lineBlack);
		panTestsParallelResultBlack.add(labResultMwStP, "w 40!, align center, spanx, growx, wrap");
		labResultMwStP.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switch (strResultMwStP)
				{
				case "":
					labResultMwStP.setBackground(Color.green);
					strResultMwStP = "green";
					break;
				case "green":
					labResultMwStP.setBackground(Color.red);
					strResultMwStP = "red";
					break;
				case "red":
					labResultMwStP.setBackground(new Color(238, 238, 238));
					strResultMwStP = "";
					break;
				default:
					break;
				}
			}
		});

		// Bezahlung
		JLabel labResultBezahlungP = new JLabel("");
		labResultBezahlungP.setPreferredSize(new Dimension(40, 32));
		labResultBezahlungP.setOpaque(true);
		labResultBezahlungP.setBorder(lineBlack);
		panTestsParallelResultBlack.add(labResultBezahlungP, "w 40!, align center, spanx, growx, wrap");
		labResultBezahlungP.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switch (strResultBezahlungP)
				{
				case "":
					labResultBezahlungP.setBackground(Color.green);
					strResultBezahlungP = "green";
					break;
				case "green":
					labResultBezahlungP.setBackground(Color.red);
					strResultBezahlungP = "red";
					break;
				case "red":
					labResultBezahlungP.setBackground(new Color(238, 238, 238));
					strResultBezahlungP = "";
					break;
				default:
					break;
				}
			}
		});

		// Bestellung
		JLabel labResultBestellungP = new JLabel("");
		labResultBestellungP.setPreferredSize(new Dimension(40, 32));
		labResultBestellungP.setOpaque(true);
		labResultBestellungP.setBorder(lineBlack);
		panTestsParallelResultBlack.add(labResultBestellungP, "w 40!, align center, spanx, growx, wrap");
		labResultBestellungP.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switch (strResultBestellungP)
				{
				case "":
					labResultBestellungP.setBackground(Color.green);
					strResultBestellungP = "green";
					break;
				case "green":
					labResultBestellungP.setBackground(Color.red);
					strResultBestellungP = "red";
					break;
				case "red":
					labResultBestellungP.setBackground(new Color(238, 238, 238));
					strResultBestellungP = "";
					break;
				default:
					break;
				}
			}
		});

		// Versand
		JLabel labResultVersandP = new JLabel("");
		labResultVersandP.setPreferredSize(new Dimension(40, 32));
		labResultVersandP.setOpaque(true);
		labResultVersandP.setBorder(lineBlack);
		panTestsParallelResultBlack.add(labResultVersandP, "w 40!, align center, spanx, growx, wrap");
		labResultVersandP.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switch (strResultVersandP)
				{
				case "":
					labResultVersandP.setBackground(Color.green);
					strResultVersandP = "green";
					break;
				case "green":
					labResultVersandP.setBackground(Color.red);
					strResultVersandP = "red";
					break;
				case "red":
					labResultVersandP.setBackground(new Color(238, 238, 238));
					strResultVersandP = "";
					break;
				default:
					break;
				}
			}
		});

		// Valuta
		JLabel labResultValutaP = new JLabel("");
		labResultValutaP.setPreferredSize(new Dimension(40, 32));
		labResultValutaP.setOpaque(true);
		labResultValutaP.setBorder(lineBlack);
		panTestsParallelResultBlack.add(labResultValutaP, "w 40!, align center, spanx, growx, wrap");
		labResultValutaP.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switch (strResultValutaP)
				{
				case "":
					labResultValutaP.setBackground(Color.green);
					strResultValutaP = "green";
					break;
				case "green":
					labResultValutaP.setBackground(Color.red);
					strResultValutaP = "red";
					break;
				case "red":
					labResultValutaP.setBackground(new Color(238, 238, 238));
					strResultValutaP = "";
					break;
				default:
					break;
				}
			}
		});

		// Mahnung
		JLabel labResultMahnungP = new JLabel("");
		labResultMahnungP.setPreferredSize(new Dimension(40, 32));
		labResultMahnungP.setOpaque(true);
		labResultMahnungP.setBorder(lineBlack);
		panTestsParallelResultBlack.add(labResultMahnungP, "w 40!, align center, spanx, growx, wrap");
		labResultMahnungP.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switch (strResultMahnungP)
				{
				case "":
					labResultMahnungP.setBackground(Color.green);
					strResultMahnungP = "green";
					break;
				case "green":
					labResultMahnungP.setBackground(Color.red);
					strResultMahnungP = "red";
					break;
				case "red":
					labResultMahnungP.setBackground(new Color(238, 238, 238));
					strResultMahnungP = "";
					break;
				default:
					break;
				}
			}
		});

		// Cookies
		JLabel labResultCookiesP = new JLabel("");
		labResultCookiesP.setPreferredSize(new Dimension(40, 32));
		labResultCookiesP.setOpaque(true);
		labResultCookiesP.setBorder(lineBlack);
		panTestsParallelResultBlack.add(labResultCookiesP, "w 40!, align center, spanx, growx, wrap");
		labResultCookiesP.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switch (strResultCookiesP)
				{
				case "":
					labResultCookiesP.setBackground(Color.green);
					strResultCookiesP = "green";
					break;
				case "green":
					labResultCookiesP.setBackground(Color.red);
					strResultCookiesP = "red";
					break;
				case "red":
					labResultCookiesP.setBackground(new Color(238, 238, 238));
					strResultCookiesP = "";
					break;
				default:
					break;
				}
			}
		});

		// Datenschutz
		JLabel labResultDatSchuP = new JLabel("");
		labResultDatSchuP.setPreferredSize(new Dimension(40, 32));
		labResultDatSchuP.setOpaque(true);
		labResultDatSchuP.setBorder(lineBlack);
		panTestsParallelResultBlack.add(labResultDatSchuP, "w 40!, align center, spanx, growx, wrap");
		labResultDatSchuP.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switch (strResultDatSchuP)
				{
				case "":
					labResultDatSchuP.setBackground(Color.green);
					strResultDatSchuP = "green";
					break;
				case "green":
					labResultDatSchuP.setBackground(Color.red);
					strResultDatSchuP = "red";
					break;
				case "red":
					labResultDatSchuP.setBackground(new Color(238, 238, 238));
					strResultDatSchuP = "";
					break;
				default:
					break;
				}
			}
		});

		//endArea AppsParaResult

		//-------------------------------------------------------------------------------

		//Area ErrorsProv

		//JPanel panTestErrorProv = new JPanel(new MigLayout("fillx"));
		panTestsErrorProvBlack.setBackground(Color.darkGray);

		// Header
		labTestsErrorProvBlack.setText("Test per Fehler provozieren");
		labTestsErrorProvBlack.setForeground(Color.lightGray);
		labTestsErrorProvBlack.setFont(labTestsErrorProvBlack.getFont().deriveFont(16.0f));
		labTestsErrorProvBlack.setHorizontalAlignment(JLabel.CENTER);
		panTestsErrorProvBlack.add(labTestsErrorProvBlack, "spanx, growx, wrap");
		//blau machen
		labTestsErrorProvBlack.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (bolpanErrorProvBlue == false)
				{
					panTestsErrorProvBlack.setBackground(Color.blue);
					labTestsErrorProvBlack.setText("Fehler provozieren gewählt");
					labTestsErrorProvBlack.setForeground(Color.white);
					panTestsErrorProvResultBlack.setBackground(Color.blue);
					labTestsErrorProvResultBlack.setText("Erg.");
					labTestsErrorProvResultBlack.setForeground(Color.white);
					bolPanTestBeendet = true;
					switchPanTestBeendetColor();
					bolpanErrorProvBlue = true;

				} else
				{
					panTestsErrorProvBlack.setBackground(Color.darkGray);
					labTestsErrorProvBlack.setText("Test per Fehler provozieren");
					labTestsErrorProvBlack.setForeground(Color.lightGray);
					panTestsErrorProvResultBlack.setBackground(Color.darkGray);
					labTestsErrorProvResultBlack.setText("Erg.");
					labTestsErrorProvResultBlack.setForeground(Color.lightGray);
					bolPanTestBeendet = false;
					switchPanTestBeendetColor();
					bolpanErrorProvBlue = false;
				}
			}

		});

		// Fehlbedienung
		JToggleButton butFehlbed = new JToggleButton("Fehlbedienung");
		butFehlbed.setPreferredSize(new Dimension(112, 32));
		panTestsErrorProvBlack.add(butFehlbed, "alignx trailing");
		panTestsErrorProvBlack.add(butFehlbed, "spanx, growx, wrap");
		butFehlbed.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butFehlbed.isSelected())
				{
					butFehlbed.setText("Test: Fehlbedienung");
					butFehlbed.setForeground(Color.blue);
					countButtonsErrorProv++;
					checkAnyPanelButtonChanged();
				} else
				{
					butFehlbed.setText("Fehlbedienung");
					butFehlbed.setForeground(Color.black);
					countButtonsErrorProv--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Daten nicht plausibel
		JToggleButton butDatenFalsch = new JToggleButton("Daten nicht plausibel");
		butDatenFalsch.setPreferredSize(new Dimension(112, 32));
		panTestsErrorProvBlack.add(butDatenFalsch, "alignx trailing");
		panTestsErrorProvBlack.add(butDatenFalsch, "spanx, growx, wrap");
		butDatenFalsch.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butDatenFalsch.isSelected())
				{
					butDatenFalsch.setText("Test: Daten nicht plausibel");
					butDatenFalsch.setForeground(Color.blue);
					countButtonsErrorProv++;
					checkAnyPanelButtonChanged();
				} else
				{
					butDatenFalsch.setText("Daten nicht plausibel");
					butDatenFalsch.setForeground(Color.black);
					countButtonsErrorProv--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Fehler Bezahlvorgang
		JToggleButton butBezahlFehler = new JToggleButton("Fehler Bezahlvorgang");
		butBezahlFehler.setPreferredSize(new Dimension(112, 32));
		panTestsErrorProvBlack.add(butBezahlFehler, "alignx trailing");
		panTestsErrorProvBlack.add(butBezahlFehler, "spanx, growx, wrap");
		butBezahlFehler.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butBezahlFehler.isSelected())
				{
					butBezahlFehler.setText("Test: Fehler Bezahlvorgang");
					butBezahlFehler.setForeground(Color.blue);
					countButtonsErrorProv++;
					checkAnyPanelButtonChanged();
				} else
				{
					butBezahlFehler.setText("Fehler Bezahlvorgang");
					butBezahlFehler.setForeground(Color.black);
					countButtonsErrorProv--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Abgebrochene Verbindung
		JToggleButton butVerbAbbruch = new JToggleButton("Abgebrochene Verbindung");
		butVerbAbbruch.setPreferredSize(new Dimension(112, 32));
		panTestsErrorProvBlack.add(butVerbAbbruch, "alignx trailing");
		panTestsErrorProvBlack.add(butVerbAbbruch, "spanx, growx, wrap");
		butVerbAbbruch.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butVerbAbbruch.isSelected())
				{
					butVerbAbbruch.setText("Test: Abgebrochene Verb.");
					butVerbAbbruch.setForeground(Color.blue);
					countButtonsErrorProv++;
					checkAnyPanelButtonChanged();
				} else
				{
					butVerbAbbruch.setText("Abgebrochene Verbindung");
					butVerbAbbruch.setForeground(Color.black);
					countButtonsErrorProv--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Datenbankfehler
		JToggleButton butDBfehler = new JToggleButton("Datenbankfehler");
		butDBfehler.setPreferredSize(new Dimension(112, 32));
		panTestsErrorProvBlack.add(butDBfehler, "alignx trailing");
		panTestsErrorProvBlack.add(butDBfehler, "spanx, growx, wrap");
		butDBfehler.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butDBfehler.isSelected())
				{
					butDBfehler.setText("Test: Datenbankfehler");
					butDBfehler.setForeground(Color.blue);
					countButtonsErrorProv++;
					checkAnyPanelButtonChanged();
				} else
				{
					butDBfehler.setText("Datenbankfehler");
					butDBfehler.setForeground(Color.black);
					countButtonsErrorProv--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Artikelbestand negativ
		JToggleButton butArtNeg = new JToggleButton("Artikelbestand negativ");
		butArtNeg.setPreferredSize(new Dimension(112, 32));
		panTestsErrorProvBlack.add(butArtNeg, "alignx trailing");
		panTestsErrorProvBlack.add(butArtNeg, "spanx, growx, wrap");
		butArtNeg.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butArtNeg.isSelected())
				{
					butArtNeg.setText("Test: Artikelbestand negativ");
					butArtNeg.setForeground(Color.blue);
					countButtonsErrorProv++;
					checkAnyPanelButtonChanged();
				} else
				{
					butArtNeg.setText("Artikelbestand negativ");
					butArtNeg.setForeground(Color.black);
					countButtonsErrorProv--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		// Artikel weg aus Warenkorb
		JToggleButton butWaKoFehler = new JToggleButton("Artikel weg aus Warenkorb");
		butWaKoFehler.setPreferredSize(new Dimension(112, 32));
		panTestsErrorProvBlack.add(butWaKoFehler, "alignx trailing");
		panTestsErrorProvBlack.add(butWaKoFehler, "spanx, growx, wrap");
		butWaKoFehler.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butWaKoFehler.isSelected())
				{
					butWaKoFehler.setText("Test: Artikel weg aus WaKo");
					butWaKoFehler.setForeground(Color.blue);
					countButtonsErrorProv++;
					checkAnyPanelButtonChanged();
				} else
				{
					butWaKoFehler.setText("Artikel weg aus Warenkorb");
					butWaKoFehler.setForeground(Color.black);
					countButtonsErrorProv--;
					checkAnyPanelButtonChanged();
				}
			}
		});

		//endArea ErrorsProv

		//-------------------------------------------------------------------------------

		//Area ErrorsProvResult

		//JPanel panTestsErrorProvResult = new JPanel(new MigLayout("fillx"));
		panTestsErrorProvResultBlack.setBackground(Color.darkGray);

		// Header
		labTestsErrorProvResultBlack.setText("      ");
		labTestsErrorProvResultBlack.setFont(labTestsErrorProvResultBlack.getFont().deriveFont(16.0f));
		labTestsErrorProvResultBlack.setForeground(Color.lightGray);
		labTestsErrorProvResultBlack.setHorizontalAlignment(JLabel.CENTER);
		panTestsErrorProvResultBlack.add(labTestsErrorProvResultBlack, "spanx, growx, wrap");

		// Fehlbedienung
		JLabel labResultFehlbedienung = new JLabel("");
		labResultFehlbedienung.setPreferredSize(new Dimension(40, 32));
		labResultFehlbedienung.setOpaque(true);
		labResultFehlbedienung.setBorder(lineBlack);
		panTestsErrorProvResultBlack.add(labResultFehlbedienung, "w 40!, align center, spanx, growx, wrap");
		labResultFehlbedienung.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switch (strResultFehlbedienung)
				{
				case "":
					labResultFehlbedienung.setBackground(Color.green);
					strResultFehlbedienung = "green";
					break;
				case "green":
					labResultFehlbedienung.setBackground(Color.red);
					strResultFehlbedienung = "red";
					break;
				case "red":
					labResultFehlbedienung.setBackground(new Color(238, 238, 238));
					strResultFehlbedienung = "";
					break;
				default:
					break;
				}
			}
		});

		// DatenFehler
		JLabel labResultDatenFehler = new JLabel("");
		labResultDatenFehler.setPreferredSize(new Dimension(40, 32));
		labResultDatenFehler.setOpaque(true);
		labResultDatenFehler.setBorder(lineBlack);
		panTestsErrorProvResultBlack.add(labResultDatenFehler, "w 40!, align center, spanx, growx, wrap");
		labResultDatenFehler.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switch (strResultDatenFehler)
				{
				case "":
					labResultDatenFehler.setBackground(Color.green);
					strResultDatenFehler = "green";
					break;
				case "green":
					labResultDatenFehler.setBackground(Color.red);
					strResultDatenFehler = "red";
					break;
				case "red":
					labResultDatenFehler.setBackground(new Color(238, 238, 238));
					strResultDatenFehler = "";
					break;
				default:
					break;
				}
			}
		});

		// BezahlFehler
		JLabel labResultBezahlFehler = new JLabel("");
		labResultBezahlFehler.setPreferredSize(new Dimension(40, 32));
		labResultBezahlFehler.setOpaque(true);
		labResultBezahlFehler.setBorder(lineBlack);
		panTestsErrorProvResultBlack.add(labResultBezahlFehler, "w 40!, align center, spanx, growx, wrap");
		labResultBezahlFehler.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switch (strResultBezahlFehler)
				{
				case "":
					labResultBezahlFehler.setBackground(Color.green);
					strResultBezahlFehler = "green";
					break;
				case "green":
					labResultBezahlFehler.setBackground(Color.red);
					strResultBezahlFehler = "red";
					break;
				case "red":
					labResultBezahlFehler.setBackground(new Color(238, 238, 238));
					strResultBezahlFehler = "";
					break;
				default:
					break;
				}
			}
		});

		// ConnBroken
		JLabel labResultConnBroken = new JLabel("");
		labResultConnBroken.setPreferredSize(new Dimension(40, 32));
		labResultConnBroken.setOpaque(true);
		labResultConnBroken.setBorder(lineBlack);
		panTestsErrorProvResultBlack.add(labResultConnBroken, "w 40!, align center, spanx, growx, wrap");
		labResultConnBroken.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switch (strResultConnBroken)
				{
				case "":
					labResultConnBroken.setBackground(Color.green);
					strResultConnBroken = "green";
					break;
				case "green":
					labResultConnBroken.setBackground(Color.red);
					strResultConnBroken = "red";
					break;
				case "red":
					labResultConnBroken.setBackground(new Color(238, 238, 238));
					strResultConnBroken = "";
					break;
				default:
					break;
				}
			}
		});

		// DBfehler
		JLabel labResultDBfehler = new JLabel("");
		labResultDBfehler.setPreferredSize(new Dimension(40, 32));
		labResultDBfehler.setOpaque(true);
		labResultDBfehler.setBorder(lineBlack);
		panTestsErrorProvResultBlack.add(labResultDBfehler, "w 40!, align center, spanx, growx, wrap");
		labResultDBfehler.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switch (strResultDBfehler)
				{
				case "":
					labResultDBfehler.setBackground(Color.green);
					strResultDBfehler = "green";
					break;
				case "green":
					labResultDBfehler.setBackground(Color.red);
					strResultDBfehler = "red";
					break;
				case "red":
					labResultDBfehler.setBackground(new Color(238, 238, 238));
					strResultDBfehler = "";
					break;
				default:
					break;
				}
			}
		});

		// ArtikelMinus
		JLabel labResultArtikelMinus = new JLabel("");
		labResultArtikelMinus.setPreferredSize(new Dimension(40, 32));
		labResultArtikelMinus.setOpaque(true);
		labResultArtikelMinus.setBorder(lineBlack);
		panTestsErrorProvResultBlack.add(labResultArtikelMinus, "w 40!, align center, spanx, growx, wrap");
		labResultArtikelMinus.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switch (strResultArtikelMinus)
				{
				case "":
					labResultArtikelMinus.setBackground(Color.green);
					strResultArtikelMinus = "green";
					break;
				case "green":
					labResultArtikelMinus.setBackground(Color.red);
					strResultArtikelMinus = "red";
					break;
				case "red":
					labResultArtikelMinus.setBackground(new Color(238, 238, 238));
					strResultArtikelMinus = "";
					break;
				default:
					break;
				}
			}
		});

		// WaKoFehler
		JLabel labResultWaKoFehler = new JLabel("");
		labResultWaKoFehler.setPreferredSize(new Dimension(40, 32));
		labResultWaKoFehler.setOpaque(true);
		labResultWaKoFehler.setBorder(lineBlack);
		panTestsErrorProvResultBlack.add(labResultWaKoFehler, "w 40!, align center, spanx, growx, wrap");
		labResultWaKoFehler.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switch (strResultWaKoFehler)
				{
				case "":
					labResultWaKoFehler.setBackground(Color.green);
					strResultWaKoFehler = "green";
					break;
				case "green":
					labResultWaKoFehler.setBackground(Color.red);
					strResultWaKoFehler = "red";
					break;
				case "red":
					labResultWaKoFehler.setBackground(new Color(238, 238, 238));
					strResultWaKoFehler = "";
					break;
				default:
					break;
				}
			}
		});

		//endArea ErrorsProvResult

		//-------------------------------------------------------------------------------

		//Area TestVarianten

		// JPanel panTestVarianten = new JPanel(new MigLayout("fillx"));
		panTestVariantenGrey.setBackground(Color.lightGray);
		// Header
		labTestVariantenGrey.setText("Testvarianten");
		labTestVariantenGrey.setForeground(Color.black);
		labTestVariantenGrey.setFont(labTestVariantenGrey.getFont().deriveFont(16.0f));
		labTestVariantenGrey.setHorizontalAlignment(JLabel.CENTER);
		panTestVariantenGrey.add(labTestVariantenGrey, "spanx, growx, wrap");

		// Main Addition der Panel der Testvarianten
		panTestVariantenGrey.add(panTestsInReiheBlack);
		panTestVariantenGrey.add(panTestsInReiheResultBlack);
		panTestVariantenGrey.add(panTestsParallelBlack);
		panTestVariantenGrey.add(panParallelQuantityBlack);
		panTestVariantenGrey.add(panTestsParaParaBlack);
		panTestVariantenGrey.add(panTestsParallelResultBlack);
		panTestVariantenGrey.add(panTestsErrorProvBlack);
		panTestVariantenGrey.add(panTestsErrorProvResultBlack, "spanx, growx, wrap");

		// Footer
		labTestVariantenUntenGrey
				.setText("Funktionstests werden von oben nach unten, Vielfachtests optional als Lasttest und die Tests insgesamt von links nach rechts abgearbeitet");
		labTestVariantenUntenGrey.setForeground(Color.black);
		labTestVariantenUntenGrey.setFont(labTestVariantenUntenGrey.getFont().deriveFont(16.0f));
		labTestVariantenUntenGrey.setHorizontalAlignment(JLabel.CENTER);
		panTestVariantenGrey.add(labTestVariantenUntenGrey, "spanx, growx, wrap");

		//endArea TestVarianten

		//-------------------------------------------------------------------------------

		//Area TestSteuerung

		//JPanel panTestSteuerungGrey = new JPanel(new MigLayout("fillx"));
		panTestSteuerungGrey.setBackground(Color.lightGray);

		// Header
		labTestSteuerungGrey.setText("Teststeuerung");
		labTestSteuerungGrey.setFont(labTestSteuerungGrey.getFont().deriveFont(16.0f));
		labTestSteuerungGrey.setHorizontalAlignment(JLabel.CENTER);
		panTestSteuerungGrey.add(labTestSteuerungGrey, "spanx, growx, wrap");
		// wrap indicates a new row

		JPanel panLadeTest = new JPanel(new MigLayout("fillx"));
		panLadeTest.setBackground(Color.darkGray);

		// Liste -> Testablauf
		JLabel labLadeTest = new JLabel("Liste -> Testablauf");
		labLadeTest.setPreferredSize(new Dimension(112, 32));
		labLadeTest.setFont(labLadeTest.getFont().deriveFont(16.0f));
		labLadeTest.setForeground(Color.lightGray);
		labLadeTest.setHorizontalAlignment(JLabel.CENTER);
		labLadeTest.setBackground(Color.lightGray);
		panLadeTest.add(labLadeTest, "alignx trailing");
		panLadeTest.add(labLadeTest, "spanx, growx, wrap");

		// Liste laden
		JButton butLadeListe = new JButton("Liste laden");
		butLadeListe.setPreferredSize(new Dimension(112, 32));
		panLadeTest.add(butLadeListe, "alignx trailing");
		panLadeTest.add(butLadeListe, "spanx, growx, wrap");
		butLadeListe.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				try
				{
					Thread.sleep(1000);
					//					butLadeListe.setText("Liste gespeichert");
					//					Thread.sleep(1000);
					//					butLadeListe.setText("Liste speichern");
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		});

		//Name lade Liste
		JTextField appTextField01 = new JTextField("RegrTest_Datum.xml");
		appTextField01.setPreferredSize(new Dimension(112, 32));
		appTextField01.setFont(appTextField01.getFont().deriveFont(12.0f));
		appTextField01.setHorizontalAlignment(JLabel.CENTER);
		appTextField01.setBackground(Color.lightGray);
		appTextField01.setOpaque(true);
		panLadeTest.add(appTextField01, "alignx trailing, wrap");
		panLadeTest.add(appTextField01, "spanx, growx, wrap");

		JPanel panSpeichereTest = new JPanel(new MigLayout("fillx"));
		panSpeichereTest.setBackground(Color.darkGray);

		// Testablauf -> Liste
		JLabel labSpeichereTest = new JLabel("Testablauf -> Liste");
		labSpeichereTest.setPreferredSize(new Dimension(112, 32));
		labSpeichereTest.setFont(labSpeichereTest.getFont().deriveFont(16.0f));
		labSpeichereTest.setForeground(Color.lightGray);
		labSpeichereTest.setHorizontalAlignment(JLabel.CENTER);
		labSpeichereTest.setBackground(Color.lightGray);
		panSpeichereTest.add(labSpeichereTest, "alignx trailing");
		panSpeichereTest.add(labSpeichereTest, "spanx, growx, wrap");

		// Liste speichern
		JButton butSpeichereListe = new JButton("Liste speichern");
		butSpeichereListe.setPreferredSize(new Dimension(112, 32));
		panSpeichereTest.add(butSpeichereListe, "alignx trailing");
		panSpeichereTest.add(butSpeichereListe, "spanx, growx, wrap");
		butSpeichereListe.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				try
				{
					Thread.sleep(1000);
					//					butSpeichereListe.setText("Liste gespeichert");
					//					Thread.sleep(1000);
					//					butSpeichereListe.setText("Liste speichern");
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		});

		//Name speichere Liste
		JTextField appTextField02 = new JTextField("TestModus_Datum.xml");
		appTextField02.setPreferredSize(new Dimension(112, 32));
		appTextField02.setFont(appTextField02.getFont().deriveFont(12.0f));
		appTextField02.setHorizontalAlignment(JLabel.CENTER);
		appTextField02.setBackground(Color.lightGray);
		appTextField02.setOpaque(true);
		panSpeichereTest.add(appTextField02, "alignx trailing, wrap");
		panSpeichereTest.add(appTextField02, "spanx, growx, wrap");

		JPanel panSteuereTest = new JPanel(new MigLayout("fillx"));
		panSteuereTest.setBackground(Color.darkGray);

		//Testreihe loeschen
		//JButton butLoescheTest = new JButton("Testablauf löschen");
		butLoescheTest.setText("Testablauf löschen");
		butLoescheTest.setPreferredSize(new Dimension(112, 32));
		butLoescheTest.setOpaque(true);
		panSteuereTest.add(butLoescheTest, "alignx trailing");
		panSteuereTest.add(butLoescheTest, "spanx, growx, wrap");
		butLoescheTest.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				//Sende eMail
				//				try
				//				{
				//					Thread.sleep(1000);
				//				} catch (InterruptedException e)
				//				{
				//					e.printStackTrace();
				//				}
			}
		});

		//Test starten
		//JButton butStartTest = new JButton("Test starten");
		butStarteTest.setPreferredSize(new Dimension(120, 64));
		butStarteTest.setText("Testablauf ?");
		//butStarteTest.setOpaque(true);
		panSteuereTest.add(butStarteTest, "alignx trailing");
		panSteuereTest.add(butStarteTest, "spanx, growx, wrap");
		butStarteTest.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				// delegate to event handler method
				switchPanTestGestartetColor();
				//if (butStarteTest.isEnabled())
				//butStarteTest.setEnabled(true);
				//goSleep();

				//Liste uebergeben
				testNG.setTestSuites(listeTestablauf);
				testNG.run();

			}
		});

		//Test verzögert starten
		//JButton butStartZeitTest = new JButton("Test starten");
		butStartZeitTest.setPreferredSize(new Dimension(112, 32));
		butStartZeitTest.setText("Startzeit ?");
		butStartZeitTest.setOpaque(true);
		panSteuereTest.add(butStartZeitTest, "alignx trailing");
		panSteuereTest.add(butStartZeitTest, "spanx, growx, wrap");
		butStartZeitTest.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (butStartZeitTest.getText() == "Testablauf Pause?")
				{
					butStartZeitTest.setText("Testablauf weiter?");
					//butStarteTest.setForeground(Color.blue);
					butStarteTest.setEnabled(false);
					butStarteTest.setText("Testablauf pausiert !");
					labGesamtHead.setText("Testablauf pausiert !");
					//labGesamtHead.setForeground(Color.orange);
					//butStarteTest.setOpaque(true);
					//butStarteTest.setBackground(Color.orange); //das interessiert nicht...

				}

				else
					if (butStartZeitTest.getText() == "Testablauf weiter?")
					{
						butStartZeitTest.setText("Testablauf Pause?");
						butStarteTest.setEnabled(true);
						//butStarteTest.setOpaque(true);
						butStarteTest.setText("Tests laufen !");
						labGesamtHead.setText("Testablauf gestartet");
					}

			}
		});

		//Test abbrechen
		//JButton butTestAbbrechen = new JButton("Testablauf abbrechen");
		butTestAbbrechen.setText("Testablauf abbrechen");
		butTestAbbrechen.setPreferredSize(new Dimension(112, 32));
		panSteuereTest.add(butTestAbbrechen, "alignx trailing");
		panSteuereTest.add(butTestAbbrechen, "spanx, growx, wrap");
		butTestAbbrechen.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{

				if (butTestAbbrechen.getText() == "Testlauf abbrechen !")
				{
					butTestAbbrechen.setText("wird beendet...");

				}

				else
					if (butTestAbbrechen.getText() == "wird beendet...")
					{
						butTestAbbrechen.setText("Testlauf abbrechen !");
					}

				if (butTestAbbrechen.getText() == "Testlauf abbrechen?")
				{
					butTestAbbrechen.setText("Testlauf abbrechen !");

					bolPanTestBeendet = true;
					switchPanTestBeendetColor();

					listeTestablaufAlt = listeTestablauf;
					
					try
					{
						List_Writer.SaveListIntoTxt(listeTestablauf);
					} catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//listeTestablauf.clear();
					testNG.setTestSuites(listeTestablauf);
					
					
					//Test beenden
					//throw new SkipException("Test manuell beendet");
					//testNG..run();
				}


			}
		});

		panTestSteuerungGrey.add(panLadeTest, "spanx, growx, wrap");
		panTestSteuerungGrey.add(panSpeichereTest, "spanx, growx, wrap");
		panTestSteuerungGrey.add(panSteuereTest, "spanx, growx, wrap");

		//endArea TestSteuerung

		//-------------------------------------------------------------------------------

		//Area PanelGesamt

		// create the main panGesamt
		// JPanel panGesamt = new JPanel(new MigLayout("filly")); // again, we want to fill the vertical space so the 2 pans will have the same height
		//panGesamt.add(panInfo, "alignx trailing");
		//panGesamt.add(panInfo, "spanx, growx, wrap");

		// Header
		// JLabel labGesamtHead = new JLabel("Festlegung des Testablaufes");
		labGesamtHead.setText("Datenquelle der Testdaten wählen + Browser wählen + Testvariante(n) wählen -> Testablauf starten");
		labGesamtHead.setForeground(Color.white);
		labGesamtHead.setFont(labGesamtHead.getFont().deriveFont(24.0f));
		labGesamtHead.setHorizontalAlignment(JLabel.CENTER);
		labGesamtHead.setOpaque(true);
		labGesamtHead.setBackground(Color.darkGray);
		JLabel labGesamtBottom = new JLabel("Kurt Schubert   ");
		labGesamtBottom.setForeground(Color.black);
		labGesamtBottom.setFont(labGesamtBottom.getFont().deriveFont(12.0f));
		labGesamtBottom.setHorizontalAlignment(JLabel.RIGHT);

		panGesamt.add(labGesamtHead, "spanx, growx, wrap");
		panGesamt.add(panDataFeedGrey);
		panGesamt.add(panBrowserGrey);
		panGesamt.add(panTestVariantenGrey);
		panGesamt.add(panTestSteuerungGrey);
		//panGesamt.add(labGesamtBottom, "spanx, growx, wrap");
		panGesamt.add(labGesamtBottom, BorderLayout.SOUTH);

		//-> beginnt oben
		//contentPane.add(panStartTest, "growy");

		frame.setContentPane(panGesamt);
		frame.pack(); // Resizes automatically
		//frame.setLocationRelativeTo(null); // Centers automatically
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		//endArea PanelGesamt

	} //endmethod drawForm()


	//-----------------------------------------------------------------------------------

	//Area MethodsBrowser

	public static void AppSetter(String setApp)
	{
		whichApp = setApp;
		appTextField.setText(setApp);
		appTextField.setHorizontalAlignment(JTextField.CENTER);
	}


	public static void browserSetter(String setBrowser)
	{
		whichBrowser = setBrowser;
		browserTextField.setText(setBrowser);
		browserTextField.setHorizontalAlignment(JTextField.CENTER);
	}


	//-------------------------------------------------------------------------------

	@DataProvider
	public void dataToTestNG()
	{
		//i = Anzahl der Durchläufe
		//j = Anzahl der Parameter

		Object[][] parameter = new Object[1][3];

		parameter[0][0] = whichApp;
		parameter[0][1] = whichBrowser;
		parameter[0][2] = 5; // whichMode;
	}


	//	@Test(dataProvider = "parameter")
	//	public void dataFromTestNG(String testApp, String testBrowser, String testMode)
	//	{
	//
	//		System.out.println(testApp);
	//		System.out.println(testBrowser);
	//		System.out.println(testMode);
	//
	//		switch (testBrowser)
	//		{
	//		case "Chrome":
	//			callChrome(testApp);
	//			break;
	//
	//		case "Explorer":
	//
	//			//			StartBrowser startBrowser = new StartBrowser(textField.getText());
	//			//			startBrowser.startChrome_2v4();
	//			break;
	//
	//		case "Firefox":
	//
	//			//			StartBrowser startBrowser = new StartBrowser(textField.getText());
	//			//			startBrowser.startChrome_2v4();
	//			break;
	//
	//		case "FF54":
	//
	//			//			StartBrowser startBrowser = new StartBrowser(textField.getText());
	//			//			startBrowser.startChrome_2v4();
	//			break;
	//
	//		case "FF66":
	//
	//			//			StartBrowser startBrowser = new StartBrowser(textField.getText());
	//			//			startBrowser.startChrome_2v4();
	//			break;
	//
	//		case "Opera":
	//
	//			//			StartBrowser startBrowser = new StartBrowser(textField.getText());
	//			//			startBrowser.startChrome_2v4();
	//			break;
	//
	//		case "Safari":
	//
	//			//			StartBrowser startBrowser = new StartBrowser(textField.getText());
	//			//			startBrowser.startChrome_2v4();
	//			break;
	//
	//		case "WF55":
	//
	//			//			StartBrowser startBrowser = new StartBrowser(textField.getText());
	//			//			startBrowser.startChrome_2v4();
	//			//			break;
	//
	//		default:
	//			break;
	//		}
	//
	//	}

	//-------------------------------------------------------------------------------
	//
	//	public static void callChrome(String testApp)
	//	{
	//
	//		StartBrowser startBrowser = new StartBrowser(appTextField.getText());
	//		startBrowser.startChrome(testApp);
	//		//startBrowser.startChrome_2v4(testApp);
	//
	//	} //endmethod
	//
	//
	//	public static void callFF66()
	//	{
	//
	//		StartBrowser startBrowser = new StartBrowser(appTextField.getText());
	//		startBrowser.startFirefox();
	//
	//	} //endmethod
	//
	//
	//	public static void callFF33()
	//	{
	//
	//		StartBrowser startBrowser = new StartBrowser(appTextField.getText());
	//		startBrowser.startFF33();
	//
	//	} //endmethod
	//
	//
	//	public static void callFF54()
	//	{
	//
	//		StartBrowser startBrowser = new StartBrowser(appTextField.getText());
	//		startBrowser.startFF54();
	//
	//	} //endmethod
	//
	//
	//	public static void callExplorer()
	//	{
	//
	//		StartBrowser startBrowser = new StartBrowser(appTextField.getText());
	//		startBrowser.startExplorer();
	//
	//	} //endmethod
	//
	//
	//	public static void callWF55()
	//	{
	//
	//		StartBrowser startBrowser = new StartBrowser(appTextField.getText());
	//		startBrowser.startWF55();
	//
	//	} //endmethod
	//

	//	public static void actionsOnDatabase()
	//	{
	//
	//		String DatenEingangsTabelle = "inputdata";
	//
	//		// Datenbankaustausch per Database_IO funzt
	//		DataBase_IO dbIO = new DataBase_IO();
	//		dbIO.clearTable(DatenEingangsTabelle);
	//		dbIO.readDataFromTable();
	//		dbIO.resetTable();
	//		dbIO.writeDataToTable();
	//
	//		dbIO.closeConnection();
	//
	//	} //endmethod

	//Achtung, nur excel xls
	//	public static void actionsOnExcel_jxl()
	//	{
	//
	//		String Datenkreis = "Stammdaten";
	//		String Datenart = "Straï¿½e";
	//
	//		//Datenbankaustausch per Database_IO funzt
	//		Excel_IO_jxl exIO = null;
	//
	//		try
	//		{
	//			exIO = new Excel_IO_jxl(Datenkreis, Datenart);
	//			exIO.RowCount();
	//			exIO.ColumnDictionary();
	//			exIO.GetCell(Datenart);
	//			exIO.GetRowsAndColumns();
	//		} catch (Exception e)
	//		{
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//
	//	} //endmethod

	//	public static void actionsOnExcel_xssf()
	//	{
	//
	//		String Datenkreis = "Stammdaten";
	//		String Datenart = "Nachname";
	//
	//		// Datenbankaustausch per Database_IO funzt
	//		Excel_IO_xssf exIO = new Excel_IO_xssf();
	//
	//		try
	//		{
	//			exIO.getDataColumn(Datenkreis, Datenart);
	//		} catch (IOException e)
	//		{
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//
	//	} //endmethod

	//endArea MethodsBrowser

	//-----------------------------------------------------------------------------------

	//Area calcSlider

	public static int calculateSliderValue(int slidererValue)

	{
		int calcValue = 0;

		if (slidererValue >= 0 && slidererValue < 3)
		{
			calcValue = 1;
		} else
			if (slidererValue >= 3 && slidererValue < 13)
			{
				calcValue = 2;
			} else
				if (slidererValue >= 13 && slidererValue < 23)
				{
					calcValue = 3;
				} else
					if (slidererValue >= 23 && slidererValue < 33)
					{
						calcValue = 5;
					} else
						if (slidererValue >= 33 && slidererValue < 43)
						{
							calcValue = 7;
						} else
							if (slidererValue >= 43 && slidererValue < 53)
							{
								calcValue = 10;
							} else
								if (slidererValue >= 53 && slidererValue < 63)
								{
									calcValue = 20;
								} else
									if (slidererValue >= 63 && slidererValue < 73)
									{
										calcValue = 30;
									} else
										if (slidererValue >= 73 && slidererValue < 83)
										{
											calcValue = 50;
										} else
											if (slidererValue >= 83 && slidererValue < 93)
											{
												calcValue = 70;
											} else
												if (slidererValue >= 93 && slidererValue <= 100)
												{
													calcValue = 100;
												}
		;

		return calcValue;

	}


	//endArea calcSlider

	//-----------------------------------------------------------------------------------

	//Area MethodsGUI

	static void checkAnyPanelButtonChanged()
	{
		if (countButtonsBrowser != countButtonsBrowserAlt)
		{
			switchPanBrowserColor();
			countButtonsBrowserAlt = countButtonsBrowser;
		}

		if (countButtonsTestInReihe != countButtonsTestInReiheAlt)
		{
			switchPanTestInReiheColor();
			countButtonsTestInReiheAlt = countButtonsTestInReihe;
		}

		if (countButtonsTestParallel != countButtonsTestParallelAlt)
		{
			switchPanTestParallelColor();
			countButtonsTestParallelAlt = countButtonsTestParallel;
		}

		if (countButtonsErrorProv != countButtonsErrorProvAlt)
		{
			switchPanTestErrorProvColor();
			countButtonsErrorProvAlt = countButtonsErrorProv;
		}

		if (countCheckBoxParaPara != countCheckBoxParaParaAlt)
		{
			switchPanTestParaParaColor();
			countCheckBoxParaParaAlt = countCheckBoxParaPara;
		}

		//kann Test gestartet werden
		switchPanTestStartenColor();

	}


	static void activateDatenXML()
	{
		panLadeXML.setBackground(Color.green);
		labLadeXML.setForeground(Color.blue);
		butLadeXML.setText("XML geladen");
		butLadeXML.setForeground(Color.blue);
		tfLadeXML.setForeground(Color.blue);
	}


	static void deactivateDatenXML()
	{
		panLadeXML.setBackground(Color.darkGray);
		labLadeXML.setForeground(Color.lightGray);
		butLadeXML.setSelected(false);
		butLadeXML.setText("XML laden");
		butLadeXML.setForeground(Color.black);
		tfLadeXML.setForeground(Color.black);
	}


	static void activateDatenExcel_jxl()
	{
		panLadeExcel_jxl.setBackground(Color.green);
		labLadeExcel_jxl.setForeground(Color.blue);
		butLadeExcel_jxl.setText("Excel_jxl geladen");
		butLadeExcel_jxl.setForeground(Color.blue);
		tfLadeExcel_jxl.setForeground(Color.blue);
	}


	static void deactivateDatenExcel_jxl()
	{
		panLadeExcel_jxl.setBackground(Color.darkGray);
		labLadeExcel_jxl.setForeground(Color.lightGray);
		butLadeExcel_jxl.setSelected(false);
		butLadeExcel_jxl.setText("Excel_jxl laden");
		butLadeExcel_jxl.setForeground(Color.black);
		tfLadeExcel_jxl.setForeground(Color.black);
	}


	static void activateDatenExcel_xssf()
	{
		panLadeExcel_xssf.setBackground(Color.green);
		labLadeExcel_xssf.setForeground(Color.blue);
		butLadeExcel_xssf.setText("Excel_xssf geladen");
		butLadeExcel_xssf.setForeground(Color.blue);
		tfLadeExcel_xssf.setForeground(Color.blue);
	}


	static void deactivateDatenExcel_xssf()
	{
		panLadeExcel_xssf.setBackground(Color.darkGray);
		labLadeExcel_xssf.setForeground(Color.lightGray);
		butLadeExcel_xssf.setSelected(false);
		butLadeExcel_xssf.setText("Excel_xssf laden");
		butLadeExcel_xssf.setForeground(Color.black);
		tfLadeExcel_xssf.setForeground(Color.black);
	}


	static void activateDatenTabelle()
	{
		panLadeTabelle.setBackground(Color.green);
		labLadeTabelle.setForeground(Color.blue);
		butLadeTabelle.setText("Tabelle geladen");
		butLadeTabelle.setForeground(Color.blue);
		tfLadeTabelle.setText("Testdaten online");
		tfLadeTabelle.setForeground(Color.blue);
	}


	static void deactivateDatenTabelle()
	{
		panLadeTabelle.setBackground(Color.darkGray);
		labLadeTabelle.setForeground(Color.lightGray);
		butLadeTabelle.setSelected(false);
		butLadeTabelle.setText("Tabelle laden");
		butLadeTabelle.setForeground(Color.black);
		tfLadeTabelle.setText("SELECT Testdaten");
		tfLadeTabelle.setForeground(Color.black);
	}


	static void switchPanDataFeed()
	{
		if (countButtonsLadeDaten > 0)
		{
			panTestsInReiheBlack.setBackground(Color.green);
			labTestsInReiheBlack.setText("Funktionstest(s) gewählt");
			labTestsInReiheBlack.setForeground(Color.blue);
			panTestsInReiheResultBlack.setBackground(Color.green);
			labTestsInReiheResultBlack.setText("Erg.");
			labTestsInReiheResultBlack.setForeground(Color.blue);
			switchPanTestStartenColor();

			//Parallel auf schwarz
			if (!bolPanTestParallelBlack)
			{
				switchPanTestParallelBlack();
			}

		} else
		{
			panTestsInReiheBlack.setBackground(Color.darkGray);
			labTestsInReiheBlack.setText("Funktionstest(s) wählen");
			labTestsInReiheBlack.setForeground(Color.lightGray);
			panTestsInReiheResultBlack.setBackground(Color.darkGray);
			labTestsInReiheResultBlack.setText("oder");
			labTestsInReiheResultBlack.setForeground(Color.lightGray);

		}
	}


	// Panels farbig schalten
	static void switchPanBrowserColor()
	{
		if (countButtonsBrowser > 0)
		{
			panBrowser.setBackground(Color.green);
			labBrowser.setText("Tests durchführen");
			labBrowser.setForeground(Color.blue);
		} else
		{
			panBrowser.setBackground(Color.darkGray);
			labBrowser.setText(" Browser wählen ");
			labBrowser.setForeground(Color.lightGray);
		}

	}


	static void switchPanTestInReiheColor()
	{
		if (countButtonsTestInReihe > 0)
		{
			panTestsInReiheBlack.setBackground(Color.green);
			labTestsInReiheBlack.setText("Funktionstest(s) gewählt");
			labTestsInReiheBlack.setForeground(Color.blue);
			panTestsInReiheResultBlack.setBackground(Color.green);
			labTestsInReiheResultBlack.setText("Erg.");
			labTestsInReiheResultBlack.setForeground(Color.blue);
			switchPanTestStartenColor();

			//Parallel auf schwarz
			if (!bolPanTestParallelBlack)
			{
				switchPanTestParallelBlack();
			}

		} else
		{
			panTestsInReiheBlack.setBackground(Color.darkGray);
			labTestsInReiheBlack.setText("Funktionstest(s) wählen");
			labTestsInReiheBlack.setForeground(Color.lightGray);
			panTestsInReiheResultBlack.setBackground(Color.darkGray);
			labTestsInReiheResultBlack.setText("oder");
			labTestsInReiheResultBlack.setForeground(Color.lightGray);
		}
	}


	static void switchPanTestParallelColor()
	{
		if (countButtonsTestParallel > 0)
		{
			panTestsParallelBlack.setBackground(Color.green);
			labTestsParallelBlack.setText("Vielfachtest(s) gewählt");
			labTestsParallelBlack.setForeground(Color.blue);
			panParallelQuantityBlack.setBackground(Color.green);
			//labParallelQuantity.setText("Anzahl 1x oder mehr");
			labParallelQuantityBlack.setForeground(Color.blue);
			panTestsParallelResultBlack.setBackground(Color.green);
			labTestsParallelResultBlack.setText("Erg.");
			labTestsParallelResultBlack.setForeground(Color.blue);
			if (!bolPanTestParallelBlack)
			{
				switchPanTestParallelBlack();
			}

		} else
		{
			panTestsParallelBlack.setBackground(Color.darkGray);
			labTestsParallelBlack.setText("Vielfachtest(s) wählen");
			labTestsParallelBlack.setForeground(Color.lightGray);
			panParallelQuantityBlack.setBackground(Color.darkGray);
			//labParallelQuantity.setText("Anzahl 1x oder mehr");
			labParallelQuantityBlack.setForeground(Color.lightGray);
			panTestsParallelResultBlack.setBackground(Color.darkGray);
			labTestsParallelResultBlack.setText("    ");
			labTestsParallelResultBlack.setForeground(Color.lightGray);
		}
	}


	static void switchPanTestParaParaColor()
	{
		if (countCheckBoxParaPara > 0)
		{
			panTestsParaParaBlack.setBackground(Color.green);
			labTestsParaParaBlack.setText("LT");
			labTestsParaParaBlack.setForeground(Color.blue);
			if (!bolPanTestsParaParaBlack)
			{
				switchPanTestParaParaBlack();
			}

		} else
		{
			panTestsParaParaBlack.setBackground(Color.darkGray);
			labTestsParaParaBlack.setText("LT");
			labTestsParaParaBlack.setForeground(Color.lightGray);
		}
	}


	static void switchPanTestErrorProvColor()
	{
		if (countButtonsErrorProv > 0)
		{
			panTestsErrorProvBlack.setBackground(Color.green);
			labTestsErrorProvBlack.setText("Fehler provozieren gewählt");
			labTestsErrorProvBlack.setForeground(Color.blue);
			panTestsErrorProvResultBlack.setBackground(Color.green);
			labTestsErrorProvResultBlack.setText("Erg.");
			labTestsErrorProvResultBlack.setForeground(Color.blue);
			if (!bolPanErrorProvBlack)
			{
				switchPanErrorProvBlack();
			}

		} else
		{
			panTestsErrorProvBlack.setBackground(Color.darkGray);
			labTestsErrorProvBlack.setText("Test per Fehler provozieren");
			labTestsErrorProvBlack.setForeground(Color.lightGray);
			panTestsErrorProvResultBlack.setBackground(Color.darkGray);
			labTestsErrorProvResultBlack.setText("    ");
			labTestsErrorProvResultBlack.setForeground(Color.lightGray);
		}
	}


	// Panels dunkel schalten
	static void switchPanTestInReiheBlack()
	{
		panTestsInReiheBlack.setBackground(Color.darkGray);
		panTestsInReiheResultBlack.setBackground(Color.darkGray);
		bolPanTestInReiheBlack = true;
	}


	static void switchPanTestParallelBlack()
	{
		panTestsParallelBlack.setBackground(Color.darkGray);
		panTestsParallelResultBlack.setBackground(Color.darkGray);
		panParallelQuantityBlack.setBackground(Color.darkGray);
		bolPanTestParallelBlack = true;
	}


	static void switchPanTestParaParaBlack()
	{
		panTestsParaParaBlack.setBackground(Color.darkGray);
		bolPanTestsParaParaBlack = true;
	}


	static void switchPanErrorProvBlack()
	{
		panTestsErrorProvBlack.setBackground(Color.darkGray);
		panTestsErrorProvResultBlack.setBackground(Color.darkGray);
		bolPanErrorProvBlack = true;
	}


	//TestStarten
	static void switchPanTestStartenColor()
	{
		if ((countButtonsLadeDaten > 0 && countButtonsBrowser > 0 && countButtonsTestInReihe > 0)
				|| (countButtonsLadeDaten > 0 && countButtonsBrowser > 0 && countButtonsTestParallel > 0)
				|| (countButtonsLadeDaten > 0 && countButtonsBrowser > 0 && countButtonsErrorProv > 0))
		{
			butLoescheTest.setText("Testablauf löschen?");
			butLoescheTest.setForeground(Color.orange);
			butStarteTest.setText("Testablauf jetzt starten");
			butStarteTest.setForeground(Color.blue);
			butStarteTest.setBackground(Color.green);
			butStartZeitTest.setText("oder Startzeit einstellen");
			butStartZeitTest.setForeground(Color.blue);
			butStartZeitTest.setBackground(Color.green);

			labGesamtHead.setText("Testablauf kann gestartet werden");
			labGesamtHead.setForeground(Color.green);
			bolPanTestStarten = true;

		} else
		{
			butLoescheTest.setText("Testablauf löschen?");
			butLoescheTest.setForeground(Color.black);
			butStarteTest.setText("Testablauf ?");
			butStarteTest.setForeground(Color.black);
			butStarteTest.setBackground(new JButton().getBackground());
			butStarteTest.setOpaque(true);
			butStartZeitTest.setText("Startzeit ?");
			butStartZeitTest.setForeground(Color.black);
			butStartZeitTest.setOpaque(true);
			butStartZeitTest.setBackground(new JButton().getBackground());
			labGesamtHead.setText("Datenquelle der Testdaten wählen + Browser wählen + Testvariante(n) wählen -> Testablauf starten");
			labGesamtHead.setForeground(Color.white);
			butStarteTest.setSelected(false);
			bolPanTestStarten = false;
		}
	}


	// Test gestartet
	static void switchPanTestGestartetColor()
	{
		if (bolPanTestStarten == true)
		{
			butLoescheTest.setText("Nachricht an eMail?");
			butLoescheTest.setForeground(Color.blue);
			butLoescheTest.setBackground(Color.green);
			//butStarteTest.setOpaque(true);
			butStarteTest.setText("Tests laufen !");
			butStarteTest.setBackground(Color.orange);
			butStartZeitTest.setText("Testablauf Pause?");
			butStartZeitTest.setBackground(Color.orange);
			butTestAbbrechen.setText("Testlauf abbrechen?");
			butTestAbbrechen.setForeground(Color.white);
			butTestAbbrechen.setBackground(Color.red);

			labGesamtHead.setText("Testablauf gestartet");
			labGesamtHead.setForeground(Color.orange);
			//labGesamtHead.setBackground(Color.blue);
			bolPanTestStarten = false;
			bolPanTestGestartet = true;
		}
	}


	static void switchPanTestBeendetColor()
	{
		if (bolPanTestBeendet == true)
		{
			butLoescheTest.setText("Testablauf löschen?");
			butLoescheTest.setForeground(Color.orange);
			butLoescheTest.setBackground(Color.blue);
			butStarteTest.setText("Testablauf beendet");
			butStarteTest.setForeground(Color.white);
			butStarteTest.setBackground(Color.blue);
			butStartZeitTest.setText("Ergebnisse speichern?");
			butStartZeitTest.setForeground(Color.white);
			butStartZeitTest.setBackground(Color.blue);
			butTestAbbrechen.setText("Erneut durchführen?");
			butTestAbbrechen.setForeground(Color.white);
			butTestAbbrechen.setBackground(Color.blue);

			labGesamtHead.setText("Testablauf beendet");
			labGesamtHead.setForeground(Color.white);
			labGesamtHead.setBackground(Color.blue);
			bolPanTestBeendet = false;
		} else
		{
			butLoescheTest.setText("Testablauf löschen?");
			butLoescheTest.setForeground(Color.lightGray);
			butLoescheTest.setBackground(Color.darkGray);
			butStarteTest.setText("Testablauf ?");
			butStarteTest.setForeground(Color.lightGray);
			butStarteTest.setBackground(Color.darkGray);
			butStartZeitTest.setText("Startzeit ?");
			butStartZeitTest.setForeground(Color.lightGray);
			butStartZeitTest.setBackground(Color.darkGray);
			butTestAbbrechen.setText("Testablauf abbrechen");
			butTestAbbrechen.setForeground(Color.lightGray);
			butTestAbbrechen.setBackground(Color.darkGray);

			labGesamtHead.setText("Datenquelle der Testdaten wählen + Browser wählen + Testvariante(n) wählen -> Testablauf starten");
			labGesamtHead.setForeground(Color.white);
			labGesamtHead.setBackground(Color.darkGray);
		}
	}

	//endArea MethodsGUI

	//-------------------------------------------------------------------------------

} //endClass StartTestGUI

//Reste:					
//sliderNewUser.setBorder(lineGreen);//.setBorder(new PartialLineBorder(Color.gray, new Insets(1, 0, 1, 1)));
//fireActionPerformed(sliderNewUser.addChangeListener(null));
//sliderNewUser.firePropertyChange(strValue, 1, 1);
//wrap indicates a new row
//if (labResultNewUserR.setBackground(new JLabel().getBackground()) == "Color.green")
//labTestResultP.setText("<html><body>pass<br>fail</body></html>"); //zwei Zeilen
//labTestResultP = new JLabel("Hallo"); funzt auch
