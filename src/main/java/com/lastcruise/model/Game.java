package com.lastcruise.model;

public class Game {
    private GameCharacter player;
    private GameMap gameMap;
    private GameLocation currentLocation;

    public Game(String playerName) {
        this.gameMap = new GameMap();
        this.player = new Player(playerName);
        GameLocation startingLocation = gameMap.getLocations().get("Beach");
        setCurrentLocation(startingLocation);
    }

    public GameLocation getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(GameLocation currentLocation) {
        this.currentLocation = currentLocation;
    }
}
