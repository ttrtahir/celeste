package GUI;

/*
 * Contains the variables that are used all across the files
 */
import java.awt.*;

public class GlobalState {
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int FRAME_WIDTH = (int) screenSize.getWidth();
    public static final int FRAME_HEIGHT = (int) screenSize.getHeight();

    public static int SCALE = 2000000;

    public static int[] focusShift = { 0, 0, 0 };

    public static int getFocusShiftX() {
        return focusShift[0] / SCALE;
    }

    public static int getFocusShiftY() {
        return focusShift[1] / SCALE;
    }

    public static int[] getCenter() {
        return new int[] { FRAME_WIDTH / 2, FRAME_HEIGHT / 2 };
    }
}
