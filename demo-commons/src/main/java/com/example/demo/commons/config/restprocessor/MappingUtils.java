package com.example.demo.commons.config.restprocessor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MappingUtils {

    private static final Logger LOGGER = LogManager.getLogger(MappingUtils.class);
    public static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public static <T> T convertJsonToType(String jsonString, Class<T> type) throws IOException {
        if (jsonString == null) {
            return null;
        }
        return OBJECT_MAPPER.readValue(jsonString, type);
    }

    public static <T> T convertJsonToTypeSafe(String jsonString, Class<T> type) {
        if (jsonString == null) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(jsonString, type);
        } catch (IOException | RuntimeException e) {
            return null;
        }
    }

    public static <T> T convertObjectToType(Object object, Class<T> type) {
        if (object == null) {
            return null;
        }
        return OBJECT_MAPPER.convertValue(object, type);
    }

    public static String convertObjectToJson(Object object) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(object);
    }

    public static JsonNode convertJsonToNode(String json) throws IOException {
        return OBJECT_MAPPER.readTree(json);
    }

    public static <T> T extractType(Map<String, Object> parent, String key, Class<T> type) {
        T child = extractTypeSafe(parent, key, type);
        if (child == null) {
            LOGGER.info("Entity: {}, not found as Type: {}", key, type.getSimpleName());
            throw new RuntimeException("Key not present in Map");
        } else {
            return child;
        }
    }

    public static <T> T extractTypeSafe(Map<String, Object> parent, String key, Class<T> type) {
        Object value = parent == null ? null : parent.get(key);
        if (type != null && type.isInstance(value)) {
            return (T) value;
        } else {
            return null;
        }
    }

    public static Map<String, Object> filterKeysInclude(Map<String, Object> input, String... keys) {
        Map<String, Object> output = new HashMap<>();
        for (String key : keys) {
            if (input.containsKey(key)) {
                output.put(key, input.get(key));
            }
        }
        return output;
    }

    public static Map<String, Object> extractMapOrCreateIfNull(Map<String, Object> parent, String child) {
        if (parent == null) {
            LOGGER.info("Entity 'parent' is null");
            throw new RuntimeException("Null response data");
        }
        Object childMap = parent.get(child);
        if (childMap != null) {
            try {
                return (Map<String, Object>) childMap;
            } catch (ClassCastException ex) {
                LOGGER.error("Existing 'child' is not a map", ex);
                throw new RuntimeException("Unexpected response data");
            }
        } else {
            childMap = new HashMap<String, Object>();
            parent.put(child, childMap);
            return (Map<String, Object>) childMap;
        }
    }

}
