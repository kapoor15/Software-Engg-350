package edu.upenn.cis350.hwk1;

import java.util.Comparator;

public class HashTag implements Comparable<HashTag> {
	String name;
	int freq;
	
	public static final Comparator<HashTag> FREQ_ORDER = new FreqOrder();
	
	public HashTag(String name, int freq) {
		this.name = name;
		this.freq = freq;
	}

	public String getName() {
		return name;
	}
	
	public int getFreq() {
		return freq;
	}
	
	public void setFreq(int x) {
		this.freq = x;
	}
	

	@Override
	public int compareTo(HashTag o) {
		// TODO Auto-generated method stub
		return this.getName().compareTo(o.getName());
	}
	
	private static class FreqOrder implements Comparator<HashTag> {

		public int compare(HashTag o1, HashTag o2) {

			if ((o1.getFreq() - o2.getFreq()) > 0) {
				return -1;
			} else if ((o1.getFreq() - o2.getFreq()) < 0) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}
