package edu.upenn.cis350.hwk1;

public class Location {

	private String city;
	private String state;
	
	public Location(String city, String state) {
		this.city = city;
		this.state = state;
	}
	
	public String getCity() { return city; }
	public String getState() { return state; }
}
