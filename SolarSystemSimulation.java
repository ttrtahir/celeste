import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.net.URL;

public class SolarSystemSimulation extends JPanel implements MouseWheelListener {

    // color array hex
    private static final Color[] COLORS = { new Color(0xffde3b), new Color(0x009973), new Color(0xffad33),
            new Color(0x6fa8dc), new Color(0xa1a2a9), new Color(0xcc7a00), new Color(0x00FFFF),
             new Color(0x99ccff),new Color(0xa1a2a9),new Color(0x0000ff), new Color(0x99d6ff) };

    // size array
    private static final int[] SIZES = { 50, 3, 6, 7, 1, 4, 43, 36, 1, 16, 15 };

    // names
    private static final String[] NAMES = { "Sun", "Mercury", "Venus", "Earth", "Moon", "Mars", "Jupiter", "Saturn",
            "Titan", "Uranus", "Neptune" };

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int notches = -e.getWheelRotation();
        if (notches < 0) {
            SCALE += 1000000;
        } else {
            SCALE -= 1000000;
        }
        SCALE = Math.max(1, SCALE);
    }

    // JPanel
    private static final int FRAME_WIDTH = 1280;
    private static final int FRAME_HEIGHT = 720;

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

    private int SCALE = 10000000;

    SolarSystem system = new SolarSystem();
    double[][] positions = SolarSystem.positions;

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        super.paintComponent(g);
        // Background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.WHITE);
        for (int i = 0; i < positions.length; i++) {
            g.setColor(Color.WHITE);
            // render name
            g.setFont(new Font("Metropolis", Font.BOLD, 16));
            g.drawString(NAMES[i], ((int) (system.celestialBody[i].getX()[0]) / SCALE) + (FRAME_WIDTH / 2) - 10,
                    ((int) system.celestialBody[i].getX()[1] / SCALE) + (FRAME_HEIGHT / 2) - 5);

            if (i < COLORS.length)
                g.setColor(COLORS[i]);

            g.fillOval(((int) system.celestialBody[i].getX()[0] / SCALE) + (FRAME_WIDTH / 2),
                    ((int) system.celestialBody[i].getX()[1] / SCALE) + (FRAME_HEIGHT / 2), SIZES[i], SIZES[i]);

        }
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
        frame.add(panel);
        frame.addMouseWheelListener(panel);
        frame.setVisible(true);

        // Repaint the panel every 10 milliseconds
        while (true) {
            panel.system.calculateForce();

            panel.system.updateAcceleration();
            panel.system.updatePosition();
            // panel.system.updateVelocity();
            frame.repaint();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
