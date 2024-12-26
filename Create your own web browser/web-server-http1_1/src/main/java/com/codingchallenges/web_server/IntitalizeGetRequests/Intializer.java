package com.codingchallenges.web_server.IntitalizeGetRequests;

public class Intializer {

    public Intializer(){

    }


    public void GetInitialize(String path, String response){
        InitializeEndpointGET initializeEndpointGET=new InitializeEndpointGET(response, path, "GET");
        initializeEndpointGET.callAddEndpoint();
    }


    public void PostInitialize(String path, String response){
        InitializeEndpointPOST initializeEndpointPost=new InitializeEndpointPOST(response, path, "POST");
        initializeEndpointPost.callAddEndpoint();
    }

    

}
