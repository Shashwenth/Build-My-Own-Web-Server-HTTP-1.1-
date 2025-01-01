package com.codingchallenges.web_server.handlePathVariables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.codingchallenges.web_server.RequestMapping.MainTrieGetter;
import com.codingchallenges.web_server.RequestMapping.Trie;

public class FindPath {

    private Trie root= MainTrieGetter.getRoot();

    private final String path;

    public List<Object> PathVariableList=new ArrayList<>();

    public FindPath(String path){
        this.path=path;
    }

    private List<String> ReturnableClassAndMethod;


    public boolean isPathValid(String Method){
        String[] pathArray=path.split("/");
        System.out.println(Arrays.toString(pathArray));
        Trie current = this.root;
        boolean ans=CheckIfPathIsValid(pathArray, 0, current, Method);
        // if(ans){
        //     System.out.println("Found path var");
        //     System.out.println(ReturnableClassAndMethod.get(0));
        //     System.out.println(ReturnableClassAndMethod.get(1));
        // }else{
        //     System.out.println("Not found");
        // }
        return ans;

    }

    public List<String> getClassAndMethod(){
        return ReturnableClassAndMethod;
    }

    public List<Object> getPathVarList(){
        return this.PathVariableList;
    }

    public boolean CheckIfPathIsValid(String[] pathArray, int i, Trie current, String Method){

        if(i==pathArray.length){
            this.ReturnableClassAndMethod=current.ReflectionPath.get(Method);
            return true;
        }

        boolean ans=false;
        if(pathArray[i].equals("")){
            System.out.printf("Inside first if checking for %s\n", pathArray[i]);
            ans=ans || CheckIfPathIsValid(pathArray, i+1, current, Method);
        }
        
        if(current.child.containsKey(pathArray[i])){
            System.out.printf("Inside if checking for %s\n", pathArray[i]);
            ans=ans || CheckIfPathIsValid(pathArray, i+1, current.child.get(pathArray[i]), Method);
        }else if(!current.PathVariableType.isEmpty()){
            System.out.printf("Inside else if checking for %s\n", pathArray[i]);
            String currentType=detectType(pathArray[i]);
            String currentPrimitiveType=detectPrimitiveType(pathArray[i]);
            System.out.printf("Inside else if path is %s\n", currentType);
            for(Map.Entry<String,String> m:current.PathVariableType.entrySet()){
                System.out.printf("the value is %s\n",m.getValue());
                if(m.getValue().equals(currentType) || m.getValue().equals(currentPrimitiveType)){
                    PathVariableList.add(pathArray[i]);
                    ans=ans || CheckIfPathIsValid(pathArray, i+1, current.child.get(m.getKey()), Method);
                }
            }
        }
        return ans;

    }

    public static String detectType(String input) {
        if (input == null || input.isEmpty()) {
            return "java.lang.String"; // Default for null or empty strings
        }

        // Check if it can be parsed as an Integer
        try {
            Integer.valueOf(input);
            return "java.lang.Integer";
        } catch (NumberFormatException ignored) {
        }

        // Check if it can be parsed as a Long
        try {
            Long.valueOf(input);
            return "java.lang.Long";
        } catch (NumberFormatException ignored) {
        }

        // Check if it can be parsed as a Double
        try {
            Double.valueOf(input);
            return "java.lang.Double";
        } catch (NumberFormatException ignored) {
        }

        // Check if it can be parsed as a Boolean
        if (input.equalsIgnoreCase("true") || input.equalsIgnoreCase("false")) {
            return "java.lang.Boolean";
        }

        // If no other type matches, treat it as a String
        return "java.lang.String";
    }

    public static String detectPrimitiveType(String input) {
        if (input == null || input.isEmpty()) {
            return "String"; // Default for null or empty strings
        }

        // Check if it can be parsed as an Integer
        try {
            Integer.valueOf(input);
            return "int";
        } catch (NumberFormatException ignored) {
        }

        // Check if it can be parsed as a Long
        try {
            Long.valueOf(input);
            return "long";
        } catch (NumberFormatException ignored) {
        }

        // Check if it can be parsed as a Double
        try {
            Double.valueOf(input);
            return "float";
        } catch (NumberFormatException ignored) {
        }

        // Check if it can be parsed as a Boolean
        if (input.equalsIgnoreCase("true") || input.equalsIgnoreCase("false")) {
            return "boolean";
        }

        // If no other type matches, treat it as a String
        return "String";
    }



}
