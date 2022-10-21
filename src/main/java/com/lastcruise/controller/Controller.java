package com.lastcruise.controller;

import static com.lastcruise.model.Commands.*;

import com.lastcruise.Main;
import com.lastcruise.model.AllSounds;
import com.lastcruise.model.Commands;
import com.lastcruise.model.CraftingLocation;
import com.lastcruise.model.Game;
import com.lastcruise.model.GameMap.InvalidLocationException;
import com.lastcruise.model.Inventory.InventoryEmptyException;
import com.lastcruise.model.Music;
import com.lastcruise.model.SoundEffect;
import com.lastcruise.view.View;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;


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
            if (command[0].equals("pick") && command[1].equals("up")) {
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

        switch (c) {
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
                Music.muteMusic();
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
                        URL grabSoundUrl = getClass().getResource(
                            AllSounds.ALL_SOUNDS.get("pickup"));
                        SoundEffect.runAudio(grabSoundUrl);
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
                    URL dropSoundUrl = getClass().getResource(AllSounds.ALL_SOUNDS.get("drop"));
                    SoundEffect.runAudio(dropSoundUrl);
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

            // MUSIC CONTROLS
            case VOLUME: {
                if (command[1].equals("up")) {
                    Music.increaseMusic();
                    break;
                } else if (command[1].equals("down")) {
                    Music.decreaseMusic();
                    break;
                } else if (command[1].equals("mute")) {
                    Music.muteMusic();
                    break;
                } else if (command[1].equals("unmute")) {
                    Music.unMuteMusic();
                    break;
                }
            }

            case MUSIC: {
                if (command[1].equals("off")) {
                    Music.muteMusic();
                    break;
                } else if (command[1].equals("on")) {
                    Music.unMuteMusic();
                    break;
                }
            }

            case SOUND: {
                if (command[1].equals("up")) {
                    SoundEffect.increaseFxVolume();
                    break;
                } else if (command[1].equals("down")) {
                    SoundEffect.decreaseFxVolume();
                    break;
                } else if (command[1].equals("off")) {
                    SoundEffect.muteSoundFx();
                    break;
                } else if (command[1].equals("on")) {
                    SoundEffect.unMuteSoundFx();
                    break;
                }
            }
            default:
                message = view.getInvalidCommandMessage();
                break;
        }
    }

    // returns false if command is not found in the Commands enum
    private boolean isValidCommand(String[] command) {
        boolean check = false;
        for (Commands c : values()) {
            if (c.getValue().equals(command[0])) {
                check = true;
                break;
            }
        }
        if(check){
            switch (Commands.valueOf(command[0].toUpperCase().replaceAll("\\s", ""))) {
                case GO:
                case GRAB:
                case PICKUP:
                case TAKE:
                case INSPECT:
                case DROP:
                case CRAFT:
                case BUILD:
                case VOLUME:
                case MUSIC:
                case SOUND:
                    return command.length >= 2;
                default:
                    return true;
            }
        }
        return false;
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

