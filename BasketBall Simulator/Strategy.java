package edu.upenn.cis350.hwk4;

public interface Strategy {
	
	public Team getWinner(Game g);
	public Team getWinner(Team one, Team two);

}
