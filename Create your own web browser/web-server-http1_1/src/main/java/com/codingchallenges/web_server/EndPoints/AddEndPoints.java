package com.codingchallenges.web_server.EndPoints;

import java.util.List;

import com.codingchallenges.web_server.RequestMapping.Trie;


/*
 *  DEPRECATED - CHECK OUT REGISTER END POINT
 * 
 */

/*
 *  Enter a description for this class:
 *  AddEndPoints is a class that adds endpoints to the Trie data structure.
 *  It is used to add endpoints to the Trie data structure.
 *  It also add the Reflection Method to the trie data structure.
 */

public class AddEndPoints {

    private final Trie root;

    public AddEndPoints(Trie root){
        this.root = root;
    }

    // Deprecated. Earlier used for adding endpoints to the Trie.
    public void AddpathToTrie(String path, String ResponsePath, String Method){

        String[] pathArray=path.split("/");
        Trie current = this.root;
        for(String p: pathArray){
            if(p.length()==0){
                continue;
            }
            if(current.child.containsKey(p)){
                current = current.child.get(p);
            }
            else{
                Trie temp = new Trie(p);
                current.child.put(p, temp);
                current = temp;
            }
        }
        //System.out.printf("Current Value %s\n",current.current);
        current.isEnd = true;
        current.ReturnFilePath=ResponsePath;
        current.ReturnFilePathMap.put(Method, ResponsePath);
        //System.out.printf("Current Value %s and its response path %s and Path %s \n",current.current, Method, current.ReturnFilePathMap.get(Method));
    }

    
    public void BackendAddEndpoint(String path, String ResponsePath, String Method, List<String> ClassAndMethod){

        String[] pathArray=path.split("/");
        Trie current = this.root;
        for(String p: pathArray){
            if(p.length()==0){
                continue;
            }
            if(current.child.containsKey(p)){
                current = current.child.get(p);
            }
            else{
                Trie temp = new Trie(p);
                current.child.put(p, temp);
                current = temp;
            }
            
        }
        //System.out.printf("Current Value %s\n",current.current);
        current.isEnd = true;
        current.ReturnFilePath=ResponsePath;
        current.ReturnFilePathMap.put(Method, ResponsePath);
        current.ReflectionPath.put(Method, ClassAndMethod);
        //System.out.printf("Current Value %s and its response path %s and Path %s \n",current.current, Method, current.ReturnFilePathMap.get(Method));
    }

}
