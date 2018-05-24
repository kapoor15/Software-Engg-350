package edu.upenn.cis350.hwk4;

public class State {
	private Team winner;
	private Team loser;
	int goTo;
	
	public State(Team winner, Team loser, int goTo) {
		this.winner = winner;
		this.loser = loser;
		this.goTo = goTo;
	}
	
	public Team getWinner() {
		return winner;
	}
	
	public Team getLoser() {
		return loser;
	}
	
	public int returnGoTo() {
		return goTo;
	}

}
