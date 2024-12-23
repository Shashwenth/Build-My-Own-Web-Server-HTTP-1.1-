package com.codingchallenges.web_server.RequestMapping;


/*
 * 
 * DEPRECATED. ONLY USE THE ADDENDPOINTS CLASS TO ADD ENDPOINTS TO THE TRIE. USE CHECKIFPATHISVALID TO CHECK IF A PATH IS VALID.
 * 
 */

public class AddPathToTrie {

    private Trie root;

    private String path;



    public AddPathToTrie(String path, Trie root){
        this.path = path;
        this.root = root;
    }

    public void AddpathToTrie(){

        String[] pathArray=this.path.split("/");
        Trie current = this.root;
        for(String p: pathArray){
            if(p.length()==0){
                continue;
            }
            Trie temp = new Trie(p);
            current.children.add(temp);
            current = temp;
        }
        current.isEnd = true;
    }


}
