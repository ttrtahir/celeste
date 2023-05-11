package GUI;
/*
 * Contains methods for creating and coloring the Planet
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import Simulator.CelestialBody;

public class Planet extends Drawable {

	@Override
	public void paintComponent(Graphics g) {
		this.g2 = (Graphics2D) g;

		this.draw(g2);
	}

	private CelestialBody celestialBody;
	private Graphics2D g2;

	private int planetPositionX = 0;
	private int planetPositionY = 0;
	// Constructor for creating the celestialBody
	public Planet(CelestialBody celestialBody) {
		this.celestialBody = celestialBody;
	}
	//Method for creating the specific planet on specific position
	public void draw(Graphics2D g2) {
		g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
				java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

		calculatePosition();
		drawPlanetName();

		g2.setStroke(new java.awt.BasicStroke(3));
		g2.setColor(celestialBody.color);
		g2.draw(getPlanet());
	}
	//Calculates the position of the celestialBody
	private void calculatePosition() {
		this.planetPositionX = (int) (celestialBody.posX / GlobalState.SCALE
				+ GlobalState.getCenter()[0]
				- GlobalState.getFocusShiftX()
				- celestialBody.size / 2);
		this.planetPositionY = (int) (celestialBody.posY / GlobalState.SCALE
				+ GlobalState.getCenter()[1]
				- GlobalState.getFocusShiftY()
				- celestialBody.size / 2);
	}

	private void drawPlanetName() {
		g2.setColor(Style.fontColor);
		g2.setFont(Style.font);
		g2.drawString(celestialBody.name,
				planetPositionX
						- celestialBody.name.length() * 3,
				planetPositionY
						- celestialBody.size / 2 - 5);
	}

	private Ellipse2D.Double getPlanet() {
		return new Ellipse2D.Double(
				planetPositionX,
				planetPositionY,
				celestialBody.size, celestialBody.size);
	}
}
