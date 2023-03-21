import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.*;

public class Rocket extends JPanel {

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Color darkRed = new Color(180, 0, 0);

        g.setColor(Color.WHITE);
        g.fillPolygon(new int[] { 100, 115, 130 }, new int[] { 100, 50, 100 }, 3);

        g.setColor(darkRed);
        g.fillRect(100, 100, 30, 40);

        g.setColor(Color.WHITE);
        g.fillRect(100, 140, 30, 20);

        g.setColor(darkRed);
        g.fillRect(100, 160, 30, 40);

        g.setColor(Color.WHITE);
        g.fillPolygon(new int[] { 140, 130, 130 }, new int[] { 240, 200, 240 }, 3);
        g.fillPolygon(new int[] { 90, 100, 100 }, new int[] { 240, 200, 240 }, 3);
        g.fillRect(100, 200, 30, 40);

        g.setColor(darkRed);
        g.fillRect(90, 240, 50, 60);

        g.setColor(Color.WHITE);
        g.fillRect(90, 300, 50, 60);

        g.setColor(darkRed);
        g.fillPolygon(new int[] { 70, 90, 90 }, new int[] { 420, 360, 420 }, 3);
        g.fillPolygon(new int[] { 160, 140, 140 }, new int[] { 420, 360, 420 }, 3);
        g.fillRect(90, 360, 50, 60);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setTitle("Rocket");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        Rocket panel = new Rocket();
        frame.add(panel);
        frame.setVisible(true);

    }

}
