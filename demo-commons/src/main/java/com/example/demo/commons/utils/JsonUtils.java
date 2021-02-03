package com.example.demo.commons.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonIOException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * @author Virender Bhargav
 * @version 1.0.0
 */
public class JsonUtils {

    private static final ObjectMapper OBJECT_MAPPER;
    private static final ObjectMapper NON_EMPTY_OBJECT_MAPPER;
    private static final ObjectMapper OBJECT_MAPPER_WITH_IGNORE_NULL;
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    static {
        OBJECT_MAPPER = new ObjectMapper();
        NON_EMPTY_OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER_WITH_IGNORE_NULL = new ObjectMapper();
        OBJECT_MAPPER_WITH_IGNORE_NULL.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        NON_EMPTY_OBJECT_MAPPER.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    }

    public static <T> String serialize(T object) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(object);
    }

    public static <T> String serializeWithIgnoreNull(T object) throws JsonProcessingException {
        return OBJECT_MAPPER_WITH_IGNORE_NULL.writeValueAsString(object);
    }

    public static <T> String serialize(T object, Class view) throws JsonProcessingException {
        return OBJECT_MAPPER.writerWithView(view).writeValueAsString(object);
    }


    public static <T> T deserialize(String json, Class<T> clazz) throws IOException {
        return OBJECT_MAPPER.readValue(json, clazz);
    }

    public static <T> T deserialize(String json, Class<T> clazz, Class view) throws IOException {
        return OBJECT_MAPPER.readerWithView(view).forType(clazz).readValue(json);
    }

    public static <T> T deserializeIgnoringEmptyValues(String json, Class<T> clazz, Class view) throws IOException {
        return NON_EMPTY_OBJECT_MAPPER.readerWithView(view).forType(clazz).readValue(json);
    }

    public static <T> List<T> deserializeToList(String json, Class<T> clazz) throws IOException {
        return OBJECT_MAPPER.readValue(json, OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
    }

    public static <T> HashSet<T> deserializeToSet(String json, Class<T> clazz) throws IOException {
        return OBJECT_MAPPER.readValue(json, OBJECT_MAPPER.getTypeFactory().constructCollectionType(HashSet.class, clazz));
    }

    public static <K, Y> Map<K, Y> deserializeToMap(String json, Class<K> keyClass, Class<Y> valueClass) throws IOException {
        return OBJECT_MAPPER.readValue(json, OBJECT_MAPPER.getTypeFactory().constructMapType(Map.class, keyClass, valueClass));
    }

    public static boolean isAValidJson(String jsonInString ) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(jsonInString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static <K,V> boolean isAValidMap(String string, Class<K> keyClass, Class<V> valueClass) {

    	try {
	    	JsonUtils.deserializeToMap(string, String.class, String.class);
	    	System.out.println("deserialized to map successful");
	    	return true;
		} catch (Exception e) {
			LOGGER.error("couldn't validate the given string to map: " + string);
			return false;
		}

    }

//    public static void main(String[] args) throws IOException {
//    	String str = "{\"randomkey1\":\"{\\\"label\\\": \\\"Room Number\\\",\\\"value\\\": \\\"123\\\"}\",\"randomkey2\":\"{\\\"label\\\": \\\"Room Number\\\",\\\"value\\\": \\\"123\\\"}\"}";
//    	System.out.println(isAValidJson(str));
//		System.out.println(isAValidMap(str, String.class, String.class));
//
//		Map<String, String> response = StringUtils.isBlank(String.valueOf(str)) ?
//				new HashMap<>():
//				new ObjectMapper().readValue(String.valueOf(str), Map.class);
//		System.out.println("response -> " + response);
//		for (String key : response.keySet()) {
//			System.out.println("key: " + key + " ; value: " + response.get(key));
//		}
//	}

    public static <T> T convertToObject(final File jsonFile, final Class<T> pClass) {
        try {
            return deserialize(FileUtils.readFileToString(jsonFile, StandardCharsets.UTF_8), pClass);
        } catch (final Exception e) {
            throw new JsonIOException("Unable to convert to Object");
        }
    }

}
