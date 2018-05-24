package edu.upenn.cis350.hwk4;

public class CoinFlip implements Strategy {
	
	public CoinFlip() {
		
	}

	@Override
	public Team getWinner(Game g) {
		Team one = g.getFirst();
		Team two = g.getSecond();
		
		double luck = Math.random();
		
		if (luck < 0.5) {
			return one;
		}
		else {
			return two;
		}
	}
	

	@Override
	public Team getWinner(Team one, Team two) {
		
        double luck = Math.random();
		
		if (luck < 0.5) {
			return one;
		}
		else {
			return two;
		}
	}

}
