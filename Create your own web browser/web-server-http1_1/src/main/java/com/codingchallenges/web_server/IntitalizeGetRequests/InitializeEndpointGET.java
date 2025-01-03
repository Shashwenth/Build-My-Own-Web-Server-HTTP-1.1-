package com.codingchallenges.web_server.IntitalizeGetRequests;

import com.codingchallenges.web_server.EndPoints.AddEndPoints;
import com.codingchallenges.web_server.RequestMapping.MainTrieGetter;

/*
 *  Deprecated
 *  Initialize the GET endpoints
 */

public class InitializeEndpointGET implements  InitializeEndPoints {

    private final String responsePath;
    private String getPath;
    private final String method;

    public InitializeEndpointGET(String responsePath, String getPath, String Method){
        this.responsePath=responsePath;
        this.getPath=getPath;
        this.method=Method;
    }

    @Override
    public void callAddEndpoint(){
        AddEndPoints addEndPoints=new AddEndPoints(MainTrieGetter.getRoot());
        addEndPoints.AddpathToTrie(getPath, responsePath, this.method);
    }

    @Override
    public String getGetPath() {
        return getPath;
    }

    @Override
    public void setGetPath(String getPath) {
        this.getPath = getPath;
    }



}
