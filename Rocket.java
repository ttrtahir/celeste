import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Group;

import java.awt.*;

public class Rocket extends JPanel {

	private int xCoordinate;
	private int yCoordinate;
	private int width;
	private int height;

	public Rocket(int xCoordinate, int yCoordinate, int width, int height) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.width = width;
		this.height = height;
	}

	public void paintComponent(Graphics g) {

		Color darkRed = new Color(180, 0, 0);
		Graphics2D g2 = (Graphics2D) g;
		g2.scale(0.3, 0.3);

		// Top
		g.setColor(Color.WHITE);
		g.fillPolygon(new int[] { xCoordinate - 3, xCoordinate + 5, xCoordinate + 13 },
				new int[] { yCoordinate, yCoordinate - 25, yCoordinate }, 3);

		// first block
		g.setColor(darkRed);
		g.fillRect(xCoordinate, yCoordinate, width, height);

		// second block
		g.setColor(Color.WHITE);
		g.fillRect(xCoordinate, yCoordinate + 20, width, height - 10);

		// third block
		g.setColor(darkRed);
		g.fillRect(xCoordinate, yCoordinate + 30, width, height);

		// middelpart
		g.setColor(Color.WHITE);
		g.fillPolygon(new int[] { xCoordinate - 13, xCoordinate - 3, xCoordinate - 3 },
				new int[] { yCoordinate + 70, yCoordinate + 50, yCoordinate + 70 }, 3);
		g.fillPolygon(new int[] { xCoordinate + 23, xCoordinate + 13, xCoordinate + 13 },
				new int[] { yCoordinate + 70, yCoordinate + 50, yCoordinate + 70 }, 3);
		g.fillRect(xCoordinate, yCoordinate + 50, width, height);

		// block
		g.setColor(darkRed);
		g.fillRect(xCoordinate - 10, yCoordinate + 70, width + 18, height + 10);

		// block
		g.setColor(Color.WHITE);
		g.fillRect(xCoordinate - 10, yCoordinate + 100, width + 18, height + 10);

		// last part
		g.setColor(darkRed);
		g.fillPolygon(new int[] { xCoordinate - 20, xCoordinate - 10, xCoordinate - 10 },
				new int[] { yCoordinate + 160, yCoordinate + 130, yCoordinate + 160 }, 3);
		g.fillPolygon(new int[] { xCoordinate + 35, xCoordinate + 25, xCoordinate + 25 },
				new int[] { yCoordinate + 160, yCoordinate + 130, yCoordinate + 160 }, 3);
		g.fillRect(xCoordinate - 10, yCoordinate + 130, width + 20, height + 10);
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
