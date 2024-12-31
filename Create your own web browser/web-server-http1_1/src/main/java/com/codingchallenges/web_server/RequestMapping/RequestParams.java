package com.codingchallenges.web_server.RequestMapping;

import java.util.LinkedHashMap;
import java.util.Map;

/*
 *  DTO to store the Request Params. Hashmap to store the param: value pair.
 * 
 */


public class RequestParams {

    public Map<String, Object> requestParams;

    public RequestParams(){
        requestParams=new LinkedHashMap<>();
    }

    @Override
    public String toString(){

        StringBuilder sb=new StringBuilder();

        sb.append("Request Params");

        for(Map.Entry<String,Object> m:requestParams.entrySet()){
            sb.append("Param ").append(String.valueOf(m.getKey())).append(" Value").append(String.valueOf(m.getValue()));
        }

        return sb.toString();

    }

}
