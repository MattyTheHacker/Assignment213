import java.util.Map;
import java.util.LinkedHashMap;

public class Location {
    private final int locationId;
    private final String description;
    private final LinkedHashMap<String, Integer> exits = new LinkedHashMap<>();

    public Location(int locationId, String description, Map<String, Integer> exits) {
        this.locationId = locationId;
        this.description = description;
        this.exits.putAll(exits);

        if (exits.isEmpty()) {
            this.exits.put("Q", 0);
        }
    }

    protected void addExit(String direction, int location) {
        exits.put(direction, location);
    }

    public int getLocationId() {
        return locationId;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Integer> getExits() {
        return exits;
    }
}