package com.kscm.corejava.jdk8.optional;

import java.util.Optional;

public class User {
    private final String id;
    private final String name;
    private final Profile profile;

    public User(String id, String name, Profile profile) {
        this.id = id;
        this.name = name;
        this.profile = profile;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public Optional<Profile> getProfile() {
        return Optional.ofNullable(profile);
    }
}