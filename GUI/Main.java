package GUI;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Main extends JPanel {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Solar System Simulation");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ArrayList<Drawable> drawables = new ArrayList<Drawable>();

        /* TODO: Will be replaced by reading from a file */

        CelestialBody sun = new CelestialBody();
        sun.posX = 0;
        sun.posY = 0;
        sun.posZ = 0;
        PlanetStats sunStats = new PlanetStats();
        sunStats.name = "Sun";
        sunStats.size = 200;
        sunStats.color = java.awt.Color.YELLOW;

        Drawable sunDrawable = new Planet(sun, sunStats);

        CelestialBody earth = new CelestialBody();
        earth.posX = 100;
        earth.posY = 100;
        earth.posZ = 1;
        PlanetStats earthStats = new PlanetStats();
        earthStats.name = "Earth";
        earthStats.size = 100;
        earthStats.color = java.awt.Color.BLUE;

        Drawable earthDrawable = new Planet(earth, earthStats);

        Drawable bg = new Background();
        /* End TODO: */

        drawables.add(bg);
        drawables.add(sunDrawable);
        drawables.add(earthDrawable);

        JLayeredPane layeredPane = new JLayeredPane();

        /* For some reason in JLayeredPane the first rendered is the one in front */
        Collections.reverse(drawables);
        for (Drawable drawable : drawables) {
            drawable.setOpaque(true);
            drawable.setBounds(0, 0, GlobalState.FRAME_WIDTH, GlobalState.FRAME_HEIGHT);
            layeredPane.add(drawable);
        }

        frame.add(layeredPane);

        frame.setVisible(true);

        /*
         * TODO:
         * - Reintroduce focus
         * - Reintroduce simulation speed
         */
        frame.addMouseWheelListener(new MouseEvents());

        while (true) {
            sun.posX += 0.000001;
            frame.repaint();
        }
    }
}
