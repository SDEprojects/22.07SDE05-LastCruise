package com.lastcruise.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import java.util.List;

@JsonTypeInfo(
    use = Id.NAME,
    include = As.PROPERTY,
    property = "type")
@JsonSubTypes({
    @Type(value = CraftingLocation.class, name = "crafting"),
    @Type(value = GameLocation.class, name = "normal")
})
public class GameLocation {

    private String name;
    private String description;
    private String north;
    private String south;
    private String east;
    private String west;
//    private Boolean craftingLocation;
    private Inventory items;

    // Constructor
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

       Inventory result  =  new Inventory();

        for (String itemName : items) {
            result.add(itemName, GameItems.GAME_ITEMS_HASHMAP.get(itemName));
        }
        this.items = result;
    }

//    public Boolean getCraftingLocation() {
//        return craftingLocation;
//    }
//
//    public void setCraftingLocation(Boolean craftingLocation) {
//        this.craftingLocation = craftingLocation;
//        if (craftingLocation){
//
//        }
//    }

    @Override
    public String toString() {
        return "\nGameLocation{" +
            "\n    name='" + name + '\'' +
            ", \n   description='" + description + '\'' +
            ", \n   north='" + north + '\'' +
            ", \n   south='" + south + '\'' +
            ", \n   east='" + east + '\'' +
            ", \n   west='" + west + '\'' +
//            ", \n   craftingLocation=" + craftingLocation +
            ", \n   items=" + items +
            "\n }";
    }
}
