import java.util.List;

public abstract class GameLocation {
    String locationName;
    String locationDescription;

    private List<Item> locationItems;

    // Constructors

    public GameLocation(String locationName, String locationDescription) {
        this.locationName = locationName;
        this.locationDescription = locationDescription;
    }

    // Accessors
    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public List<Item> getLocationItems() {
        return locationItems;
    }
}
