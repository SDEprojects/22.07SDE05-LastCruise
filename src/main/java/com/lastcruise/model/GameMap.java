package com.lastcruise.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameMap {

    // list of locations
    private Map<String, GameLocation> locations;

    private GameLocation currentLocation;

    // Constructors
    public GameMap() {

        Map<String, GameLocation> mapOfLocations = generateLocations();

        // visualize locations and their items;
        for (var key : mapOfLocations.keySet()){
            System.out.println(mapOfLocations.get(key));
        }

        this.locations = mapOfLocations;

    }
    public void setStartLocation(GameLocation startLocation){
        currentLocation = startLocation;
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

    public GameLocation getCurrentLocation(){
        return currentLocation;
    }

    public void updateCurrentLocation(String[] command){
        String newLocation = null;
        System.out.println(command[1]);
        switch(command[1].toLowerCase()){
            case "north":{
                newLocation = currentLocation.getNorth();
                break;
            }
            case "south":{
                newLocation = currentLocation.getSouth();
                break;
            }
            case "east":{
                newLocation = currentLocation.getEast();
                break;
            }
            case "west":{
                newLocation = currentLocation.getWest();
                break;
            }
        }
        if(locations.containsKey(newLocation)){
            currentLocation =  locations.get(newLocation);
        }
        else{
            System.out.println("You can't go that way!");
        }


    }




}
