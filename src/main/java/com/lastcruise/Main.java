package com.lastcruise;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lastcruise.controller.Controller;
import com.lastcruise.model.AllSounds;
import com.lastcruise.model.Music;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

public class Main {

  public static void main(String[] args) throws IOException {

      Controller controller = new Controller();
      boolean runGame = controller.gameSetUp();

      URL backgroundMusic2 = Main.class.getResource(AllSounds.ALL_SOUNDS.get("main"));
      Music.runAudio(backgroundMusic2);

      while(runGame){
        runGame = controller.getCommand();
        controller.updateView();
      }
      System.out.println("Thanks for playing!");
  }
}
