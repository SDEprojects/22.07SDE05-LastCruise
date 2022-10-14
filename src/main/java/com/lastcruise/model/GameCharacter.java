package com.lastcruise.model;

public abstract class GameCharacter {
    private String name;

    // Constructors
    public GameCharacter(String name) {
        this.name = name;
    }

    // Accessors

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
