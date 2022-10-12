import java.util.List;

public abstract class GameLocation {
    String locationName;
    String locationDescription;
    private List<Item> locationItems;

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
