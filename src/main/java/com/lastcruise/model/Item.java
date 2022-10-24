package com.lastcruise.model;

import java.util.Set;

public class Item {
    private String name;
    private String description;
    private boolean edible;
    private Set<String> required;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getRequired() {
        return required;
    }

    public void setRequired(Set<String> required) {
        this.required = required;
    }

    public void setEdible(boolean edible) {
        this.edible = edible;
    }

    public boolean getEdible(){
        return edible;
    }

    @Override
    public String toString() {
        return "Item{" +
            "name='" + name + '\'' +
            ", description='" + description + '\'' +
            "}";
    }
}
