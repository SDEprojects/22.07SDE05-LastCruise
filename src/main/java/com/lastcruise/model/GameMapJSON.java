package com.lastcruise.model;
import java.util.HashMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.json.simple.parser.ParseException;

public class GameMapJSON {

    // list of locations
    private Map<String, GameLocation> locations;

    // Constructors

    public GameMapJSON() {
        Map<String, GameLocation> locations = generateLocations();
        this.locations = locations;
    }
    // Business Methods

    private Map<String, GameLocation> generateLocations(){

        Map<String, GameLocation> stringGameLocationHashMap = new HashMap<>();

        ObjectMapper mapper = new ObjectMapper();
        File jsonFile = new File("src/main/java/com.lastcruise/model/location.json");

        try{
            Map<String, Object> locations = mapper.readValue(jsonFile, new TypeReference<Map<String, Object>>(){});
            System.out.println(locations);

        }catch (Exception e){
            System.out.println("something happened");
        }

        return stringGameLocationHashMap;
    }
    public Map<String, GameLocation> getLocations() {
        return locations;
    }
}