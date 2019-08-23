package com.hydro17.pizzaservice.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.hydro17.pizzaservice.globals.PizzaServiceConstants;

@Component
public class LocalDateTimeToStringConverter implements Converter<LocalDateTime, String> {

	@Override
	public String convert(LocalDateTime localDateTime) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PizzaServiceConstants.DATE_TIME_FORMAT);
		
		return localDateTime.format(formatter);
	}

}
