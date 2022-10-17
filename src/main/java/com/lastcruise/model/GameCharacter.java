package com.lastcruise.model;

public abstract class GameCharacter {
    private String name;
    private final Inventory inventory;


    // Constructors
    public GameCharacter(String name) {
        this.name = name;
        this.inventory = new Inventory();
    }

    // Accessors

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
