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

        myInitialTestApp.initialize("/Shashwenth/name", "getName", "GET");
        myInitialTestApp.initialize("/Shashwenth/age", "getAge", "GET");
        myInitialTestApp.initialize("/Shashwenth/name", "setName", "POST");
        myInitialTestApp.initialize("/Shashwenth", "TestRequestParams", "POST");
        
        ThreadStartUp threadStartUp;
        try {
            threadStartUp = new ThreadStartUp(jsonEntity.getPORT(), jsonEntity.getWebRoot());
            threadStartUp.start();
        } catch (IOException ex) {
            System.err.println("Error: " + ex.getMessage());
        } 
        
    }



}
