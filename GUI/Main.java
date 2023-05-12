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
import Simulator.State;

public class Main extends JPanel {
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Solar System Simulation");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ArrayList<Drawable> drawables = new ArrayList<Drawable>();

        /* TODO: Finish */

        ArrayList<PlanetStats> planetStats = new ArrayList<PlanetStats>();

        for (int i = 0; i < 11; i++) {
            planetStats.add(new PlanetStats(Values.NAMES[i], Values.SIZES[i], Values.COLORS[i]));

            drawables.add(new Planet(planetStats.get(i)));
        }

        Drawable bg = new Background();

        drawables.add(bg);

        JLayeredPane layeredPane = new JLayeredPane();

        for (Drawable drawable : drawables) {
            drawable.setOpaque(true);
            drawable.setBounds(0, 0, GlobalState.FRAME_WIDTH, GlobalState.FRAME_HEIGHT);
            layeredPane.add(drawable);
        }

        /* For some reason in JLayeredPane the first rendered is the one in front */
        Collections.reverse(drawables);

        frame.add(layeredPane);

        frame.setVisible(true);

        /*
         * TODO:
         * - Reintroduce focus
         * - Reintroduce simulation speed
         */
        frame.addMouseWheelListener(new MouseEvents());

        SolarSystem solarSystem = new SolarSystem();
        solarSystem.initialProcess();

        int currStateIndex = 0;
        State[] states = solarSystem.getStates();

        System.out.println("States ready ...");
        System.out.println(states.length);
        while (true) {
            frame.repaint();

            for (int i = 0; i < 11; i++) {
                planetStats.get(i).setPos((int) states[currStateIndex].state[i][0].getX(),
                        (int) states[currStateIndex].state[i][0].getY());
            }

            currStateIndex++;
            Thread.sleep(10);
        }
    }
}