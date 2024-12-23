package com.codingchallenges.web_server.RequestMapping;

public class CheckIfPathIsValid {

    private Trie root;

    private String path;

    public CheckIfPathIsValid(String path, Trie root){
        this.path = path;
        this.root = root;
    }

    public boolean checkIfPathIsValid(){
        if(path.length()==1 && path.charAt(0)=='/'){
            return true;
        }
        String[] pathArray=this.path.split("/");
        // for(String x:pathArray){
        //     System.out.println(x);
        // }
        //System.out.println("Inside Trie");
        Trie current = root;
        for(String p: pathArray){
            if(p.length()==0){
                continue;
            }
            boolean found = false;
            for(Trie t: current.children){
                //System.out.println(t.current);
                if(t.current.equals(p)){
                    current = t;
                    found = true;
                    break;
                }
            }
            if(!found){
                return false;
            }
        }
        return current.isEnd;
    }

}
