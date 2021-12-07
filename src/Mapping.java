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
        vocabulary.put("Q", "QUIT");
        vocabulary.put("U", "UP");
        vocabulary.put("D", "DOWN");
        vocabulary.put("N", "NORTH");
        vocabulary.put("E", "EAST");
        vocabulary.put("S", "SOUTH");
        vocabulary.put("W", "WEST");
        vocabulary.put("NE", "NORTHEAST");
        vocabulary.put("NW", "NORTHWEST");
        vocabulary.put("SE", "SOUTHEAST");
        vocabulary.put("SW", "SOUTHWEST");
    }

    public void mapping() {
        Scanner stdIn = new Scanner(System.in);
        int currentLocation = INITIAL_LOCATION;

        while (true) {
            Location currentLoc = locmap.get(currentLocation);

            String msg = (currentLoc.getLocationId() + currentLoc.getDescription());
            flog.log(msg);
            clog.log(msg);

            if (currentLoc.getLocationId() == 141 || currentLoc.getLocationId() == 0) {
                break;
            }

            Map<String, Integer> currentExits = currentLoc.getExits();

            StringBuilder sb = new StringBuilder();
            sb.append("Available exits are ");
            currentExits.forEach((k, v) -> sb.append(k).append(", "));
            msg = sb.toString();
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
                currentLocation = currentExits.get(intendedDirection);
            }
        }
    }

    public static void main(String[] args) { // Maybe done ????
        Mapping mapping = new Mapping();
        mapping.mapping();
    }
}
