package com.example.Example1;

public class ExampleMethods {
    
    public ExampleMethods(){
        
    }


    public String GetName(){
        return "Hi my name is Shashwenth";
    }

    public int getAge(){
        return 10;
    }

    public String GetNameandAge(String name, String age){
        return "My name is "+name+" and my age is "+String.valueOf(age);
    }

    public String SetName(String name){
        return "Successfully Set Name "+name;
    }

    public String SetNameAsReqParameter(String name){
        return "Successfully Set Name as Request Parameter"+name;
    }

    public String TestCastRequestParams(Integer id, String name, Double amount, String body){
        return "Test Request Params Successful";
    }


}
