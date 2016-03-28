package com.goeuro.devtest.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.csvreader.CsvWriter;

public class CSVWriter {

	private static final Logger logger = Logger.getLogger(CSVWriter.class);

	@SuppressWarnings("unchecked")
	public static boolean writeCSV(List<Map<String, Object>> suggestionsObject, String fileName) {
		boolean success = false;
		logger.info("Starting writeCSV.");
		
		try {
			CsvWriter csvOutput = new CsvWriter(new FileWriter(fileName, true), ',');
			
			csvOutput.write("id");
			csvOutput.write("name");
			csvOutput.write("type");
			csvOutput.write("latitude");
			csvOutput.write("longitude");
			csvOutput.endRecord();
			
			for(Map<String, Object> suggestion: suggestionsObject){
				Map<String, Double> geoPosition = (Map<String, Double>) suggestion.get("geo_position");
				csvOutput.write(suggestion.get("_id").toString());
				csvOutput.write(suggestion.get("name").toString());
				csvOutput.write(suggestion.get("type").toString());
				csvOutput.write(geoPosition.get("latitude").toString());
				csvOutput.write(geoPosition.get("longitude").toString());
				csvOutput.endRecord();
			}
			
			csvOutput.close();
			success = true;
			logger.info("Finished writeCSV successfully.");
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return success;
	}
}
