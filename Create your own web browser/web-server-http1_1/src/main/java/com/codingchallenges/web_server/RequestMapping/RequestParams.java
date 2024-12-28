package com.codingchallenges.web_server.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/*
 *  DTO to store the Request Params. Hashmap to store the param: value pair.
 * 
 */


public class RequestParams {

    public HashMap<String, Object> requestParams;

    public RequestParams(){
        requestParams=new HashMap<>();
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
