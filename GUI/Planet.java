package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Planet extends Drawable {

	@Override
	public void paintComponent(Graphics g) {
		this.g2 = (Graphics2D) g;

		this.draw(g2);
	}

	private CelestialBody celestialBody;
	private PlanetStats planetStats;
	private Graphics2D g2;

	private int planetPositionX = 0;
	private int planetPositionY = 0;

	public Planet(CelestialBody celestialBody, PlanetStats planetStats) {
		this.celestialBody = celestialBody;
		this.planetStats = planetStats;
	}

	public void draw(Graphics2D g2) {
		g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
				java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

		calculatePosition();
		drawPlanetName();

		g2.setStroke(new java.awt.BasicStroke(3));
		g2.setColor(planetStats.color);
		g2.draw(getPlanet());
	}

	private void calculatePosition() {
		this.planetPositionX = (int) (celestialBody.posX / GlobalState.SCALE
				+ GlobalState.getCenter()[0]
				- GlobalState.getFocusShiftX()
				- planetStats.size / 2);
		this.planetPositionY = (int) (celestialBody.posY / GlobalState.SCALE
				+ GlobalState.getCenter()[1]
				- GlobalState.getFocusShiftY()
				- planetStats.size / 2);
	}

	private void drawPlanetName() {
		g2.setColor(Style.fontColor);
		g2.setFont(Style.font);
		g2.drawString(planetStats.name,
				planetPositionX
						- planetStats.name.length() * 3,
				planetPositionY
						- planetStats.size / 2 - 5);
	}

	private Ellipse2D.Double getPlanet() {
		return new Ellipse2D.Double(
				planetPositionX,
				planetPositionY,
				planetStats.size, planetStats.size);
	}
}
