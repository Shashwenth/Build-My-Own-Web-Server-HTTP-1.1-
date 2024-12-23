package com.codingchallenges.web_server.IntitalizeGetRequests;

public class Intializer {

    public Intializer(){

    }


    public void GetInitialize(String path, String response){
        InitializeEndpointGET initializeEndpointGET=new InitializeEndpointGET(response, path);
        initializeEndpointGET.callAddEndpoint();
    }

    

}
