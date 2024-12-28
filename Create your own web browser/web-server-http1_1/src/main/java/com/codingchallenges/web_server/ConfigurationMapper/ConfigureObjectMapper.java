package com.codingchallenges.web_server.ConfigurationMapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * 
 *  Enter a description for this class:
 *  This class is used to configure the ObjectMapper for the JSON parser.
 *  The ObjectMapper is configured to ignore unknown properties.
 *  This class is used by the JsonParser class.
 * 
 * 
 */

public class ConfigureObjectMapper {


    public ConfigureObjectMapper() {
    }

    public ObjectMapper getObjectMapper() {
        ObjectMapper localObjectMapper = new ObjectMapper();
        localObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return localObjectMapper;
    }




}
