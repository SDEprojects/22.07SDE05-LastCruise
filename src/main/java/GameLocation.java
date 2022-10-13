import java.util.List;

public abstract class GameLocation {
    String name;
    String description;

    private List<Item> items;

    // Constructors

    public GameLocation(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Accessors
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Item> getItems() {
        return items;
    }
}
