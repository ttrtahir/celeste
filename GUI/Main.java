package GUI;

/*
 * Contains the main class for running and testing the project
 */
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import GUI.Drawables.Background;
import GUI.Drawables.Drawable;
import GUI.Drawables.Planet;
import GUI.Drawables.PlanetStats;
import GUI.Drawables.Text;
import GUI.Events.KeyEvents;
import GUI.Events.MouseEvents;
import Simulator.SolarSystem;
import Simulator.State;

public class Main extends JPanel {
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Solar System Simulation");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ArrayList<Drawable> drawables = new ArrayList<Drawable>();

        ArrayList<PlanetStats> planetStats = new ArrayList<PlanetStats>();

        for (int i = 0; i <= 11; i++) {
            planetStats.add(new PlanetStats(Values.NAMES[i], Values.SIZES[i], Values.COLORS[i]));

            drawables.add(new Planet(planetStats.get(i)));
        }

        Text currentDateText = new Text(GlobalState.getCenter()[0], 40, "center");
        Text daysText = new Text(GlobalState.getCenter()[0], 70, "center");
        Text simulationSpeed = new Text(20, 0, "bottom");
        Text nameFocusedPlanet = new Text("right", "bottom");

        drawables.add((Drawable) currentDateText);
        drawables.add((Drawable) daysText);
        drawables.add((Drawable) simulationSpeed);
        drawables.add((Drawable) nameFocusedPlanet);

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

        while (true) {
            if (GlobalState.paused) {
                frame.repaint();
                continue;
            }

            frame.repaint();

            daysSinceStart = (int) (currStateIndex * 0.1); // No idea why 0.1 works, but it calculates days EXACTLY
                                                           // Yoo, it's actually the step size multiplier
            keyEvents.setPlanetStats(planetStats);

            for (int i = 0; i <= 11; i++) {
                planetStats.get(i).setPos((int) states[currStateIndex].state[i][0].getX(),
                        (int) states[currStateIndex].state[i][0].getY());
            }

            int[] currDate = Values.daysPassedToDate(daysSinceStart);

            currentDateText.setText(Values.MONTHS[currDate[1] - 1] + " " + (currDate[2]) + ", " + currDate[0]);
            daysText.setText("Days since start: " + daysSinceStart);
            nameFocusedPlanet.setText("Focused planet: " + GlobalState.planetFocused.name);

            double sSpeed = Math.ceil((10.00 / GlobalState.simulationSpeed) * 100) / 100;

            simulationSpeed.setText("Simulation speed: " + sSpeed + "x");

            currStateIndex++;
            Thread.sleep(GlobalState.simulationSpeed);
        }
    }

}