package com.codingchallenges.web_server.RequestMapping;

public class RequestMethod {

    private final String method;

    public RequestMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public boolean isGet() {
        return method.equals("GET");
    }

    public boolean isPost() {
        return method.equals("POST");
    }

    public boolean isPut() {
        return method.equals("PUT");
    }

    public boolean isDelete() {
        return method.equals("DELETE");
    }

    public boolean isHead() {
        return method.equals("HEAD");
    }

    public boolean isOptions() {
        return method.equals("OPTIONS");
    }

    public boolean isTrace() {
        return method.equals("TRACE");
    }

    public boolean isConnect() {
        return method.equals("CONNECT");
    }

    public boolean isPatch() {
        return method.equals("PATCH");
    }

}
