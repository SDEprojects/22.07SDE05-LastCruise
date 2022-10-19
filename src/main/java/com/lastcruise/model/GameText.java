package com.lastcruise.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.Map;

public class GameText {

  Map<String, String> gameText;

  public GameText() {
    gameText = generateGameText();
  }

  private Map<String, String> generateGameText() {

    ObjectMapper mapper = new ObjectMapper();

    try (InputStream jsonGameText = Thread.currentThread().getContextClassLoader()
        .getResourceAsStream("json/gametext.json")) {
      gameText = mapper.readValue(jsonGameText, new TypeReference<>() {
      });
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
    return gameText;
  }

  public Map<String, String> getGameText() {
    return gameText;
  }
}
