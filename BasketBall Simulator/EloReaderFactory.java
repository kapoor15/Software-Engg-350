package edu.upenn.cis350.hwk4;

public class EloReaderFactory {
	//FACTORY METHOD
		public EloReader getEloReader(String fileName) {
			if (fileName.contains("csv")) {
				return new EloFileReader();
			} else if (fileName.contains("json")) {
				return new EloJsonReader();
			} else {
				return null;
			}
		}
}
