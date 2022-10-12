public abstract class GameCharacter {
    String name;
//    GameLocation currentLocation;

    public GameCharacter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
