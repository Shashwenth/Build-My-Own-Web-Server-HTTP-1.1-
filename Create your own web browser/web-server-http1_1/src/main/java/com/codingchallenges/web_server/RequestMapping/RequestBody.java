package com.codingchallenges.web_server.RequestMapping;

/*
 * 
 * DTO to store the HTTP Method and the Path. This is to Store the request from the user.
 * 
 */

public class RequestBody {

    private String METHOD;

    private String PATH;

    public RequestBody(String METHOD, String PATH) {
        this.METHOD = METHOD;
        this.PATH = PATH;
    }

    public RequestBody() {
        super();
    }

    public String getMETHOD() {
        return METHOD;
    }

    public String getPATH() {
        return PATH;
    }
}
