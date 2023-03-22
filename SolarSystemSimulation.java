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
import java.awt.geom.Ellipse2D;
import javax.swing.JComboBox;

public class SolarSystemSimulation extends JPanel implements MouseWheelListener {

    // JPanel
    private static final int FRAME_WIDTH = 1280;
    private static final int FRAME_HEIGHT = 720;

    private int rocketX = 50;
    private int rocketY = 50;

    private boolean pause = false;

    private int SCALE = 2500000;

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
            ((Graphics2D) g).draw(planet);
            g.setColor(Color.BLACK);
            ((Graphics2D) g).fill(planet);

            // draw string in the middle of the oval
            g.setColor(Color.WHITE);
            g.setFont(new Font("Metropolis", Font.BOLD, 6));
            // Convert int to scientific notation
            /*
             * String shorterNumber = String.format("%.2e", planetsSortedByZ[i].getX()[2]);
             * g.drawString(shorterNumber,
             * ((int) planetsSortedByZ[i].getX()[0] / SCALE) + (FRAME_WIDTH / 2)
             * + planetsSortedByZ[i].getSize() / 2
             * - ((planetsSortedByZ[i].getX()[2] + "").length() / 2 * 4),
             * ((int) planetsSortedByZ[i].getX()[1] / SCALE) + (FRAME_HEIGHT / 2) + size / 2
             * + 5);
             */
        }
    }

    private static double[] focusScale = new double[2];

    public void changeFocus(JComboBox<String> planetList) {
        int index = planetList.getSelectedIndex();
        focusScale = system.celestialBody[index].getX();
        System.out.println(focusScale[0]);
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
        panel.add(planetsList);
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
            // try {
            // Thread.sleep(100);
            // } catch (InterruptedException e) {
            // e.printStackTrace();
            // }

        }
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
