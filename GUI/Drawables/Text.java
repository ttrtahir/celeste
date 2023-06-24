package GUI.Drawables;

import java.awt.Color;
/*
 * Contains methods for creating and coloring the Planet
 */
import java.awt.Graphics;
import java.awt.Graphics2D;

import GUI.GlobalState;

public class Text extends Drawable {
    private String text = "Hello world!";
    private int x = 0;
    private int y = 0;

    private boolean center = false;
    private boolean right = false;
    private boolean bottom = false;
    private boolean top = false;

    private boolean bigFont = false;

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

    public Text(int x, int y, String horizontalPosition) {
        this.x = x;
        this.y = y;
        if (horizontalPosition == "center") {
            this.center = true;
        } else if (horizontalPosition == "right") {
            this.right = true;
        } else if (horizontalPosition == "bottom") {
            this.bottom = true;
        }
    }

    public Text(String horizontalPosition, String verticalPosition) {
        this.x = 0;
        this.y = 200;
        if (horizontalPosition == "center") {
            this.center = true;
        } else if (horizontalPosition == "right") {
            this.right = true;
        } else if (horizontalPosition == "left") {
            this.x = 20;
        }

        if (verticalPosition == "bottom") {
            this.bottom = true;
        } else if (verticalPosition == "top") {
            this.top = true;
        }
    }

    // Method for creating the specific planet on specific position
    public void draw(Graphics2D g2) {
        g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
                java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setFont(Style.font);
        if (this.bigFont) {
            g2.setFont(Style.fontBig);
        }
        g2.setColor(Color.WHITE);

        int tempX = this.x;
        int tempY = this.y;
        if (center) {
            tempX = this.x - g2.getFontMetrics().stringWidth(this.text) / 2;
        }
        if (right) {
            tempX = GlobalState.FRAME_WIDTH - g2.getFontMetrics().stringWidth(this.text) - 20;
        }
        if (bottom) {
            tempY = GlobalState.FRAME_HEIGHT - 90;
        }
        if (top) {
            tempY = 30;
        }
        g2.drawString(this.text, tempX, tempY);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setBigFont(boolean bigFont) {
        this.bigFont = bigFont;
    }
}