package app.src.main.java.celeste.GUI.Drawables;

/*
 * Contains a custom style
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.net.URL;

import app.src.main.java.celeste.GUI.Resources.FontFamily;

public class Style {
    public static Color fontColor = Color.WHITE;
    public static Font fontSmall = new Font("Metropolis", Font.BOLD, 12);
    public static Font font = new Font("Metropolis", Font.BOLD, 20);
    public static Font fontBig = new Font("Metropolis", Font.BOLD, 24);

    public Style() {
        try {
            URL url = FontFamily.class.getResource("Metropolis-Bold.otf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, url.openStream());
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
