package com.codingchallenges.web_server.ThreadImplementation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

import com.codingchallenges.web_server.RequestMapping.InitialiseRequestBody;
import com.codingchallenges.web_server.RequestMapping.RequestBody;
import com.codingchallenges.web_server.handleRequest.CheckValidityService;
import com.codingchallenges.web_server.handleResponse.HandleHTMLResponse;

public class ThreadSocketImplementation extends Thread {

    private final Socket socket;
    private final int x;
    public ThreadSocketImplementation(Socket socket, int x){
        this.x=x;
        this.socket = socket;
    }

    @Override
    public void run(){

        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    OutputStream outputStream = socket.getOutputStream();) {
                        String inputLine;
                        StringBuilder requestData = new StringBuilder();
                            RequestBody requestBody;
                            String HTMLResponsePath="";
                            boolean flag=true;
                            while ((inputLine = in.readLine()) != null && !inputLine.isEmpty()) {
                                if(flag){
                                    InitialiseRequestBody initialiseRequestBody = new InitialiseRequestBody(inputLine);
                                    requestBody = initialiseRequestBody.initialiseRequestBody();
                                    CheckValidityService checkValidityService = new CheckValidityService(requestBody);
                                    String validity = checkValidityService.checkValidity();
                                    HTMLResponsePath=checkValidityService.ReturnHTMLResponseBody();
                                    //System.out.println(validity);
                                    flag=false;
                                    
                                }
                                requestData.append(inputLine).append("\n");
                                break;
                            }


                            //System.out.printf("The HTMLResponsePath is %s and it's length is %d\n",HTMLResponsePath,HTMLResponsePath.length());
                            //System.out.println(requestData);
                        
                        HandleHTMLResponse handleHTMLResponse=new HandleHTMLResponse();
                        
                        
                        //System.out.println("Request complete, sending response...");

                        // try (BufferedWriter writer = new BufferedWriter(new FileWriter("web-server-http1_1\\src\\main\\java\\com\\codingchallenges\\web_server\\ThreadImplementation\\readthis.txt", true))) {
                        //     writer.write("X: " + x + "\n");
                        //     writer.write("Thread " + Thread.currentThread().getName() + " received request:\n");
                        //     writer.write("\n-------------------\n");
                        //     writer.flush();
                        // }catch (IOException e) {
                        //     System.err.println("Error writing to file: " + e.getMessage());
                        // }
                        
                        // String html = "<html><head><title>My Web Server</title></head><body><h1>Welcome to my web server!</h1></body></html>";
                        final String CRLF = "\r\n";
                        String response="";
                        if(HTMLResponsePath.equals("")){
                            String notFoundHtml = "<html><body><h1>404 Not Found</h1><p>The requested resource could not be found.</p></body></html>";
                                response = "HTTP/1.1 404 Not Found" + CRLF +
                                        "Content-Type: text/html" + CRLF +
                                        "Content-Length: " + notFoundHtml.getBytes().length + CRLF +
                                        CRLF +
                                        notFoundHtml;
                        }
                        else{
                            response=handleHTMLResponse.GetHTMLResponse(HTMLResponsePath);
                        }
                         
                        
                        outputStream.write(response.getBytes());
                        outputStream.flush();
                        outputStream.close();

                        // try {
                        //     sleep(5000);
                        // } catch (InterruptedException e) {
                        //     System.err.println("Error: " + e.getMessage());
                        // }
                        

                    }
                    catch (SocketTimeoutException e) {
                        System.err.println("Connection timed out: " + e.getMessage());
                    } catch (IOException e) {
                        System.err.println("Connection error: " + e.getMessage());
                    } finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            System.err.println("Error: " + e.getMessage());
                        }
                    }

        } 

    }

