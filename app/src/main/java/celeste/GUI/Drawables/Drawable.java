package celeste.GUI.Drawables;

/*
 * Interface class for drawing objects
 * Each class that implements this class must have a draw function
 * that takes in a Graphics2D object as a parameter. Then a paintComponent
 * function must be created that calls the draw function.
 */
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Drawable extends JPanel {

    public void draw(Graphics2D g2) {
    };
}
