package cn.assignment3;

public class Roster {
	private String number = "-n";
	private String firstName = "-f";
	private String lastName = "-l";
	/**
	 * 
	 */
	public Roster() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param number
	 * @param firstName
	 * @param lastName
	 */
	public Roster(String number, String firstName, String lastName) {
		super();
		this.number = number;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public String getNumber() {
		return number;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	
	@Override
	public String toString() {
		return "Roster [number=" + number + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
}
