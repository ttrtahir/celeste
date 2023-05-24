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

    public static PlanetStats planetFocused = new PlanetStats(null, 0, null);

    public static int getFocusShiftX() {
        return planetFocused.positionX / SCALE;
    }

    public static int getFocusShiftY() {
        return planetFocused.positionY / SCALE;
    }

    public static int[] getCenter() {
        return new int[] { FRAME_WIDTH / 2, FRAME_HEIGHT / 2 };
    }
}
