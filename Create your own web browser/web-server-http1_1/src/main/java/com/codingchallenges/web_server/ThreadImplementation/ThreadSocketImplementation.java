package com.codingchallenges.web_server.ThreadImplementation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ThreadSocketImplementation extends Thread {

    private final Socket socket;

    public ThreadSocketImplementation(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){

        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    OutputStream outputStream = socket.getOutputStream();) {
                        String inputLine;
                        
                        while ((inputLine = in.readLine()) != null && !inputLine.isEmpty()) {
                            System.out.println("Received: " + inputLine);
                        }
                        
                        System.out.println("Request complete, sending response...");

                        
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

