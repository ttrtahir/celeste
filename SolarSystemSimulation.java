import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.awt.geom.Ellipse2D;
import javax.swing.JComboBox;
import java.awt.RenderingHints;
// import Path2D
import java.awt.geom.Path2D;
import java.io.File;
import java.io.FileNotFoundException;

public class SolarSystemSimulation extends JPanel implements MouseWheelListener {

    // JPanel
    private static final int FRAME_WIDTH = 1280;
    private static final int FRAME_HEIGHT = 720;

    private boolean pause = false;

    private int SCALE = 2500000;

    private int daysSinceStart = 0;

    private static String focusName = Values.focusNames[0];

    private static int focusIndex = Values.focusIndex[0];

    private static int focusTrack = 0;

    // April 1st 2023
    private final int[] START_DATE = { 1, 4, 2023 };

    private double[][] earthPositions = new double[84][3];

    // curent date
    private int[] currentDate = START_DATE;
    private int counter = 0;

    SolarSystem system = new SolarSystem();

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        DrawFunctions.setG(g);

        DrawFunctions.drawBackground();

        DrawFunctions.drawBackgroundStars();

        g.setColor(Color.WHITE);

        CelestialBody[] planetsSortedByZ = new CelestialBody[system.celestialBody.length];
        // sort planets by z
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

        for (int i = 0; i < system.celestialBody.length; i++) {
            int bodyPositionX = ((int) (planetsSortedByZ[i].getX()[0]) / SCALE) + (FRAME_WIDTH / 2)
                    - (int) (focusScale[0] / SCALE);
            int bodyPositionY = ((int) (planetsSortedByZ[i].getX()[1]) / SCALE) + (FRAME_HEIGHT / 2)
                    - (int) (focusScale[1] / SCALE);
            int size = planetsSortedByZ[i].getSize();

            g.setColor(Color.WHITE);
            // render name
            g.setFont(new Font("Metropolis", Font.BOLD, 10 + (int) (SCALE / 6000000)));
            g.drawString(planetsSortedByZ[i].getName(),
                    bodyPositionX
                            - planetsSortedByZ[i].getName().length() * 3,
                    bodyPositionY
                            - size / 2 - 5);

            g.setColor(planetsSortedByZ[i].getColor());

            Ellipse2D.Double planet = new Ellipse2D.Double(
                    bodyPositionX
                            - size / 2
                            + (int) (SCALE / 500000) / 2,
                    bodyPositionY
                            - size / 2
                            + (int) (SCALE / 500000) / 2,
                    size - (int) (SCALE / 500000),
                    size - (int) (SCALE / 500000));
            // draw only border
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setStroke(new java.awt.BasicStroke(5));

            g2.draw(planet);
            g.setColor(Color.BLACK);
            g2.fill(planet);

            // draw string in the middle of the oval
            g.setColor(Color.WHITE);
            g.setFont(new Font("Metropolis", Font.BOLD, 6));

        }
        Graphics2D g2 = (Graphics2D) g;

        // draw a line using Path2D
        g2.setColor(Color.WHITE);
        g2.setStroke(new java.awt.BasicStroke(1));
        Path2D.Double arrow = new Path2D.Double();
        arrow.moveTo(0, 0);
        // curve the line
        arrow.curveTo(187, 62, 250, 250, 250, 250);
        arrow.curveTo(375, 125, 500, 500, 500, 500);
        g2.draw(arrow);

        currentDate = updateCurrentDate(daysSinceStart, currentDate);
        // set color #ccc
        g.setColor(new Color(204, 204, 204));
        g.setFont(new Font("Metropolis", Font.PLAIN, 24));

        String displayDate = Values.MONTHS[currentDate[1] - 1] + " " + currentDate[0] + ", " + currentDate[2];
        g.drawString(displayDate,
                FRAME_WIDTH / 2 - displayDate.length() * 6, 50);

        g.setColor(new Color(175, 175, 175));
        g.setFont(new Font("Metropolis", Font.PLAIN, 15));

        String daysSinceString = "Days since liftoff: " + daysSinceStart;
        g.drawString(daysSinceString,
                FRAME_WIDTH / 2 - (daysSinceString).length() * 3 - 5, 75);

        String simulationSpeed = "Simulation speed: " + system.timeStep;
        g.drawString(simulationSpeed,
                10, FRAME_HEIGHT - 50);
        g.drawString("Focus on: " + focusName, 0, 0);

        // Draw line
        g.setColor(Color.WHITE);

        for (int i = 0; i < earthPositions.length - 1; i++) {
            g.drawLine((int) ((earthPositions[i][0] / SCALE) + FRAME_WIDTH / 2 - (focusScale[0] / SCALE)),
                    (int) ((earthPositions[i][1] / SCALE) + FRAME_HEIGHT / 2 - (focusScale[1] / SCALE)),
                    (int) ((earthPositions[i + 1][0] / SCALE) + FRAME_WIDTH / 2 - (focusScale[0] / SCALE)),
                    (int) ((earthPositions[i + 1][1] / SCALE) + FRAME_HEIGHT / 2 - (focusScale[1] / SCALE)));
        }

    }

    private static double[] focusScale = new double[2];

    public void changeFocus(JComboBox<String> planetList) {
        int index = planetList.getSelectedIndex();
        focusScale = system.celestialBody[index].getX();
    }

    public static void main(String[] args) {
        // load font from file
        try {
            URL url = SolarSystemSimulation.class.getResource("Metropolis-Bold.otf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, url.openStream());
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Solar System Simulation");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SolarSystemSimulation panel = new SolarSystemSimulation();
        final JComboBox<String> planetsList = new JComboBox<String>(Values.NAMES);
        planetsList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.changeFocus(planetsList);
            }
        });
        frame.add(panel);
        frame.addMouseWheelListener(panel);
        frame.addKeyListener(
                new java.awt.event.KeyAdapter() {
                    @Override
                    public void keyPressed(java.awt.event.KeyEvent evt) {
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
                                focusName = Values.focusNames[focusTrack];
                                focusIndex = Values.focusIndex[focusTrack];
                            }
                            focusScale = panel.system.celestialBody[focusIndex].getX();
                            System.out.println(focusTrack);
                        }
                        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_RIGHT) {
                            if (focusTrack < Values.focusIndex.length) {
                                focusTrack++;
                                focusName = Values.focusNames[focusTrack];
                                focusIndex = Values.focusIndex[focusTrack];
                            }
                            focusScale = panel.system.celestialBody[focusIndex].getX();
                            System.out.println(Values.focusIndex.length);
                            System.out.println(focusTrack);
                        }

                    }
                });

        try {
            File myObj = new File("Earth");
            Scanner myReader = new Scanner(myObj);
            int index = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] splitData = data.split(";");
                for (int i = 0; i < splitData.length; i++) {
                    if (splitData[i].length() == 0) {
                        continue;
                    }
                    panel.earthPositions[index][i] = Double.parseDouble(splitData[i]);
                }
                index++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        frame.setVisible(true);
        double min = 1e9;
        double distanceProbeTitan;
        double counter = 0;

        while (true) {
            if (!panel.pause) {
                panel.system.calculateForce();
                panel.system.updateAcceleration();
                panel.system.updatePosition();
                panel.system.updateVelocity();

                panel.daysSinceStart = (int) (panel.counter * panel.system.timeStep / 86400);
                panel.counter++;
            }
            frame.repaint();
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

    public static int updateRocketPosition(int z) {
        z -= 2;
        return z;
    }

}
