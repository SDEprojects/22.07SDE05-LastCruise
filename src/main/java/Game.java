import java.util.List;

public class Game {
    GameCharacter player;
    GameMap gameMap;

    public Game(String playerName, List<GameLocation> level) {
        this.player = new Player(playerName);
        this.gameMap = new GameMap(level);
    }
}
