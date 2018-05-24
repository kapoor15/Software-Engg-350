package edu.upenn.cis350.hwk6;

public class Combiner implements Runnable{
	private int[][] input;
	private int startIdx;
	private int endIdx;
	private int boxSize;
	private int[][] tempImg;
	
	public Combiner(int[][] input, int[][] tempImg, int startIdx, int endIdx, int boxSize) {
		this.input = input;
		this.startIdx = startIdx;
		this.endIdx = endIdx;
		this.boxSize = boxSize;
		this.tempImg = tempImg;
	}

	@Override
	public void run() {
		Main.blur(input, tempImg, startIdx, endIdx, boxSize);
	}

}
