package Utilities;

import java.io.FileWriter;
import java.io.IOException;

/**
 * FileIOUtilClass: This class is used to write to a file.
 * @author Dillon Shepherd dshep80@wgu.edu
 */
public abstract class FileIOUtil {

    /**
     * This method is used to write to a file.
     * @param filePath: The name of the file to write to.
     * @param message: The text to write to the file.
     * @param clearFileBeforeWrite: the flag to clear the file before writing.
     * @return a boolean of success
     */
    public static boolean writeToFile(String filePath, String message, boolean clearFileBeforeWrite){
        FileWriter writer = null;
        try {
            writer = new FileWriter(filePath, !clearFileBeforeWrite);
            writer.append(message);
            writer.close();
        } catch (IOException e) {
            System.out.println("could not write to file");
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
