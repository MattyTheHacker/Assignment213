import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Logger {
    private static final String FILE_LOGGER_NAME = "StudentFileOutput.txt";

    static {
        try {
            File output = new File(FILE_LOGGER_NAME);
            boolean deleted = output.delete();
            boolean created = output.createNewFile();
        } catch (IOException e) {
            System.out.println("[WARN] The new file could not be created...");
        }
    }

    @Override
    public void log(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_LOGGER_NAME, true))) {
            writer.write(message);
            writer.write(System.lineSeparator());
        } catch (IOException e) {
            System.out.println("[WARN] An error prevented the message: " + message + " from being written...");
        }
    }
}