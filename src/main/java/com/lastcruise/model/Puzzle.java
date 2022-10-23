package com.lastcruise.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.Map;

public class Puzzle {

  Map<String, String> puzzleText;

  public Puzzle() {
    puzzleText = generatePuzzleText();
  }

  private Map<String, String> generatePuzzleText() {

    ObjectMapper mapper = new ObjectMapper();

    try (InputStream jsonPuzzleText = Thread.currentThread().getContextClassLoader()
        .getResourceAsStream("json/puzzle.json")) {
      puzzleText = mapper.readValue(jsonPuzzleText, new TypeReference<>() {
      });
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }

    return puzzleText;
  }

  public Map<String, String> getPuzzleText() {
    return puzzleText;
  }


}
