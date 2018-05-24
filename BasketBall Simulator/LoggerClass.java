package edu.upenn.cis350.hwk4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LoggerClass {
	
	FileWriter writer;
	static LoggerClass logger;
	
	 LoggerClass(String fileName) {
		File logsFile = new File(fileName);
		
		if (!logsFile.exists()) {
			try {
				logsFile.createNewFile();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		writer = null;
		try {
			writer = new FileWriter(logsFile, true);
		}
		catch (Exception e) { e.printStackTrace(); }
		finally { }
	}
	
	public synchronized static LoggerClass getInstance() {
		if (logger == null) {
			logger = new LoggerClass("log.txt");
		}
		return logger;
	}
	
	public void write(String event) {
		try {
			writer.write(System.currentTimeMillis() + ": " + event +"\n");
			writer.flush();
		}
		catch (Exception e) { e.printStackTrace(); }
		finally { }
	}
	
	public void close() throws IOException {
		writer.close();
	}

}
