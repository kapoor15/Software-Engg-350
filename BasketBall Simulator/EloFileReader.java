package edu.upenn.cis350.hwk4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class EloFileReader extends EloReader {

	@Override
	public ArrayList<Team> read(String fileName) {
		
		
		ArrayList<Team> teams = new ArrayList<Team>();
		
		try {
			@SuppressWarnings("resource")
			BufferedReader eloReader = new BufferedReader(new FileReader(fileName));
			String readLine = "";
			eloReader.readLine();
			while ((readLine = eloReader.readLine()) != null) {
				String[] split = readLine.split(",");
				Team readTeam = new Team(split[0], split[1], Integer.parseInt(split[2]), 
						Double.parseDouble(split[3]));
				teams.add(readTeam);
			}
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return teams;
	}

}
