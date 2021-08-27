package com.kscm.heapdump.analysis.mat.ex2;

public class SimpleExample {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();

        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            //handle
        }

        System.out.println("Done");
    }
}
