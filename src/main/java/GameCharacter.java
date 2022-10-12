public abstract class GameCharacter {
    String name;
    GameLocation currentLocation;

    // Constructors
    public GameCharacter(String name, GameLocation currentLocation) {
        this.name = name;
        this.currentLocation = currentLocation;
    }


    // Accessors
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
