public class View {

  private final String gameBanner = "\n"
      + "████████╗██╗░░██╗███████╗  ██╗░░░░░░█████╗░░██████╗████████╗  ░█████╗░██████╗░██╗░░░██╗██╗░██████╗███████╗\n"
      + "╚══██╔══╝██║░░██║██╔════╝  ██║░░░░░██╔══██╗██╔════╝╚══██╔══╝  ██╔══██╗██╔══██╗██║░░░██║██║██╔════╝██╔════╝\n"
      + "░░░██║░░░███████║█████╗░░  ██║░░░░░███████║╚█████╗░░░░██║░░░  ██║░░╚═╝██████╔╝██║░░░██║██║╚█████╗░█████╗░░\n"
      + "░░░██║░░░██╔══██║██╔══╝░░  ██║░░░░░██╔══██║░╚═══██╗░░░██║░░░  ██║░░██╗██╔══██╗██║░░░██║██║░╚═══██╗██╔══╝░░\n"
      + "░░░██║░░░██║░░██║███████╗  ███████╗██║░░██║██████╔╝░░░██║░░░  ╚█████╔╝██║░░██║╚██████╔╝██║██████╔╝███████╗\n"
      + "░░░╚═╝░░░╚═╝░░╚═╝╚══════╝  ╚══════╝╚═╝░░╚═╝╚═════╝░░░░╚═╝░░░  ░╚════╝░╚═╝░░╚═╝░╚═════╝░╚═╝╚═════╝░╚══════╝\n";

  private final String instructions = "INSTRUCTIONS: Explore the island and find a way to escape! Type 'help' at anytime to see a list of available commands. You can 'quit' at anytime\n";

  private final String story = "Board the Maximus Ship, and journey into an exciting world.\n"
      + "The Maximus will hit an iceberg but you will survive the shipwreck.\n"
      + "Swim to a nearby island where you have to rely on your navigation skills, strategy and survival instinct to survive and escape the island.  \n"
      + "The only way to escape the island and win the game is to explore the island, gather useful items, return to the beach, and build a raft.\n";

  private final String helpCommands = "Help Commands: \n"
      + "To navigate type : 'go [north, south, east, west]' \n"
      + "To grab items type: 'grab [item name]'\n"
      + "To craft an item type: 'craft [item name]'\n"
      + "To inspect an item type: 'inspect [item name]'\n"
      ;

  private final String namePrompt = "Captain : Welcome Aboard Ship Maximus! May I have your name : ";
  private final String storyIntro = "Welcome aboard %s. We are expecting to arrive in a few hours.\n\n";
  private final String storyIntro2 = "A few hours later......\n\n\n"
      + "Captain: Attention Passengers! There is a bad storm a head of us. We can't turn the ship around\n"
      + "Captain: MAYDAY! MAYDAY! MAYDAY!\n"
      + "Shipwreck! You jump in the water and swim to the nearest land\n\n";

  private final String statusBanner = "_________________________________________________________%n"
                                      + "Location: %s          Inventory: %s%n";


  public String getGameBanner() {
    return gameBanner;
  }

  public String getStory() {
    return story;
  }

  public String getHelpCommands() {
    return helpCommands;
  }

  public String getInstructions() {
    return instructions;
  }

  public String getStoryIntro() {
    return storyIntro+storyIntro2;
  }

  public String getStatusBanner() {
    return statusBanner;
  }

  public String getNamePrompt() {
    return namePrompt;
  }
}
