import java.io.File;
import java.io.Serializable;
import java.util.*;

public class FileManager implements Serializable {

    private final String SUFFIX;
    private HashMap<File,Integer> fileMap;

    private int weightOffset = 1; // offsets non-positive file weights so that they appear positive when called

    public static File[] getRelevantFiles(String suffix, File directoryFile) {
        return directoryFile.listFiles((dir, name) -> name.toLowerCase().endsWith(suffix.toLowerCase()));
    }

    public FileManager(String suffix, File directoryFile) {
        this.SUFFIX = suffix;
        this.fileMap = new HashMap<>();

        for (File f : getRelevantFiles(SUFFIX,directoryFile))
            fileMap.put(f,0);
    }

    public FileManager update(File directoryFile) {
        fileMap.keySet().removeIf(file -> !file.exists());

        // discover new files by getting an up to date file list and removing the ones already in fileMap
        ArrayList<File> newFiles = new ArrayList<>(Arrays.asList(getRelevantFiles(SUFFIX,directoryFile)));
        newFiles.removeAll(fileMap.keySet());

        for(File f: newFiles)
            fileMap.put(f,0);

        return this;
    }

    public List<File> getFileSet() {
        return new ArrayList<>(fileMap.keySet());
    }

    public int getWeightSum() {
        return fileMap.values().stream().mapToInt(i -> i).sum() + fileMap.size()*weightOffset;
    }

    public int getFileWeight(File file) {
        return fileMap.get(file) + weightOffset;
    }

    public void decrementFileWeight(File file) {
        fileMap.replace(file, fileMap.get(file) - 1);

        if (weightOffset + fileMap.get(file) == 0)
            weightOffset++;
    }
}
