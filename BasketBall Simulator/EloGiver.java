package edu.upenn.cis350.hwk4;

import java.util.ArrayList;

public class EloGiver {

	public EloGiver() {

	}

	public ArrayList<Team> getElo(String fileName) {

		EloReaderFactory erf = new EloReaderFactory();
		EloReader reader = erf.getEloReader(fileName);

		ArrayList<Team> teams = reader.read(fileName);
		return teams;
	}

}
