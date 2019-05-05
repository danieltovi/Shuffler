import javax.swing.*;
import java.util.Arrays;

public class SwingUtils {
    public static String suffixPrompt() {
        return JOptionPane.showInputDialog("Enter file extension",".mp4");
    }

    public static String invalidSuffixPrompt(String prev) {
        return JOptionPane.showInputDialog("Invalid file extension, try again",prev);
    }

    public static void showError(Exception exceptionError) {
        String errorMessage = String.format("Message: %s\nStackTrace: %s",
                                            exceptionError.getMessage(),
                                            Arrays.toString((exceptionError.getStackTrace())));

        String title = exceptionError.getClass().getName();

        JOptionPane.showMessageDialog(null,errorMessage,title,JOptionPane.ERROR_MESSAGE);
    }
}
