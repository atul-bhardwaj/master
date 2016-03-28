package com.goeuro.devtest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.goeuro.devtest.config.Config;
import com.goeuro.devtest.util.CSVWriter;
import com.goeuro.devtest.util.JsonReader;
import com.goeuro.devtest.util.NetClientGet;

public class App {
	private static final Logger logger = Logger.getLogger(App.class);

	public static void main(String[] args) {
		run(args);
	}

	private static void run(String[] args) {
		logger.info("Starting GoEuroTest Application.");
		if (!validateInputs(args)) {
			System.out.println(Config.ERRORMESSAGE);
			logger.error(Config.LOGERRORMESSAGE);
			return;
		}
		String city = args[0];
		logger.info("City Name: " + city);
		String timeStamp = String.valueOf(System.currentTimeMillis());
		if (!runApp(city, timeStamp)) {
			System.out.println(Config.ERRORMESSAGE);
			logger.error(Config.LOGERRORMESSAGE);
			return;
		}
		System.out.println(Config.SUCCESSMESSAGE + city + "_" + timeStamp
				+ ".csv");
		logger.info(Config.LOGSUCCESSMESSAGE);
	}

	private static boolean runApp(String city, String timeStamp) {
		String suggestions = NetClientGet.getResponse(city);
		if (suggestions == null) {
			return false;
		}
		List<Map<String, Object>> suggestionsObject = new ArrayList<Map<String, Object>>();
		if (!JsonReader.readJsonString(suggestions, suggestionsObject)) {
			return false;
		}
		String fileName = city + "_" + timeStamp + ".csv";
		if (!CSVWriter.writeCSV(suggestionsObject, fileName)) {
			return false;
		}
		return true;
	}

	private static boolean validateInputs(String[] args) {
		boolean inputCorrect = true;
		if (args.length == 0) {
			logger.error("City name was not provided.");
			inputCorrect = false;
		}
		if (args.length > 1) {
			logger.error("Please provide only one word for city name");
			inputCorrect = false;
		}
		return inputCorrect;
	}
}
