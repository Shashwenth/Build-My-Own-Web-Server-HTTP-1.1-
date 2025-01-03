package com.example;

import java.util.ArrayList;
import java.util.List;

import com.example.Example1.ExampleMethods;


/*
 *  
 * Run the following in the terminal: 
 * 
 * >mvn clean install
 * 
 * Store in Local Maven Repo
 * >mvn install:install-file -Dfile=target\testintialization-1.0-SNAPSHOT.jar -DgroupId=com.example -DartifactId=testintialization -Dversion=1.0-SNAPSHOT -Dpackaging=jar
 * 
 */



public class Main {

    public static class StoreClassAndMethod{
        public String ClassName;
        public String FunctionName;
        public String MethodName;
        public String Path;
        public StoreClassAndMethod(String ClassName, String FunctionName, String MethodName, String Path){
            this.ClassName=ClassName;
            this.FunctionName=FunctionName;
            this.MethodName=MethodName;
            this.Path=Path;
        }
    }

    public static List<StoreClassAndMethod> response;
    public static void main(String[] args) {
        System.out.println("Hello world!");

        ExampleMethods exampleMethods = new ExampleMethods();

        //myInitialTestApp.getClass().getName()

        response = new ArrayList<>();
        response.add(new StoreClassAndMethod(exampleMethods.getClass().getName(), "GetName", "GET", "/GetName"));
        response.add(new StoreClassAndMethod(exampleMethods.getClass().getName(), "GetNameandAge", "GET", "/GetName/{name}/Age/{age}"));
        response.add(new StoreClassAndMethod(exampleMethods.getClass().getName(), "getAge", "GET", "/getAge"));
        response.add(new StoreClassAndMethod(exampleMethods.getClass().getName(), "SetName", "POST", "/SetName/{name}"));
        response.add(new StoreClassAndMethod(exampleMethods.getClass().getName(), "SetNameAsReqParameter", "POST", "/SetNameAsReqParameter"));
        response.add(new StoreClassAndMethod(exampleMethods.getClass().getName(), "TestCastRequestParams", "POST", "/TestCastRequestParams/"));
    }

    public static List<StoreClassAndMethod> getResponse(){
        return response;
    }
}