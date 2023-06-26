package celeste.GUI.Drawables;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import celeste.GUI.GlobalState;
import celeste.Simulator.State;

/* Draws the space probe's trajectory and planets' orbits */
public class Trajectory extends Drawable {
    private Graphics2D g2;

    private State[] states;
    private int index;
    private int currentStateIndex = 0;
    private boolean spaceProbe = false;

    public Trajectory(int index, boolean spaceProbe) {
        this(index);
        this.spaceProbe = spaceProbe;
    }

    public Trajectory(int index) {
        this.states = GlobalState.states;
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
        /* Different opacities for space probe and planet's orbit */
        if (spaceProbe) {
            g2.setStroke(new java.awt.BasicStroke(2));
            g2.setColor(new Color(255, 255, 255, 200));
        } else {
            g2.setStroke(new java.awt.BasicStroke(1));
            g2.setColor(new Color(255, 255, 255, 60));
        }

        /* Take into consideration the focus shift of the GUI and the zoom */
        int oldX = (int) states[0].state[index][0].getX() / GlobalState.SCALE
                + GlobalState.getCenter()[0]
                - GlobalState.getFocusShiftX();
        int oldY = (int) states[0].state[index][0].getY() / GlobalState.SCALE
                + GlobalState.getCenter()[1]
                - GlobalState.getFocusShiftY();

        /*
         * This makes the simulation more optimized. We don't want to draw everything
         * the ODE solvers calculate, because it is useless for the user and slows
         * down the simulation drastically.
         */
        int increment = (int) (5 / (GlobalState.STEP_MULTIPLIER));
        if (spaceProbe) {
            increment = (int) (0.5 / (GlobalState.STEP_MULTIPLIER));
        }
        for (int i = 1; i < states.length; i += increment) {
            if (spaceProbe && i > currentStateIndex) {
                /* This draws the trajectory that the probe WILL undergo red */
                g2.setColor(new Color(255, 0, 0, 200));
            }

            /* Take into consideration the focus shift of the GUI and the zoom */
            int posX = (int) states[i].state[index][0].getX()
                    / GlobalState.SCALE
                    + GlobalState.getCenter()[0]
                    - GlobalState.getFocusShiftX() - 1;
            int posY = (int) states[i].state[index][0].getY()
                    / GlobalState.SCALE
                    + GlobalState.getCenter()[1]
                    - GlobalState.getFocusShiftY() - 1;
            g2.drawLine(oldX, oldY, posX, posY);

            oldX = posX;
            oldY = posY;
        }
    }

    /* For space probe to achieve the red/white trajectory line */
    public void updateCurrentStateIndex(int index) {
        this.currentStateIndex = index;
    }
}
