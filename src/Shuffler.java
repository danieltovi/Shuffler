import java.awt.*;
import java.io.File;
import java.util.*;

public class Shuffler {

    private static final Random rng = new Random();

    private static boolean playMedia(File media, FileManager fm) throws Exception {
        if (media.exists()) {
            Desktop.getDesktop().open(media);
            fm.decrementFileWeight(media);

            return true;
        }
        else {
            fm.removeFile(media);

            return false;
        }
    }

    public static void weightedShufflePlay(FileManager fm) throws Exception {
        int randomSample = rng.nextInt(fm.getWeightSum());

        for (File media : fm.getFileSet()) {
            randomSample -= fm.getFileWeight(media);

            if (randomSample <= 0) {
                if (!playMedia(media,fm))
                    weightedShufflePlay(fm);

                break;
            }
        }
    }
}
