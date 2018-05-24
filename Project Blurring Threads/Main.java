package edu.upenn.cis350.hwk6;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		// get picture and error for no such file
		File temp = new File(args[0]);
		if (!temp.exists()) {
			System.out.println("could not find input file - Program terminating");
			System.exit(0);
		}
		int[][] input = ImageData.load(args[0]);
		int[][] tempImg = ImageData.load(args[0]);

		// store/create end picture position
		File output = new File(args[1]);
		if (!output.exists()) {
			try {
				output.createNewFile();
			} catch (IOException e) {
			}
		}

		// store blur box size
		int boxSize = 0;
		try {
			boxSize = Integer.parseInt(args[2]);
			if (boxSize < 1) {
				System.out.println("box size is less than 1 - Pogram terminating");
				System.exit(0);
			}
		} catch (NumberFormatException e) {
			System.out.println("boxSize is not a valid number - Program terminating");
			System.exit(0);
		}

		// store no. of threads
		int threadCount = 0;
		try {
			threadCount = Integer.parseInt(args[3]);
			if (threadCount < 1) {
				System.out.println("boxSize is less than one - terminating program");
				System.exit(0);
			}
		} catch (NumberFormatException e) {
			System.out.println("Not a valid number - terminating program");
			System.exit(0);
		}

		// divide no. of threads to obtain rows per thread
		int rows = input.length;
		int threadWidth = (int) (1.0 * rows / threadCount);

		List<Thread> threads = new ArrayList<Thread>();

		// create threads with runnable instance to blur tempImg
		for (int i = 0; i < threadCount; i++) {
			if (i == threadCount - 1) {
				threads.add(new Thread(new Combiner(input, tempImg, i * threadWidth, rows - 1, boxSize)));
			} else {
				threads.add(new Thread(
						new Combiner(input, tempImg, i * threadWidth, (i * threadWidth) + threadWidth - 1, boxSize)));
			}
			threads.get(i).start();
		}

		for (Thread t : threads) {
			t.join();
		}

		// create the new output image
		ImageData.save(tempImg, args[1]);

	}

	public static void blur(int[][] input, int[][] tempImg, int startIdx, int endIdx, int boxSize) {
		for (int i = startIdx; i <= endIdx; i++) {
			tempImg[i] = getRowAverage(input, i, boxSize);
		}
	}

	public static int[] getRowAverage(int[][] input, int index, int boxSize) {
		int[][] temp = input;
		int[] returnArray = new int[temp[index].length];

		int maxR = Math.min(index + boxSize - 1, temp.length - 1);
		int minR = Math.max(0, index - (boxSize - 1));

		for (int i = 0; i < temp[index].length; i++) {
			double totalRed = 0;
			double totalGreen = 0;
			double totalBlue = 0;

			double divider = 0;

			int minC = Math.max(0, i - (boxSize - 1));
			int maxC = Math.min(temp[index].length - 1, i + (boxSize - 1));
			// every time you look at surrounding of temp[index][i]
			if (index == 1 && i == 3) {
			}
			for (int r = minR; r <= maxR; r++) {
				for (int c = minC; c <= maxC; c++) {

					Color value = new Color(input[r][c]);
					totalRed += value.getRed();
					totalGreen += value.getGreen();
					totalBlue += value.getBlue();
					divider += 1;

				}
			}

			// get final averaged colors

			int answer = -1;
			int finalRed = (int) (totalRed / divider);
			int finalGreen = (int) (totalGreen / divider);
			int finalBlue = (int) (totalBlue / divider);
			Color blurred = new Color(finalRed, finalGreen, finalBlue);
			answer = blurred.getRGB();

			returnArray[i] = answer;
		}
		return returnArray;
	}

}
