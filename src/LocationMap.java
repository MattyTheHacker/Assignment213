import java.io.*;
import java.util.*;

//class that behaves like a map
public class LocationMap implements Map<Integer, Location> {

    private static final String LOCATIONS_FILE_NAME = "locations.txt";
    private static final String DIRECTIONS_FILE_NAME = "directions.txt";

    private static HashMap<Integer, Location> locations = new HashMap<>();


    /** DONE
     * create a static locations HashMap
     */
    static {
        /** DONE
         * create a FileLogger object
         */
        FileLogger flog = new FileLogger();

        /** DONE
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
    public int size() { // TODO: FIX
        return this.size();
    }

    @Override
    public boolean isEmpty() { // DONE
        return this.size() == 0;
    }

    @Override
    public boolean containsKey(Object key) { // DONE
        return this.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) { // DONE
        return this.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return this.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return this.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return this.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {
        this.putAll(m);
    }

    @Override
    public void clear() {
        this.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return this.keySet();
    }

    @Override
    public Collection<Location> values() {
        return this.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return entrySet();
    }
}
