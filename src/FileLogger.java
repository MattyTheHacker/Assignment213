import java.io.*;

public class FileLogger implements Logger {
    private static final String FILE_LOGGER_NAME = "StudentFileOutput.txt";

    static {
        try {
            File output = new File(FILE_LOGGER_NAME);

            if (output.delete()) {
                System.out.println("[INFO] File deleted successfully...");
                if (output.createNewFile()) {
                    System.out.println("[INFO] File created successfully...");
                } else {
                    System.out.println("[WARN] The file was not created, but an exception wasn't thrown...");
                }
            } else {
                System.out.println("[WARN] The file couldn't be deleted, but an exception wasn't thrown...");
            }
        } catch (FileNotFoundException e) {
            System.out.println("The File wasn't found...");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("The new file could not be created...");
            e.printStackTrace();
        }

        /** DONE
         * create a new File object for FILE_LOGGER_NAME
         * if the file already exists, delete it first
         * use try/catch block
         */
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
        /** DONE
         * create a new FileWriter in append mode
         * write the message to file
         * check the ExpectedOutput files
         * use try-with-resources/catch block
         */
    }
}
