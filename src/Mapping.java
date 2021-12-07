import java.io.Console;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Mapping {

    public static final int INITIAL_LOCATION = 95;

    /**
     * TODO
     * create a static LocationMap object
     */
    private static LocationMap locmap = new LocationMap();
    private static HashMap<String, String> vocabulary = new HashMap<>();
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

        while (true) { // nah fuck that

            /** TODO
             * get the location and print its description to both console and file
             * use the FileLogger and ConsoleLogger objects
             */

            /** TODO
             * verify if the location is exit
             */

            /** TODO
             * get a map of the exits for the location
             */

            /** TODO
             * print the available exits (to both console and file)
             * crosscheck with the ExpectedOutput files
             * Hint: you can use a StringBuilder to append the exits
             */

            /** TODO
             * input a direction
             * ensure that the input is converted to uppercase
             */

            /** TODO
             * are we dealing with a letter / word for the direction to go to?
             * available inputs are: a letter(the HashMap value), a word (the HashMap key), a string of words that contains the key
             * crosscheck with the ExpectedInput and ExpectedOutput files for examples of inputs
             * if the input contains multiple words, extract each word
             * find the direction to go to using the vocabulary mapping
             * if multiple viable directions are specified in the input, choose the last one
             */

            /** TODO
             * if user can go in that direction, then set the location to that direction
             * otherwise print an error message (to both console and file)
             * check the ExpectedOutput files
             */
        }
    }

    public static void main(String[] args) { // Maybe done ????
        Mapping mapping = new Mapping();
        mapping.mapping();
    }
}
