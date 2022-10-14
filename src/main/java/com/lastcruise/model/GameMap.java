package com.lastcruise.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameMap {

    // list of locations
    private Map<String, GameLocation> locations;

    // Constructors

    public GameMap() {

        Map<String, GameLocation> mapOfLocations = generateLocations();

        // visualize locations and their items;
        for (var key : mapOfLocations.keySet()){
            System.out.println(mapOfLocations.get(key));
        }

        this.locations = mapOfLocations;
    }
    // Business Methods

    private Map<String, GameLocation> generateLocations() {

        Map<String, GameLocation> stringGameLocationHashMap = new HashMap<>();

        ObjectMapper mapper = new ObjectMapper();
        File jsonLocations = new File("src/main/java/com/lastcruise/model/locations.json");

        try {
            List<GameLocation> locationsDecoded = mapper.readValue(jsonLocations,
                new TypeReference<List<GameLocation>>(){});

            for (GameLocation location : locationsDecoded){
                stringGameLocationHashMap.put(location.getName(), location);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return stringGameLocationHashMap;
    }

    public Map<String, GameLocation> getLocations() {
        return locations;
    }
}
