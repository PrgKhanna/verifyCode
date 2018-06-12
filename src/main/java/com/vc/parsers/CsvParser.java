package com.vc.parsers;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CsvParser<T> extends Parser<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(CsvParser.class);

	@Override
	public String parse(T t) {
		LOGGER.info("Converting to CSV");
		try {
			// For this i will use reflection , in case of complex Responses we can use
			// CsvParser Libraries
			// Plus this will have performance impact
			LOGGER.info("Converting to CSV");
			try {
				List<String> values = new ArrayList<String>();
				Class<?> cls = t.getClass();
				Field[] fields = cls.getDeclaredFields();
				for (int i = 1; i < fields.length; i++) {
					Field f = fields[i];
					boolean isAccesible = f.isAccessible();

					if (!isAccesible) {
						f.setAccessible(true);
					}

					values.add(f.get(t).toString());

					if (!isAccesible) {
						f.setAccessible(isAccesible);
					}

				}
				return String.join(", ", values);
			} catch (Exception e) {
				// LOGGER.error("Failed to parse to Csv", e.getMessage());
			}
			return null;
		} catch (Exception e) {
			LOGGER.error("Failed to parse to Csv", e.getMessage());
		}
		return null;
	}
}
