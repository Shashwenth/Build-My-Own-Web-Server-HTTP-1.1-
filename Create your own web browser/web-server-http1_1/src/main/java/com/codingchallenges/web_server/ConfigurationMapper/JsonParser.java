package com.codingchallenges.web_server.ConfigurationMapper;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser<T> {
    private ObjectMapper objectMapper;
    private final Class<T> type;

    public JsonParser(Class<T> type) {
        this.type = type;
        setObjectMapper();
    }

    private void setObjectMapper() {
        ConfigureObjectMapper configureObjectMapper = new ConfigureObjectMapper();
        this.objectMapper = configureObjectMapper.getObjectMapper();
    }

    public T parseFromFile(String filePath) {
        T entity = null;
        try {
            entity = this.objectMapper.readValue(
                ConfigureObjectMapper.class.getResourceAsStream(filePath),
                type
            );
        } catch (IOException e) {
            System.err.println("Error parsing JSON from file: " + e.getMessage());
        }
        return entity;
    }

    public T parseFromString(String jsonString) {
        T entity = null;
        try {
            entity = this.objectMapper.readValue(jsonString, type);
        } catch (IOException e) {
            System.err.println("Error parsing JSON from string: " + e.getMessage());
        }
        return entity;
    }

    public void printEntity(T entity) {
        if (entity != null) {
            System.out.println(entity.toString());
        } else {
            System.err.println("Entity is null.");
        }
    }
}
