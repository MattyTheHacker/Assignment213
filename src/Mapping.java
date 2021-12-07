import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Mapping {

    public static final int INITIAL_LOCATION = 95;
    private static final String INVALID_DIRECTION = "You cannot go in that direction";
    private static LocationMap locmap = new LocationMap();

    private static final HashMap<String, String> vocabulary = new HashMap<>();

    FileLogger flog = new FileLogger();
    ConsoleLogger clog = new ConsoleLogger();

    public Mapping() {
        vocabulary.put("QUIT", "Q");
        vocabulary.put("UP", "U");
        vocabulary.put("DOWN", "D");
        vocabulary.put("NORTH", "N");
        vocabulary.put("EAST", "E");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("WEST", "W");
        vocabulary.put("NORTHEAST", "NE");
        vocabulary.put("NORTHWEST", "NW");
        vocabulary.put("SOUTHEAST", "SE");
        vocabulary.put("SOUTHWEST", "SW");
    }

    public void mapping() {
        Scanner stdIn = new Scanner(System.in);
        int currentLocation = INITIAL_LOCATION;

        while (true) {
            Location currentLoc = locmap.get(currentLocation);

            String msg = (currentLoc.getDescription());
            flog.log(msg);
            clog.log(msg);

            if (currentLoc.getLocationId() == 141 || currentLoc.getLocationId() == 0) {
                break;
            }

            Map<String, Integer> currentExits = currentLoc.getExits();

            StringBuilder availableExits = new StringBuilder();
            availableExits.append("Available exits are ");
            currentExits.forEach((k, v) -> availableExits.append(k).append(", "));
            msg = availableExits.toString();
            flog.log(msg);
            clog.log(msg);

            String usrInput = stdIn.nextLine().toUpperCase();
            String intendedDirection = null;

            if (!usrInput.trim().contains(" ")) {
                if (vocabulary.containsKey(usrInput) || vocabulary.containsValue(usrInput)) {
                    intendedDirection = usrInput;
                }
            } else {
                String[] words = usrInput.split(" ");
                for (String s : words) {
                    if (vocabulary.containsKey(s)) {
                        intendedDirection = s;
                    } else {
                        intendedDirection = null;
                    }
                }
            }
            if (intendedDirection == null) {
                flog.log(INVALID_DIRECTION);
                clog.log(INVALID_DIRECTION);
            } else {
                String newLocation = vocabulary.get(intendedDirection);
                currentLocation = currentExits.get(newLocation);
            }
        }
    }

    public static void main(String[] args) {
        Mapping mapping = new Mapping();
        mapping.mapping();
    }
}
