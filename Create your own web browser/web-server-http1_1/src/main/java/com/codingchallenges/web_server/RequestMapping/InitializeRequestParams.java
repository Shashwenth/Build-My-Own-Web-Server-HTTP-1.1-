package com.codingchallenges.web_server.RequestMapping;


/*
 *  Enter the Description of this class
 *  This class checks if the request params are present in the request line. 
 *  If present, it initializes the request params and returns the object.
 * 
 */

public class InitializeRequestParams {

    private final String RequestLine;

    private RequestParams requestParams;

    public InitializeRequestParams(String RequestLine){
        this.RequestLine=RequestLine;
    }

    public boolean checkIfRequestParamsPresent(){
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

        return  requestParams;


    }

}
