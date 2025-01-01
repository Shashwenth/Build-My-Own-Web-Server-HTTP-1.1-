package com.codingchallenges.web_server;


import java.io.IOException;

import com.codingchallenges.web_server.ConfigurationMapper.JsonParser;
import com.codingchallenges.web_server.ConfigurationModel.JsonEntity;
import com.codingchallenges.web_server.RequestMapping.MainTrieGetter;
import com.codingchallenges.web_server.RequestMapping.Trie;
import com.codingchallenges.web_server.ThreadImplementation.ThreadStartUp;
import com.codingchallenges.web_server.UserImplementation.MyInitialTestApp;

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
        JsonParser<JsonEntity> jsonParser = new JsonParser<>(JsonEntity.class);
        JsonEntity jsonEntity = jsonParser.parseFromFile(jsonFilePath);
        jsonParser.printEntity(jsonEntity);

        //JsonEntity jsonEntity = jsonParser.getJsonEntity();

        Trie trie = new Trie("/");

        // Intializer it=new Intializer();
        MainTrieGetter.setRoot(trie);

        // it.GetInitialize("/Shashwenth", "web-server-http1_1\\src\\main\\java\\com\\codingchallenges\\web_server\\ResponseHTMLFiles\\Shashwenth.html");

        // it.GetInitialize("/Dhay", "web-server-http1_1\\src\\main\\java\\com\\codingchallenges\\web_server\\ResponseHTMLFiles\\Dhay.html");

        // it.GetInitialize("/", "web-server-http1_1\\src\\main\\java\\com\\codingchallenges\\web_server\\ResponseHTMLFiles\\Welcome.html");

        // it.PostInitialize("/Shashwenth", "web-server-http1_1\\src\\main\\java\\com\\codingchallenges\\web_server\\ResponseHTMLFiles\\Dhay.html");

        // it.PostInitialize("/Dhay", "web-server-http1_1\\src\\main\\java\\com\\codingchallenges\\web_server\\ResponseHTMLFiles\\Shashwenth.html");

        MyInitialTestApp myInitialTestApp=new MyInitialTestApp();

        // myInitialTestApp.initialize("/Shashwenth/name", "getName", "GET");
        // myInitialTestApp.initialize("/Shashwenth/age", "getAge", "GET");
        // myInitialTestApp.initialize("/Shashwenth/name", "setName", "POST");
        // myInitialTestApp.initialize("/Shashwenth", "TestRequestParams", "POST");
        //TestCastRequestParams TestPathVariable
        //myInitialTestApp.initialize("/Shashwenth/cast", "TestCastRequestParams", "POST");
        myInitialTestApp.initializeNew("/Shashwenth/{name}", "TestPathVariable", "POST");
        myInitialTestApp.initializeNew("/Shashwenth/{name}/{id}", "TestPathVariable2", "POST");
        myInitialTestApp.initializeNew("/Shashwenth/name", "getName", "GET");
        myInitialTestApp.initializeNew("/Shashwenth/age", "getAge", "GET");
        myInitialTestApp.initializeNew("/Shashwenth/name", "setName", "POST");
        myInitialTestApp.initializeNew("/Shashwenth", "TestRequestParams", "POST");
        myInitialTestApp.initialize("/Shashwenth/cast", "TestCastRequestParams", "POST");
        // FindPath findPath0=new FindPath("/Shashwenth/Shash");
        // findPath0.isPathValid("POST");
        // FindPath findPath1=new FindPath("/Shashwenth/name");
        // findPath1.isPathValid("GET");
        // FindPath findPath2=new FindPath("/Shashwenth/age");
        // findPath2.isPathValid("GET");
        // FindPath findPath3=new FindPath("/Shashwenth/name");
        // findPath3.isPathValid("POST");
        // FindPath findPath4=new FindPath("/Shashwenth");
        // findPath4.isPathValid("POST");
        // FindPath findPath5=new FindPath("/Shashwenth/1");
        // findPath5.isPathValid("POST");
        // FindPath findPath6=new FindPath("/Shashwenth/Shashwenth");
        // findPath6.isPathValid("POST");
        // findPath6=new FindPath("/Shashwenth/Shashwenth/10");
        // findPath6.isPathValid("POST");

        ThreadStartUp threadStartUp;
        try {
            threadStartUp = new ThreadStartUp(jsonEntity.getPORT(), jsonEntity.getWebRoot());
            threadStartUp.start();
        } catch (IOException ex) {
            System.err.println("Error: " + ex.getMessage());
        } 
        
    }



}
