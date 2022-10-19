package com.lastcruise.controller;

import static com.lastcruise.model.Commands.*;

import com.lastcruise.model.Commands;
import com.lastcruise.model.CraftingLocation;
import com.lastcruise.model.Game;
import com.lastcruise.model.GameMap.InvalidLocationException;
import com.lastcruise.model.Inventory;
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
    //  updateView();
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      input = reader.readLine().toLowerCase().trim();
      command = input.split("\\s+");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    // CHECKS FOR VALID COMMAND
    if (!isValidCommand(command)) {
      message = view.getInvalidCommandMessage() + view.getHelpCommands();

      // PROCESS COMMAND
    } else if (command[0].equals(ESCAPE.getValue())) {
      if (game.getCurrentLocationItems().containsKey("raft")) {
        message = "Congratulations! You've escaped this island!";
        return false;
      } else {
        message = "You cannot escape without a raft!";
      }
    } else {
      processCommand2(command);
    }

    // QUIT COMMAND
    return !command[0].equals(QUIT.getValue());
  }


  public void processCommand(String[] command) {
    // HELP COMMAND
    if (command[0].equals(HELP.getValue())) {
      message = view.getHelpCommands();

      // GO COMMAND
    } else if (command[0].equals(GO.getValue())) {

      try {
        game.moveLocation(command);
      } catch (InvalidLocationException e) {
        message = view.getInvalidLocationMessage();
      }

      // INSPECT COMMAND
    } else if (command[0].equals(INSPECT.getValue())) {
      if (game.inspectItem(command) != null) {
        message = view.getItemDescription(game.inspectItem(command));
      }

      // either GRAB or DROP COMMAND
    } else if (command[0].equals(GRAB.getValue()) || command[0].equals(
        DROP.getValue())) {
      var currentLocationInventory = game.getCurrentLocationInventory();
      var playerInventory = game.getPlayerInventory();

      try {
        // GRAB COMMAND
        if (command[0].equals(GRAB.getValue())) {
          // GRABBING LOG
          if (command[1].equals("log") && !playerInventory.getInventory().containsKey("machete")) {
            message = view.cantGrabItem();
          } else {
            game.transferItemFromTo(currentLocationInventory, playerInventory, command[1]);
          }

          // DROP COMMAND
        } else {
          game.transferItemFromTo(playerInventory, currentLocationInventory, command[1]);
        }
      } catch (InventoryEmptyException e) {
        view.getInvalidItemMessage();
      }
    } else if (command[0].equals(CRAFT.getValue())) {
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

      }
    }
  }

  public void processCommand2(String[] command) {

    switch (Commands.valueOf(command[0].toUpperCase())) {

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
      }
      //---- INSPECT -------//
      case INSPECT: {
        if (game.inspectItem(command) != null) {
          message = view.getItemDescription(game.inspectItem(command));
        }
        break;
      }
      //---- GRAB -------//
      case GRAB: {
        var currentLocationInventory = game.getCurrentLocationInventory();
        var playerInventory = game.getPlayerInventory();
        // GRABBING LOG
        if (command[1].equals("log") && !playerInventory.getInventory().containsKey("machete")) {
          message = view.cantGrabItem();
        } else {
          try {
            game.transferItemFromTo(currentLocationInventory, playerInventory, command[1]);
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
      case CRAFT:
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
        }
        break;

      default:
        throw new IllegalStateException("Unexpected value: " + command[0]);
    }
  }

  // returns false if command is not found in the Commands enum
  private boolean isValidCommand(String[] command) {
    boolean valid = false;
    for (Commands c : values()) {
      if (c.getValue().equals(command[0])) {
        valid = true;
        break;
      }
    }
    if ((!command[0].equals(HELP.getValue()) && !command[0].equals(
        QUIT.getValue()) && !command[0].equals(ESCAPE.getValue())) && (command.length < 2)) {
      valid = false;
    }
    return valid;
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

