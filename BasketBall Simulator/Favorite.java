package edu.upenn.cis350.hwk4;

public class Favorite implements Strategy{
	
	public Favorite() {
		
	}

	@Override
	public Team getWinner(Game g) {
		Team one = g.getFirst();
		Team two = g.getSecond();
		double firstElo = one.getElo();
		double secondElo = two.getElo();
		if (firstElo > secondElo) {
			return one;
		}
		else {
			return two;
		}
	}

	@Override
	public Team getWinner(Team one, Team two) {
		double firstElo = one.getElo();
		double secondElo = two.getElo();
		if (firstElo > secondElo) {
			return one;
		}
		else {
			return two;
		}
	}

}
