package com.codingchallenges.web_server.RequestMapping;

import java.util.HashSet;

public class Trie{
    HashSet<Trie> children;
    boolean isEnd;
    String current;


    public Trie(String current){
        this.current = current;
        this.children = new HashSet<>();
        this.isEnd = false;
    }

}
