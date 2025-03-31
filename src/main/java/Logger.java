import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

// Singleton Logger class
public class Logger {
    private static Logger instance;
    private PrintWriter writer;
    private String fileName;

    private Logger() {
        this.fileName = "default_log.txt";
        System.out.println("Logger initialized with file: " + fileName);
        openFile();
    }

    public static synchronized Logger getInstance() {
        if (instance == null) {
            System.out.println("Creating new Logger instance");
            instance = new Logger();
        } else {
            System.out.println("Returning existing Logger instance");
        }
        return instance;
    }

    private void openFile() {
        try {
            System.out.println("Opening log file: " + fileName);
            writer = new PrintWriter(new FileWriter(fileName, true));
        } catch (IOException e) {
            System.err.println("Error opening log file: " + e.getMessage());
        }
    }

    public synchronized void setFileName(String newFileName) {
        if (newFileName == null || newFileName.isEmpty()) {
            System.err.println("Invalid file name.");
            return;
        }
        System.out.println("Changing log file to: " + newFileName);
        close();
        this.fileName = newFileName;
        openFile();
    }

    public synchronized void write(String message) {
        if (writer != null) {
            System.out.println("Writing to log: " + message);
            writer.println(message);
            writer.flush();
        } else {
            System.err.println("Logger is not initialized properly.");
        }
    }

    public synchronized void close() {
        if (writer != null) {
            System.out.println("Closing log file: " + fileName);
            writer.close();
        }
    }
}
