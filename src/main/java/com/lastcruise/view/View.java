package com.lastcruise.view;

public class View {


  public String getGameBanner() {
    return "\n"
          + "████████╗██╗░░██╗███████╗  ██╗░░░░░░█████╗░░██████╗████████╗  ░█████╗░██████╗░██╗░░░██╗██╗░██████╗███████╗\n"
          + "╚══██╔══╝██║░░██║██╔════╝  ██║░░░░░██╔══██╗██╔════╝╚══██╔══╝  ██╔══██╗██╔══██╗██║░░░██║██║██╔════╝██╔════╝\n"
          + "░░░██║░░░███████║█████╗░░  ██║░░░░░███████║╚█████╗░░░░██║░░░  ██║░░╚═╝██████╔╝██║░░░██║██║╚█████╗░█████╗░░\n"
          + "░░░██║░░░██╔══██║██╔══╝░░  ██║░░░░░██╔══██║░╚═══██╗░░░██║░░░  ██║░░██╗██╔══██╗██║░░░██║██║░╚═══██╗██╔══╝░░\n"
          + "░░░██║░░░██║░░██║███████╗  ███████╗██║░░██║██████╔╝░░░██║░░░  ╚█████╔╝██║░░██║╚██████╔╝██║██████╔╝███████╗\n"
          + "░░░╚═╝░░░╚═╝░░╚═╝╚══════╝  ╚══════╝╚═╝░░╚═╝╚═════╝░░░░╚═╝░░░  ░╚════╝░╚═╝░░╚═╝░╚═════╝░╚═╝╚═════╝░╚══════╝\n";
  }

  public String getStory() {
    return "Board the Maximus Ship, and journey into an exciting world.\n"
          + "The Maximus will hit an iceberg but you will survive the shipwreck.\n"
          + "Swim to a nearby island where you have to rely on your navigation skills, strategy and survival instinct to survive and escape the island.  \n"
          + "The only way to escape the island and win the game is to explore the island, gather useful items, return to the beach, and build a raft.\n";
  }

  public String getHelpCommands() {
    return "Help Commands: \n"
          + "To navigate type : 'go [north, south, east, west]' \n"
          + "To grab items type: 'grab [item name]'\n"
          + "To craft an item type: 'craft [item name]'\n"
          + "To inspect an item type: 'inspect [item name]'\n";
  }

  public String getInstructions() {
    return "INSTRUCTIONS: Explore the island and find a way to escape! Type 'help' at anytime to see a list of available commands. You can 'quit' at anytime\n";
  }

  public String getStoryIntro() {
    return "Welcome aboard %s. We are expecting to arrive in a few hours.\n\n"
        + "A few hours later......\n\n\n"
        + "Captain: Attention Passengers! There is a bad storm a head of us. We can't turn the ship around\n"
        + "Captain: MAYDAY! MAYDAY! MAYDAY!\n"
        + "Shipwreck! You jump in the water and swim to the nearest land\n\n";

  }

  public String getStatusBanner() {
    return "_________________________________________________________%n"
          + "Location: %s          Inventory: %s%n";
  }

  public String getNamePrompt() {
    return "Captain : Welcome Aboard Ship Maximus! May I have your name : ";
  }
}