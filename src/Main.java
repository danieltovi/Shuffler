import java.awt.*;
import java.io.*;
import java.util.Random;

public class Main {

    private static final Random rng = new Random();

    private static void weightedShufflePlay(FileManager fm) throws Exception {
        int randomSample = rng.nextInt(fm.getWeightSum());

        for (File media : fm.getFileSet()) {
            randomSample -= fm.getFileWeight(media);

            if (randomSample <= 0) {
                Desktop.getDesktop().open(media);
                fm.decrementFileWeight(media);
                break;
            }
        }
    }

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

                // catch user canceling or x-ing out of prompt
                if (suffix == null) return;

                fm = new FileManager(suffix, userDirFile);
            }

            weightedShufflePlay(fm);
            Persistence.saveToFile(fm, serializedFileName);

        } catch (Exception e) {
            SwingUtils.showError(e);
        }
    }
}
