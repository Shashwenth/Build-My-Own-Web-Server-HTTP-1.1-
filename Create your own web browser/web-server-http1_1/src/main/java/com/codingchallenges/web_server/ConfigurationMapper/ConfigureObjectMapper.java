package com.codingchallenges.web_server.ConfigurationMapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConfigureObjectMapper {


    public ConfigureObjectMapper() {
    }

    public ObjectMapper getObjectMapper() {
        ObjectMapper localObjectMapper = new ObjectMapper();
        localObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return localObjectMapper;
    }




}
