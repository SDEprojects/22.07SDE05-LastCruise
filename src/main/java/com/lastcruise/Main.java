package com.lastcruise;

import com.lastcruise.controller.Controller;
import java.io.IOException;

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
