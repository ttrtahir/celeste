package GUI;
/*
 * Contains the main class for running and testing the project
 */
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import Simulator.CelestialBody;
import Simulator.SolarSystem;

public class Main extends JPanel {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Solar System Simulation");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ArrayList<Drawable> drawables = new ArrayList<Drawable>();

        /* TODO: Finish */
        SolarSystem solarSystem = new SolarSystem();

        for (CelestialBody celestialBody : solarSystem.getCelestialBodies()) {
            Drawable temp = new Planet(celestialBody);
            drawables.add(temp);
        }

        Drawable bg = new Background();

        drawables.add(bg);

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
            frame.repaint();
        }
    }
}
