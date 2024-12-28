package com.codingchallenges.web_server.handleRequest;

import java.util.List;

import com.codingchallenges.web_server.RequestMapping.CheckIfPathIsValid;
import com.codingchallenges.web_server.RequestMapping.MainTrieGetter;
import com.codingchallenges.web_server.RequestMapping.RequestBody;
import com.codingchallenges.web_server.RequestMapping.Trie;

/*
 *  Enter a description of the class
 *  CheckValidityService is a class that checks if the path is valid or not.
 *  CheckIfPathIsValid is called to check if the path is valid or not.
 *  If the path is valid, it returns "Valid Path". If the path is invalid, it returns "Invalid Path".
 *  It also returns the HTML response body and the class and method of the path.
 *  It also returns the Class and Method name that would be used by the Reflection API.
 * 
 */

public class CheckValidityService {

    private final RequestBody requestBody;

    Trie root=MainTrieGetter.getRoot();

    CheckIfPathIsValid checkIfPathIsValid;

    public CheckValidityService(RequestBody requestBody){
        this.requestBody = requestBody;
    }

    public String checkValidity(){
        checkIfPathIsValid = new CheckIfPathIsValid(requestBody.getPATH(), root, requestBody.getMETHOD());
            if(checkIfPathIsValid.checkIfPathIsValid()){
               return ("Valid Path");
            }else{
                return ("Invalid Path");
            }
    }

    public String ReturnHTMLResponseBody(){
        return checkIfPathIsValid.getHTMLResponsePath()==null?"":checkIfPathIsValid.getHTMLResponsePath();
    }

    public List<String> ReturnClassAndMethod(){
        return checkIfPathIsValid.getClassAndMethod()==null?null:checkIfPathIsValid.getClassAndMethod();
    }

}
