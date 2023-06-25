package celeste.Simulator.LandingModule;
/*
 * A class that contains a very basic 2D GUI representation of the spaceship's landing.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LandingSimulatorGUI extends JFrame {
    private Spaceship spaceship;
    private static final int APPROACHING_Y = 6000;  //The Y coordinate where the spaceship starts moving to the bottom of the screen

    public LandingSimulatorGUI(Spaceship spaceship) {
        this.spaceship = spaceship;

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new SpaceshipPanel());
        setVisible(true);

        // Create a timer that updates every 10 milliseconds
        new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the spaceship's state
                spaceship.updateState(0.1);

                // Repaint the GUI
                repaint();
            }
        }).start();
    }

    class SpaceshipPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            int width = this.getWidth();
            int height = this.getHeight();
        
            int drawX = width / 2;
            int drawY = height / 2;

            if (spaceship.getY() <= APPROACHING_Y) {
                drawY = height - (int)(spaceship.getY() / 20000 * height) - 40;
            }

            //Create the spaceship image
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.rotate(-spaceship.getTheta(), drawX, drawY + 40); //rotate around bottom center of the rocket
        
            g2d.setColor(Color.RED);
            g2d.fillRect(drawX - 10, drawY, 20, 40); 

            Polygon head = new Polygon();
            head.addPoint(drawX, drawY - 10); 
            head.addPoint(drawX - 10, drawY);  
            head.addPoint(drawX + 10, drawY);  
            g2d.fillPolygon(head);

            Polygon fin1 = new Polygon();
            fin1.addPoint(drawX - 10, drawY + 30); 
            fin1.addPoint(drawX - 10, drawY + 40);
            fin1.addPoint(drawX - 20, drawY + 30); 
            g2d.fillPolygon(fin1);

            Polygon fin2 = new Polygon();
            fin2.addPoint(drawX + 10, drawY + 30);  
            fin2.addPoint(drawX + 10, drawY + 40);  
            fin2.addPoint(drawX + 20, drawY + 30);  
            g2d.fillPolygon(fin2);

        
            g2d.dispose(); 
        }
    }        
        
    public static void main(double x, double y, double vX, double vY) {
        
        Spaceship spaceship = new Spaceship(x, y, -1, 0, 0, vX, vY, 3, new Engine2(10 * Environment.GRAVITY, 1), new Wind());

        SwingUtilities.invokeLater(() -> {
            new LandingSimulatorGUI(spaceship);
        });
    }
}
