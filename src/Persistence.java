import java.io.*;

public class Persistence {
    public static void saveToFile(FileManager fm, String fileName) throws Exception {
        // try-with-resources automatically closes streams
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             ObjectOutputStream out = new ObjectOutputStream(fileOut))
        {
            out.writeObject(fm);
        }
    }

    public static FileManager readFromFile(String fileName) throws Exception {
        // try-with-resources automatically closes streams
        try (FileInputStream fileIn = new FileInputStream(fileName);
             ObjectInputStream in = new ObjectInputStream(fileIn))
        {
            return (FileManager) in.readObject();
        }
    }
}
