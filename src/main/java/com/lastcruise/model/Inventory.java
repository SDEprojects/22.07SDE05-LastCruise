package com.lastcruise.model;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

  private final Map <String, Item> inventory;

  public Inventory() {

    this.inventory = new HashMap<> () ;
  }

  public void add(String itemName, Item item) {

    inventory.putIfAbsent(itemName, item);

  }
  public Item remove(String itemName) throws InventoryEmptyException {

    if(!inventory.containsKey(itemName)){
      throw new InventoryEmptyException();

    }
     return inventory.remove(itemName);

  }



  public Map<String, Item> getInventory() {
    return inventory;
  }

  public static class InventoryEmptyException extends Throwable {

  }
}
