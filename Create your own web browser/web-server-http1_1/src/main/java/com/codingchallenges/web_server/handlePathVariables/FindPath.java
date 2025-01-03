package com.codingchallenges.web_server.handlePathVariables;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codingchallenges.web_server.RequestMapping.MainTrieGetter;
import com.codingchallenges.web_server.RequestMapping.Trie;

/*
 * 
 * Enter description for this class:
 * FindPath is used to check if the given URL is valid.
 * It also keeps track of the path variables in the URL.
 * DFS the whole TRIE to check if the path is valid.
 * Finally has implementation to return the classandmethod, pathvariables
 * 
 */

public class FindPath {

    private static final Logger logger=LoggerFactory.getLogger(FindPath.class);


    private final Trie root= MainTrieGetter.getRoot();

    private final String path;

    public List<Object> PathVariableList=new ArrayList<>();

    public FindPath(String path){
        this.path=path;
        logger.atInfo().addKeyValue("Path", this.path).log("Initializating Find Path");
    }

    private List<String> ReturnableClassAndMethod;


    public boolean isPathValid(String Method){
        String[] pathArray=path.split("/");
        //System.out.println(Arrays.toString(pathArray));
        Trie current = this.root;
        boolean ans=CheckIfPathIsValid(pathArray, 0, current, Method);
        // if(ans){
        //     System.out.println("Found path var");
        //     System.out.println(ReturnableClassAndMethod.get(0));
        //     System.out.println(ReturnableClassAndMethod.get(1));
        // }else{
        //     System.out.println("Not found");
        // }

        logger.atInfo().log("End Of Checking Path validity");


        return ans;
        

    }

    public List<String> getClassAndMethod(){
        logger.atInfo().log("Returning Class and Method List at FindPath");

        return ReturnableClassAndMethod;
    }

    public List<Object> getPathVarList(){
        logger.atInfo().log("Retuning Path Variables at FIndPath");

        return this.PathVariableList;
    }

    public boolean CheckIfPathIsValid(String[] pathArray, int i, Trie current, String Method){

        if(i==pathArray.length){
            this.ReturnableClassAndMethod=current.ReflectionPath.get(Method);
            return true;
        }

        boolean ans=false;
        if(pathArray[i].equals("")){
            //System.out.printf("Inside first if checking for %s\n", pathArray[i]);
            ans=ans || CheckIfPathIsValid(pathArray, i+1, current, Method);
        }
        
        if(current.child.containsKey(pathArray[i])){
            //System.out.printf("Inside if checking for %s\n", pathArray[i]);
            ans=ans || CheckIfPathIsValid(pathArray, i+1, current.child.get(pathArray[i]), Method);
        }else if(!current.PathVariableType.isEmpty()){
            //System.out.printf("Inside else if checking for %s\n", pathArray[i]);
            // String currentType=detectType(pathArray[i]);
            // String currentPrimitiveType=detectPrimitiveType(pathArray[i]);
            //System.out.printf("Inside else if path is %s\n", currentType);
            for(Map.Entry<String,String> m:current.PathVariableType.entrySet()){
                //System.out.printf("the value is %s\n",m.getValue());
                // if(m.getValue().equals(currentType) || m.getValue().equals(currentPrimitiveType)){
                if(canConvertToType(pathArray[i], m.getValue())){
                    PathVariableList.add(pathArray[i]);
                    ans=ans || CheckIfPathIsValid(pathArray, i+1, current.child.get(m.getKey()), Method);
                }
            }
        }
        return ans;

    }

    public  boolean canConvertToType(String value, String typeName) {
        return switch (typeName) {
            case "int", "java.lang.Integer" -> tryParseInt(value);
            case "double", "java.lang.Double" -> tryParseDouble(value);
            case "boolean", "java.lang.Boolean" -> tryParseBoolean(value);
            case "long", "java.lang.Long" -> tryParseLong(value);
            case "float", "java.lang.Float" -> tryParseFloat(value);
            case "char", "java.lang.Character" -> value.length() == 1;
            case "short", "java.lang.Short" -> tryParseShort(value);
            case "byte", "java.lang.Byte" -> tryParseByte(value);
            case "java.lang.String" -> true;
            default -> false;
            };
        }
    
        // Helper methods for primitive and wrapper parsing
        private boolean tryParseInt(String value) {
            try {
                Integer.valueOf(value);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    
        private boolean tryParseDouble(String value) {
            try {
                Double.valueOf(value);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    
        private boolean tryParseBoolean(String value) {
            return value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false");
        }
    
        private boolean tryParseLong(String value) {
            try {
                Long.valueOf(value);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    
        private boolean tryParseFloat(String value) {
            try {
                Float.valueOf(value);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    
        private boolean tryParseShort(String value) {
            try {
                Short.valueOf(value);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    
        private boolean tryParseByte(String value) {
            try {
                Byte.valueOf(value);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    
    
        //Deprecated
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

        //Deprecated
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
