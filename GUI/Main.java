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
import GUI.Drawables.PlanetStats;
import GUI.Drawables.Style;
import GUI.Drawables.Text;
import GUI.Drawables.Trajectory;
import GUI.Events.KeyEvents;
import GUI.Events.MouseEvents;
import GUI.Resources.StyleValues;
import Interface.IVector3;
import Simulator.SolarSystem;

public class Main extends JPanel {
    public static void main(String[] args) throws InterruptedException {
        new Style();

        JFrame frame = new JFrame("Solar System Simulation");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* The PHYSICS part */
        SolarSystem solarSystem = new SolarSystem();
        solarSystem.initialProcess();
        GlobalState.states = solarSystem.getStates();

        /* Some notes so I don't forget them: */
        /* Space probe and Titan are closest at state 3492 or 3493 */
        /* Distance at 3492: 315929.89353160484 */
        /* Distance at 3493: 293897.19830514514 */

        /* Let's start our return journey at state 4494 */
        /* state 6742 is when we go near Earth */
        /*
         * IVector3 missilePos = GlobalState.states[3493].state[11][0];
         * IVector3 titanPos = GlobalState.states[3493].state[8][0];
         * 
         * System.out.println("Missile position: " + missilePos);
         * System.out.println("Titan position: " + titanPos);
         * System.out.println("Distance between them: " +
         * missilePos.euclideanDist(titanPos));
         *
         * IVector3 missilePos = GlobalState.states[6742].state[11][0];
         * IVector3 earthPos = GlobalState.states[6742].state[4][0];
         * System.out.println("Distance between them: " +
         * missilePos.euclideanDist(earthPos));
         */

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

        /* Space Probe's trajectory */
        Trajectory trajectoryMercury = new Trajectory(1);
        Trajectory trajectoryVenus = new Trajectory(2);
        Trajectory trajectoryEarth = new Trajectory(3);
        Trajectory trajectoryMars = new Trajectory(5);
        Trajectory trajectoryJupiter = new Trajectory(6);
        Trajectory trajectorySaturn = new Trajectory(7);
        Trajectory trajectorySpaceProbe = new Trajectory(11, true);

        drawables.add(trajectorySpaceProbe);
        drawables.add(trajectoryMercury);
        drawables.add(trajectoryVenus);
        drawables.add(trajectoryEarth);
        drawables.add(trajectoryMars);
        drawables.add(trajectoryJupiter);
        drawables.add(trajectorySaturn);

        drawables.add(new Background());

        /* add all drawables to a panel */
        JLayeredPane layeredPane = new JLayeredPane();
        for (Drawable drawable : drawables) {
            drawable.setOpaque(true);
            drawable.setBounds(0, 0, GlobalState.FRAME_WIDTH, GlobalState.FRAME_HEIGHT);
            layeredPane.add(drawable);
        }

        /*
         * For some reason in JLayeredPane the first rendered is the one in front.
         * Makes no sense but it is what it is
         */
        Collections.reverse(drawables);

        frame.add(layeredPane);
        frame.setVisible(true);
        /* key and mouse press events */
        frame.addMouseWheelListener(new MouseEvents());
        KeyEvents keyEvents = new KeyEvents(planetStats);
        frame.addKeyListener(keyEvents);

        /*
         * Can change simulation starting point here. Max state can be calculated from
         * values in SolarSystem.java by (timeFinal / h) + 1
         */
        int currStateIndex = 0;
        /* Counts how many days passed */
        int daysSinceStart = (int) (GlobalState.STEP_MULTIPLIER * currStateIndex);
        /*
         * Choose which planet to focus on. Can be changed using right and left arrow
         * keys.
         */
        GlobalState.planetFocused = planetStats.get(11);
        /* Can set initial SCALE */
        GlobalState.SCALE = GlobalState.SCALE;
        /*
         * Set simulations speed. A bit deceiving variable name (if you come up with a
         * better one let me know), because it is time in milliseconds between each
         * frame. So the lower the number the faster the simulation.
         */
        GlobalState.simulationSpeed = 10;
        while (true) {
            if (GlobalState.paused) {
                /* These allow for SCALE and focused planet change even if paused */
                frame.repaint();
                keyEvents.setPlanetStats(planetStats);
                continue;
            }
            frame.repaint();
            currStateIndex++;

            daysSinceStart = (int) (currStateIndex * GlobalState.STEP_MULTIPLIER);

            /* Neccessary to keep the focusedPlanet position up-to-date */
            keyEvents.setPlanetStats(planetStats);

            /* Update all planets' positions */
            for (int i = 0; i <= 11; i++) {
                planetStats.get(i).setPos((int) GlobalState.states[currStateIndex].state[i][0].getX(),
                        (int) GlobalState.states[currStateIndex].state[i][0].getY());
            }

            /* Update space probe's white/red trajectory ratio */
            trajectorySpaceProbe.updateCurrentStateIndex(currStateIndex);

            /* Updating all UI text on screen */
            int[] currDate = StyleValues.daysPassedToDate(daysSinceStart);
            uiTexts.get(0).setText(StyleValues.MONTHS[currDate[1] - 1] + " " + (currDate[2]) + ", " + currDate[0]);
            uiTexts.get(1).setText("Days since start: " + daysSinceStart);
            uiTexts.get(2)
                    .setText("Simulation speed: " + Math.ceil((10.00 / GlobalState.simulationSpeed) * 100) / 100 + "x");
            uiTexts.get(3).setText("Focused planet: " + GlobalState.planetFocused.name);
            uiTexts.get(4).setText("Current iteration: " + currStateIndex);

            /* Determines the simulation speed */
            Thread.sleep(GlobalState.simulationSpeed);
        }
    }

}