import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SolarSystemSimulation extends JPanel {

    // JPanel
    private static final int FRAME_WIDTH = 1980;
    private static final int FRAME_HEIGHT = 1080;

    // Sun
    private static final double SUN_RADIUS = 50;
    private static final int SUN_X = FRAME_WIDTH / 2;
    private static final int SUN_Y = FRAME_HEIGHT / 2;

    // Planets
    private static final float EARTH_RADIUS = 10;
    private static final float EARTH_DISTANCE = 100;
    private static final float JUPYTER_RADIUS = 30;
    private static final float JUPYTER_DISTANCE = 250;
    private static final float TITAN_RADIUS = 5;
    private static final float TITAN_DISTANCE_FROM_JUPYTER = 50;
    private static final float ANGLE_INCREMENT = 0.01f;

    private float angle = 0;
    private float angleJP = 0;
    private float angleTitan = 0;

    SolarSystem system = new SolarSystem();
    double[][] positions = SolarSystem.positions;

    public void paintComponent(Graphics g) {

        // Background
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.WHITE);
        for (int i = 0; i < positions.length; i++) {
            g.fillOval(((int)positions[i][0]/100000000)+(FRAME_WIDTH/2), ((int)positions[i][1]/100000000)+(FRAME_HEIGHT/2), 10, 10);
        }

        // // Draw the Sun
        // g.setColor(Color.YELLOW);
        // g.fillOval((int) (system.getCelestialBody()[0].getX()[0] + FRAME_WIDTH / 2 - SUN_RADIUS),
        //         (int) (system.getCelestialBody()[0].getX()[1] + FRAME_HEIGHT / 2 - (int) SUN_RADIUS),
        //         (int) (2 * SUN_RADIUS), (int) (2 * SUN_RADIUS));

        // // Draw the earth's orbit, OLD
        // // g.setColor(Color.WHITE);
        // // int earthOrbitSize = (int)EARTH_DISTANCE*2;
        // // g.drawOval(SUN_X - earthOrbitSize / 2, SUN_Y - earthOrbitSize / 2,
        // // earthOrbitSize, earthOrbitSize);

        // // Draw the MERCURY NOT EARTH
        // // double velocityX =
        // double x = FRAME_WIDTH / 2 + system.getCelestialBody()[1].getX()[0] / 300000;
        // double y = FRAME_HEIGHT / 2 + system.getCelestialBody()[1].getX()[1] / 300000;
        // g.setColor(Color.BLUE);
        // g.fillOval((int) x - (int) EARTH_RADIUS, (int) y - (int) EARTH_RADIUS, (int) (2 * EARTH_RADIUS),
        //         (int) (2 * EARTH_RADIUS));

        // Increment angle
        // angle += ANGLE_INCREMENT;
        // angleJP += ANGLE_INCREMENT/2;
        // angleTitan += ANGLE_INCREMENT;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Solar System Simulation");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SolarSystemSimulation panel = new SolarSystemSimulation();
        frame.add(panel);
        frame.setVisible(true);

        System.out.println("test");
        // Repaint the panel every 10 milliseconds
        while (true) {
            panel.repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
