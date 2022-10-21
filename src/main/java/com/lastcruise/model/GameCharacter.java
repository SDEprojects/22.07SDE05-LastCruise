package com.lastcruise.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(
    use = Id.NAME,
    include = As.PROPERTY,
    property = "type"
)
@JsonSubTypes(
    {
        @JsonSubTypes.Type(value = Player.class, name = "player")
    }
)
public abstract class GameCharacter {
    private String name;
    private Inventory inventory;

    public GameCharacter() {
    }

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

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
