import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {
    View view = new View();
    private static final String LEVEL = "1";

    public void gameSetUp(){
      String name = null;
      System.out.println(view.getGameBanner());

      System.out.println(view.getHelpCommands());
      try{
        System.out.println(view.getNamePrompt());
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        name = reader.readLine();
        name.trim();
        System.out.printf(view.getStoryIntro(), name);

        System.out.println(view.getInstructions());
        System.out.printf(view.getStatusBanner(), "Test Current Location", "[Test Item]");
      }catch (IOException e){
        throw new RuntimeException(e);
      }

    }
}
