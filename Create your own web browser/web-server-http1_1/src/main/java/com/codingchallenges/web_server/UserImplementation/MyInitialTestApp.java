package com.codingchallenges.web_server.UserImplementation;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codingchallenges.web_server.ConfigurationMapper.JsonParser;
import com.codingchallenges.web_server.EndPoints.AddEndPoints;
import com.codingchallenges.web_server.RequestMapping.MainTrieGetter;
import com.codingchallenges.web_server.handlePathVariables.RegisterEndpoint;

public class MyInitialTestApp {

    private static final Logger logger=LoggerFactory.getLogger(MyInitialTestApp.class);

    public MyInitialTestApp(){

    }

    private List<String> register(String FunctionName){
        List<String> response=new ArrayList<>();
        MyInitialTestApp myInitialTestApp=new MyInitialTestApp();
        response.add(myInitialTestApp.getClass().getName());
        response.add(FunctionName);

        logger.info("Registered the Class name and function name for Reflection");

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

    public String TestRequestParams(String username, String pass, String body){
        //System.out.printf("This is userName %s and Pass %s",username,pass);
        return "Test Request Params Successful";
    }

    public String TestCastRequestParams(Integer id, String name, Double amount, String body){
        //System.out.printf("This is id %d and name %s and amount %f\n",id,name,amount);
        return "Test Request Params Successful";
    }

    /*DEPRECATED */
    public void initialize(String path, String FunctionName, String Method){
        List<String> classAndMethod=register(FunctionName);
        AddEndPoints addEndPoints=new AddEndPoints(MainTrieGetter.getRoot());
        addEndPoints.BackendAddEndpoint(path, path, Method, classAndMethod);
    }

    public String TestPathVariable(String name, String Body){
        return("name: "+name+ "body "+ Body);
    }

    public String TestPathVariable2(String name, int id, String Body){
        return("name: "+name+" id "+String.valueOf(id));
    }
//TestPathVariable2
    //initializeNew
    public void initializeNew(String path, String FunctionName, String Method){
        List<String> classAndMethod=register(FunctionName);

        logger.atInfo().addKeyValue("path", path).log("Registering Endpoint");

        RegisterEndpoint registerEndpoint=new RegisterEndpoint(path);

        registerEndpoint.RegisterEndPointPath(path, Method, classAndMethod);

        logger.info("Successfully initialized the path {} and method {} with FunctionName {}",path,Method,FunctionName);
        //System.out.println("Sucessfully added the path");
    }
}
