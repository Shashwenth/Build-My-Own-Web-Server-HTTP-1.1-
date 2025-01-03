package com.codingchallenges.web_server.handleRequest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codingchallenges.web_server.CustomExceptions.NoSuchClassOrMethodFoundException;
import com.codingchallenges.web_server.RequestMapping.RequestBody;
import com.codingchallenges.web_server.handlePathVariables.FindPath;

/*
 *  Enter a description of the class
 *  CheckValidityService is a class that checks if the path is valid or not.
 *  findPath is called to check if the path is valid or not.
 *  If the path is valid, it returns "Valid Path". If the path is invalid, it returns "Invalid Path".
 *  It also returns Path variables list
 *  It also returns the Class and Method name that would be used by the Reflection API.
 * 
 */

public class CheckValidityService {

    private static final Logger logger=LoggerFactory.getLogger(CheckValidityService.class);

    private final RequestBody requestBody;

    //CheckIfPathIsValid checkIfPathIsValid;

    FindPath findPath;

    public CheckValidityService(RequestBody requestBody){
        this.requestBody = requestBody;
        logger.atInfo().log("Initializing Validity Service");
    }

    public String checkValidity(){
        //checkIfPathIsValid = new CheckIfPathIsValid(requestBody.getPATH(), root, requestBody.getMETHOD());
        findPath = new FindPath(requestBody.getPATH());
        if(findPath.isPathValid(requestBody.getMETHOD())){
               return ("Valid Path");
        }else{
                return ("Invalid Path");
        }
    }

    // public String ReturnHTMLResponseBody(){
    //     return checkIfPathIsValid.getHTMLResponsePath()==null?"":checkIfPathIsValid.getHTMLResponsePath();
    // }

    public List<String> ReturnClassAndMethod() throws Exception{
        List<String> returnBody= findPath.getClassAndMethod();
        if(returnBody==null){
            logger.warn("--------------Insode NO Class Exception------------");
            throw new NoSuchClassOrMethodFoundException("Did not Find Class and Method associated with the URL");
        }
        return returnBody;
    }

    public List<Object> getPAthVars(){
        return findPath.getPathVarList();
    }

}
