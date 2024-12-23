package com.codingchallenges.web_server.IntitalizeGetRequests;

import com.codingchallenges.web_server.EndPoints.AddEndPoints;
import com.codingchallenges.web_server.RequestMapping.MainTrieGetter;

public class InitializeEndpointGET {

    private String responsePath;
    private String getPath;

    public InitializeEndpointGET(String responsePath, String getPath){
        this.responsePath=responsePath;
        this.getPath=getPath;
    }

    protected  void callAddEndpoint(){
        AddEndPoints addEndPoints=new AddEndPoints(MainTrieGetter.getRoot());
        addEndPoints.AddpathToTrie(getPath, responsePath);
    }



}
