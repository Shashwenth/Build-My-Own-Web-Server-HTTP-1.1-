package com.codingchallenges.web_server.handlePathVariables;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import com.codingchallenges.web_server.RequestMapping.MainTrieGetter;
import com.codingchallenges.web_server.RequestMapping.Trie;

public class RegisterEndpoint {

    private final String path;

    private Class<?>[] OurMethodparameterTypes;

    private Trie root=MainTrieGetter.getRoot();

    public RegisterEndpoint(String path){
        this.path=path;
        System.out.println(path);
    }


    public void RegisterEndPointPath(String path, String Method, List<String> ClassAndMethod){
        int i=0;
        getUnderLyingDataType(ClassAndMethod);
        String[] pathArray=path.split("/");
        System.out.println(Arrays.toString(pathArray));
        Trie current = this.root;

        for(String p: pathArray){
            System.out.println(p);
            if(p.length()==0){
                continue;
            }
            if(current.child.containsKey(p)){
                current = current.child.get(p);
            }
            else{
                Trie temp = new Trie(p);
                current.child.put(p, temp);
                System.out.println("This is for p "+p);
                if(p.startsWith("{") && p.endsWith("}")){
                    System.out.printf("This is getname %s\n",OurMethodparameterTypes[i].getName());
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

    }

    public String getUnderLyingDataType(List<String> ClassAndMethod){

        try {
            Class<?> loadedClass=Class.forName(ClassAndMethod.get(0));
            Method[] methods=loadedClass.getDeclaredMethods();
            Method ourMethod=null;
            for(Method m:methods){
                if(m.getName().equals(ClassAndMethod.get(1))){
                    ourMethod=m;
                    break;
                }
            }
            this.OurMethodparameterTypes = ourMethod.getParameterTypes();
            System.out.println(Arrays.toString(this.OurMethodparameterTypes));
        } catch (ClassNotFoundException e) {
        }        

        return "";

    }

}
