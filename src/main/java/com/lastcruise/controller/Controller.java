package com.lastcruise.controller;

import static com.lastcruise.model.Commands.*;

import com.lastcruise.model.Commands;
import com.lastcruise.model.CraftingLocation;
import com.lastcruise.model.Game;
import com.lastcruise.model.GameMap.InvalidLocationException;
import com.lastcruise.model.Inventory.InventoryEmptyException;
import com.lastcruise.view.View;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Controller {

    private final View view = new View();
    private String name;
    private Game game;

    private String message = "";

    private boolean keepPlaying = true;


    public boolean gameSetUp() {
        String input;
        boolean start = false;
        view.printGameBanner();
        view.printStory();
        view.printHelpCommands();
        view.printInstructions();
        try {
            view.printStartGamePrompt();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            input = reader.readLine().trim();
            if (input.equals("yes")) {
                start = true;
                getPlayerName();
                view.printStoryIntro(name);
                game = new Game(name);
                updateView();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return start;
    }


    public void getPlayerName() {
        try {
            view.printNamePrompt();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            name = reader.readLine().trim();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean getCommand() {
        String[] command;
        String input;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            input = reader.readLine().toLowerCase().trim();
            command = input.split("\\s+");
            if(command[0].equals("pick") && command[1].equals("up")){
                command[0] = "pickup";
                command[1] = command[2];
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // CHECKS FOR VALID COMMAND
        if (!isValidCommand(command)) {
            message = view.getInvalidCommandMessage() + view.getHelpCommands();

            // PROCESS COMMAND
        } else {
            processCommand(command);
        }

        return keepPlaying;
    }


    public void processCommand(String[] command) {
        Commands c = Commands.valueOf(command[0].toUpperCase());

        switch (c){
            //---- GO -------//
            case GO: {
                try {
                    game.moveLocation(command);
                } catch (InvalidLocationException e) {
                    message = view.getInvalidLocationMessage();
                }
                break;
            }
            //---- HELP -------//
            case HELP: {
                message = view.getHelpCommands();
                break;
            }
            //---- INSPECT -------//
            case INSPECT: {
                if (game.inspectItem(command) != null) {
                    message = view.getItemDescription(game.inspectItem(command));
                }
                break;
            }
            //---- GRAB -------//
            case PICKUP:
            case TAKE:
            case GRAB: {
                var currentLocationInventory = game.getCurrentLocationInventory();
                var playerInventory = game.getPlayerInventory();
                // GRABBING LOG
                if (command[1].equals("log") && !playerInventory.getInventory()
                    .containsKey("machete")) {
                    message = view.cantGrabItem();
                } else {
                    try {
                        game.transferItemFromTo(currentLocationInventory, playerInventory,
                            command[1]);
                    } catch (InventoryEmptyException e) {
                        message = view.getInvalidItemMessage();
                    }
                }
                break;
            }
            //--- DROP ------//
            case DROP: {
                var currentLocationInventory = game.getCurrentLocationInventory();
                var playerInventory = game.getPlayerInventory();
                try {
                    game.transferItemFromTo(playerInventory,
                        currentLocationInventory,
                        command[1]);
                } catch (InventoryEmptyException e) {
                    message = view.getInvalidItemMessage();
                }
                break;
            }
            //--- CRAFT ---//
            case BUILD:
            case CRAFT: {
                if (command[1].equals("raft")) {
                    if (game.getCurrentLocation() instanceof CraftingLocation) {
                        //Craft raft
                        if (game.craftRaft()) {
                            message = view.getSuccesfulRaftBuildMessage();

                        } else {
                            message = view.getNotSuccesfulRaftBuildMessage();
                        }

                    } else {
                        message = view.getNotInRaftLocationBuildMessage();
                    }

                } else {
                    message = view.getItemNotCraftable();
                }
                break;
            }

            case ESCAPE: {
                if (game.getCurrentLocationItems().containsKey("raft")) {
                    message = view.getYouWonMessage();
                    keepPlaying = false;
                } else {
                    message = view.getCantEscape();
                }
                break;
            }
            case QUIT: {
                keepPlaying = false;
                break;
            }
            default:
                message = view.getInvalidCommandMessage();
                break;
        }
    }

    // returns false if command is not found in the Commands enum
    private boolean isValidCommand(String[] command) {
        for (Commands c : values()) {
            if (c.getValue().equals(command[0])) {
                break;
            }
        }
        switch (Commands.valueOf(command[0].toUpperCase().replaceAll("\\s", ""))) {
            case GO:
            case GRAB:
            case PICKUP:
            case TAKE:
            case INSPECT:
            case DROP:
            case CRAFT:
            case BUILD:
                return command.length >= 2;
            default:
                return true;
        }

    }

    public void updateView() {
        String location = game.getCurrentLocationName();
        String inventory = game.getPlayerInventory().getInventory().keySet().toString();
        String locationDesc = game.getCurrentLocationDesc();
        String locationItems = game.getCurrentLocationItems().keySet().toString();

        view.printStatusBanner(location, inventory, locationDesc, locationItems, message);
        message = "";
    }
}

