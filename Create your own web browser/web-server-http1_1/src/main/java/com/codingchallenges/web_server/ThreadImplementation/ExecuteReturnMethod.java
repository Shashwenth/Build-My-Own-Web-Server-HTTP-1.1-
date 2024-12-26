package com.codingchallenges.web_server.ThreadImplementation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ExecuteReturnMethod {

    private final String className;
    
    private final String methodName;

    private final String CRLF = "\r\n";
    
        public ExecuteReturnMethod(String ClassName, String MethodName){
    
            this.className=ClassName;
            this.methodName=MethodName;
    
        }
    
    @SuppressWarnings("CallToPrintStackTrace")
    public String invokeMethod(Object ...args) {
        try {
            // Load the class
        Class<?> loadedClass = Class.forName(className);
        
        Class<?>[] parameterTypes = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                parameterTypes[i] = args[i].getClass();
            }

        // Create instance
        Object instance = loadedClass.getDeclaredConstructor().newInstance();
        
        // Get and invoke method
        Method method = loadedClass.getMethod(methodName, parameterTypes);
        Object result=method.invoke(instance, args);  // Returns Object type
        String responseText = result != null ? result.toString() : "";
            
            // Format HTTP response
            return "HTTP/1.1 200 OK" + CRLF +
                   "Content-Type: text/plain" + CRLF +
                   "Content-Length: " + responseText.getBytes().length + CRLF +
                   CRLF +
                   responseText;
        
    } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
        e.printStackTrace();
        return null;
    }
}

}
