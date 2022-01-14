import java.io.*;
import java.util.*;

public class LocationMap implements Map<Integer, Location> {

    private static final String LOCATIONS_FILE_NAME = "locations.txt";
    private static final String DIRECTIONS_FILE_NAME = "directions.txt";
    private static final HashMap<Integer, Location> locations = new HashMap<>();

    static {
        FileLogger flog = new FileLogger();
        ConsoleLogger clog = new ConsoleLogger();
        StringBuilder availableLocations = new StringBuilder();
        StringBuilder availableDirections = new StringBuilder();

        availableDirections.append("Available directions:").append(System.lineSeparator());
        availableLocations.append("Available locations:").append(System.lineSeparator());

        try (BufferedReader reader = new BufferedReader(new FileReader(LOCATIONS_FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int idSeparator = line.indexOf(",");
                int locationId = Integer.parseInt(line.substring(0, idSeparator));
                String description = line.substring(idSeparator + 1);
                Location loc = new Location(locationId, description, new HashMap<>());
                locations.put(locationId, loc);
                availableLocations.append(locationId).append(": ").append(description).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(DIRECTIONS_FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int locationId;
                String direction;
                int destination;
                String[] data = line.split(",");
                locationId = Integer.parseInt(data[0]);
                direction = data[1];
                destination = Integer.parseInt(data[2]);

                availableDirections.append(locationId).append(": ").append(direction)
                        .append(": ").append(destination).append(System.lineSeparator());

                locations.get(locationId).addExit(direction, destination);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        availableDirections.deleteCharAt(availableDirections.length() - 1);
        availableLocations.deleteCharAt(availableLocations.length() - 1);
        flog.log(availableLocations.toString());
        clog.log(availableLocations.toString());
        flog.log(availableDirections.toString());
        clog.log(availableDirections.toString());
    }

    @Override
    public int size() { // DONE
        return locations.size();
    }

    @Override
    public boolean isEmpty() { // DONE
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) { // DONE
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) { // DONE
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) { // DONE
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) { // DONE
        locations.putAll(m);
    }

    @Override
    public void clear() { // DONE
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() { // DONE
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() { // DONE
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() { // DONE
        return locations.entrySet();
    }
}