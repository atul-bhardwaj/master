package com.goeuro.devtest.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonReader {
	private static final Logger logger = Logger.getLogger(JsonReader.class);

	@SuppressWarnings("unchecked")
	public static boolean readJsonString(String jsonString, List<Map<String, Object>> suggestionsObject) {
		boolean success = false ;
		logger.info("Starting readJsonString.");
		try {
			suggestionsObject.addAll((List<Map<String, Object>>) new ObjectMapper().readValue(jsonString,
					Object.class));
			
			success = true;
			logger.info("Finished readJsonString successfully.");
		} catch (JsonParseException e) {
			logger.error(e.getMessage());
		} catch (JsonMappingException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return success;
	}
}
