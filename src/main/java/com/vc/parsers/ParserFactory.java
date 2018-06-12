package com.vc.parsers;

public class ParserFactory<T> {

	public Parser<T> getParser(String format) {
		Parser<T> parser;
		if (format.equals("xml")) {
			parser = new XmlParser<T>();
		} else if (format.equals("csv")) {
			parser = new CsvParser<T>();
		} else {
			parser = new JsonParser<T>();
		}
		return parser;
	}

}
