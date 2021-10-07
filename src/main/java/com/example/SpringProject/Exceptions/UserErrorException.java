package com.example.SpringProject.Exceptions;

public class UserErrorException extends Exception {
    /**
     * A constructor for user errors exceptions: when something is wrong with the data
     * that the user had typed.
     *
     * @param className  - The class in which the exception occurred.
     * @param methodName - The method in which exception occurred.
     * @param message    - The possible explanation for the exception.
     */
    public UserErrorException(String className, String methodName, String message) {
        System.out.println("\nUser error at: \nCLASS: " + className + ", METHOD: " + methodName + "\n" + message + "\n");
    }

    public UserErrorException(String message) {
        super(message);
    }
}
