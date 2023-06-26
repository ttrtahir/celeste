package celeste.GUI.Drawables;

import java.awt.Color;

/* 
 * A class holding the information for a planet
 */
public class PlanetStats {
    public String name;
    public int size;
    public Color color;

    public int positionX = 0;
    public int positionY = 0;

    public PlanetStats(String name, int size, Color color) {
        this.name = name;
        this.size = size;
        this.color = color;
    }

    public void setPos(int x, int y) {
        this.positionX = x;
        this.positionY = y;
    }
}