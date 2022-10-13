package com.lastcruise.model;

import java.util.HashMap;

public class GameMap {

    // list of locations
    private HashMap<String, GameLocation> locations;

    // Constructors

    public GameMap() {
        HashMap<String, GameLocation> locations = generateLocations();
        this.locations = locations;
    }
    // Business Methods

    private HashMap<String, GameLocation> generateLocations(){

        HashMap<String, GameLocation> stringGameLocationHashMap = new HashMap<>();

        GameLocation location1 = new CraftingLocation("Beach", "Beach Description");
        GameLocation location2 = new NormalLocation("Jungle", "Jungle Description");

        stringGameLocationHashMap.put(location1.getName(), location1);
        stringGameLocationHashMap.put(location2.getName(), location2);

        return stringGameLocationHashMap;
    }
    public HashMap<String, GameLocation> getLocations() {
        return locations;
    }
}
