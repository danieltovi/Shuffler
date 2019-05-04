import java.io.*;

public class Main {

    public static void main(String[] args) {
        FileManager fm;

        String userDir = System.getProperty("user.dir");
        File userDirFile = new File(userDir);

        String serializedFileName = userDir + File.separator + "shuffler.ser";

        try {
            if (new File(serializedFileName).exists())
                fm = Persistence.readFromFile(serializedFileName).update(userDirFile);
            else {
                String suffix = SwingUtils.suffixPrompt();

                while(suffix != null && FileManager.getRelevantFiles(suffix,userDirFile).length == 0)
                    suffix = SwingUtils.invalidSuffixPrompt(suffix);

                if (suffix == null) return;

                fm = new FileManager(suffix, userDirFile);
            }

            Shuffler.weightedShufflePlay(fm);
            Persistence.saveToFile(fm, serializedFileName);

        } catch (Exception e) {
            SwingUtils.showError(e);
        }
    }
}
