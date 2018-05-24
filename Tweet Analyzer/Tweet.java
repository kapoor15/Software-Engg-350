package edu.upenn.cis350.hwk1;

public class Tweet {
	private String location;
	private String date;
	private String text;
	
	public Tweet(String location, String date, String text) {
		this.location = location;
		this.date = date;
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getLocation() {
		return location;
	}
	
	public double getX() {
		String x = location.substring(1, location.indexOf(","));
		return Double.parseDouble(x);
	}
	
	public double getY() {
		String y = location.substring(location.indexOf(",") + 2, location.length() - 1);
		return Double.parseDouble(y);
	}

}
