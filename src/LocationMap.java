import java.io.*;
import java.util.*;

//class that behaves like a map
public class LocationMap implements Map<Integer, Location> {

    private static final String LOCATIONS_FILE_NAME = "locations.txt";
    private static final String DIRECTIONS_FILE_NAME = "directions.txt";

    private static HashMap<Integer, Location> locations = new HashMap<Integer, Location>();

    transient int size;


    /** DONE
     * create a static locations HashMap
     */
    static {
        /** TODO
         * create a FileLogger object
         */

        /** TODO
         * create a ConsoleLogger object
         */
        ConsoleLogger clog = new ConsoleLogger();

        /** TODO
         *          * Read from LOCATIONS_FILE_NAME so that a user can navigate from one location to another
         *          * use try-with-resources/catch block for the FileReader
         *          * extract the location and the description on each line
         *          * print all locations and descriptions to both console and file
         *          * check the ExpectedOutput files
         *          * put each location in the locations HashMap using temporary empty hashmaps for exits
         */

        /**TODO
         * Read from DIRECTIONS_FILE_NAME so that a user can move from A to B, i.e. current location to next location
         * use try-with-resources/catch block for the FileReader
         * extract the 3 elements  on each line: location, direction, destination
         * print all locations, directions and destinations to both console and file
         * check the ExpectedOutput files
         * for each location, create a new location object and add its exit
         */

    }

    /**
     * TODO
     * implement all methods for Map
     *
     * @return
     */
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
    public Location get(Object key) {
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
        //TODO
        return null;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {
        //TODO
    }

    @Override
    public void clear() {
        size = 0;
        this.keySet().removeAll(this.keySet());
    }

    @Override
    public Set<Integer> keySet() {
        Set<Integer> setOfKeys = new HashSet<>();
        Integer[] keys = new Integer[size() + 1];
        this.forEach((k, v) -> keys[k] = k);
        Collections.addAll(setOfKeys, keys);
        return setOfKeys;
    }

    @Override
    public Collection<Location> values() {
        Set<Location> setOfValues = new HashSet<>();
        Location[] arrayOfValues = new Location[size() + 1];
        this.forEach((k, v) -> arrayOfValues[k] = v);
        Collections.addAll(setOfValues, arrayOfValues);
        return setOfValues;
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
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
