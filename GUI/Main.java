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

        for (int i = 0; i <= 11; i++) {
            planetStats.add(new PlanetStats(Values.NAMES[i], Values.SIZES[i], Values.COLORS[i]));

            drawables.add(new Planet(planetStats.get(i)));
        }

        Text daysText = new Text(612, 40);
        Text iText = new Text(100, 200);
        drawables.add((Drawable) daysText);
        drawables.add((Drawable) iText);

        drawables.add(new Background());

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
        KeyEvents keyEvents = new KeyEvents(planetStats);
        frame.addKeyListener(keyEvents);

        SolarSystem solarSystem = new SolarSystem();
        solarSystem.initialProcess();

        int currStateIndex = 0;
        State[] states = solarSystem.getStates();
        int daysSinceStart = 0;

        System.out.println("States ready ...");
        System.out.println(states.length);

        int[] currentDate = { 2023, 4, 1 };
        while (true) {
            if (GlobalState.paused) {
                frame.repaint();
                continue;
            }

            frame.repaint();

            daysSinceStart = (int) (currStateIndex * 0.1); // No idea why 0.1 works, but it calculates days EXACTLY
            keyEvents.setPlanetStats(planetStats);

            for (int i = 0; i <= 11; i++) {
                planetStats.get(i).setPos((int) states[currStateIndex].state[i][0].getX(),
                        (int) states[currStateIndex].state[i][0].getY());
            }

            int[] currDate = Values.getDateFromDays(daysSinceStart);

            daysText.setText(Values.MONTHS[currDate[1]] + " " + currDate[2] + " " + currDate[0]);
            iText.setText("i: " + currStateIndex);

            currStateIndex++;
            Thread.sleep(GlobalState.simulationSpeed);
        }
    }

}