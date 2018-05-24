package edu.upenn.cis350.hwk1;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LoggerClass {
	
	FileWriter writer;
	static LoggerClass logger;
	
	public LoggerClass() {
		writer = null;
		try {
			writer = new FileWriter(new File("log.txt"), true);
		}
		catch (Exception e) { e.printStackTrace(); }
		finally { }
	}
	
	public synchronized static LoggerClass getInstance() {
		if (logger == null) {
			logger = new LoggerClass();
		}
		return logger;
	}
	
	public void write(String event) {
		try {
			writer.write(System.currentTimeMillis() + event +"\n");
			writer.flush();
		}
		catch (Exception e) { e.printStackTrace(); }
		finally { }
	}
	
	public void close() throws IOException {
		writer.close();
	}

}
