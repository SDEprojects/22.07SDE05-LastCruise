package com.lastcruise.model;

import java.util.List;

public class CraftingLocation extends GameLocation {


    public CraftingLocation(String locationName, String locationDescription) {
        super(locationName, locationDescription);
    }

    public boolean buildRaft(){
        List<Item> locationItems = getItems();

        // check if locationItems contains necessary items

        // if yes return true

        // otherwise return false
        return false;
    }
}
