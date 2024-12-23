package com.codingchallenges.web_server.EndPoints;

import com.codingchallenges.web_server.RequestMapping.Trie;

public class AddEndPoints {

    private final Trie root;

    public AddEndPoints(Trie root){
        this.root = root;
    }

    public void AddpathToTrie(String path, String ResponsePath){

        String[] pathArray=path.split("/");
        Trie current = this.root;
        for(String p: pathArray){
            if(p.length()==0){
                continue;
            }
            Trie temp = new Trie(p);
            current.children.add(temp);
            current = temp;
        }
        //System.out.printf("Current Value %s\n",current.current);
        current.isEnd = true;
        current.ReturnFilePath=ResponsePath;
        //System.out.printf("Current Value %s and its response path %s \n",current.current, current.ReturnFilePath);
    }

}
