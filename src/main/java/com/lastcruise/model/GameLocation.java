package com.lastcruise.model;

import com.lastcruise.model.GameMap.InvalidLocationException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameLocation {

    private String name;
    private String description;
    private String north;
    private String south;
    private String east;
    private String west;

    private Boolean craftingLocation;

//    private Map<String, Item> items;

    private Inventory items;
    Item it = new Item();



    public GameLocation() {
    }

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

    public String getNorth() {
        return north;
    }

    public void setNorth(String north) {
        this.north = north;
    }

    public String getSouth() {
        return south;
    }

    public void setSouth(String south) {
        this.south = south;
    }

    public String getEast() {
        return east;
    }

    public void setEast(String east) {
        this.east = east;
    }

    public String getWest() {
        return west;
    }

    public void setWest(String west) {
        this.west = west;
    }

    public Inventory getItems() {
        return items;
    }

    public void setItems(List<String> items) {
//        Map<String, Item> result = new LinkedHashMap<>();

       Inventory result  =  new Inventory();

        for (String itemName : items) {
           // result.put(itemName, GameItems.GAME_ITEMS_HASHMAP.get(itemName));

            result.add(itemName, GameItems.GAME_ITEMS_HASHMAP.get(itemName));


        }
        this.items = result;
    }

    public Boolean getCraftingLocation() {
        return craftingLocation;
    }

    public void setCraftingLocation(Boolean craftingLocation) {
        this.craftingLocation = craftingLocation;
    }








    @Override
    public String toString() {
        return "\nGameLocation{" +
            "\n    name='" + name + '\'' +
            ", \n   description='" + description + '\'' +
            ", \n   north='" + north + '\'' +
            ", \n   south='" + south + '\'' +
            ", \n   east='" + east + '\'' +
            ", \n   west='" + west + '\'' +
            ", \n   craftingLocation=" + craftingLocation +
            ", \n   items=" + items +
            "\n }";
    }


}
