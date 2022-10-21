package com.lastcruise.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Item {
    private String name;
    private String description;

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

    @Override
    public String toString() {
        return "Item{" +
            "name='" + name + '\'' +
            ", description='" + description + '\'' +
            "}";
    }
}
