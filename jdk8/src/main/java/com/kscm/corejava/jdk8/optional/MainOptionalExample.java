package com.kscm.corejava.jdk8.optional;

import java.util.NoSuchElementException;

public class MainOptionalExample {
    public static void main(String[] args) {
        UserService service = new UserService();

        System.out.println(service.getDisplayName("1")); // Alicious
        System.out.println(service.getDisplayName("2")); // Guest
        System.out.println(service.getDisplayName("3")); // Guest

        try {
            User user = service.getOrThrow("3"); // Throws exception
        } catch (NoSuchElementException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }
}
