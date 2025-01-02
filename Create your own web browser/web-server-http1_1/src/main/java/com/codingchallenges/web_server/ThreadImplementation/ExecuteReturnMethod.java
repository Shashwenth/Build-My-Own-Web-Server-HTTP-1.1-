package com.codingchallenges.web_server.ThreadImplementation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Enter the description of the class
 * 
 * It is used to invoke the method of the class using the Reflection API.
 * Gets Var Args and returns a String response.
 * Using Java reflction it call the other classes and methods dynamically.
 * 
 * 
 */

public class ExecuteReturnMethod {

    private static final Logger logger= LoggerFactory.getLogger(ExecuteReturnMethod.class);

    private final String className;
    
    private final String methodName;

    private final String CRLF = "\r\n";
    
        public ExecuteReturnMethod(String ClassName, String MethodName){
    
            this.className=ClassName;
            this.methodName=MethodName;
            logger.atInfo().addKeyValue("className", this.className)
            .addKeyValue("Method Name", this.methodName)
            .log("Inititlaizing the Execute Return Method with Java Reflection");
    //        System.out.println("This is the "+this.className+" and "+this.methodName);
    
        }
    
    @SuppressWarnings("CallToPrintStackTrace")
    public String invokeMethod(Object ...args) {
        try {
            // Load the class
        Class<?> loadedClass = Class.forName(className);

        logger.atInfo().addKeyValue("Class", loadedClass.getName()).log("Successfully Loaded the Class");
    //    System.out.println("LEt us SEE HERE");
    //     Class<?>[] parameterTypes = new Class[args.length];
    //         for (int i = 0; i < args.length; i++) {
    //             parameterTypes[i] = args[i].getClass();
    // //           System.out.println(parameterTypes[i]);
    //         }

        // Create instance
        Object instance = loadedClass.getDeclaredConstructor().newInstance();
        
        // Get and invoke method

        Method ourMethod = null;

        Method[] methods=loadedClass.getDeclaredMethods();
        for(Method m:methods){
            if(m.getName().equals(methodName) && m.getParameterCount()==args.length){
                ourMethod=m;
                break;
            }
        }

        Class<?>[] OurMethodparameterTypes;
            if(ourMethod!=null){
                logger.atInfo().addKeyValue("Method", ourMethod.getName()).log("Found The Function");
                OurMethodparameterTypes = ourMethod.getParameterTypes();
            }else{
                logger.atInfo().log("The OurMethod is Null");
            OurMethodparameterTypes=new Class[1];
            }
        
        
        Object[] newArgs=new Object[args.length];
        
        //System.out.println(OurMethodparameterTypes[i]);
        //System.out.println(args[i]);

        // for(int i=0;i<args.length;i++){
        //     newArgs[i]=OurMethodparameterTypes[i].cast(args[i]);
        // }

        for (int i = 0; i < args.length; i++) {
        //    System.out.println(OurMethodparameterTypes[i].getName());

            if (args[i] != null) {
                switch (OurMethodparameterTypes[i].getName()) {
                    // Wrapper types
                    case "java.lang.Integer":
                        newArgs[i] = Integer.valueOf(args[i].toString()); // Convert to Integer
                        break;
                    case "java.lang.Double":
                        newArgs[i] = Double.valueOf(args[i].toString()); // Convert to Double
                        break;
                    case "java.lang.String":
                        newArgs[i] = args[i].toString(); // Already a String
                        break;
                    case "java.lang.Boolean":
                        newArgs[i] = Boolean.valueOf(args[i].toString()); // Convert to Boolean
                        break;
                    case "java.lang.Long":
                        newArgs[i] = Long.valueOf(args[i].toString()); // Convert to Long
                        break;
                    case "java.lang.Float":
                        newArgs[i] = Float.valueOf(args[i].toString()); // Convert to Float
                        break;
                    case "java.lang.Character":
                        newArgs[i] = args[i].toString().charAt(0); // Convert to Character (first char)
                        break;
        
                    // Primitive types
                    case "int":
                        newArgs[i] = Integer.valueOf(args[i].toString()); // Convert to primitive int
                        break;
                    case "double":
                        newArgs[i] = Double.valueOf(args[i].toString()); // Convert to primitive double
                        break;
                    case "boolean":
                        newArgs[i] = Boolean.valueOf(args[i].toString()); // Convert to primitive boolean
                        break;
                    case "long":
                        newArgs[i] = Long.valueOf(args[i].toString()); // Convert to primitive long
                        break;
                    case "float":
                        newArgs[i] = Float.valueOf(args[i].toString()); // Convert to primitive float
                        break;
                    case "char":
                        newArgs[i] = args[i].toString().charAt(0); // Convert to primitive char
                        break;
        
                    // Add cases for other primitive types as necessary
                    default:
                        throw new IllegalArgumentException("Unsupported type: " + OurMethodparameterTypes[i].getName());
                }
            } else {
                // Handle null values, if required
                System.out.println("Inside null");
                newArgs[i] = null;
            }
        }
        

        Method method = loadedClass.getMethod(methodName, OurMethodparameterTypes);
        Object result=method.invoke(instance, newArgs);  // Returns Object type
        String responseText = result != null ? result.toString() : "";

        logger.info("Successfully Completed the Execute Return Method");

            
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
