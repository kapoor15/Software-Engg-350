package edu.upenn.cis350.hwk4;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class EloJsonReader extends EloReader {

	@Override
	public ArrayList<Team> read(String fileName) {
		// TODO Auto-generated method stub
		ArrayList<Team> teams = new ArrayList<Team>();
		
		FileReader file;
		try {
			file = new FileReader(fileName);
			JSONTokener jsonToken = new JSONTokener(file);
			JSONArray array = new JSONArray(jsonToken);
			
			for (int i = 0; i < array.length(); i++) {
				JSONObject curr = array.getJSONObject(i);
				String name = (String) curr.get("Team");
				String region = (String) curr.get("Region");
				Integer seed = (Integer) curr.get("Seed");
				Integer intElo = (Integer) curr.get("Elo");
				String stringElo = "" + intElo;
				Double doubleElo = Double.parseDouble(stringElo);
				Team team = new Team(name, region, seed, doubleElo);
				teams.add(team);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return teams;
	}

}
