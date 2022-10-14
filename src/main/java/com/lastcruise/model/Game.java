package com.lastcruise.model;

import java.util.List;
import java.util.Map;

public class Game {
    private final GameCharacter player;
    private final GameMap gameMap;

    private static final  String STARTING_LOCATION = "BEACH";

    public Game(String playerName) {
        this.gameMap = new GameMap();
        this.player = new Player(playerName);
        gameMap.setStartLocation(gameMap.getLocations().get(STARTING_LOCATION));;

    }

    public GameLocation getCurrentLocation() {
        return gameMap.getCurrentLocation();
    }

    public String getCurrentLocationName(){
        return gameMap.getCurrentLocation().getName();
    }

    public String getCurrentLocationDesc(){
        return gameMap.getCurrentLocation().getDescription();
    }

    public Map<String, Item> getCurrentLocationItems(){
        return gameMap.getCurrentLocation().getItems();
    }


    public void moveLocation(String[] direction){
        gameMap.updateCurrentLocation(direction);
    }

    public String inspectItem(String[] command){
        String item = command[1].toLowerCase();
        if(getCurrentLocationItems().containsKey(item)){
           return getCurrentLocationItems().get(item).getDescription();
        }
            return null;

    }
}
