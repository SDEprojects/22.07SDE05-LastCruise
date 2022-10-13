import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  public static void main(String[] args) throws IOException {

      Controller controller = new Controller();
      boolean runGame = controller.gameSetUp();

      while(runGame){

        runGame = controller.getCommand();
      }
      System.out.println("Thanks for playing!");
  }




}
