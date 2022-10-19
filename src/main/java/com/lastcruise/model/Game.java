package com.lastcruise.model;

import com.lastcruise.model.GameMap.InvalidLocationException;
import com.lastcruise.model.Inventory.InventoryEmptyException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Game {

    private final GameCharacter player;
    private final GameMap gameMap;

    private static final String STARTING_LOCATION = "BEACH";


    public Game(String playerName) {
        this.gameMap = new GameMap();
        this.player = new Player(playerName);
        gameMap.setStartLocation(gameMap.getLocations().get(STARTING_LOCATION));
        ;

    }

    public GameLocation getCurrentLocation() {
        return gameMap.getCurrentLocation();
    }

    public String getCurrentLocationName() {
        return gameMap.getCurrentLocation().getName();
    }

    public String getCurrentLocationDesc() {
        return gameMap.getCurrentLocation().getDescription();
    }

    public Map<String, Item> getCurrentLocationItems() {

        return gameMap.getCurrentLocation().getItems().getInventory();
    }

    public Inventory getCurrentLocationInventory() {
        return gameMap.getCurrentLocation().getItems();
    }

    public Inventory getPlayerInventory() {
        return player.getInventory();
    }


    public void moveLocation(String[] direction) throws InvalidLocationException {
        gameMap.updateCurrentLocation(direction);
    }

    public void transferItemFromTo(Inventory from, Inventory to, String itemName)
        throws InventoryEmptyException {
        Item removed = from.remove(itemName);
        to.add(itemName, removed);
    }

    public String inspectItem(String[] command) {
        String item = command[1].toLowerCase();
        if (getCurrentLocationItems().containsKey(item)) {
            return getCurrentLocationItems().get(item).getDescription();
        }
        return null;

    }

    public boolean craftRaft() {
// To be continued
        Map<String, Item> currLocationInventory = getCurrentLocation().getItems().getInventory();
        Set<String> requiredForRaft = GameItems.GAME_ITEMS_HASHMAP.get("raft").getRequired();

        if (currLocationInventory.keySet()
            .containsAll(requiredForRaft)) {

            for (var requiredItem : requiredForRaft) {
                currLocationInventory.remove(requiredItem);
            }
            currLocationInventory.put("raft", GameItems.GAME_ITEMS_HASHMAP.get("raft"));
            return true;
        }
        ;

        return false;
    }


}
