package com.codingchallenges.web_server.CustomExceptions;

public class NoSuchClassOrMethodFoundException extends RuntimeException {
    public NoSuchClassOrMethodFoundException(){
        super();
    }

    public NoSuchClassOrMethodFoundException(String Message){
        super(Message);
    }
}
