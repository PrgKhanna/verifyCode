package com.vc.parsers;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlParser<T> extends Parser<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(XmlParser.class);

	@Override
	public String parse(T t) {

		// Using JaxB
		try {
			StringWriter sw = new StringWriter();
			JAXBContext context = JAXBContext.newInstance(t.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.marshal(t, sw);
			return sw.toString();
		} catch (JAXBException e) {
			LOGGER.error("Failed to parse to Xml");
		}

		return null;
	}

}
