package com.codingchallenges.web_server.RequestMapping;

import java.util.Arrays;
import java.util.List;

public class CheckIfPathIsValid {

    private final Trie root;

    private final String path;

    private String responseBody=null;

    private final String Method;

    private List<String> ClassAndMethod;

    public CheckIfPathIsValid(String path, Trie root, String Method){
        this.path = path;
        this.root = root;
        this.Method=Method;
    }

    public boolean checkIfPathIsValid(){
        String[] pathArray=this.path.split("/");
        System.out.println("Path Array");
        System.out.println(Arrays.toString(pathArray));
        Trie current = root;
        for(String p: pathArray){
            if(p.length()==0){
                continue;
            }
            boolean found = false;
            for(Trie t: current.child.values()){
                //System.out.println(t.current);
                if(t.current.equals(p)){
                    current = t;
                    found = true;
                    break;
                }
            }
            if(!found){
                //System.out.println("Return False");
                return false;
            }
        }
        //System.out.println(current.current);
        this.responseBody=current.ReturnFilePathMap.get(Method);
        this.ClassAndMethod=current.ReflectionPath.get(Method);
        //System.out.printf("Check Validity Current Value %s and its response path %s and %s \n",current.current, Method, current.ReturnFilePathMap.get(Method));
        return current.isEnd;
    }

    public String getHTMLResponsePath(){
        return this.responseBody;
    }

    public List<String> getClassAndMethod(){
        return this.ClassAndMethod;
    }
}
