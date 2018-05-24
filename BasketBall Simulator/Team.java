package edu.upenn.cis350.hwk4;

public class Team extends Observer{
	private String name;
	private String region;
	private int seed;
	private double elo;
	private int wins;
	private int losses;
	private Bracket subject;

	public Team(String name, String region, int seed, double elo) {
		this.name = name;
		this.region = region;
		this.seed = seed;
		this.elo = elo;
		this.subject = null;
		this.wins = 0;
		this.losses = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public String getRegion() {
		return region;
	}
	
	public int getSeed() {
		return seed;
	}
	
	public double getElo() {
		return elo;
	}
	
	public int getWins() {
		return wins;
	}
	
	public void setWins(int wins) {
		this.wins = wins;
	}
	
	public void setLosses(int losses) {
		this.losses = losses;
	}
	
	public int getLosses() {
		return losses;
	}
	
	public void setSubject(Bracket subject) {
		this.subject = subject;
		subject.attach(this);
	}
	
	public void setElo(double elo) {
		this.elo = elo;
	}

	@Override
	public void update() {
		
		
		State state = subject.getState();
		Team winner = state.getWinner();
		Team loser = state.getLoser();
		
		//update wins and losses
		if (this.name.equalsIgnoreCase(winner.getName())) {
			this.wins = this.wins + 1;
		}
		if (this.name.equalsIgnoreCase(loser.getName())) {
			this.losses = this.losses + 1;
		}
	}
}
