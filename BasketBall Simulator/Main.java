package edu.upenn.cis350.hwk4;

import java.io.IOException;

public class Main {
	
	
	public static void main(String[] args) throws IOException {
		String eloFile = args[0];
		String gamesFile = args[1];
		String readLogFile = args[2];
		
		@SuppressWarnings("unused")
		LoggerClass logFile = new LoggerClass(readLogFile);
		PresentationTier presenter = new PresentationTier(eloFile, gamesFile);
		
		System.out.println("Welcome to Game Simulator");
		presenter.present();
	}

}
