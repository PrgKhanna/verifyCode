package com.vc.parsers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CsvParser<T> extends Parser<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(CsvParser.class);

	@Override
	public String parse(T t) {
		LOGGER.info("Converting to CSV");
		try {

			return "";
		} catch (Exception e) {
			LOGGER.error("Failed to parse to Csv", e.getMessage());
		}
		return null;
	}
}
