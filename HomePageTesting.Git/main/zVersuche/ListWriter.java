package zVersuche;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
//import com.thoughtworks.xstream.XStream;
//import com.thoughtworks.xstream.io.xml.DomDriver;
import cucumber.deps.com.thoughtworks.xstream.XStream;
import cucumber.deps.com.thoughtworks.xstream.io.xml.DomDriver;

public class ListWriter
{
	
		public static void main(String[] args) throws Exception {
			System.out.println("-----------------------------");
			System.out.println("Create and save list via .txt");
			System.out.println("-----------------------------");
			createAndSaveListViaTxt();
			
			System.out.println();
			System.out.println("-----------------------------");
			System.out.println("Create and save list via XStream");
			System.out.println("-----------------------------");
			createAndSaveListViaXStream();
		}
		
		private static void createAndSaveListViaTxt() throws Exception {
			// Erstellen der Personenliste
			List<Person> personenliste = new ArrayList<Person>();
			personenliste.add(new Person("Monika Mustermann", 1999, "Hauptstr.", "12a", 70000, "Stuttgart"));
			personenliste.add(new Person("Iegor Tarasov", 1988, "Landhausstr.", "14", 70190, "Stuttgart"));
			personenliste.add(new Person("Iuliia Gorshkova", 1985, "Bebelstr.", "65", 70183, "Hannover"));
			
			// Testausgabe 1
			System.out.println("Erstellte Personenliste:");
			for(Person person: personenliste) {
				System.out.println(person);
			}
			System.out.println();
			
			// Zusammenfassen der Personenliste
			StringBuilder builder = new StringBuilder();
			for(Person person: personenliste) {
				builder.append(person.toString());
				builder.append(System.getProperty("line.separator"));
			}
			builder.delete(builder.length()-System.getProperty("line.separator").length(), builder.length());
			
			// Speichern der Personenliste in eine .txt-Datei
			//File file = new File(System.getProperty("user.home")+"/Personenliste.txt");
			//File file = new File(System.getProperty("c:/Users/master/")+"/Personenliste.txt");
			File file = new File("j:/Java.Testing/HomePageTesting/Personenliste.txt");
			file.createNewFile();
			FileWriter fw = new FileWriter(file);
			fw.write(builder.toString());
			fw.flush();
			fw.close();
			
			// Leeren der Personenliste
			personenliste.removeAll(personenliste);
			
			// Auslesen der Datei
			BufferedReader bw = new BufferedReader(new FileReader(file));
			String line = null;
			List<String> linelist = new ArrayList<String>();
			while((line = bw.readLine()) != null) {
				linelist.add(line);
			}
			bw.close();
			
			// Umwandeln zu einzelnen Strings
			String[][] contents = new String[linelist.size()][6];
			for(int i=0; i<linelist.size(); i++) {
				contents[i] = linelist.get(i).replaceAll("\"", "").split(",");
			}
			
			// Einfügen in die Pesonenliste
			for(String[] content: contents) {
				personenliste.add(new Person(content[0], Integer.parseInt(content[1]), content[2], content[3], Integer.parseInt(content[4]), content[5]));
			}
			
			// Testausgabe 2
			System.out.println("Ausgelesene Personenliste:");
			for(Person person: personenliste) {
				System.out.println(person);
			}
		}
		
		private static void createAndSaveListViaXStream() throws Exception {
			// Erstellen der Personenliste
			List<Person> personenliste = new ArrayList<Person>();
			personenliste.add(new Person("Monika Mustermann", 1999, "Hauptstr.", "12a", 70000, "Stuttgart"));
			personenliste.add(new Person("Iegor Tarasov", 1988, "Landhausstr.", "14", 70190, "Stuttgart"));
			personenliste.add(new Person("Iuliia Gorshkova", 1985, "Bebelstr.", "65", 70183, "Hannover"));
			
			// Testausgabe 1
			System.out.println("Erstellte Personenliste:");
			for(Person person: personenliste) {
				System.out.println(person);
			}
			System.out.println();
			
			// Speichern der Personenliste in ein .xml Datei via XStream
			//File file = new File(System.getProperty("user.home")+"/Personenliste.xml");
			File file = new File("J:/Java.Testing/HomePageTesting/Personenliste.xml");
			file.createNewFile();
			XStream xstream = new XStream(new DomDriver());
			xstream.toXML(personenliste, new FileOutputStream(file));
			
			// Leeren der Personenliste
			personenliste.removeAll(personenliste);
			
			// Befüllen der Personenliste mit den Dateiinhalten via XStream
			personenliste = (List<Person>) xstream.fromXML(file);
			
			// Testausgabe 2
			System.out.println("Ausgelesene Personenliste:");
			for(Person person: personenliste) {
				System.out.println(person);
			}
		}

}
