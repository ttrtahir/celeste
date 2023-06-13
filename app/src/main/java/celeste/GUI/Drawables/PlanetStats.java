package app.src.main.java.celeste.GUI.Drawables;

import java.awt.Color;

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

        // System.out.println(name + " " + positionX + " " + positionY);
    }
}