package com.codingchallenges.web_server.ThreadImplementation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.List;

import com.codingchallenges.web_server.RequestMapping.InitialiseRequestBody;
import com.codingchallenges.web_server.RequestMapping.RequestBody;
import com.codingchallenges.web_server.handleRequest.CheckValidityService;

public class ThreadSocketImplementation extends Thread {

    private final Socket socket;

    @SuppressWarnings("unused")
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
                        StringBuilder ReqDataAsString = new StringBuilder();
                            RequestBody requestBody=null;
                            //String HTMLResponsePath="";
                            boolean flag=true;
                            List<String> ClassAndMethod=null;
                            int contentLength = 0;
                            while ((inputLine = in.readLine()) != null && !inputLine.isEmpty()) {

                                if(flag){
                                    InitialiseRequestBody initialiseRequestBody = new InitialiseRequestBody(inputLine);
                                    requestBody = initialiseRequestBody.initialiseRequestBody();
                                    CheckValidityService checkValidityService = new CheckValidityService(requestBody);
                                    @SuppressWarnings("unused")
                                    String validity = checkValidityService.checkValidity();
                                    //System.out.println(validity);
                                    //HTMLResponsePath=checkValidityService.ReturnHTMLResponseBody();
                                    ClassAndMethod=checkValidityService.ReturnClassAndMethod();
                                    if(ClassAndMethod==null){
                                        System.out.println("Class and Method is nyll");

                                    }
                                    //
                                    flag=false;
                                    
                                }
                                if (inputLine.toLowerCase().startsWith("content-length:")) {
                                    contentLength = Integer.parseInt(inputLine.substring(15).trim());
                                }
                                
                                requestData.append(inputLine).append("\n");
                            }


                            final String CRLF = "\r\n";
                            String ErrorResponse="HTTP/1.1 200 OK" + CRLF +
                                                    "Content-Type: text/plain" + CRLF +
                                                    "Content-Length: " + "NO Such MethodName Defined".getBytes().length + CRLF +
                                                    CRLF +
                                                    "NO Such MethodName Defined";

                            if(ClassAndMethod==null){
                                outputStream.write(ErrorResponse.getBytes());
                            }
                            else{   
                                String response;                             
                                if (requestBody != null && requestBody.getMETHOD().equals("POST") && contentLength > 0) {
                                    char[] bodyChars = new char[contentLength];
                                    in.read(bodyChars, 0, contentLength);
                                    String body = new String(bodyChars);
                                    ReqDataAsString.append("\n").append(body); 
                                    ExecuteReturnMethod executeReturnMethod=new ExecuteReturnMethod(ClassAndMethod.get(0), ClassAndMethod.get(1));
                                    response=executeReturnMethod.invokeMethod(ReqDataAsString.toString());
                                }
                                else{
                                    ExecuteReturnMethod executeReturnMethod=new ExecuteReturnMethod(ClassAndMethod.get(0), ClassAndMethod.get(1));
                                    response=executeReturnMethod.invokeMethod();
                                }

                                outputStream.write(response.getBytes());
                            }
                            

                        //HandleHTMLResponse handleHTMLResponse=new HandleHTMLResponse();
                        


                                                       
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
                        
                        // final String CRLF = "\r\n";
                        // String response;
                        // if(HTMLResponsePath.equals("")){

                        //     String notFoundHtml = "<html><body><h1>404 Not Found</h1><p>The requested resource could not be found.</p></body></html>";
                        //     response = "HTTP/1.1 404 Not Found" + CRLF +
                        //                 "Content-Type: text/html" + CRLF +
                        //                 "Content-Length: " + notFoundHtml.getBytes().length + CRLF +
                        //                 CRLF +
                        //                 notFoundHtml;
                        // }

                        // else{

                        //     response=handleHTMLResponse.GetHTMLResponse(HTMLResponsePath);
                        
                        // }
             
                        
                        outputStream.flush();
                        outputStream.close();

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

