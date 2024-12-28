package com.codingchallenges.web_server.ThreadImplementation;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


/*
 * 
 *  Thread to start the server and listen to the port.
 *  It creates a new thread for each request.
 *  This allows concurrent requests to be handled.
 * 
 */

public class ThreadStartUp extends Thread {

    private final int port;
    @SuppressWarnings("unused")
    private final  String webRoot;

    private final ServerSocket serverSocket;
    
    public ThreadStartUp(int port, String webRoot) throws IOException{
        this.port = port;
        this.webRoot = webRoot;
        this.serverSocket = new ServerSocket(this.port, 50, InetAddress.getByName("10.0.0.25"));
    }


    @Override
    public void run(){
        int x=0;
        try {
            while(serverSocket.isBound() && !serverSocket.isClosed()){
                try {
                    Socket socket = serverSocket.accept();
                    
                    ThreadSocketImplementation threadSocketImplementation = new ThreadSocketImplementation(socket,x++);
                    threadSocketImplementation.start();
                    
                } catch (IOException ex) {
                }
                
        }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally{
            try {
                serverSocket.close();
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
        
        
    }

    

}
