package com.example.demo.commons.config.mapper;

import com.google.gson.*;
import com.example.demo.commons.config.annotation.Exclude;
import com.example.demo.commons.constants.DatePatterns;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Virender Bhargav
 * @version 1.0.0
 */
@Component
public class SerializerGSON {

	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
			.ofPattern(DatePatterns.DATE_FORMAT_YYYYMMDD);

	private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter
			.ofPattern(DatePatterns.DATE_TIME_FORMAT_YYYYMMDDHHMMSS);

	public static class AnnotationExclusionStrategy implements ExclusionStrategy {

		@Override
		public boolean shouldSkipField(FieldAttributes f) {
			return f.getAnnotation(Exclude.class) != null;
		}

		@Override
		public boolean shouldSkipClass(Class<?> clazz) {
			return false;
		}
	}

	@Bean
	@Primary
	public Gson gson() {
		return new GsonBuilder().setExclusionStrategies(new AnnotationExclusionStrategy())
				.registerTypeAdapter(LocalDate.class,
						(JsonSerializer<LocalDate>) (localDate, type,
								context) -> new JsonPrimitive(DATE_FORMATTER.format(localDate)))
				.registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) (localDateTime, type,
						context) -> new JsonPrimitive(DATETIME_FORMATTER.format(localDateTime)))
				.create();
	}
}
