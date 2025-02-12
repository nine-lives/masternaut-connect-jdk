package com.nls.masternaut.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class RequestParameterMapper {
    private static final PropertyNamingStrategies.LowerCamelCaseStrategy STRATEGY = new PropertyNamingStrategies.LowerCamelCaseStrategy();

    private final ObjectMapper objectMapper = ObjectMapperFactory.make();

    public <T> Map<String, String> writeToMap(T object) {
        try {
            Map<String, String> values = new LinkedHashMap<>();
            for (Field field : getDeclaredFields(object.getClass())) {
                field.setAccessible(true);
                Object value = field.get(object);
                if (value == null || Modifier.isTransient(field.getModifiers())) {
                    continue;
                }
                if (value instanceof Collection<?>) {
                    Collection<?> collection = (Collection<?>) value;
                    if (!collection.isEmpty()) {
                        values.put(STRATEGY.translate(field.getName()), collection.stream()
                                .map(Object::toString)
                                .collect(Collectors.joining(",")));
                    }
                } else {
                    values.put(STRATEGY.translate(field.getName()), String.valueOf(value));
                }
            }
            return values;
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    public <T> String write(T object) {
        Map<String, String> params = writeToMap(object);
        StringBuilder paramString = new StringBuilder();
        for (Entry<String, String> entry : params.entrySet()) {
            paramString
                    .append(paramString.length() == 0 ? '?' : '&')
                    .append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8))
                    .append('=')
                    .append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
        }
        return paramString.toString();
    }

    public <T> T read(URL url, Class<T> type) {
        try {
            Map<String, Object> params = splitQuery(url);
            return objectMapper.readValue(objectMapper.writeValueAsString(params), type);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private <T> List<Field> getDeclaredFields(Class<T> type) {
        List<Field> fields = new ArrayList<>();
        Class<?> clazz = type;
        while (!clazz.equals(Object.class)) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        return fields;
    }

    private static Map<String, Object> splitQuery(URL url) throws UnsupportedEncodingException {
        Map<String, Object> queryPairs = new LinkedHashMap<>();
        String query = url.getQuery();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            String key = URLDecoder.decode(pair.substring(0, idx), StandardCharsets.UTF_8);
            String value = URLDecoder.decode(pair.substring(idx + 1), StandardCharsets.UTF_8);
            if (key.endsWith("[]")) {
                key = key.substring(0, key.length() - 2);
                Set<String> set = (Set<String>) queryPairs.get(key);
                if (set == null) {
                    set = new TreeSet<>();
                    queryPairs.put(key, set);
                }
                set.add(value);
            } else {
                queryPairs.put(key, value);
            }
        }
        return queryPairs;
    }
}
