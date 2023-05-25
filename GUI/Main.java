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

        /*
         * drawables represents any element, that will be drawed to the screen in each
         * frame
         */
        ArrayList<Drawable> drawables = new ArrayList<Drawable>();

        /* planetStats is a state of each celestial body, including the space probe */
        ArrayList<PlanetStats> planetStats = new ArrayList<PlanetStats>();
        HelperFunctions.addPlanetsToDrawables(planetStats, drawables);

        /* All the UI text on screen */
        ArrayList<Text> uiTexts = new ArrayList<Text>();
        HelperFunctions.createUIText(drawables, uiTexts);

        drawables.add(new Background());

        /* add all drawables to a panel */
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
        /* key and mouse events */
        frame.addMouseWheelListener(new MouseEvents());
        KeyEvents keyEvents = new KeyEvents(planetStats);
        frame.addKeyListener(keyEvents);

        /* The PHYSICS part */
        SolarSystem solarSystem = new SolarSystem();
        solarSystem.initialProcess();

        int currStateIndex = 0;
        State[] states = solarSystem.getStates();
        int daysSinceStart = (int) (GlobalState.STEP_MULTIPLIER * currStateIndex);

        while (true) {
            if (GlobalState.paused) {
                frame.repaint();
                continue;
            }
            frame.repaint();

            currStateIndex++;
            daysSinceStart = (int) (currStateIndex * GlobalState.STEP_MULTIPLIER);

            /* Neccessary to keep the focusedPlanet position up-to-date */
            keyEvents.setPlanetStats(planetStats);

            /* Update all planets' positions */
            for (int i = 0; i <= 11; i++) {
                planetStats.get(i).setPos((int) states[currStateIndex].state[i][0].getX(),
                        (int) states[currStateIndex].state[i][0].getY());
            }

            /* Updating all UI text on screen */
            int[] currDate = Values.daysPassedToDate(daysSinceStart);
            uiTexts.get(0).setText(Values.MONTHS[currDate[1] - 1] + " " + (currDate[2]) + ", " + currDate[0]);
            uiTexts.get(1).setText("Days since start: " + daysSinceStart);
            uiTexts.get(2)
                    .setText("Simulation speed: " + Math.ceil((10.00 / GlobalState.simulationSpeed) * 100) / 100 + "x");
            uiTexts.get(3).setText("Focused planet: " + GlobalState.planetFocused.name);

            /* Determines the simulation speed */
            Thread.sleep(GlobalState.simulationSpeed);
        }
    }

}