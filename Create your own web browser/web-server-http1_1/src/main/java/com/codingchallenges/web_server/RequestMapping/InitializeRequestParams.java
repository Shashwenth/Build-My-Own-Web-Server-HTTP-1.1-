package com.codingchallenges.web_server.RequestMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 *  Enter the Description of this class
 *  This class checks if the request params are present in the request line. 
 *  If present, it initializes the request params and returns the object.
 * 
 */

public class InitializeRequestParams {

    private static final Logger logger=LoggerFactory.getLogger(InitializeRequestParams.class);


    private final String RequestLine;

    private RequestParams requestParams;

    public InitializeRequestParams(String RequestLine){
        this.RequestLine=RequestLine;
        logger.atInfo().addKeyValue("RequestLine", this.RequestLine).log("Initializing Request Line");
    }

    public boolean checkIfRequestParamsPresent(){
        logger.atInfo().addKeyValue("Contins Params? ", RequestLine.contains("?")).log();
        return RequestLine.contains("?");
    }

    public RequestParams GetRequestParams(){
        requestParams=new RequestParams();

        String TwoWaySplit= RequestLine.split(" ")[1].split("\\?")[1];

        String[] ParameterArray=TwoWaySplit.split("\\&");

        for(String parameter: ParameterArray){

            String[] KeyValPair=parameter.split("\\=");

            requestParams.requestParams.put(KeyValPair[0], KeyValPair[1]);

        }
        logger.info("Returning the Request Parameters");
        return  requestParams;


    }

}
