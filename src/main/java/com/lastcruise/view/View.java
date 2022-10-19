package com.lastcruise.view;

import com.lastcruise.model.GameText;
import java.util.Map;

public class View {

  private final Map<String, String> GAME_TEXT;

  public View() {
    GameText gameText = new GameText();
    GAME_TEXT = gameText.getGameText();
  }

  public void printGameBanner() {
    System.out.println(GAME_TEXT.get("Banner"));
  }

  public void printStory() {
    System.out.println(GAME_TEXT.get("Intro"));

  }


  public void printHelpCommands() {
    System.out.println(GAME_TEXT.get("Help"));
  }


  public void printInstructions() {
    System.out.println(GAME_TEXT.get("Instructions"));
  }


  public void printStoryIntro(String name) {
    System.out.printf(GAME_TEXT.get("StoryIntro"), name);
  }

  public void printNamePrompt() {
    System.out.print(GAME_TEXT.get("NamePrompt"));
  }

  public void printStartGamePrompt() {
    System.out.print(GAME_TEXT.get("StartGamePrompt"));
  }

  public void printStatusBanner(String location, String inventory, String locationDesc,
      String locationItems, String message) {
    System.out.printf(GAME_TEXT.get("Status"), location, inventory, locationDesc, locationItems,
        message);
//    System.out.printf(
//              "______________________________________________________________________________________________________%n"
//            + "Location: %s                                 Inventory: %s%n"
//            + "______________________________________________________________________________________________________%n%n"
//            + "Description: %s %n"
//            + "Location Items: %s %n%n"
//            + "%s%n"
//            + "%n> ", location, inventory, locationDesc, locationItems, message);
  }

  //------------VIEW MESSAGES------------------------------------------
  public String getItemDescription(String description) {
    return String.format(GAME_TEXT.get("ItemDescription"), description);
  }

  public String getInvalidItemMessage() {
    return GAME_TEXT.get("ItemNotFound");
  }

  public String getInvalidCommandMessage() {
    return GAME_TEXT.get("InvalidCommand");
  }

  public String getInvalidLocationMessage() {
    return GAME_TEXT.get("InvalidLocation");
  }

  public String getSuccesfulRaftBuildMessage() {
    return GAME_TEXT.get("BuildSuccessful");
  }

  public String getNotSuccesfulRaftBuildMessage() {
    return GAME_TEXT.get("BuildNotSuccessful");
  }

  public String getNotInRaftLocationBuildMessage() {
    return GAME_TEXT.get("InvalidCraftingLocation");
  }

  public String getHelpCommands() {
    return GAME_TEXT.get("Help");
  }

  public String cantGrabItem() {
    return GAME_TEXT.get("CantGrabItem");
  }

  public void clearConsole() {
    for (int i = 0; i < 50; i++) {
      System.out.println();
    }

  }
}