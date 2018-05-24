package edu.upenn.cis350.hwk4;

public class Game extends Observer{
	private Team first;
	private Team second;
	private int number;
	private int goTo;
	private Bracket subject;
	
	public Game(int number, Team first, Team second, int goTo) {
		this.number = number;
		this.first = first;
		this.second = second;
		this.goTo = goTo;
		this.subject = null;
	}
	
	public int getNumber() {
		return number;
	}

	public int getGoTo() {
		return goTo;
	}
	
	public Team getFirst() {
		return first;
	}
	
	public Team getSecond() {
		return second;
	}
	
	public void setSubject(Bracket subject) {
		this.subject = subject;
		subject.attach(this);
	}
	
	public int getNumberTeams() {
		int answer = 0;
		if (first != null) {
			answer++;
		}
	    if (second != null) {
	    	answer++;
	    }
	    return answer;
	}

	@Override
	public void update() {	
		State state = subject.getState();
		
		//to update game
		Team winner = state.getWinner();
		int numberGoTo = state.returnGoTo();
		
		if (numberGoTo == this.number) {
			if (first == null) {
				first = winner;
			}
			else {
				if (second == null) {
					second = winner;
				}
			}
		}
		
	}
}
