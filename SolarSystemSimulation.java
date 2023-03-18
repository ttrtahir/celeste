import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SolarSystemSimulation extends JPanel {

    //JPanel
    private static final int FRAME_WIDTH = 1980;
    private static final int FRAME_HEIGHT = 1080;

    //Sun
    private static final double SUN_RADIUS = 50;
    private static final int SUN_X = FRAME_WIDTH/2;
    private static final int SUN_Y = FRAME_HEIGHT/2;

    //Planets
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

    public void paintComponent(Graphics g) {

        //Background
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw the Sun
        g.setColor(Color.YELLOW);
        g.fillOval((int) (system.Sun.getX1() + FRAME_WIDTH/2 - SUN_RADIUS), (int) (system.Sun.getX2() + FRAME_HEIGHT/2 - (int)SUN_RADIUS), (int)(2*SUN_RADIUS), (int)(2*SUN_RADIUS));

        // Draw the earth's orbit
//        g.setColor(Color.WHITE);
//        int earthOrbitSize = (int)EARTH_DISTANCE*2;
//        g.drawOval(SUN_X - earthOrbitSize / 2, SUN_Y - earthOrbitSize / 2, earthOrbitSize, earthOrbitSize);

        // Draw the Earth
//        double velocityX =
        double x = FRAME_WIDTH/2 + system.Earth.getX1()/1500000;
        double y = FRAME_HEIGHT/2 + system.Earth.getX2()/1500000;
        g.setColor(Color.BLUE);
        g.fillOval((int)x - (int)EARTH_RADIUS, (int)y - (int)EARTH_RADIUS, (int)(2*EARTH_RADIUS), (int)(2*EARTH_RADIUS));

        // Draw the jupyter's orbit
//        g.setColor(Color.WHITE);
//        int jupyterOrbitSize = (int)JUPYTER_DISTANCE*2;
//        g.drawOval(SUN_X - jupyterOrbitSize / 2, SUN_Y - jupyterOrbitSize / 2, jupyterOrbitSize, jupyterOrbitSize);

        // Draw the Jupyter
//        double jupiterX = FRAME_WIDTH/2 + system.Jupiter.getX1()/1500000;
//        double jupyterY = FRAME_HEIGHT/2 + system.Jupiter.getX2()/1500000;
//        g.setColor(Color.ORANGE);
//        g.fillOval((int)jupiterX - (int)JUPYTER_RADIUS, (int)jupyterY - (int)JUPYTER_RADIUS, (int)(2*JUPYTER_RADIUS), (int)(2*JUPYTER_RADIUS));

        // Draw the Titan's orbit
//        g.setColor(Color.WHITE);
//        int titanOrbitSize = (int)TITAN_DISTANCE_FROM_JUPYTER*2;
//        g.drawOval((int)jupiterX - titanOrbitSize / 2, (int)jupyterY - titanOrbitSize / 2, titanOrbitSize, titanOrbitSize);


        // Draw the Titan
//        double titanX = jupiterX + TITAN_DISTANCE_FROM_JUPYTER*Math.cos(angleTitan);
//        double titanY = jupyterY + TITAN_DISTANCE_FROM_JUPYTER*Math.sin(angleTitan);
//        double titanX = FRAME_WIDTH/2 + system.Titan.getX1()/1500000;
//        double titanY = FRAME_HEIGHT/2 + system.Titan.getX2()/1500000;
//        g.setColor(Color.white);
//        g.fillOval((int)titanX - (int)TITAN_RADIUS, (int)titanY - (int)TITAN_RADIUS, (int)(2*TITAN_RADIUS), (int)(2*TITAN_RADIUS));

        // Increment angle
//        angle += ANGLE_INCREMENT;
//        angleJP += ANGLE_INCREMENT/2;
//        angleTitan += ANGLE_INCREMENT;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Solar System Simulation");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SolarSystemSimulation panel = new SolarSystemSimulation();
        frame.add(panel);
        frame.setVisible(true);

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
