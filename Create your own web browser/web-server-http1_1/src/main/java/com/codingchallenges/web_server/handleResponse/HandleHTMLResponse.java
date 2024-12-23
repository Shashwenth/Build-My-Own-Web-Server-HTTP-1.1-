package com.codingchallenges.web_server.handleResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HandleHTMLResponse {


    public String GetHTMLResponse(String path) throws IOException{
        String CRLF="\r\n";
        String html= new String(Files.readAllBytes(Paths.get(path)));
        String response="HTTP/1.1 200 OK" + CRLF +
                                "Content-Type: text/html" + CRLF +
                                "Content-Length: " + html.getBytes().length + CRLF +
                                CRLF +
                                html;
        return response;
     }


}
