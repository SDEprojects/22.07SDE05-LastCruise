package com.lastcruise.controller;

import com.lastcruise.model.Commands;
import com.lastcruise.model.Game;
import com.lastcruise.view.View;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Controller {

  private final View view = new View();
  private String name;
  private Game game;


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
      input = reader.readLine();
      input.toLowerCase().trim();
      if (input.equals("yes")) {
        start = true;
        getPlayerName();
        view.printStoryIntro(name);
        game = new Game(name);
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
      name = reader.readLine();
      name.trim();


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
      input = reader.readLine();
      input = input.toLowerCase().trim();
      command = input.split("\\s+");

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    // CHECKS FOR VALID COMMAND
    if (!isValidCommand(command)) {
      view.printInvalidCommandMessage();
      view.printHelpCommands();

    // PROCESS COMMAND
    } else {
      processCommand(command);
    }
    // QUIT COMMAND
    return !command[0].equals(Commands.QUIT.getValue());
  }


  public void processCommand(String[] command) {
      // HELP COMMAND
    if (command[0].equals(Commands.HELP.getValue())) {
      view.printHelpCommands();

      // GO COMMAND
    } else if (command[0].equals(Commands.GO.getValue())) {
      game.moveLocation(command);

      // INSPECT COMMAND
    } else if (command[0].equals(Commands.INSPECT.getValue())) {
      if (game.inspectItem(command) != null) {
        view.printItemDescription(game.inspectItem(command));
      }
    }
  }

  // returns false if command is not found in the Commands enum
  private boolean isValidCommand(String[] command) {
    boolean valid = false;
    for (Commands c : Commands.values()) {
      if (c.getValue().equals(command[0])) {
            valid = true;
      }
    }
    if((!command[0].equals(Commands.HELP.getValue()) &&!command[0].equals(Commands.QUIT.getValue())) && command.length < 2){
            valid = false;
    }
    return valid;
  }

  public void updateView() {
    view.printStatusBanner(game.getCurrentLocationName(), "[]", game.getCurrentLocationDesc(),
        game.getCurrentLocationItems().keySet().toString());

  }
}

