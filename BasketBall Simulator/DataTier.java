package edu.upenn.cis350.hwk4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataTier {
	private ArrayList<Team> teams;
	private Bracket schedule;
	
	public DataTier(String eloFile, String gamesFile) {
		
		schedule = new Bracket();
		EloGiver eg = new EloGiver();
		teams = eg.getElo(eloFile);
		readGames(gamesFile);
		
		
		//set observer and subject
		for (int i = 0; i < teams.size(); i++) {
			Team a = teams.remove(i);
			a.setSubject(schedule);
			teams.add(i, a);
		}
		for (int i = 0; i < schedule.size(); i++) {
			Game a = schedule.removeGame(i);
			a.setSubject(schedule);
			schedule.addGame(i, a);
		}
		
	}
	
	public void readGames(String gamesFile) {
		
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(gamesFile));
			String readLine = "";
			br.readLine();
			
			while ((readLine = br.readLine()) != null) {
				String[] split = readLine.split(",");
				//get teams
				String firstTeam = split[1];
				String secondTeam = split[2];
				Team one = null;
				Team two = null;
				for (int i = 0; i < teams.size(); i++) {
					Team t = teams.get(i);
					String name = t.getName();
					if (name.equalsIgnoreCase(firstTeam)) {
						one = t;
					}
					else if (name.equalsIgnoreCase(secondTeam)) {
						two = t;
					}
				}
				
				//add game
				Game readGame = new Game(Integer.parseInt(split[0]), one, two, Integer.parseInt(split[3]));
				schedule.addGame(readGame);
			}
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	ArrayList<String> getAllTeamNames() {
		ArrayList<String> names = new ArrayList<String>();
		for (Team t : teams) {
			names.add(t.getName().toLowerCase());
		}
		return names;
	}

	Team getTeam(String name) {
		for (Team t : teams) {
			if (t.getName().toLowerCase().equals(name)) {
				return t;
			}
		}
		return null;
	}

	Bracket getBracket() {
		return schedule;
	}
	
	void setElo(String name, double elo) {
		for (int i = 0; i < teams.size(); i++) {
			Team a = teams.remove(i);
			if (a.getName().equalsIgnoreCase(name)) {
				a.setElo(elo);
				teams.add(i, a);
			}
		}
	}

	ArrayList<Team> getAllTeams() {
		return teams;
	}
	
	void clearWinsLosses() {
		for (int i = 0; i < teams.size(); i++) {
			Team a = teams.remove(i);
			a.setWins(0);
			a.setLosses(0);
			teams.add(i, a);
		}
	}

}
