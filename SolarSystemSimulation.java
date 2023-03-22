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

    private boolean pause = false;

    private int SCALE = 10000000;

    SolarSystem system = new SolarSystem();

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        super.paintComponent(g);
        // Background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

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
        for (CelestialBody planet : planetsSortedByZ) {
//            System.out.println(planet.getX()[2]);
        }

        for (int i = 0; i < system.celestialBody.length; i++) {
            g.setColor(Color.WHITE);
            // render name
            g.setFont(new Font("Metropolis", Font.BOLD, 16));
            g.drawString(planetsSortedByZ[i].getName(),
                    ((int) (planetsSortedByZ[i].getX()[0]) / SCALE) + (FRAME_WIDTH / 2) - 10,
                    ((int) planetsSortedByZ[i].getX()[1] / SCALE) + (FRAME_HEIGHT / 2) - 5);

            g.setColor(planetsSortedByZ[i].getColor());

            int size = planetsSortedByZ[i].getSize();
            g.fillOval(((int) planetsSortedByZ[i].getX()[0] / SCALE) + (FRAME_WIDTH / 2),
                    ((int) planetsSortedByZ[i].getX()[1] / SCALE) + (FRAME_HEIGHT / 2),
                    size,
                    size);

            // draw string in the middle of the oval
            g.setColor(Color.WHITE);
            g.setFont(new Font("Metropolis", Font.BOLD, 12));
            // Convert int to scientific notation
            String shorterNumber = String.format("%.2e", planetsSortedByZ[i].getX()[2]);
            g.drawString(shorterNumber,
                    ((int) planetsSortedByZ[i].getX()[0] / SCALE) + (FRAME_WIDTH / 2)
                            + planetsSortedByZ[i].getSize() / 2
                            - ((planetsSortedByZ[i].getX()[2] + "").length() / 2 * 4),
                    ((int) planetsSortedByZ[i].getX()[1] / SCALE) + (FRAME_HEIGHT / 2)
                            + planetsSortedByZ[i].getSize() + 10);
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
                    }
                });
        frame.setVisible(true);

        // Repaint the panel every 10 milliseconds
        while (true) {
            if (!panel.pause) {
                panel.system.calculateForce();
                panel.system.updateAcceleration();
                panel.system.updatePosition();
                panel.system.updateVelocity();
            }
            frame.repaint();
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }

}
