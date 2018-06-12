package com.vc.mappers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ma.glasnost.orika.impl.ConfigurableMapper;

@Service
public class ObjectMapperService extends ConfigurableMapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(ObjectMapperService.class);

	// @Override
	// public void configure(MapperFactory mapperFactory) {
	//
	// mapperFactory.registerClassMap(mapperFactory
	// .classMap(ScheduleBO.class, ScheduleDTO.class).byDefault()
	// .customize(new CustomMapper<ScheduleBO, ScheduleDTO>() {
	//
	// @Override
	// public void mapAtoB(ScheduleBO a, ScheduleDTO b, MappingContext context) {
	// b.setDate(SWFDateFormatter.DATE_FORMAT_YYYY_MM_DD.format(a.getDate()));
	// }
	//
	// @Override
	// public void mapBtoA(ScheduleDTO b,ScheduleBO a, MappingContext context) {
	// try {
	// a.setDate(SWFDateFormatter.DATE_FORMAT_YYYY_MM_DD.parse(b.getDate()));
	// } catch (ParseException e) {
	// LOGGER.error("Failed to parse Date", e.getMessage());
	// }
	// }
	//
	// }).toClassMap());
	//
	// mapperFactory.registerClassMap(mapperFactory
	// .classMap(EngineerShift.class, EngineerShiftBO.class).byDefault()
	// .customize(new CustomMapper<EngineerShift, EngineerShiftBO>() {
	//
	// @Override
	// public void mapAtoB(EngineerShift a, EngineerShiftBO b, MappingContext
	// context) {
	// b.setEngineer(engineerService.getById(a.getEngineerId()));
	// }
	//
	// @Override
	// public void mapBtoA(EngineerShiftBO b,EngineerShift a, MappingContext
	// context) {
	// a.setEngineerId(b.getEngineer().getId());
	// }
	//
	// }).toClassMap());
	//
	// mapperFactory.getConverterFactory().registerConverter(new
	// CustomConverter<Shift, Byte>() {
	//
	// @Override
	// public Byte convert(Shift a, Type<? extends Byte> b) {
	// return a.getId();
	// }
	// });
	//
	// mapperFactory.getConverterFactory().registerConverter(new
	// CustomConverter<Byte, Shift>() {
	//
	// @Override
	// public Shift convert(Byte a, Type<? extends Shift> b) {
	// return Shift.getShiftById(a);
	// }
	//
	//
	// });
	// }
}
