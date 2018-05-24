package edu.upenn.cis350.hwk1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
	private static ArrayList<Tweet> tweets;
	private static ArrayList<State> states;
	private static boolean statesDetected = false;
	private static boolean tweetsAddedState;
	static Logger logger = Logger.getLogger(Main.class.getName());

	public static void main(String args[]) {
		// initialize fields
		tweets = new ArrayList<Tweet>();
		states = new ArrayList<State>();
		tweetsAddedState = false;

		// throw argument error
		if (args.length > 3) {
			System.out.println("Cannot have less than 3 command line arguments." 
		+ " Program terminating");
			logger.info(System.currentTimeMillis() + "ERROR: Program was fun with fewer than " 
		+ "3 arguments");
			System.exit(0);
		}

		// read args
		File tweetsFile = new File(args[0]);
		File statesFile = new File(args[1]);
		File logsFile = new File(args[2]);

		if (!logsFile.exists()) {
			try {
				logsFile.createNewFile();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Unknown error in creating log file." + "Program terminating");
				logger.info(System.currentTimeMillis() + "ERROR: Could not create log file");
				System.exit(0);
			}
		}

		if (!tweetsFile.canRead() || !tweetsFile.canWrite() || !statesFile.canRead() || 
				!statesFile.canWrite()) {
			System.out.println("Cannot read and / or write to tweets and or states file");
			logger.info(System.currentTimeMillis() + "ERROR: Could not read and / or write "
					+ "tweets and / or states file");
			System.exit(0);
		}

		if (!logsFile.canWrite()) {
			System.out.println("Cannot write to log file" + "Program terminating");
			logger.info(System.currentTimeMillis() + "ERROR: Cannot write to log file ");
			System.exit(0);
		}

		// logger files
		try {
			Handler FileHandler = new FileHandler(args[2], true);
			logger.addHandler(FileHandler);
			FileHandler.setLevel(Level.ALL);
			logger.setLevel(Level.ALL);

		} catch (Exception exception) {
			System.out.println("Unknown error in creating log file" + "Program terminating");
			logger.info(System.currentTimeMillis() + "ERROR: Could not create file handler ");
			System.exit(0);
		}

		logger.info(System.currentTimeMillis() + ": " + args[0] + ", " + args[1] + ", " 
		+ args[2]);

		BufferedReader tweetReader = null;
		BufferedReader stateReader = null;

		// read from tweets
		try {
			tweetReader = new BufferedReader(new FileReader(tweetsFile));
			String readLine = "";
			while ((readLine = tweetReader.readLine()) != null) {
				String[] split = readLine.split("\t");
				Tweet readTweet = new Tweet(split[0], split[2], split[3]);
				tweets.add(readTweet);
			}

		} catch (Exception e) {
			System.out.println("Error in reading tweets");
			logger.info(System.currentTimeMillis() + "ERROR: Could not read tweets ");
			System.exit(0);
		} finally {
			try {
				if (tweetReader != null) {
					tweetReader.close();
				}
			} catch (Exception e) {
				System.out.println("Error in reading tweets");
				logger.info(System.currentTimeMillis() + "ERROR: Could not read tweets ");
				System.exit(0);
			}
		}

		// read from states
		try {
			stateReader = new BufferedReader(new FileReader(statesFile));
			String readLine = "";
			while ((readLine = stateReader.readLine()) != null) {
				String[] split = readLine.split(",");
				State readState = new State(split[0], split[1], split[2]);
				states.add(readState);
			}
		} catch (Exception e) {
			System.out.println("Error in reading states");
			logger.info(System.currentTimeMillis() + "ERROR: Could not read states ");
			System.exit(0);
		} finally {
			try {
				if (stateReader != null) {
					stateReader.close();
				}
			} catch (Exception e) {
				System.out.println("Error in reading tweets");
				logger.info(System.currentTimeMillis() + "ERROR: Could not read tweets ");
				System.exit(0);
			}
		}

		menu();

	}

	/*
	 * Function that ranks displays a menu with given choices.
	 * The output is a numbered list of all the possible options
	 */
	public static void menu() {

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		System.out.println("Choose one of the following options by pressing appropriate" + 
		" number");
		System.out.println("1. Rank the states by the number of tweets");
		System.out.println("2. Show the most popular hashtags in a given state");
		System.out.println("3. Show the number of tweets per hour containing a given term");
		System.out.println("4. Quit the program");

		String input = "";
		boolean check = false;
		// check valid menu choice
		while (!check) {
			input = scanner.nextLine();

			logger.info(System.currentTimeMillis() + ": " + input);

			// error for incorrect input
			if (!input.equals("1") && !input.equals("2") && !input.equals("3") && 
					!input.equals("4")) {
				System.out.println("Only a number can be chosen. For " + "example: '1'");

				System.out.println("Choose one of the following options by pressing appropriate" 
				+ " number");
				System.out.println("1. Rank the states by the number of tweets");
				System.out.println("2. Show the most popular hashtags in a given state");
				System.out.println("3. Show the number of tweets per hour containing a given "
						+ "term");
				System.out.println("4. Quit the program");
			}

			else {
				check = true;
			}
		}

		// do rank
		if (input.equals("1")) {
			rankTweetsState();
		}

		// do hashtags
		if (input.equals("2")) {
			popularState();
		}

		// do tweets per hour
		if (input.equals("3")) {
			tweetsPerHour();
		}

		// quit
		if (input.equals("4")) {
			logger.info(System.currentTimeMillis() + ": program ended");
			System.exit(0);
		}
	}

	/*
	 * Function that gives the number of tweets tweeted containing a chosen phrase.
	 * The output is a list of times and the number of tweets in that hour 
	 * sorted by the time in ascending order.
	 */
	public static void tweetsPerHour() {

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		System.out.println("Please enter the phrase you wish to analyze");

		String phrase = scanner.nextLine();
		// log entry
		logger.info(System.currentTimeMillis() + ": " + phrase);

		phrase = phrase.toLowerCase();

		// list of all the tweets with phrase
		ArrayList<Tweet> phraseTweets = new ArrayList<Tweet>();

		// go through all tweets to check for phrase
		for (Tweet a : tweets) {
			String tweetText = a.getText();
			tweetText = tweetText.toLowerCase();
			if (tweetText.contains(phrase)) {
				phraseTweets.add(a);
			}
		}

		boolean noPhrase = false;
		if (phraseTweets.size() == 0) {
			noPhrase = true;
		}

		// from these tweets, make a list of all the times
		ArrayList<MyTime> phraseTimes = new ArrayList<MyTime>();
		if (!noPhrase) {
			for (int i = 0; i < phraseTweets.size(); i++) {
				// get date and time
				String tweetTime = phraseTweets.get(i).getDate();
				Calendar currCal = Calendar.getInstance();
				currCal.clear();
				String year = tweetTime.substring(0, 4);
				String month = tweetTime.substring(5, 7);
				String day = tweetTime.substring(8, 10);
				String hour = tweetTime.substring(11, 13);
				int yyyy = Integer.parseInt(year);
				int mm = Integer.parseInt(month);
				int dd = Integer.parseInt(day);
				int hh = Integer.parseInt(hour);
				currCal.set(yyyy, mm, dd, hh, 00, 00);
				MyTime adder = new MyTime(currCal, 1);
				boolean already = false;
				// check if time is already there
				for (MyTime a : phraseTimes) {
					if (a.getCal().equals(currCal)) {
						a.setCount(a.getCount() + 1);
						already = true;
					}
				}
				if (!already) {
					phraseTimes.add(adder);
				}
			}

			Collections.sort(phraseTimes);

			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			for (int i = 0; i < phraseTimes.size(); i++) {
				System.out.println(myFormat.format(phraseTimes.get(i).getCal().getTime()) + " "
						+ phraseTimes.get(i).getCount() + " times");
			}

		} else {
			System.out.println("Chosen phrase was not found");
		}

		// go back to menu
		menu();
	}

	/*
	 * Function that determines the top 10 hashtags from any chosen state.
	 * The output is a list of the top 10 hashtags in descending order of frequency
	 */
	public static void popularState() {

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		System.out.println("Please enter the name of the state you wish to analyze");

		boolean check = false;
		String stateName = "";

		// check valid state name
		while (!check) {
			stateName = scanner.nextLine();

			// log entry
			logger.info(System.currentTimeMillis() + ": " + stateName);

			for (State a : states) {
				if (a.getName().equalsIgnoreCase(stateName)) {
					check = true;
				}
			}

			if (!check) {
				System.out.println("Please enter a valid state name");
			}
		}

		// add tweets to selected state
		stateName = stateName.toLowerCase();
		if (!tweetsAddedState) {
			for (Tweet a : tweets) {
				double x = a.getX();
				double y = a.getY();
				double lowest = Double.MAX_VALUE;
				State low = null;
				for (State b : states) {
					double x2 = b.getX();
					double y2 = b.getY();
					double distance = Math.sqrt(((x - x2) * (x - x2)) + ((y - y2) * (y - y2)));
					if (distance < lowest) {
						lowest = distance;
						low = b;
					}
				}
				low.addTweet(a);
			}
			tweetsAddedState = true;
		}

		// store the hashtags
		ArrayList<HashTag> hashList = new ArrayList<HashTag>();

		// get hashtags and calculate frequencies
		ArrayList<Tweet> stateTweets = new ArrayList<Tweet>();
		for (State a : states) {
			if (a.getName().equalsIgnoreCase(stateName)) {
				stateTweets = a.getTweets();
			}
		}

		for (Tweet t : stateTweets) {
			String text = t.getText();
			text = text.toLowerCase();
			text = text.replace("#", " #");

			// analyze text
			String[] splitText = text.split(" ");
			for (int i = 0; i < splitText.length; i++) {
				String hash = "";
				// check if hashtag
				if (splitText[i].length() > 0) {
					if (splitText[i].charAt(0) == '#' && splitText[i].length() > 1) {

						int j = 0;

						boolean limitCheck = false;
						// remove non-letters
						for (j = 1; j < splitText[i].length(); j++) {
							if (!Character.isLetter(splitText[i].charAt(j))) {
								hash = splitText[i].substring(0, j);
								limitCheck = true;
								break;
							}
						}
						if (!limitCheck) {
							hash = splitText[i];
						}
						// check for already added hashTags
						if (hash.length() > 1) {
							boolean already = false;
							for (HashTag h : hashList) {
								if (h.getName().equals(hash)) {
									h.setFreq(h.getFreq() + 1);
									already = true;
								}
							}
							if (!already) {
								HashTag adder = new HashTag(hash, 1);
								hashList.add(adder);
							}
						}
					}
				}

			}

		}

		Collections.sort(hashList, HashTag.FREQ_ORDER);

		for (int i = 0; i < 10; i++) {
			if (i < hashList.size()) {
				HashTag curr = hashList.get(i);
				System.out.println(curr.getName());
			}
		}

		menu();

	}

	/*
	 * Function that ranks the states based on the number of tweets from each.
	 * The output is a list of all the states in descending order of tweets
	 */
	public static void rankTweetsState() {

		// update counts from each state
		if (!statesDetected) {
			for (Tweet a : tweets) {
				double x = a.getX();
				double y = a.getY();
				double lowest = Double.MAX_VALUE;
				State low = null;
				for (State b : states) {
					double x2 = b.getX();
					double y2 = b.getY();
					double distance = Math.sqrt(((x - x2) * (x - x2)) + ((y - y2) * (y - y2)));
					if (distance < lowest) {
						lowest = distance;
						low = b;
					}
				}
				low.setNumTweets(low.getNumTweets() + 1);
				statesDetected = true;
			}
		}

		// make list to sort everything
		ArrayList<State> stateCount = new ArrayList<State>();
		for (State a : states) {
			stateCount.add(a);
		}

		// print out everything
		Collections.sort(stateCount, State.TWEET_ORDER);
		for (int i = 0; i < stateCount.size(); i++) {
			State a = stateCount.get(i);
			if (a.getNumTweets() != 0) {
				System.out.println(a.getName() + ": " + a.getNumTweets());
			}
		}

		// go back to menu
		menu();
	}
}
