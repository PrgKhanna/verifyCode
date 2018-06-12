package com.vc.utils;

import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvException;
import com.vc.dtos.GenerateVerificationCodeResponseDTO;

@Component
public class ResponseToCSV {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResponseToCSV.class);

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void writeVerificationCodeToCsv(PrintWriter writer, GenerateVerificationCodeResponseDTO responseDTO) {

		try {

			ColumnPositionMappingStrategy mapStrategy = new ColumnPositionMappingStrategy();

			mapStrategy.setType(GenerateVerificationCodeResponseDTO.class);

			String[] columns = new String[] { "id", "name", "population" };
			mapStrategy.setColumnMapping(columns);

			StatefulBeanToCsv btcsv = new StatefulBeanToCsvBuilder(writer).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
					.withMappingStrategy(mapStrategy).withSeparator(',').build();

			btcsv.write(responseDTO);

		} catch (CsvException ex) {

			LOGGER.error("Error mapping Bean to CSV", ex);
		}
	}

}
