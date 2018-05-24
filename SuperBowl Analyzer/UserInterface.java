package edu.upenn.cis350.hwk1;

public interface UserInterface {
	
	// subclasses must implement these methods
	public String getInputString();
	public int getInputInt();
	public void start();
	public void displayOutput(String s);
	
}
