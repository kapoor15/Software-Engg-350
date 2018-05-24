package edu.upenn.cis350.hwk4;

import java.util.ArrayList;

public class ControllerTier {
	private DataTier data;
	private LoggerClass logFile = LoggerClass.getInstance();
	
	public ControllerTier(String eloF, String gamesF) {
		data = new DataTier(eloF, gamesF);
	}
	
	boolean isTeam(String teamName) {
		ArrayList<String> names = data.getAllTeamNames();
		String lowerCase = teamName.toLowerCase();
		if (names.contains(lowerCase)) {
			return true;
		}
		else {
			return false;
		}
	}

	String simulateSingleGame(String firstTeam, String secondTeam) {
		firstTeam = firstTeam.toLowerCase();
		secondTeam = secondTeam.toLowerCase();
		Team one = data.getTeam(firstTeam);
		Team two = data.getTeam(secondTeam);
		
		Elo method = new Elo();
		Team winner = method.getWinner(one, two);
		return winner.getName();
	}
	
	String getWinPercent(String firstTeam, String secondTeam) {
		firstTeam = firstTeam.toLowerCase();
		secondTeam = secondTeam.toLowerCase();
		Team one = data.getTeam(firstTeam);
		Team two = data.getTeam(secondTeam);
		
		Elo method = new Elo();
		String winPercent = method.getWinPercent(one, two);
		return winPercent;
	}

	void simulateTournament(String type) {
		Bracket schedule = data.getBracket();
		
		String results = "";
		String teamStuff = "";
		
		if (type.equalsIgnoreCase("Coin")) {
			CoinFlip method = new CoinFlip();
			
			for (int i = 0; i < schedule.size(); i++) {
				Game a = schedule.getGame(i);
				
				//find winner and loser
				Team winner = method.getWinner(a);
				Team one = a.getFirst();
				Team two = a.getSecond();
				Team loser = null;
				if (one.getName().equalsIgnoreCase(winner.getName())) {
					loser = two;
				}
				else {
					loser = one;
				}
				//find winner bracket goTo
				int goTo = a.getGoTo();
				
				//set subject state to update all other observers
				schedule.setState(new State(winner, loser, goTo));
				
				//add results
				results += "Game #" + i + " - " + one.getName() + " vs. " + two.getName() +
						", winner: " + winner.getName() + "\n";
				
			}
			//display team wins/losses
			ArrayList<Team> allTeams = data.getAllTeams();
			for (int i = 0; i < allTeams.size(); i++) {
				Team a = allTeams.get(i);
				teamStuff += a.getName() + " : " + a.getWins() + "W - " + a.getLosses() 
				+ "L ; Elo: " + a.getElo() + "\n";
			}
			
			System.out.println("=====Team Results=====");
			System.out.println(teamStuff);
			System.out.println("=====Game Results=====");
			System.out.println(results);
			
			logFile.write("Following was displayed " + "\n" + "=====Team Results====="
			+ teamStuff + "=====Game Results=====" + results);
		}
		
		else if (type.equalsIgnoreCase("Elo")) {
			Elo method = new Elo();
			
			for (int i = 0; i < schedule.size(); i++) {
				Game a = schedule.getGame(i);
				
				//find winner and loser
				Team winner = method.getWinner(a);
				Team one = a.getFirst();
				Team two = a.getSecond();
				Team loser = null;
				if (one.getName().equalsIgnoreCase(winner.getName())) {
					loser = two;
				}
				else {
					loser = one;
				}
				//find winner bracket goTo
				int goTo = a.getGoTo();
				
				//set subject state to update all other observers
				schedule.setState(new State(winner, loser, goTo));
				
				//add results
				results += "Game #" + i + " - " + one.getName() + " vs. " + two.getName() +
						", winner: " + winner.getName() + "\n";
				
			}
			//display team wins/losses
			ArrayList<Team> allTeams = data.getAllTeams();
			for (int i = 0; i < allTeams.size(); i++) {
				Team a = allTeams.get(i);
				teamStuff += a.getName() + " : " + a.getWins() + "W - " + a.getLosses() 
				+ "L ; Elo: " + a.getElo() + "\n";
			}
			
			System.out.println("=====Team Results=====");
			System.out.println(teamStuff);
			System.out.println("=====Game Results=====");
			System.out.println(results);
			
			logFile.write("Following was displayed " + "\n" + "=====Team Results====="
			+ teamStuff + "=====Game Results=====" + results);
		}
		
		else {
			Favorite method = new Favorite();
			
			for (int i = 0; i < schedule.size(); i++) {
				Game a = schedule.getGame(i);
				
				//find winner and loser
				Team winner = method.getWinner(a);
				Team one = a.getFirst();
				Team two = a.getSecond();
				Team loser = null;
				if (one.getName().equalsIgnoreCase(winner.getName())) {
					loser = two;
				}
				else {
					loser = one;
				}
				//find winner bracket goTo
				int goTo = a.getGoTo();
				
				//set subject state to update all other observers
				schedule.setState(new State(winner, loser, goTo));
				
				//add results
				results += "Game #" + i + " - " + one.getName() + " vs. " + two.getName() +
						", winner: " + winner.getName() + "\n";
				
			}
			//display team wins/losses
			ArrayList<Team> allTeams = data.getAllTeams();
			for (int i = 0; i < allTeams.size(); i++) {
				Team a = allTeams.get(i);
				teamStuff += a.getName() + " : " + a.getWins() + "W - " + a.getLosses() 
				+ "L ; Elo: " + a.getElo() + "\n";
			}
			
			System.out.println("=====Team Results=====");
			System.out.println(teamStuff);
			System.out.println("=====Game Results=====");
			System.out.println(results);
			
			logFile.write("Following was displayed " + "\n" + "=====Team Results====="
			+ teamStuff + "=====Game Results=====" + results);
			
			
		}
		data.clearWinsLosses();
	}

	void setElo(String name, double givenElo) {
		data.setElo(name, givenElo);
		logFile.write(name + "'s elo was changed to " + givenElo);
	}
	

}
