import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.RenderingHints;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JComboBox;

import java.net.URL;

import java.text.DecimalFormat;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.File;

public class SolarSystemSimulation extends JPanel implements MouseWheelListener {
    SolarSystem system = new SolarSystem();

    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    // JPanel
    private static final int FRAME_WIDTH = (int) screenSize.getWidth();
    private static final int FRAME_HEIGHT = (int) screenSize.getHeight();

    private int SCALE = 2500000;
    private boolean pause = false;

    private CelestialBody rocket;
    private ArrayList<double[]> rocketPositions = new ArrayList<>();
    private final int RECORD_EVERY_ROCKET = 100000;
    private int recordRocketPos = RECORD_EVERY_ROCKET;

    private ArrayList<ArrayList<double[]>> celestialPositions = new ArrayList<>();

    // April 1st 2023
    private final int[] START_DATE = { 1, 4, 2023 };
    private int[] currentDate = START_DATE;
    public static int daysSinceStart = 0;

    private static int focusIndex = Values.focusIndex[0];
    private static int focusTrack = 0;
    private static double[] focusScale = new double[2];

    private double[] relativeVelocity = new double[3];

    private int counter = 0;
    private double lastTime = 0;
    private int fps = 0;
    private int calculationsSinceStart = 0;

    private double min = 1e39;
    private double distanceProbeTitan;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Setup all variables in DrawFunctions
        DrawFunctions.setup(g, FRAME_WIDTH, FRAME_HEIGHT, SCALE, focusScale, system);

        // Background
        DrawFunctions.drawBackground();
        DrawFunctions.drawBackgroundStars();

        // Orbits of celestial bodies
        DrawFunctions.drawOrbits(celestialPositions);
        DrawFunctions.drawRocketPath(rocketPositions);

        // Planets && Celestial bodies
        CelestialBody[] planetsSortedByZ = sortPlanetsByZ();
        DrawFunctions.drawPlanets(planetsSortedByZ);

