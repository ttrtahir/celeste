package celeste.GUI.Drawables;

import java.awt.Color;
/*
 * Contains methods for creating and coloring the Planet
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import celeste.GUI.GlobalState;

public class Planet extends Drawable {
	@Override
	public void paintComponent(Graphics g) {
		this.g2 = (Graphics2D) g;

		this.draw(g2);
	}

	/* This contains the planet's name, size... */
	private PlanetStats planetStats;
	private Graphics2D g2;

	private int planetPositionX = 0;
	private int planetPositionY = 0;

	// Constructor for saving the planetStats
	public Planet(PlanetStats planetStats) {
		this.planetStats = planetStats;
	}

	// Method for creating the specific planet on specific position
	public void draw(Graphics2D g2) {
		g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
				java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

		calculatePosition();
		drawPlanetName();

		g2.setStroke(new java.awt.BasicStroke(5));
		g2.setColor(planetStats.color);
		/* For missile (space probe), we want to have different visuals */
		if (planetStats.name == "Missile") {
			g2.fill(getPlanet());
			g2.setStroke(new java.awt.BasicStroke(4));
			g2.setColor(Color.WHITE);
			g2.draw(getPlanet());
		} else {
			g2.draw(getPlanet());
		}
	}

	// Calculates the position of the celestialBody
	// Takes into consideration the zoom and the focus
	private void calculatePosition() {
		this.planetPositionX = (int) (planetStats.positionX / GlobalState.SCALE
				+ GlobalState.getCenter()[0]
				- GlobalState.getFocusShiftX()
				- planetStats.size / 2);
		this.planetPositionY = (int) (planetStats.positionY / GlobalState.SCALE
				+ GlobalState.getCenter()[1]
				- GlobalState.getFocusShiftY()
				- planetStats.size / 2);
	}

	/* Draws the planet's name */
	private void drawPlanetName() {
		g2.setColor(Style.fontColor);
		g2.setFont(Style.fontSmall);
		g2.drawString(planetStats.name,
				planetPositionX
						- g2.getFontMetrics().stringWidth(planetStats.name) / 3,
				planetPositionY - 5);
	}

	private Ellipse2D.Double getPlanet() {
		return new Ellipse2D.Double(
				planetPositionX,
				planetPositionY,
				planetStats.size - (int) (GlobalState.SCALE / 500000),
				planetStats.size - (int) (GlobalState.SCALE / 500000));
	}
}