package com.lastcruise.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public final class PuzzleClient {

  public boolean generatePuzzle() {

    boolean correctAnswer;

    int min = 1;
    int max = 6;
    int randQuestion = (int) (Math.random() * (max - min + 1) + min);

    // Create a HashMap object called capitalCities
    HashMap<String, String> randomPuzzleAnswerKey = new HashMap<>();
    // Add keys and values (QUestion, Answer)
    randomPuzzleAnswerKey.put("1", "HAST");
    randomPuzzleAnswerKey.put("2", "SEVEN");
    randomPuzzleAnswerKey.put("3", "MEENA");
    randomPuzzleAnswerKey.put("4", "MIRROR");
    randomPuzzleAnswerKey.put("5", "PILLOW");
    randomPuzzleAnswerKey.put("6", "THREE");

    List<String> list = new ArrayList<>();
    try {

      File myObj = new File("src/main/resources/text/puzzle.txt");
      Scanner myReader = new Scanner(myObj);
      list = new ArrayList<>();
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        list.add(data);
      }
      myReader.close();

    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

    System.out.println("Question: " + randQuestion);
    System.out.println(list.get(randQuestion));

    String a = String.valueOf(randQuestion);
    Scanner playerResponse = new Scanner(System.in);
    System.out.print("Enter your response here: ");
    String answer = playerResponse.nextLine();

    //System.out.println("Answer is: " + randomPuzzleAnswerKey.get(a));
    // System.out.println("You are correct");
    //System.out.println("Nice try!!better luck next time");
    correctAnswer = answer.equals(randomPuzzleAnswerKey.get(a));
    System.out.println(correctAnswer);

    return correctAnswer;
  }
}
