package com.lastcruise.model;

public class Game {
    private final GameCharacter player;
    private final GameMap gameMap;


    public Game(String playerName) {
        this.gameMap = new GameMap();
        this.player = new Player(playerName);
        GameLocation startingLocation = gameMap.getLocations().get("Beach");

    }

//    public GameLocation getCurrentLocation() {
//        return gameMap.getCurrentLocation();
//    }
//
//    public String getCurrentLocationName(){
//        return gameMap.getCurrentLocation().getName();
//    }
//
//
//    public void move(String[] direction){
//        gameMap.updateCurrentLocation(direction);
//    }
}
