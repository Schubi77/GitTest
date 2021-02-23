package zVersuche;

public class Person {
	private String name;
	private int yearOfBirth;
	private String street;
	private String streetNumber;
	private int plz;
	private String residence;
	
	public Person(String name, int yearOfBirth, String street,
			String streetNumber, int plz, String residence) {
		this.name = name;
		this.yearOfBirth = yearOfBirth;
		this.street = street;
		this.streetNumber = streetNumber;
		this.plz = plz;
		this.residence = residence;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getYearOfBirth() {
		return yearOfBirth;
	}
	
	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getStreetNumber() {
		return streetNumber;
	}
	
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	
	public int getPlz() {
		return plz;
	}
	
	public void setPlz(int plz) {
		this.plz = plz;
	}
	
	public String getResidence() {
		return residence;
	}
	
	public void setResidence(String residence) {
		this.residence = residence;
	}

	@Override
	public String toString() {
		return "\"" + name + "," + yearOfBirth + "," + street
				+ "," + streetNumber + "," + plz + "," + residence + "\"";
	}
}

