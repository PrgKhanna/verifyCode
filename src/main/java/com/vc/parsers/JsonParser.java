package com.vc.parsers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser<T> extends Parser<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(JsonParser.class);

	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public String parse(T t) {
		try {
			return mapper.writeValueAsString(t);
		} catch (JsonProcessingException e) {
			LOGGER.error("Failed to parse to Json");
		}
		return null;
	}

}
