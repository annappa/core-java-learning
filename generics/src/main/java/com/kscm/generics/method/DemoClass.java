package com.kscm.generics.method;

public class DemoClass {
    // create a generics method
    //Here, the type parameter <T> is inserted after the modifier public and before the return type void.
    public <T> void genericsMethod(T data) {
        System.out.println("Generics Method:");
        System.out.println("Data Passed: " + data);
    }
}
