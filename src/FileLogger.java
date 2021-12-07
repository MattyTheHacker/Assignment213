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
            e.printStackTrace();
        }
    }

    @Override
    public void log(String message) {
        try {
            FileWriter fw = new FileWriter(FILE_LOGGER_NAME, true);
            fw.write(message);
            fw.write(System.lineSeparator());
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