        // All the textual information
        currentDate = updateCurrentDate(daysSinceStart, currentDate);
        DrawFunctions.drawStrings(currentDate, daysSinceStart, focusIndex, calculationsSinceStart, fps);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int notches = -e.getWheelRotation();
        if (notches < 0) {
            SCALE += 300000;
        } else {
            SCALE -= 300000;
        }
        SCALE = Math.max(1, SCALE);
    }

    public void changeFocus(JComboBox<String> planetList) {
        int index = planetList.getSelectedIndex();
        focusScale = system.celestialBody[index].getX();
    }

    private CelestialBody[] sortPlanetsByZ() {
        CelestialBody[] planetsSortedByZ = new CelestialBody[system.celestialBody.length];

        for (int i = 0; i < system.celestialBody.length; i++) {
            planetsSortedByZ[i] = system.celestialBody[i];
        }
        for (int i = 0; i < system.celestialBody.length; i++) {
            for (int j = 0; j < system.celestialBody.length; j++) {
                if (planetsSortedByZ[i].getX()[2] < planetsSortedByZ[j].getX()[2]) {
                    CelestialBody temp = planetsSortedByZ[i];
                    planetsSortedByZ[i] = planetsSortedByZ[j];
                    planetsSortedByZ[j] = temp;
                }
            }
        }

        return planetsSortedByZ;
    }

    private void recordRocketPositions(SolarSystemSimulation panel) {
        recordRocketPos--;

        if (recordRocketPos == 0) {
            recordRocketPos = RECORD_EVERY_ROCKET;

            double[] curPosition = new double[2];
            curPosition[0] = panel.rocket.getX()[0];
            curPosition[1] = panel.rocket.getX()[1];
            rocketPositions.add(curPosition);
        }
    }

    private int[] updateCurrentDate(int daysSinceStart, int[] currentDate) {
        int[] newDate = new int[3];
        newDate[0] = START_DATE[0] + daysSinceStart;
        newDate[1] = START_DATE[1];
        newDate[2] = START_DATE[2];

        while (newDate[0] > Values.DAYS_IN_MONTH[newDate[1] - 1]) {
            newDate[0] -= Values.DAYS_IN_MONTH[newDate[1] - 1];

            newDate[1]++;
            if (newDate[1] > 12) {
                newDate[1] = 1;
                newDate[2]++;
            }
        }

        return newDate;
    }

    private void loadFont() {
        try {
            URL url = SolarSystemSimulation.class.getResource("Metropolis-Bold.otf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, url.openStream());
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkForKeys(KeyEvent evt, SolarSystemSimulation panel) {
        // spacebar
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_SPACE) {
            panel.pause = !panel.pause;
        }
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }

        double increaseStep = 0.5;

        // arrows
        DecimalFormat df = new DecimalFormat("#.#");
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_UP) {
            panel.system.timeStep += increaseStep;
            panel.system.timeStep = Double.parseDouble(df.format(panel.system.timeStep));

            panel.counter = (int) ((panel.counter * (panel.system.timeStep - increaseStep))
                    / (panel.system.timeStep));
        }
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_DOWN) {
            if (panel.system.timeStep <= increaseStep)
                return;
            panel.system.timeStep -= increaseStep;
            panel.system.timeStep = Double.parseDouble(df.format(panel.system.timeStep));

            panel.counter = (int) ((panel.counter * (panel.system.timeStep + increaseStep))
                    / (panel.system.timeStep));
        }

        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_LEFT) {
            if (focusTrack > 0) {
                focusTrack--;
                focusIndex = Values.focusIndex[focusTrack];
            }
            focusScale = panel.system.celestialBody[focusIndex].getX();
        }
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_RIGHT) {
            if (focusTrack < Values.focusIndex.length) {
                focusTrack++;
                focusIndex = Values.focusIndex[focusTrack];
            }
            focusScale = panel.system.celestialBody[focusIndex].getX();
        }
    }

    private void loadOrbitValues(SolarSystemSimulation panel) {
        try {
            for (int g = 0; g < Values.ORBIT_NAMES.length; g++) {
                panel.celestialPositions.add(new ArrayList<double[]>());
                String fileName = Values.ORBIT_NAMES[g]+".txt";
                File myObj = new File(fileName);
                Scanner myReader = new Scanner(myObj);
                int index = 0;
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    String[] splitData = data.split(";");
                    for (int i = 0; i < splitData.length; i++) {
                        if (splitData[i].length() == 0) {
                            continue;
                        }
                        panel.celestialPositions.get(g).add(new double[3]);
                        panel.celestialPositions.get(g).get(index)[i] = Double.parseDouble(splitData[i]);
                    }
                    index++;
                }
                myReader.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void checkTitanCollision(SolarSystemSimulation panel) {
        panel.distanceProbeTitan = panel.system.calculateDistanceBetweenCelestials(
                panel.system.getCelestialBody()[11].getX(), panel.system.getCelestialBody()[8].getX());
        if (panel.min > panel.distanceProbeTitan)
            panel.min = panel.distanceProbeTitan;
        else {
            panel.pause = true;
            System.out.println(panel.min + " in " + panel.counter * panel.system.timeStep / 86400);
            for (int k = 0; k < 3; k++) {
                System.out.println(k + " axis spacecraft: " + panel.system.celestialBody[11].getX()[k] + k
                        + " axis titan: " + panel.system.celestialBody[8].getX()[k]);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Solar System Simulation");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SolarSystemSimulation panel = new SolarSystemSimulation();

        panel.loadFont();
        panel.loadOrbitValues(panel);

        frame.add(panel);
        frame.addMouseWheelListener(panel);
        frame.addKeyListener(
                new java.awt.event.KeyAdapter() {
                    @Override
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        panel.checkForKeys(evt, panel);
                    }
                });

        frame.setVisible(true);

        panel.rocket = panel.system.celestialBody[panel.system.celestialBody.length - 1];

        while (true) {
            if (!panel.pause) {
                // Physics Engine
                panel.system.calculateForce();
                panel.system.updateAcceleration();
                panel.system.updatePosition();
                panel.system.updateVelocity();

                // Record past rocket positions
                panel.recordRocketPositions(panel);

                // Count how many days passed since liftoff
                daysSinceStart = (int) (panel.counter * panel.system.timeStep / 86400);

                // Count how many calculations have been made
                panel.calculationsSinceStart++;
                panel.counter++;

                // Checks if rocket has approached Titan
                panel.checkTitanCollision(panel);
            }
            frame.repaint();

            panel.fps = (int) (1000000.0 / (System.nanoTime() - panel.lastTime));
            panel.lastTime = System.nanoTime();
        }
    }
}
