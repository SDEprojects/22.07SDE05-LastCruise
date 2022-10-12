import java.util.List;

public class Game {
    GameCharacter player;
    GameMap gameMap;

    public Game(String playerName, List<GameLocation> locations) {
        GameLocation startingLocation = locations.get(0);
        this.player = new Player(playerName, startingLocation);
        this.gameMap = new GameMap(locations);
    }
}
