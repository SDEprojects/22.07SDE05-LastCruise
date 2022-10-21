package com.lastcruise.model;

import com.lastcruise.model.Inventory.InventoryEmptyException;

public class Player extends GameCharacter {

    private int stamina = 100;

    public Player() {
    }

    public Player(String name) {
        super(name);
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public void reduceStaminaMove() throws NoEnoughStaminaException {
        int energy = 15;
        if (hasEnoughStamina(energy)) {
            stamina -= energy;
        } else {
            throw new NoEnoughStaminaException();
        }
    }

    public void reduceStaminaPickUp() throws NoEnoughStaminaException {
        int energy = 25;
        if (hasEnoughStamina(energy)) {
            stamina -= energy;
        } else {
            throw new NoEnoughStaminaException();
        }
    }

    private boolean hasEnoughStamina(int energy) {
        return stamina - energy >= 0;
    }

    public void consumeItem(String item) throws ItemNotEdibleException, InventoryEmptyException {
        if(!GameItems.GAME_ITEMS_HASHMAP.containsKey(item)){
            throw new ItemNotEdibleException();
        }
        Item food = GameItems.GAME_ITEMS_HASHMAP.get(item);
        if (food.getEdible()) {
            this.getInventory().remove(food.getName());
            stamina += 50;
        } else {
            throw new ItemNotEdibleException();
        }
    }

    public void sleep(){
        stamina = 100;
    }


    public static class NoEnoughStaminaException extends Throwable {

    }

    public static class ItemNotEdibleException extends Throwable {

    }
}
