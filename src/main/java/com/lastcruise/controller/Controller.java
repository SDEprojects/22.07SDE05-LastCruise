package com.lastcruise.controller;

import com.lastcruise.model.Commands;
import com.lastcruise.model.Game;
import com.lastcruise.view.View;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Controller {

  View view = new View();
  private static final String LEVEL = "1";
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
    view.printStatusBanner(game.getCurrentLocationName(), "[]", game.getCurrentLocationDesc() );
    String[] command;
    String input;
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      input = reader.readLine();
      input = input.toLowerCase().trim();
      command = input.split("\\s+");

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    // CHECKS FOR VALID COMMAND
    if (!isValidCommand(command[0])) {
      view.printInvalidCommandMessage();
      view.printHelpCommands();
    }
    // HELP COMMAND
    if (command[0].equals(Commands.HELP.getValue())) {
      System.out.println(view.getHelpCommands());

    // GO COMMAND
    } else if (command[0].equals(Commands.GO.getValue())) {
      game.moveLocation(command);
    }

    // QUIT COMMAND
    return !command[0].equals(Commands.QUIT.getValue());
  }

  // returns false if command is not found in the Commands enum
  private boolean isValidCommand(String input) {
    for (Commands c : Commands.values()) {
      if (c.getValue().equals(input)) {
        return true;
      }
    }
    return false;
  }
}

