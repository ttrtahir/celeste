import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class DrawFunctions {
    static Graphics g;
    static Graphics2D g2;

    static int STAR_COUNT = 500;
    static int[] STAR_SIZES = new int[500];
    static int[][] starPositions = new int[STAR_COUNT][3];
    static {
        for (int i = 0; i < STAR_COUNT; i++) {
            starPositions[i][0] = (int) (Math.random() * 2000);
            starPositions[i][1] = (int) (Math.random() * 1200);
            starPositions[i][2] = (int) (Math.random() * 200);

            STAR_SIZES[i] = (int) (Math.random() * 2 + 2 + starPositions[i][2] / 255 * 2);
        }
    }

    static int FRAME_WIDTH;
    static int FRAME_HEIGHT;
    static double SCALE;
    static double[] focusScale;
    static SolarSystem system;

    public static void setup(Graphics G, int FRAME_WIDTH, int FRAME_HEIGHT, double SCALE, double[] focusScale,
            SolarSystem system) {
        g = G;
        g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
                RenderingHints.VALUE_STROKE_PURE);

        DrawFunctions.FRAME_WIDTH = FRAME_WIDTH;
        DrawFunctions.FRAME_HEIGHT = FRAME_HEIGHT;
        DrawFunctions.SCALE = SCALE;
        DrawFunctions.focusScale = focusScale;
        DrawFunctions.system = system;
    }

    public static void drawBackground() {
        // set HEX color
        g.setColor(Color.decode("#000"));
        g.fillRect(0, 0, 2000, 2000);
    }

    public static void drawBackgroundStars() {
        // set HEX color
        for (int i = 0; i < STAR_COUNT; i++) {
            g.setColor(new Color(255, 255, 255, starPositions[i][2]));
            g.fillOval(starPositions[i][0], starPositions[i][1], STAR_SIZES[i], STAR_SIZES[i]);
        }
    }

    public static void drawOrbits(ArrayList<ArrayList<double[]>> celestialPositions) {
        g.setColor(new Color(140, 140, 140));
        for (int h = 0; h < celestialPositions.size(); h++) {
            for (int i = 0; i < celestialPositions.get(h).size() - 1; i++) {
                if (celestialPositions.get(h).get(i + 1)[0] == 0 && celestialPositions.get(h).get(i + 1)[1] == 0)
                    continue;

                g.drawLine(
                        (int) ((celestialPositions.get(h).get(i)[0] / SCALE) + FRAME_WIDTH / 2
                                - (focusScale[0] / SCALE)),
                        (int) ((celestialPositions.get(h).get(i)[1] / SCALE) + FRAME_HEIGHT / 2
                                - (focusScale[1] / SCALE)),
                        (int) ((celestialPositions.get(h).get(i + 1)[0] / SCALE) + FRAME_WIDTH / 2
                                - (focusScale[0] / SCALE)),
                        (int) ((celestialPositions.get(h).get(i + 1)[1] / SCALE) + FRAME_HEIGHT / 2
                                - (focusScale[1] / SCALE)));
            }
        }
    }

    public static void drawRocketPath(ArrayList<double[]> rocketPositions) {
        g2.setStroke(new BasicStroke(3));
        g.setColor(Color.red);
        for (int i = 0; i < rocketPositions.size() - 1; i++) {
            g.drawLine(
                    (int) ((rocketPositions.get(i)[0] / SCALE) + FRAME_WIDTH / 2
                            - (focusScale[0] / SCALE)),
                    (int) ((rocketPositions.get(i)[1] / SCALE) + FRAME_HEIGHT / 2
                            - (focusScale[1] / SCALE)),
                    (int) ((rocketPositions.get(i + 1)[0] / SCALE) + FRAME_WIDTH / 2
                            - (focusScale[0] / SCALE)),
                    (int) ((rocketPositions.get(i + 1)[1] / SCALE) + FRAME_HEIGHT / 2
                            - (focusScale[1] / SCALE)));
        }
    }

    public static void drawPlanets(CelestialBody[] planetsSortedByZ) {
        for (int i = 0; i < system.celestialBody.length; i++) {
            int bodyPositionX = (int) ((int) (planetsSortedByZ[i].getX()[0]) / SCALE) + (FRAME_WIDTH / 2)
                    - (int) (focusScale[0] / SCALE);
            int bodyPositionY = (int) ((int) (planetsSortedByZ[i].getX()[1]) / SCALE) + (FRAME_HEIGHT / 2)
                    - (int) (focusScale[1] / SCALE);
            int size = planetsSortedByZ[i].getSize();

            g.setColor(Color.WHITE);
            g.setFont(new Font("Metropolis", Font.BOLD, 12 + (int) (SCALE / 6000000)));
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

            g2.setStroke(new java.awt.BasicStroke(3));

            g2.draw(planet);
        }
    }

    public static void drawStrings(int[] currentDate, int daysSinceStart, int focusIndex, int calculationsSinceStart,
            int fps) {
        g2.setStroke(new java.awt.BasicStroke(1));
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

        String simulationSpeed = "Simulation speed: " + system.timeStep + "x";
        g.drawString(simulationSpeed,
                10, FRAME_HEIGHT - 80);

        String focus = "Focus on: " + Values.NAMES[focusIndex];
        g.drawString(focus, FRAME_WIDTH - 150, FRAME_HEIGHT - 80);

        g.drawString("Missile Position and Velocity Relative to Earth", 10, 25);

        double[] probePosition = system.celestialBody[11].getX();
        double[] earthPosition = system.celestialBody[4].getX();
        double[] relativePosition = new double[3];
        for (int i = 0; i < 3; i++) {
            relativePosition[i] = probePosition[i] - earthPosition[i];
        }
        String positionRelativeEarth = "{ " + (int) relativePosition[0] + " ; " + (int) relativePosition[1] + " ; "
                + (int) relativePosition[2] + " }";
        g.drawString(positionRelativeEarth, 10, 45);

        double[] probeVelocity = system.celestialBody[11].getV();
        double[] earthVelocity = system.celestialBody[4].getV();
        double[] relativeVelocity = new double[3];
        for (int i = 0; i < 3; i++) {
            relativeVelocity[i] = probeVelocity[i] - earthVelocity[i];
        }
        String velocityRelativeEarth = "{ " + +(int) relativeVelocity[0] + " ; " + (int) relativeVelocity[1] + " ; "
                + (int) relativeVelocity[2] + " }";
        g.drawString(velocityRelativeEarth, 10, 65);

        g.drawString("Calculations since start:", FRAME_WIDTH - 200, 25);
        g.drawString("" + calculationsSinceStart, FRAME_WIDTH - 200, 45);

        g.drawString("Frame rate: " + fps, FRAME_WIDTH - 200, 65);
    }
}