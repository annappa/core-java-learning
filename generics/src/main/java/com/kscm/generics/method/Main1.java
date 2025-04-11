package com.kscm.generics.method;

public class Main1 {
    public static void main(String[] args) {
        // initialize the class with Integer data
        DemoClass demo = new DemoClass();

        // generics method working with String
        demo.<String>genericsMethod("Java Programming");

        //In this case, the compiler can match the type parameter based on the value passed to the method.
        demo.genericsMethod("Java Programming");

        // generics method working with integer
        demo.<Integer>genericsMethod(25);
    }
}
