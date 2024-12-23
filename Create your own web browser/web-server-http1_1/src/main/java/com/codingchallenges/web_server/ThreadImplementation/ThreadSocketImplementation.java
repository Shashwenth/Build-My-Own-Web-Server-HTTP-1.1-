package com.codingchallenges.web_server.ThreadImplementation;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

import com.codingchallenges.web_server.RequestMapping.CheckIfPathIsValid;
import com.codingchallenges.web_server.RequestMapping.InitialiseRequestBody;
import com.codingchallenges.web_server.RequestMapping.MainTrieGetter;
import com.codingchallenges.web_server.RequestMapping.RequestBody;
import com.codingchallenges.web_server.RequestMapping.Trie;

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
                        
                            boolean flag=true;
                            while ((inputLine = in.readLine()) != null && !inputLine.isEmpty()) {
                                if(flag){
                                    RequestBody requestBody;
                                    InitialiseRequestBody initialiseRequestBody = new InitialiseRequestBody(inputLine);
                                    requestBody = initialiseRequestBody.initialiseRequestBody();
                                    Trie root = MainTrieGetter.getRoot();
                                    CheckIfPathIsValid checkIfPathIsValid = new CheckIfPathIsValid(requestBody.getPATH(), root);
                                    if(checkIfPathIsValid.checkIfPathIsValid()){
                                        System.out.println("Valid Path");
                                    }else{
                                        System.out.println("Invalid Path");
                                    }
                                    flag=false;
                                }
                                requestData.append(inputLine).append("\n");
                                
                            }
                        
                        
                        
                        System.out.println("Request complete, sending response...");

                        try (BufferedWriter writer = new BufferedWriter(new FileWriter("web-server-http1_1\\src\\main\\java\\com\\codingchallenges\\web_server\\ThreadImplementation\\readthis.txt", true))) {
                            writer.write("X: " + x + "\n");
                            writer.write("Thread " + Thread.currentThread().getName() + " received request:\n");
                            writer.write(requestData.toString());
                            writer.write("\n-------------------\n");
                            writer.flush();
                        }catch (IOException e) {
                            System.err.println("Error writing to file: " + e.getMessage());
                        }
                        
                        String html = "<html><head><title>My Web Server</title></head><body><h1>Welcome to my web server!</h1></body></html>";
                        final String CRLF = "\r\n";
                        String response = "HTTP/1.1 200 OK" + CRLF +
                                "Content-Type: text/html" + CRLF +
                                "Content-Length: " + html.getBytes().length + CRLF +
                                CRLF +
                                html;
                        
                        outputStream.write(response.getBytes());
                        outputStream.flush();
                        outputStream.close();

                        try {
                            sleep(5000);
                        } catch (InterruptedException e) {
                            System.err.println("Error: " + e.getMessage());
                        }
                        

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

