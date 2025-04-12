package com.kscm.corejava.jdk8.optional;

import java.util.NoSuchElementException;

public class UserService {
    private final UserRepository repo = new UserRepository();

    public String getDisplayName(String userId) {
        return repo.findById(userId)
                .flatMap(User::getProfile)
                .map(Profile::getDisplayName)
                .orElse("Guest");
    }

    public User getOrThrow(String userId) {
        return repo.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }
}
