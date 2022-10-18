package com.lastcruise.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameMap {

  // list of locations
  private Map<String, GameLocation> locations;

  private GameLocation currentLocation;

  private String currentItemDescription;

  // Constructors
  public GameMap() {

    Map<String, GameLocation> mapOfLocations = generateLocations();
    this.locations = mapOfLocations;

  }

  public void setStartLocation(GameLocation startLocation) {
    currentLocation = startLocation;
  }

  // Business Methods
  private Map<String, GameLocation> generateLocations() {

    Map<String, GameLocation> stringGameLocationHashMap = new HashMap<>();
    ObjectMapper mapper = new ObjectMapper();

    // try with resources to automatically close the file
    try(InputStream jsonLocations = Thread.currentThread().getContextClassLoader().getResourceAsStream("json/locations.json")) {
      List<GameLocation> locationsDecoded = mapper.readValue(jsonLocations,
          new TypeReference<List<GameLocation>>() {
          });

      for (GameLocation location : locationsDecoded) {
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

  public GameLocation getCurrentLocation() {
    return currentLocation;
  }

  public void updateCurrentLocation(String[] command) throws InvalidLocationException {
    String newLocation = null;
    switch (command[1].toLowerCase()) {
      case "north": {
        newLocation = currentLocation.getNorth();
        break;
      }
      case "south": {
        newLocation = currentLocation.getSouth();
        break;
      }
      case "east": {
        newLocation = currentLocation.getEast();
        break;
      }
      case "west": {
        newLocation = currentLocation.getWest();
        break;
      }
    }
    if (locations.containsKey(newLocation)) {
      currentLocation = locations.get(newLocation);
    } else {
       throw new InvalidLocationException();
    }


  }


  public class InvalidLocationException extends Throwable {

  }
}
