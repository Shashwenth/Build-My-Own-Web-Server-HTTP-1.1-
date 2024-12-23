package com.codingchallenges.web_server.EndPoints;

import com.codingchallenges.web_server.RequestMapping.MainTrieGetter;
import com.codingchallenges.web_server.RequestMapping.Trie;

public class AddEndPoints {

    private static Trie root=MainTrieGetter.getRoot();

    public AddEndPoints(){
        
    }

    // public void AddpathToTrie(String path){

    //     String[] pathArray=path.split("/");
    //     Trie current = this.root;
    //     for(String p: pathArray){
    //         if(p.length()==0){
    //             continue;
    //         }
    //         Trie temp = new Trie(p);
    //         current.children.add(temp);
    //         current = temp;
    //     }
    //     current.isEnd = true;
    // }

}
