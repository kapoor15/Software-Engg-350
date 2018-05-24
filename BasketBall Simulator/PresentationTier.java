package edu.upenn.cis350.hwk4;

import java.io.IOException;
import java.util.Scanner;

public class PresentationTier {
	private ControllerTier controller;
	static private LoggerClass logFile = LoggerClass.getInstance();

	public PresentationTier(String eloF, String gamesF) {
		controller = new ControllerTier(eloF, gamesF);
	}

	public void present() throws IOException {
		
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);

		int choice = -1;

		while (choice != 4) {
			System.out.println("Please choose from one of the options below:");
			prompt();
			choice = in.nextInt();
			logFile.write("Option chosen: " + choice);

			if (choice == 1) {
				System.out.println("Please enter the names of two teams one by one");
				in.nextLine();
				String firstTeam = in.nextLine();
				logFile.write("First team name entered: " + firstTeam);
				boolean good = false;
				while (!good) {
					if (!controller.isTeam(firstTeam)) {
						System.out.println("Please enter valid team name");
						firstTeam = in.nextLine();
						logFile.write("Error: First Team name re-entered: " + firstTeam);
					} else {
						good = true;
					}
				}
				good = false;
				System.out.println("please enter name of the other team");
				String secondTeam = in.nextLine();
				logFile.write("Second team name entered: " + secondTeam);
				while (!good) {
					if (!controller.isTeam(secondTeam)) {
						System.out.println("Please enter valid team name");
						secondTeam = in.nextLine();
						logFile.write("Error: Second Team name re-entered: " + secondTeam);
					} else {
						good = true;
					}
				}

				String winPercent = controller.getWinPercent(firstTeam, secondTeam);
				System.out.println("The probability that " + firstTeam + " wins is: " + winPercent);
				logFile.write("output is: " + "The probability that " + firstTeam + " wins is: " + winPercent);
				String winner = controller.simulateSingleGame(firstTeam, secondTeam);
				System.out.println("And the simulated winner is: " + winner);
				logFile.write("Out of" + firstTeam + "& " + secondTeam + "the simulated winner is: " + winner);

			}

			else if (choice == 2) {

				System.out.println("Please choose how you want to simulate from the options below:");
				System.out.println("1) Coin flip -- every game is 50/50 odds");
				System.out.println("2) Elo -- every game is randomly decided but considering Elo");
				System.out.println(
						"3) Favorite wins -- The team with the higher Elo rating always wins. If two teams have the"
								+ "same Elo, pick one randomly.");

				int chosen = in.nextInt();
				logFile.write("Option chosen is: " + chosen);

				if (chosen == 1) {
					String type = "Coin";
					controller.simulateTournament(type);
				} else if (chosen == 2) {
					String type = "Elo";
					controller.simulateTournament(type);
				} else if (chosen == 3) {
					String type = "Favorite";
					controller.simulateTournament(type);
				}
			}

			else if (choice == 3) {
				System.out.println("Please enter the name of the team you want to change the Elo" + " for");
				in.nextLine();
				String name = in.nextLine();
				logFile.write("User input: " + name);
				name = name.toLowerCase();
				boolean good = false;
				while (!good) {
					if (!controller.isTeam(name)) {
						System.out.println("Please enter valid team name");
						name = in.nextLine();
						logFile.write("Error: Second Team name re-entered: " + name);
					} else {
						good = true;
					}
				}
				
				System.out.println("Please enter the Elo you would like for this team");
				
				double givenElo = in.nextDouble();
				
				controller.setElo(name, givenElo);
				
				System.out.println("The elo has been changed");
				

			}

			else if (choice == 4) {
				logFile.write("Terminating program");
				System.out.println("Exiting application");
				logFile.close();
				System.exit(0);
			}

			else {
				System.out.println("Please select an option from 1-4 only");
				logFile.write("Output was: " + "Please select an option from 1-4 only");
			}
		}
	}

	public void prompt() {
		System.out.println("1) Simulate a single hypothetical game");
		System.out.println("2) Simulate the entire tournament");
		System.out.println("3) Change a single team's elo rating");
		System.out.println("4) Exit");
	}

}
