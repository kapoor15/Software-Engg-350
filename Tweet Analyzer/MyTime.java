package edu.upenn.cis350.hwk1;

import java.util.Calendar;
import java.util.Comparator;


public class MyTime implements Comparable<MyTime>{
private Calendar x;
private int count;

public static final Comparator<MyTime> COUNT_ORDER = new CountOrder();

	public MyTime(Calendar x, int count) {
		this.x = x;
		this.count = count;
	}
	
	public Calendar getCal() {
		return x;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int add) {
		this.count = add;
	}

	@Override
	public int compareTo(MyTime o) {
		// TODO Auto-generated method stub
		return (this.x.compareTo(o.x));
	}
	
	private static class CountOrder implements Comparator<MyTime> {

		@Override
		public int compare(MyTime o1, MyTime o2) {
			// TODO Auto-generated method stub
			return o2.getCount() - o1.getCount();
		}
	}
}
