package edu.upenn.cis350.hwk4;

import java.util.ArrayList;

public class Bracket extends Subject{
	private ArrayList<Game> schedule;
	private State state;
	
	public Bracket() {
		schedule = new ArrayList<Game>();
		this.state = null;
	}
	
	public void addGame(Game game) {
		schedule.add(game);
	}
	
	public void addGame(int i, Game game) {
		schedule.add(i, game);
	}
	
	public Game getGame(int number) {
		return schedule.get(number);
	}
	
	public Game removeGame(int i) {
		return schedule.remove(i);
	}
	
	public State getState() {
		return state;
	}
	
	public void setState(State state) {
		this.state = state;
		notifyAllObservers();
	}
	
	public int size() {
		return schedule.size();
	}
	
	public ArrayList<Game> getAllGames() {
		return schedule;
	}

}
