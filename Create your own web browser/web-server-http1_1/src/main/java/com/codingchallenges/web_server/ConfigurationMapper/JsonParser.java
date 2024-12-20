package com.codingchallenges.web_server.ConfigurationMapper;

import java.io.IOException;

import com.codingchallenges.web_server.ConfigurationModel.JsonEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {
    private ObjectMapper objectMapper;

    private JsonEntity jsonEntity;

    private final String path;

    public JsonParser(String path) {
        this.path = path;
    }
    
    private JsonEntity mapJsonToModel() {
        System.out.println(path);
        try {
            this.jsonEntity = this.objectMapper.readValue(ConfigureObjectMapper.class.getResourceAsStream(path), JsonEntity.class);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return jsonEntity;
    }

    public JsonEntity getJsonEntity() {
        SetObjectMapper();
        return mapJsonToModel();
    }

    
    public void printJsonEntity() {
        SetObjectMapper();
        this.jsonEntity = mapJsonToModel();
        System.out.println(this.jsonEntity.toString());
    }

    private void SetObjectMapper() {
        ConfigureObjectMapper configureObjectMapper = new ConfigureObjectMapper();
        this.objectMapper = configureObjectMapper.getObjectMapper();
    }

}
