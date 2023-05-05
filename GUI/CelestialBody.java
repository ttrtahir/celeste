package GUI;

import java.awt.Color;
import javax.swing.JPanel;

public class CelestialBody extends JPanel {
    public int step;

    public double mass;
    public double gravity;

    // velocity
    public double veloX;
    public double veloY;
    public double veloZ;

    // position
    public double posX;
    public double posY;
    public double posZ;

    public static CelestialBody[] celestialBodies;

    public CelestialBody() {
        step = 0;
    }

    public void createVectors() {

    }
}
