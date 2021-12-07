import java.io.*;
import java.util.*;

public class LocationMap implements Map<Integer, Location> {

    private static final String LOCATIONS_FILE_NAME = "locations.txt";
    private static final String DIRECTIONS_FILE_NAME = "directions.txt";

    private static HashMap<Integer, Location> locations = new HashMap<>();

    static {
        FileLogger flog = new FileLogger();
        ConsoleLogger clog = new ConsoleLogger();

        ArrayList<Map<String, Integer>> exitMaps = new ArrayList<>();
        Map<String, Integer> exits = new HashMap<>();

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

                if (exitMaps.size() == locationId - 1) {
                    exits = exitMaps.get(locationId);
                    exits.put(direction, destination);
                } else {
                    exits = new HashMap<>();
                    exits.put(direction, destination);
                    exitMaps.add(exits);
                }
            }
        } catch (IOException e) {
            System.out.println("[ERROR] IO Exception...");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Number Exception...");
            e.printStackTrace();
        }

        ArrayList<Map<Integer, Location>> locs = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(LOCATIONS_FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int idSeparator = line.indexOf(",");
                int locationId = Integer.parseInt(line.substring(0, idSeparator));
                String description = line.substring(idSeparator + 1);
                Location loc = new Location(locationId, description, exitMaps.get(locationId));
                locations.put(locationId, loc);
            }
        } catch (IOException e) {
            System.out.println("[ERROR] IO Exception");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Number Exception...");
            e.printStackTrace();
        }
    }

    transient int size = 0;

    @Override
    public int size() { // DONE
        return size;
    }

    @Override
    public boolean isEmpty() { // DONE
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) { // DONE
        if (!this.isEmpty()) {
            for (var e : this.entrySet()) {
                if (e.getValue() == key) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) { // DONE
        if (!this.isEmpty()) {
            for (var e : this.entrySet()) {
                if (e.getValue() == value) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Location get(Object key) { // DONE
        if (!this.isEmpty()) {
            for (var e : this.keySet()) {
                if (e == key) {
                    return this.get(e);
                }
            }
        }
        return null;
    }

    @Override
    public Location put(Integer key, Location value) {
        // TODO: check if the value exists already, if it does use replace, otherwise putIfAbsent...
        this.putIfAbsent(key, value);
        this.replace(key, value);
        return null;
    }

    @Override
    public Location remove(Object key) {
        //TODO: find the Entry using the key and delete it...
        return null;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) { // DONE
        m.forEach(this::put);
        m.forEach((k, v) -> size++);
    }

    @Override
    public void clear() { // DONE
        this.keySet().removeAll(this.keySet());
        size = 0;
    }

    @Override
    public Set<Integer> keySet() { // DONE
        Set<Integer> setOfKeys = new HashSet<>();
        Integer[] keys = new Integer[size() + 1];
        this.forEach((k, v) -> keys[k] = k);
        Collections.addAll(setOfKeys, keys);
        return setOfKeys;
    }

    @Override
    public Collection<Location> values() { // DONE
        Set<Location> setOfValues = new HashSet<>();
        Location[] arrayOfValues = new Location[size() + 1];
        this.forEach((k, v) -> arrayOfValues[k] = v);
        Collections.addAll(setOfValues, arrayOfValues);
        return setOfValues;
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() { // DONE
        Set<Entry<Integer, Location>> setOfEntries = new HashSet<>();
        Integer[] keys = new Integer[size() + 1];
        Location[] arrayOfValues = new Location[size() + 1];
        Entry[] arrayOfEntries = new Entry[size() + 1];

        this.forEach((k, v) -> keys[k] = k);
        this.forEach((k, v) -> arrayOfValues[k] = v);

        for (int i = 0; i < keys.length; i++) {
            Map.Entry<Integer, Location> entry = new AbstractMap.SimpleEntry<>(keys[i], arrayOfValues[i]);
            arrayOfEntries[i] = entry;
        }

        Collections.addAll(setOfEntries, arrayOfEntries);

        return setOfEntries;
    }
}