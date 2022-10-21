package com.lastcruise.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lastcruise.model.Game;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
public class GameLoader {
    public void saveGame(Game game) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("saved-data.json"), game);
        System.out.println("saved");
    }
    public Game loadGame() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Game savedGame = mapper.readValue(Paths.get("saved-data.json").toFile(), Game.class);
        System.out.println("Game Loaded");
        return savedGame;
    }
}