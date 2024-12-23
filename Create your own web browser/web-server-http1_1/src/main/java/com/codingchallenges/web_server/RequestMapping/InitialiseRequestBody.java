package com.codingchallenges.web_server.RequestMapping;

public class InitialiseRequestBody {

    private String RequestLine;

    public InitialiseRequestBody(String RequestLine) {
        this.RequestLine = RequestLine;
    }

    public RequestBody initialiseRequestBody() {
        String Method="";
        String Path="";
        String[] requestLineArray = this.RequestLine.split(" ");

        RequestMethod requestMethod = new RequestMethod(requestLineArray[0]);
        if(requestMethod.isGet()){
            Method = "GET";
        }else if(requestMethod.isPost()){
            Method = "POST";
        }else if(requestMethod.isPut()){
            Method = "PUT";
        }else if(requestMethod.isDelete()){
            Method = "DELETE";
        }else if(requestMethod.isHead()){
            Method = "HEAD";
        }else if(requestMethod.isOptions()){
            Method = "OPTIONS";
        }else if(requestMethod.isTrace()){
            Method = "TRACE";
        }else if(requestMethod.isConnect()){
            Method = "CONNECT";
        }else if(requestMethod.isPatch()){
            Method = "PATCH";
        }

        Path = requestLineArray[1];

        RequestBody requestBody = new RequestBody(Method, Path);
        return requestBody;

    }

}
