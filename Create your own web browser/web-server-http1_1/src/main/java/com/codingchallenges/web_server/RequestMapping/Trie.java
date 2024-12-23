package com.codingchallenges.web_server.RequestMapping;

/*
 * 
 * This Trie data structure is going to hold the Request paths.
 * The ReturnFilePath is going to point to the path of the return HTML file.
 * 
 */


import java.util.HashSet;

public class Trie{
    public HashSet<Trie> children;
    public boolean isEnd;
    public String current;
    public String ReturnFilePath;


    public Trie(String current){
        this.current = current;
        this.children = new HashSet<>();
        this.isEnd = false;
        this.ReturnFilePath="";
    }

}
