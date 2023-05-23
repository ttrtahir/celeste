package GUI;

import java.awt.Color;
/*
 * Contains methods for creating and coloring the Planet
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Text extends Drawable {
    private String text = "Hello world!";
    private int x = 0;
    private int y = 0;

    @Override
    public void paintComponent(Graphics g) {
        this.g2 = (Graphics2D) g;

        this.draw(g2);
    }

    private Graphics2D g2;

    // Constructor for creating the planetStats
    public Text(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Method for creating the specific planet on specific position
    public void draw(Graphics2D g2) {
        g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
                java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setFont(Style.font);
        g2.setColor(Color.WHITE);
        g2.drawString(this.text, this.x, this.y);
    }

    public void setText(String text) {
        this.text = text;
    }
}