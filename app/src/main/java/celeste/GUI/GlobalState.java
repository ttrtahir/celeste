package app.src.main.java.celeste.GUI;

/*
 * Contains the variables that are used all across the files
 */
import java.awt.*;

import app.src.main.java.celeste.GUI.Drawables.PlanetStats;
import app.src.main.java.celeste.Simulator.State;

public class GlobalState {
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int FRAME_WIDTH = (int) screenSize.getWidth();
    public static final int FRAME_HEIGHT = (int) screenSize.getHeight();

    /* To unify values in GUI and Physics */
    public static final double STEP_MULTIPLIER = 0.1;

    public static boolean paused = false;

    public static int simulationSpeed = 10;
    public static int simulationSpeedIncrement = 1;

    public static int SCALE = 2000000;

    public static PlanetStats planetFocused = new PlanetStats("Sun", 0, null);
    public static State[] states;

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
