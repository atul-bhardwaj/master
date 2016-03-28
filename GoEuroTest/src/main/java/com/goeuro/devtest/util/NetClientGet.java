package com.goeuro.devtest.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;

import com.goeuro.devtest.config.Config;

public class NetClientGet {
	private static final Logger logger = Logger.getLogger(NetClientGet.class);

	public static String getResponse(String city) {
		logger.info("Starting in getResponse method.");
		String response = null;
		try {
			URL url = new URL(Config.GOEUROENDPOINT + city);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != Config.HTTPSUCCESSCODE) {
				logger.error("Failed : HTTP error code : "
						+ conn.getResponseCode());
				return response;
			}
			logger.info("Response retrieved successfully.");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output = "";
			response = "";
			while ((output = br.readLine()) != null) {
				response += output;
			}
			conn.disconnect();

		} catch (MalformedURLException e) {
			logger.error(e.getMessage());

		} catch (IOException e) {
			logger.error(e.getMessage());

		}
		return response;

	}

}
