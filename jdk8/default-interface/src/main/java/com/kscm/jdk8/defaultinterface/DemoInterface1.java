package com.kscm.jdk8.defaultinterface;

interface Phone {
    void call();
    default void message() {
        System.out.println("Sent");
    }
}

class AndroidPhone implements Phone {
    public void call() {
        System.out.println("Calling");
    }
}

public class DemoInterface1 {
    public static void main(String[] args) {
        Phone phone = new AndroidPhone();
        phone.call();
        phone.message();
    }
}
