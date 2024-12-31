package com.codingchallenges.web_server.UserImplementation;

import java.util.ArrayList;
import java.util.List;

import com.codingchallenges.web_server.ConfigurationMapper.JsonParser;
import com.codingchallenges.web_server.EndPoints.AddEndPoints;
import com.codingchallenges.web_server.RequestMapping.MainTrieGetter;

public class MyInitialTestApp {

    

    public MyInitialTestApp(){

    }

    private List<String> register(String FunctionName){
        List<String> response=new ArrayList<>();
        MyInitialTestApp myInitialTestApp=new MyInitialTestApp();
        response.add(myInitialTestApp.getClass().getName());
        response.add(FunctionName);
        return response;
    }

    public String getName(){
        return "Shashwenth";
    }


    public int getAge(){
        return 10;
    }

    public String setName(String ReqDataAsString){
        JsonParser<UserEntity> userParser = new JsonParser<>(UserEntity.class);
        UserEntity userEntity = userParser.parseFromString(ReqDataAsString);
        userParser.printEntity(userEntity);
        return "Added Successfully";
    }

    public String TestRequestParams(String body, String username, String pass){
        System.out.printf("This is userName %s and Pass %s",username,pass);
        return "Test Request Params Successful";
    }

    public String TestCastRequestParams(String body, Integer id, String name, Double amount){
        System.out.printf("This is id %d and name %s and amount %f\n",id,name,amount);
        return "Test Request Params Successful";
    }

    public void initialize(String path, String FunctionName, String Method){
        List<String> classAndMethod=register(FunctionName);
        AddEndPoints addEndPoints=new AddEndPoints(MainTrieGetter.getRoot());
        addEndPoints.BackendAddEndpoint(path, path, Method, classAndMethod);
    }


}
