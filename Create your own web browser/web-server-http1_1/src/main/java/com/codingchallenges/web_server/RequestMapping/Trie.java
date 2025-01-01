package com.codingchallenges.web_server.RequestMapping;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Trie{
    public HashSet<Trie> children;
    public HashMap<String,Trie> child;
    public boolean isEnd;
    public String current;
    public String ReturnFilePath;
    public HashMap<String, String> ReturnFilePathMap;
    public HashMap<String, List<String>> ReflectionPath;
    public HashMap<String, String> PathVariableType;

    public Trie(String current){
        this.current = current;
        this.child= new HashMap<>();
        this.children = new HashSet<>();
        this.isEnd = false;
        this.ReturnFilePath="";
        this.ReturnFilePathMap=new HashMap<>();
        this.ReflectionPath=new HashMap<>();
        this.PathVariableType=new HashMap<>();
    }

}
