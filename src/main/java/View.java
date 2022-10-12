public class View {

  private final String gameBanner = "\n"
      + "████████╗██╗░░██╗███████╗  ██╗░░░░░░█████╗░░██████╗████████╗  ░█████╗░██████╗░██╗░░░██╗██╗░██████╗███████╗\n"
      + "╚══██╔══╝██║░░██║██╔════╝  ██║░░░░░██╔══██╗██╔════╝╚══██╔══╝  ██╔══██╗██╔══██╗██║░░░██║██║██╔════╝██╔════╝\n"
      + "░░░██║░░░███████║█████╗░░  ██║░░░░░███████║╚█████╗░░░░██║░░░  ██║░░╚═╝██████╔╝██║░░░██║██║╚█████╗░█████╗░░\n"
      + "░░░██║░░░██╔══██║██╔══╝░░  ██║░░░░░██╔══██║░╚═══██╗░░░██║░░░  ██║░░██╗██╔══██╗██║░░░██║██║░╚═══██╗██╔══╝░░\n"
      + "░░░██║░░░██║░░██║███████╗  ███████╗██║░░██║██████╔╝░░░██║░░░  ╚█████╔╝██║░░██║╚██████╔╝██║██████╔╝███████╗\n"
      + "░░░╚═╝░░░╚═╝░░╚═╝╚══════╝  ╚══════╝╚═╝░░╚═╝╚═════╝░░░░╚═╝░░░  ░╚════╝░╚═╝░░╚═╝░╚═════╝░╚═╝╚═════╝░╚══════╝\n";

  private final String instructions = "INSTRUCTIONS: Explore the island and find a way to escape! Type 'help' at anytime to see a list of available commands\n";
  private final String helpCommands = "Help Commands: \n"
      + "To navigate type : 'go [north, south, east, west]' \n"
      + "To grab items type: 'grab [item name]'\n"
      + "To craft an item type: 'craft [item name]'\n"
      + "To inspect an item type: 'inspect [item name]'\n"
      ;

  private final String namePrompt = "Captain : Welcome Aboard Ship Maximus! May I have your name : ";
  private final String storyIntro = "Welcome aboard %s. We are expecting to arrive in a few hours.\n";
  private final String storyIntro2 = "A few hours later......\n"
      + "Captain: Attention Passengers! There is a bad storm a head of us. We can't turn the ship around\n"
      + "Captain: MAYDAY! MAYDAY! MAYDAY!\n"
      + "Shipwreck! You jump in the water and swim to the nearest land";

  private final String statusBanner = "Location: %s    Inventory: %s";


  public String getGameBanner() {
    return gameBanner;
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
