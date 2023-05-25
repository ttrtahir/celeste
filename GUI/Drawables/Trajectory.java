package GUI.Drawables;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import GUI.GlobalState;
import Simulator.State;

public class Trajectory extends Drawable {
    private Graphics2D g2;

    private State[] states;
    private int index;
    private int currentStateIndex = 0;

    public Trajectory(State[] states, int index) {
        this.states = states;
        this.index = index;
    }

    @Override
    public void paintComponent(Graphics g) {
        this.g2 = (Graphics2D) g;

        this.draw(g2);
    }

    public void draw(Graphics2D g2) {
        g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
                java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setFont(Style.fontBig);
        g2.setStroke(new java.awt.BasicStroke(2));

        int oldX = (int) states[0].state[index][0].getX() / GlobalState.SCALE
                + GlobalState.getCenter()[0]
                - GlobalState.getFocusShiftX();
        int oldY = (int) states[0].state[index][0].getY() / GlobalState.SCALE
                + GlobalState.getCenter()[1]
                - GlobalState.getFocusShiftY();

        g2.setColor(new Color(255, 255, 255, 200));
        for (int i = 1; i < states.length; i += 20) {
            if (i - 10 > currentStateIndex) {
                g2.setColor(new Color(255, 0, 0, 200));
            }
            int posX = (int) states[i].state[index][0].getX()
                    / GlobalState.SCALE
                    + GlobalState.getCenter()[0]
                    - GlobalState.getFocusShiftX() - 1;
            int posY = (int) states[i].state[index][0].getY()
                    / GlobalState.SCALE
                    + GlobalState.getCenter()[1]
                    - GlobalState.getFocusShiftY() - 1;
            g2.drawLine(oldX, oldY, posX, posY);

            System.out.println(oldX + " " + oldY + " " + posX + " " + posY);

            oldX = posX;
            oldY = posY;
        }
    }

    public void updateCurrentStateIndex(int index) {
        this.currentStateIndex = index;
    }
}
