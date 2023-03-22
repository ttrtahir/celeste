import java.awt.*;

public class DrawFunctions {
    static Graphics g;
    static Graphics2D g2;

    static int STAR_COUNT = 500;
    static int[] STAR_SIZES = new int[500];
    static int[][] starPositions = new int[STAR_COUNT][3];
    static {
        for (int i = 0; i < STAR_COUNT; i++) {
            starPositions[i][0] = (int) (Math.random() * 2000);
            starPositions[i][1] = (int) (Math.random() * 1200);
            starPositions[i][2] = (int) (Math.random() * 200);

            STAR_SIZES[i] = (int) (Math.random() * 2 + 1 + starPositions[i][2] / 255 * 2);
        }
    }

    public static void setG(Graphics G) {
        g = G;
        g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
                RenderingHints.VALUE_STROKE_PURE);
    }

    public static void drawBackground() {
        // set HEX color
        g.setColor(Color.decode("#000"));
        g.fillRect(0, 0, 2000, 2000);
    }

    public static void drawBackgroundStars() {
        // set HEX color
        for (int i = 0; i < STAR_COUNT; i++) {
            g.setColor(new Color(255, 255, 255, starPositions[i][2]));
            g.fillOval(starPositions[i][0], starPositions[i][1], STAR_SIZES[i], STAR_SIZES[i]);
        }
    }
}