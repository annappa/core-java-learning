package com.kscm.corejava.jdk8.optional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserRepository {
    private final Map<String, User> database = new HashMap<>();

    public UserRepository() {
        database.put("1", new User("1", "Alice", new Profile("Alicious")));
        database.put("2", new User("2", "Bob", null));  // no profile
    }

    public Optional<User> findById(String id) {
        return Optional.ofNullable(database.get(id));
    }
}
