package com.codingchallenges.web_server.RequestMapping;

public class CheckIfPathIsValid {

    private Trie root;

    private String path;

    private String responseBody;

    public CheckIfPathIsValid(String path, Trie root){
        this.path = path;
        this.root = root;
    }

    public boolean checkIfPathIsValid(){
        String[] pathArray=this.path.split("/");
        // System.out.println("Path Array");
        // System.out.println(Arrays.toString(pathArray));
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
        //System.out.println(current.current);
        this.responseBody=current.ReturnFilePath;
        //System.out.printf("Check Validity Current Value %s and its response path %s \n",current.current, current.ReturnFilePath);
        return current.isEnd;
    }

    public String getHTMLResponsePath(){
        return this.responseBody;
    }

}
