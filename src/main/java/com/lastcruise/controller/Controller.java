package com.lastcruise.controller;

import static com.lastcruise.model.Commands.*;

import com.lastcruise.model.AllSounds;
import com.lastcruise.model.Commands;
import com.lastcruise.model.CraftingLocation;
import com.lastcruise.model.Game;
import com.lastcruise.model.GameMap.InvalidLocationException;
import com.lastcruise.model.Inventory.InventoryEmptyException;
import com.lastcruise.model.Music;
import com.lastcruise.model.Player.ItemNotEdibleException;
import com.lastcruise.model.Player.NoEnoughStaminaException;
import com.lastcruise.model.PuzzleClient;
import com.lastcruise.model.SoundEffect;
import com.lastcruise.view.View;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;


public class Controller {

    private final View view = new View();

    PuzzleClient puzzleClient = new PuzzleClient();
    private String name;
    private Game game;
    private String message = "";
    private boolean keepPlaying = true;
    private final GameLoader gameLoader = new GameLoader();
    public boolean gameSetUp() {
        String input;
        boolean start = false;
        view.printGameBanner();
        view.printStory();
//        view.printHelpCommands();
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
                System.out.println("enter any key to continue");
                String continueStory = reader.readLine().trim();
                updateView();
            } else if (input.equals("load")) {
                start = true;

                try {
                    game = gameLoader.loadGame();
                } catch (Exception e){
                    view.printCantLoadGame();
                    getPlayerName();
                    view.printHelpCommands();
                    game = new Game(name);
                } finally {
                    updateView();
                }
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

                    if(game.getCurrentLocationName().equals("PIT")) {

                        URL grabSoundUrl = getClass().getResource(
                            AllSounds.ALL_SOUNDS.get("pitFall"));
                        SoundEffect.runAudio(grabSoundUrl);

                        message = view.pitFallPrompt();
                        updateView();
                        updateLocationTimer();
                        message = view.puzzleMessagePrompt();
                        updateView();

                        if(puzzleClient.puzzleGenerator()){
                            message = view.solvedPuzzleMessage();
                        }else
                        {
                            message = view.unSolvedPuzzleMessage();
                            updateView();
                            puzzleClient.puzzlePunishment();
                            message = view.pitFallEscapePrompt();
                        }

                    }
                } catch (InvalidLocationException e) {
                    message = view.getInvalidLocationMessage();

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);

                }catch(NoEnoughStaminaException e){
                    message = view.getNoStaminaToMove();

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
                        URL grabSoundUrl = getClass().getResource(
                            AllSounds.ALL_SOUNDS.get("pickup"));
                        SoundEffect.runAudio(grabSoundUrl);
                    } catch (InventoryEmptyException e) {
                        message = view.getInvalidItemMessage();
                    }catch(NoEnoughStaminaException e){
                        message = view.getNoPickUpStamina();
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
                }catch (NoEnoughStaminaException e){
                    message = view.getNoDropStamina();
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
                            message = view.getSuccessfulRaftBuildMessage();

                        } else {
                            message = view.getNotSuccessfulRaftBuildMessage();
                        }

                    } else {
                        message = view.getNotInRaftLocationBuildMessage();
                    }

                } else {
                    message = view.getItemNotCraftable();
                }
                break;
            }
            case EAT:{
                try{
                    game.eatItem(command[1]);
                    message = view.getEating();
                    URL eatSoundUrl = getClass().getResource(AllSounds.ALL_SOUNDS.get("eat"));
                    SoundEffect.runAudio(eatSoundUrl);
                } catch (InventoryEmptyException e) {
                    message = view.getInvalidItemMessage();
                } catch (ItemNotEdibleException e) {
                    message = view.getCantEatThat();
                }
                break;
            }
            case SLEEP:{
                game.playerSleep();
                message = view.getSleeping();
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
            // MUSIC and SOUND EFFECTS CONTROLS
            case VOLUME:
            case MUSIC: {
                if (command[1].equals("up")) {
                    Music.increaseMusic();
                    break;
                } else if (command[1].equals("down")) {
                    Music.decreaseMusic();
                    break;
                } else if (command[1].equals("off") || command[1].equals("mute")) {
                    Music.muteMusic();
                    break;
                } else if (command[1].equals("on") || command[1].equals("unmute")) {
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
                } else if (command[1].equals("off") || command[1].equals("mute")) {
                    SoundEffect.muteSoundFx();
                    break;
                } else if (command[1].equals("on") || command[1].equals("unmute")) {
                    SoundEffect.unMuteSoundFx();
                    break;
                }
            }

            // SAVING THE GAME
            case SAVE: {
                try {
                    gameLoader.saveGame(game);
                    message = view.getGameSaved();
                } catch (IOException e) {
                    message = view.getGameSaveFailed();
                }
                break;
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
            if (c.name().equals(command[0].toUpperCase())) {
                check = true;
                break;
            }
        }
        if(check){
            switch (Commands.valueOf(command[0].toUpperCase().replaceAll("\\s", ""))) {
                case GO:
                case EAT:
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


    public void updateLocationTimer() throws InterruptedException {
        Thread.sleep(3000);
    }


    public void updateView() {
        view.clearConsole();
        String location = game.getCurrentLocationName();
        String inventory = game.getPlayerInventory().getInventory().keySet().toString();
        String stamina = game.getPlayerStamina();
        String locationDesc = game.getCurrentLocationDesc();
        String locationItems = game.getCurrentLocationItems().keySet().toString();

        view.printStatusBanner(location, stamina, inventory, locationDesc, locationItems, message);
        message = "";
    }
}

