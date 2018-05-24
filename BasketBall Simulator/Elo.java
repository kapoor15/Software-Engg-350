package edu.upenn.cis350.hwk4;

public class Elo implements Strategy {
	
	public Elo() {
		
	}

	@Override
	public Team getWinner(Game g) { 
		
		Team one = g.getFirst();
		Team two = g.getSecond();
		double firstElo = one.getElo();
		double secondElo = two.getElo();
		boolean firstBetter = false;
		double eloDifference = 0;
		if (firstElo > secondElo) {
			firstBetter = true;
			eloDifference = firstElo - secondElo;
		}
		else {
			firstBetter = false;
			eloDifference = secondElo - firstElo;
		}
		double winExp = 1 / (Math.pow(10, (-eloDifference / 400)) + 1);
		double luck = Math.random();
		if (luck < winExp) {
			if (firstBetter) {
				return one;
			}
			else {
				return two;
			}
		}
		else {
			if (firstBetter) {
				return two;
			}
			else {
				return one;
			}
		}
	}

	@Override
	public Team getWinner(Team one, Team two) {

		double firstElo = one.getElo();
		double secondElo = two.getElo();
		boolean firstBetter = false;
		double eloDifference = 0;
		if (firstElo > secondElo) {
			firstBetter = true;
			eloDifference = firstElo - secondElo;
		}
		else {
			firstBetter = false;
			eloDifference = secondElo - firstElo;
		}
		double winExp = 1 / (Math.pow(10, (-eloDifference / 400)) + 1);
		double luck = Math.random();
		if (luck < winExp) {
			if (firstBetter) {
				return one;
			}
			else {
				return two;
			}
		}
		else {
			if (firstBetter) {
				return two;
			}
			else {
				return one;
			}
		}
	}
	
	public String getWinPercent(Team one, Team two) {
		double value = 0;
		double firstElo = one.getElo();
		double secondElo = two.getElo();
		boolean firstBetter = false;
		double eloDifference = 0;
		if (firstElo > secondElo) {
			firstBetter = true;
			eloDifference = firstElo - secondElo;
		}
		else {
			firstBetter = false;
			eloDifference = secondElo - firstElo;
		}
		value = 1 / (Math.pow(10, (-eloDifference / 400)) + 1);
		if (!firstBetter) {
			value = 1 - value;
		}
		value = value * 1000;
		value = Math.round(value);
		value = value/10.0;
		
		return value + "%";
		
	}

}
