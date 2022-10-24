package com.lastcruise.model;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class PuzzleClient {

  private final int MAX;
  private final Map<String, List> PUZZLE_TEXT;


  public PuzzleClient() {
    Puzzle puzzle = new Puzzle();
    PUZZLE_TEXT = puzzle.getPuzzleText();
    MAX = PUZZLE_TEXT.size();
  }


  public boolean puzzleGenerator(){
    Random r =  new Random();
    boolean correctAnswer;
    int randQuestionNumber = r.nextInt(MAX);
    String stringValueOfRandomQuestionNumber = String.valueOf(randQuestionNumber);

    String question = (String) PUZZLE_TEXT.get(stringValueOfRandomQuestionNumber).get(0);
    String answerWord = (String) PUZZLE_TEXT.get(stringValueOfRandomQuestionNumber).get(1);
    String answerLetter = (String) PUZZLE_TEXT.get(stringValueOfRandomQuestionNumber).get(2);

    System.out.println("Puzzle:" + randQuestionNumber);
    System.out.println(question);
    Scanner playerResponse = new Scanner(System.in);
    System.out.print("Type your response here: ");
    String answer = playerResponse.nextLine().toUpperCase().trim();
    correctAnswer = answer.equalsIgnoreCase(answerWord) ||answer.equalsIgnoreCase( answerLetter);
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
