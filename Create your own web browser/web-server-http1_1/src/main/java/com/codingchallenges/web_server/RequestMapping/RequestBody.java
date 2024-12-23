package com.codingchallenges.web_server.RequestMapping;

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
