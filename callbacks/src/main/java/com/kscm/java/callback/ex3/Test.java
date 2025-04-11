package com.kscm.java.callback.ex3;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

public class Test {
    public static void main(String[] args) {
        ConsumerCallback consumerCallback = new ConsumerCallback();
        int ageDifference = 10;
        AtomicInteger newAge1 = new AtomicInteger();
        int initialAge = 20;
        consumerCallback.getAge(initialAge, (initialAge1) -> {
            consumerCallback.increaseAge(initialAge, ageDifference, (newAge) -> {
                System.out.printf("New age ==> %s", newAge);
                newAge1.set(newAge);
            });
        });
        assertEquals(initialAge + ageDifference, newAge1.get());
    }
}
