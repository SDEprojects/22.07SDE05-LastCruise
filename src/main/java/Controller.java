import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Controller {
  View view = new View();
  private static final String LEVEL = "1";
  String name;
  public boolean gameSetUp(){
    String input = null;
    boolean start = false;
    System.out.println(view.getGameBanner());
    System.out.println(view.getStory());
    System.out.println(view.getHelpCommands());
    System.out.println(view.getInstructions());
    try{
      System.out.println("Would you like to start the game? enter ('yes' or 'no') ");
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      input = reader.readLine();
      input.toLowerCase().trim();
      if (input.equals("yes")){
        start = true;
        getPlayerName();
      }
    }catch (IOException e){
      throw new RuntimeException(e);
    }
    return start;
  }
  public void getPlayerName(){
    try{
      System.out.println(view.getNamePrompt());
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      name = reader.readLine();
      name.trim();
      System.out.printf(view.getStoryIntro(), name);

    }catch (IOException e){
      throw new RuntimeException(e);
    }
  }

  public boolean getCommand(){
    System.out.printf(view.getStatusBanner(), "Test Current Location", "[Test Item]");
    String[] command;
    String input = null;
    try{
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      input = reader.readLine();
      input = input.toLowerCase().trim();
      command = input.split("\\s+");

    }catch (IOException e){
      throw new RuntimeException(e);
    }
    if(command[0].equals("help")){
      System.out.println(view.getHelpCommands());
    }
    return !command[0].equals("quit");
  }


}
