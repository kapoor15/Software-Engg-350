package edu.upenn.cis350.hwk1;

import java.util.ArrayList;
import java.util.Comparator;

public class State implements Comparable<State>{
	private String name;
	private String x;
	private String y;
	private int tweetNum;
	private ArrayList<Tweet> tweets;
	
	public static final Comparator<State> TWEET_ORDER = new TweetOrder();
	
	public State(String name, String x, String y) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.tweetNum = 0;
		this.tweets = new ArrayList<Tweet>();
	}

	public String getName() {
		return name;
	}
	
	public double getX() {
		double answer = Double.parseDouble(x);
		return answer;
	}
	
	public double getY() {
		double answer = Double.parseDouble(y);
		return answer;
	}
	
	public void setNumTweets(int x) {
		this.tweetNum = x;
	}
	
	public int getNumTweets() {
		return tweetNum;
	}
	
	public void addTweet(Tweet e) {
		tweets.add(e);
	}
	
	public ArrayList<Tweet> getTweets() {
		return tweets;
	}

	@Override
	public int compareTo(State o) {
		// TODO Auto-generated method stub
		if (this.getName().compareTo(o.getName()) > 0) {
			return 1;
		}
		else if (this.getName().compareTo(o.getName()) < 0) {
			return -1;
		}
		else {
			return 0;
		}
	}
	
	private static class TweetOrder implements Comparator<State> {

		public int compare(State o1, State o2) {

			if ((o1.tweetNum - o2.tweetNum) > 0) {
				return -1;
			} else if ((o1.tweetNum - o2.tweetNum) < 0) {
				return 1;
			} else {
				return 0;
			}
		}
	}
	
	
}
