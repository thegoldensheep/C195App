package Utilities;

import java.io.FileWriter;
import java.io.IOException;

public abstract class FileIOUtil {

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
