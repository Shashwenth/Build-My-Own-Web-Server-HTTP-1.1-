package com.codingchallenges.web_server.handlePathVariables;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codingchallenges.web_server.RequestMapping.MainTrieGetter;
import com.codingchallenges.web_server.RequestMapping.Trie;

/*
 *  
 * Enter description for this class:
 * When we register a Get/Post mapping the URL is stored in TRIE
 * Using Java reflection the type of the path variables are scanned and are stored in the Path variable datatype.
 * This is later used to check if the URL has correct type.
 * 
 */

public class RegisterEndpoint {

    private static final Logger logger= LoggerFactory.getLogger(RegisterEndpoint.class);

    @SuppressWarnings("unused")
    private final String path;

    private Class<?>[] OurMethodparameterTypes;

    private final Trie root=MainTrieGetter.getRoot();

    public RegisterEndpoint(String path){
        this.path=path;
        //System.out.println(path);
    }


    public void RegisterEndPointPath(String path, String Method, List<String> ClassAndMethod){
        int i=0;
        getUnderLyingDataType(ClassAndMethod);
        String[] pathArray=path.split("/");
        logger.info("Path array ------------ {} ", Arrays.toString(pathArray));
        Trie current = this.root;

        for(String p: pathArray){
          //  System.out.println(p);
            if(p.length()==0){
                continue;
            }
            if(current.child.containsKey(p)){
                current = current.child.get(p);
            }
            else{
                Trie temp = new Trie(p);
                current.child.put(p, temp);
            //    System.out.println("This is for p "+p);
                if(p.startsWith("{") && p.endsWith("}")){
              //      System.out.printf("This is getname %s\n",OurMethodparameterTypes[i].getName());
                    current.PathVariableType.put(p, OurMethodparameterTypes[i].getName());
                }
                current = temp;
            }
            if(p.startsWith("{") && p.endsWith("}")){
                i++;
            }
        }

        current.isEnd = true;
        current.ReturnFilePath="";
        current.ReturnFilePathMap.put(Method, "");
        current.ReflectionPath.put(Method, ClassAndMethod);

        logger.info("Successfully Registered The Endpoint in Root");

    }

    public String getUnderLyingDataType(List<String> ClassAndMethod){

        try {
            Class<?> loadedClass=Class.forName(ClassAndMethod.get(0));
            Method[] methods=loadedClass.getDeclaredMethods();
            Method ourMethod = null;
            for(Method m:methods){
                if(m.getName().equals(ClassAndMethod.get(1))){
                    ourMethod=m;
                    break;
                }
            }
            if(ourMethod==null){
                return "";
            }

            this.OurMethodparameterTypes = ourMethod.getParameterTypes();
            logger.info("Our Method Param Types ------------ {}", Arrays.toString(this.OurMethodparameterTypes));
            //System.out.println(Arrays.toString(this.OurMethodparameterTypes));
        } catch (ClassNotFoundException e) {
        }        

        logger.info("Successfully initialized methodParameters list");

        return "";

    }

}
