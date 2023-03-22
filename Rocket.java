import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Group;

import java.awt.*;

public class Rocket extends JPanel {

    private int xCoördinate;
    private int yCoördinate;
    private int width;
    private int height;

    public Rocket(int xCoördinate, int yCoördinate, int width, int height) {
        this.xCoördinate = xCoördinate;
        this.yCoördinate = yCoördinate;
        this.width = width;
        this.height = height;
    }

    public void paintComponent(Graphics g) {

        Color darkRed = new Color(180, 0, 0);
        Graphics2D g2 = (Graphics2D) g;
        g2.scale(0.3, 0.3);

        // Top
        g.setColor(Color.WHITE);
        g.fillPolygon(new int[] { xCoördinate - 3, xCoördinate + 5, xCoördinate + 13 },
                new int[] { yCoördinate, yCoördinate - 25, yCoördinate }, 3);

        // first block
        g.setColor(darkRed);
        g.fillRect(xCoördinate, yCoördinate, width, height);

        // second block
        g.setColor(Color.WHITE);
        g.fillRect(xCoördinate, yCoördinate + 20, width, height - 10);

        // third block
        g.setColor(darkRed);
        g.fillRect(xCoördinate, yCoördinate + 30, width, height);

        // middelpart
        g.setColor(Color.WHITE);
        g.fillPolygon(new int[] { xCoördinate - 13, xCoördinate - 3, xCoördinate - 3 },
                new int[] { yCoördinate + 70, yCoördinate + 50, yCoördinate + 70 }, 3);
        g.fillPolygon(new int[] { xCoördinate + 23, xCoördinate + 13, xCoördinate + 13 },
                new int[] { yCoördinate + 70, yCoördinate + 50, yCoördinate + 70 }, 3);
        g.fillRect(xCoördinate, yCoördinate + 50, width, height);

        // block
        g.setColor(darkRed);
        g.fillRect(xCoördinate - 10, yCoördinate + 70, width + 18, height + 10);

        // block
        g.setColor(Color.WHITE);
        g.fillRect(xCoördinate - 10, yCoördinate + 100, width + 18, height + 10);

        // last part
        g.setColor(darkRed);
        g.fillPolygon(new int[] { xCoördinate - 20, xCoördinate - 10, xCoördinate - 10 },
                new int[] { yCoördinate + 160, yCoördinate + 130, yCoördinate + 160 }, 3);
        g.fillPolygon(new int[] { xCoördinate + 35, xCoördinate + 25, xCoördinate + 25 },
                new int[] { yCoördinate + 160, yCoördinate + 130, yCoördinate + 160 }, 3);
        g.fillRect(xCoördinate - 10, yCoördinate + 130, width + 20, height + 10);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setSize(1000, 500);
        frame.setTitle("Rocket");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        // frame.setResizable(false);
        // frame.setVisible(true);
        Rocket panel = new Rocket(50, 50, 16, 20);
        frame.add(panel);
        // Rocket panel2 = new Rocket(50, 50, 15, 20);
        panel.setBounds(200, 50, 200, 400);
        // frame.add(panel2);

        frame.setVisible(true);

    }
}
