package com.codingchallenges.web_server;

import com.codingchallenges.web_server.ConfigurationMapper.JsonParser;

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

    }



}
