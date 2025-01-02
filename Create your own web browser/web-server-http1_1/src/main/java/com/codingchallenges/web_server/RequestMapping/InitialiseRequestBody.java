package com.codingchallenges.web_server.RequestMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * 
 * Sets the Request Body DTO.
 * 
 */

public class InitialiseRequestBody {

    private static final Logger logger=LoggerFactory.getLogger(InitialiseRequestBody.class);

    private final String RequestLine;

    public InitialiseRequestBody(String RequestLine) {
        this.RequestLine = RequestLine;
        logger.atInfo().addKeyValue("RequestLine", this.RequestLine).log("Inside InitialiseRequestBody");
    }

    public RequestBody initialiseRequestBody() {

        String Method="";
        
        String[] requestLineArray = this.RequestLine.split(" ");

        RequestMethod requestMethod = new RequestMethod(requestLineArray[0]);
        if(requestMethod.isGet()){
            Method = "GET";
        }else if(requestMethod.isPost()){
            Method = "POST";
        }else if(requestMethod.isPut()){
            Method = "PUT";
        }else if(requestMethod.isDelete()){
            Method = "DELETE";
        }else if(requestMethod.isHead()){
            Method = "HEAD";
        }else if(requestMethod.isOptions()){
            Method = "OPTIONS";
        }else if(requestMethod.isTrace()){
            Method = "TRACE";
        }else if(requestMethod.isConnect()){
            Method = "CONNECT";
        }else if(requestMethod.isPatch()){
            Method = "PATCH";
        }

        String Path= requestLineArray[1].split("\\?")[0];

        RequestBody requestBody = new RequestBody(Method, Path);

        logger.atInfo()
        .addKeyValue("Request Body Method", requestBody.getMETHOD())
        .addKeyValue("REquest Body Path", requestBody.getPATH())
        .log("Successfully initialized Request Body");
        
        return requestBody;

    }

}
