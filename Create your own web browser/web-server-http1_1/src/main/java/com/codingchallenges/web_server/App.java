package com.codingchallenges.web_server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codingchallenges.web_server.ConfigurationMapper.JsonParser;
import com.codingchallenges.web_server.ConfigurationModel.JsonEntity;
import com.codingchallenges.web_server.RequestMapping.MainTrieGetter;
import com.codingchallenges.web_server.RequestMapping.Trie;
import com.codingchallenges.web_server.ThreadImplementation.ThreadStartUp;
import com.codingchallenges.web_server.handlePathVariables.RegisterEndpoint;
import com.example.Main;




/**
 * Hello world!
 */
public final class App {

    
    private static final Logger logger=LoggerFactory.getLogger(App.class);

    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        //System.out.println("Hello World!");
        logger.info("Execution Started");
        String jsonFilePath = "/config.json";
        JsonParser<JsonEntity> jsonParser = new JsonParser<>(JsonEntity.class);
        JsonEntity jsonEntity = jsonParser.parseFromFile(jsonFilePath);
        jsonParser.printEntity(jsonEntity);

        //JsonEntity jsonEntity = jsonParser.getJsonEntity();
        logger.info("Intitalizing Trie Mapper");
        Trie trie = new Trie("/");

        // Intializer it=new Intializer();
        MainTrieGetter.setRoot(trie);

        // it.GetInitialize("/Shashwenth", "web-server-http1_1\\src\\main\\java\\com\\codingchallenges\\web_server\\ResponseHTMLFiles\\Shashwenth.html");

        // it.GetInitialize("/Dhay", "web-server-http1_1\\src\\main\\java\\com\\codingchallenges\\web_server\\ResponseHTMLFiles\\Dhay.html");

        // it.GetInitialize("/", "web-server-http1_1\\src\\main\\java\\com\\codingchallenges\\web_server\\ResponseHTMLFiles\\Welcome.html");

        // it.PostInitialize("/Shashwenth", "web-server-http1_1\\src\\main\\java\\com\\codingchallenges\\web_server\\ResponseHTMLFiles\\Dhay.html");

        // it.PostInitialize("/Dhay", "web-server-http1_1\\src\\main\\java\\com\\codingchallenges\\web_server\\ResponseHTMLFiles\\Shashwenth.html");

        logger.info("Initializing User Implementation");
        // MyInitialTestApp myInitialTestApp=new MyInitialTestApp();
        // myInitialTestApp.initializeNew("/Shashwenth/{name}", "TestPathVariable", "POST");
        // myInitialTestApp.initializeNew("/Shashwenth/{name}/{id}", "TestPathVariable2", "POST");
        // myInitialTestApp.initializeNew("/Shashwenth/name", "getName", "GET");
        // myInitialTestApp.initializeNew("/Shashwenth/age", "getAge", "GET");
        // myInitialTestApp.initializeNew("/Shashwenth/name", "setName", "POST");
        // myInitialTestApp.initializeNew("/Shashwenth", "TestRequestParams", "POST");
        // myInitialTestApp.initialize("/Shashwenth/cast", "TestCastRequestParams", "POST");
        Main.main(args);
        List<Main.StoreClassAndMethod> routes = Main.getResponse();

        for (Main.StoreClassAndMethod route : routes) {
            initializeNew(route.Path, route.FunctionName, route.MethodName, route.ClassName);
        }

        // myInitialTestApp.initialize("/Shashwenth/name", "getName", "GET");
        // myInitialTestApp.initialize("/Shashwenth/age", "getAge", "GET");
        // myInitialTestApp.initialize("/Shashwenth/name", "setName", "POST");
        // myInitialTestApp.initialize("/Shashwenth", "TestRequestParams", "POST");
        //TestCastRequestParams TestPathVariable
        //myInitialTestApp.initialize("/Shashwenth/cast", "TestCastRequestParams", "POST");
        
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
            logger.atInfo().addKeyValue("Thread Id", threadStartUp.getId()).log("Server Socket Thread: ");
            threadStartUp.start();
        } catch (IOException ex) {
            System.err.println("Error: " + ex.getMessage());
        } 
        
    }

    public static void initializeNew(String path, String FunctionName, String Method, String Classname){
        List<String> classAndMethod=register(FunctionName, Classname);
        
                logger.atInfo().addKeyValue("path", path).log("Registering Endpoint");
        
                RegisterEndpoint registerEndpoint=new RegisterEndpoint(path);
        
                registerEndpoint.RegisterEndPointPath(path, Method, classAndMethod);
        
                logger.info("Successfully initialized the path {} and method {} with FunctionName {}",path,Method,FunctionName);
                //System.out.println("Sucessfully added the path");
            }
        
    private static List<String> register(String FunctionName, String Classname){
        List<String> response=new ArrayList<>();
        response.add(Classname);
        response.add(FunctionName);

        logger.info("Registered the Class name and function name for Reflection");

        return response;
    }



}
