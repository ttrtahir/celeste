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

    static CelestialBody[] planets;

    private int SCALE = 100000000;

    SolarSystem system = new SolarSystem();
    double[][] positions = SolarSystem.positions;

    public void paintComponent(Graphics g) {

        // Background
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.WHITE);
        for (int i = 0; i < positions.length; i++) {
            g.fillOval(((int) planets[i].getX()[0] / SCALE) + (FRAME_WIDTH / 2),
                    ((int) planets[i].getX()[1] / SCALE) + (FRAME_HEIGHT / 2), 10, 10);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Solar System Simulation");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SolarSystemSimulation panel = new SolarSystemSimulation();
        frame.add(panel);
        frame.setVisible(true);

        planets = new CelestialBody[11];

        for (int i = 0; i < SolarSystem.positions.length; i++) {
            planets[i] = new CelestialBody(SolarSystem.positions[i], SolarSystem.velocity[i], SolarSystem.mass[i][0]);
        }

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
