package com.lastcruise.model;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PuzzleClient {

  int min = 1;
  int max = 7;

  private final Map<String, String> PUZZLE_TEXT;


  public PuzzleClient() {
    Puzzle puzzle = new Puzzle();
    PUZZLE_TEXT = puzzle.getPuzzleText();

  }


  public boolean puzzleGenerator(){

    int randQuestionNumber = (int) (Math.random() * (max - min + 1) + min);
    String stringValueOfRandomQuestionNumber = String.valueOf(randQuestionNumber);

    boolean correctAnswer;

    HashMap<String, String> randomPuzzleAnswerKey = new HashMap<>();
    // Add keys and values (QUestion, Answer)
    randomPuzzleAnswerKey.put("1", "HAPT");
    randomPuzzleAnswerKey.put("2", "SEVEN");
    randomPuzzleAnswerKey.put("3", "MEENA");
    randomPuzzleAnswerKey.put("4", "MIRROR");
    randomPuzzleAnswerKey.put("5", "PILLOW");
    randomPuzzleAnswerKey.put("6", "THREE");
    randomPuzzleAnswerKey.put("7", "NINE");

    List<String> list = new ArrayList<>();



    System.out.println("Puzzle:" + randQuestionNumber);
    System.out.println(PUZZLE_TEXT.get(stringValueOfRandomQuestionNumber));
    Scanner playerResponse = new Scanner(System.in);
    System.out.print("Enter your response here,[do not type (a) or (b)]: ");
    String answer = playerResponse.nextLine().toUpperCase().trim();
    correctAnswer = answer.equals(randomPuzzleAnswerKey.get(stringValueOfRandomQuestionNumber));
    System.out.println(correctAnswer);

    return correctAnswer;

  }

  public void puzzlePunishment() throws InterruptedException {


    URL grabSoundUrl = getClass().getResource(
        AllSounds.ALL_SOUNDS.get("pitFall"));
    SoundEffect.runAudio(grabSoundUrl);
    puzzleFailureTimer();
    for (int a = 1; a< 11; a++){
      System.out.println(a +"0"+ " Years later..");
      URL grabSoundUrl1 = getClass().getResource(
          AllSounds.ALL_SOUNDS.get("bell"));
      SoundEffect.runAudio(grabSoundUrl1);
      puzzleFailureTimer();

    }
  }

  public void puzzleFailureTimer() throws InterruptedException {
    Thread.sleep(1500);
  }

}
