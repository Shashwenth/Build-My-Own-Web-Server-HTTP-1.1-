package com.codingchallenges.web_server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import com.codingchallenges.web_server.ConfigurationMapper.JsonParser;
import com.codingchallenges.web_server.ConfigurationModel.JsonEntity;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");

        String jsonFilePath = "/config.json";
        JsonParser jsonParser = new JsonParser(jsonFilePath);
        jsonParser.printJsonEntity();
        JsonEntity jsonEntity = jsonParser.getJsonEntity();

        try (ServerSocket serverSocket = new ServerSocket(jsonEntity.getPORT(), 50, InetAddress.getByName("10.0.0.25"));) {
                try (Socket socket = serverSocket.accept()) {
                   // socket.setSoTimeout(3000);
                    
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
                        socket.close();
                        serverSocket.close();
                        outputStream.close();
                    }

                } catch (SocketTimeoutException e) {
                    System.err.println("Connection timed out: " + e.getMessage());
                } catch (IOException e) {
                    System.err.println("Connection error: " + e.getMessage());
                }
            } catch (IOException e) {
                System.err.println("Server error: " + e.getMessage());
            }


    }



}
