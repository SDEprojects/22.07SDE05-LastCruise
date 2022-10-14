package com.lastcruise.view;

public class View {


  public void printGameBanner(){
    System.out.println("\n"
        + "████████╗██╗░░██╗███████╗  ██╗░░░░░░█████╗░░██████╗████████╗  ░█████╗░██████╗░██╗░░░██╗██╗░██████╗███████╗\n"
        + "╚══██╔══╝██║░░██║██╔════╝  ██║░░░░░██╔══██╗██╔════╝╚══██╔══╝  ██╔══██╗██╔══██╗██║░░░██║██║██╔════╝██╔════╝\n"
        + "░░░██║░░░███████║█████╗░░  ██║░░░░░███████║╚█████╗░░░░██║░░░  ██║░░╚═╝██████╔╝██║░░░██║██║╚█████╗░█████╗░░\n"
        + "░░░██║░░░██╔══██║██╔══╝░░  ██║░░░░░██╔══██║░╚═══██╗░░░██║░░░  ██║░░██╗██╔══██╗██║░░░██║██║░╚═══██╗██╔══╝░░\n"
        + "░░░██║░░░██║░░██║███████╗  ███████╗██║░░██║██████╔╝░░░██║░░░  ╚█████╔╝██║░░██║╚██████╔╝██║██████╔╝███████╗\n"
        + "░░░╚═╝░░░╚═╝░░╚═╝╚══════╝  ╚══════╝╚═╝░░╚═╝╚═════╝░░░░╚═╝░░░  ░╚════╝░╚═╝░░╚═╝░╚═════╝░╚═╝╚═════╝░╚══════╝\n");
  }

  public void printStory(){
    System.out.println("INTRODUCTION:\n"
        + "Board the Maximus Ship, and journey into an exciting world.\n"
        + "The Maximus will hit an iceberg but you will survive the shipwreck.\n"
        + "Swim to a nearby island where you have to rely on your navigation skills, strategy and survival instinct to survive and escape the island.  \n"
        + "The only way to escape the island and win the game is to explore the island, gather useful items, return to the beach, and build a raft.\n");
  }

  public String getHelpCommands() {
    return "Help Commands: \n"
          + "To navigate type : 'go [north, south, east, west]' \n"
          + "To grab items type: 'grab [item name]'\n"
          + "To craft an item type: 'craft [item name]'\n"
          + "To inspect an item type: 'inspect [item name]'\n";
  }

  public void printHelpCommands(){
    System.out.println("Help Commands: \n"
        + "To navigate type : 'go [north, south, east, west]' \n"
        + "To grab items type: 'grab [item name]'\n"
        + "To craft an item type: 'craft [item name]'\n"
        + "To inspect an item type: 'inspect [item name]'\n");
  }


  public void printInstructions(){
    System.out.println("INSTRUCTIONS: Explore the island and find a way to escape! Type 'help' at anytime to see a list of available commands. You can 'quit' at anytime\n");
  }


  public void printStoryIntro(String name){
    System.out.printf("Welcome aboard %s. We are expecting to arrive in a few hours.%n%n"
        + "A few hours later......%n%n%n"
        + "Captain: Attention Passengers! There is a bad storm a head of us. We can't turn the ship around%n"
        + "Captain: MAYDAY! MAYDAY! MAYDAY!%n"
        + "Shipwreck! You jump in the water and swim to the nearest land%n%n", name);
  }


  public void printStatusBanner(String location, String inventory, String locationDesc, String locationItems){
    System.out.printf("_________________________________________________________%n"
                      + "Location: %s          Inventory: %s%n%n"
                      + "Description: %s %n"
                      + "Location Items: %s %n"
                      + "%n>", location, inventory, locationDesc, locationItems);
  }

  public void printItemDescription(String description){
    System.out.printf("Item Description: %s", description);
  }

  public void printNamePrompt(){
    System.out.print("\nCaptain : Welcome Aboard Ship Maximus! May I have your name: ");
  }

  public void printStartGamePrompt(){
    System.out.print("Would you like to start the game? enter ('yes' or 'no') ");
  }

  public void printInvalidCommandMessage(){
    System.out.println("Invalid command entered");
  }

  public void clearConsole(){
    for(int i =0; i< 50; i++){
      System.out.println();
    }

  }
}