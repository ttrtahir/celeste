package GUI;

import java.awt.event.KeyListener;
import java.util.ArrayList;

/*
 * Contains methods for the mouse events
 */

public class KeyEvents implements KeyListener {
    private ArrayList<PlanetStats> planetStats;

    public KeyEvents(ArrayList<PlanetStats> planetStats) {
        this.planetStats = planetStats;
    }

    public void setPlanetStats(ArrayList<PlanetStats> planetStats) {
        this.planetStats = planetStats;
    }

    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
        if (e.getKeyCode() == java.awt.event.KeyEvent.VK_LEFT) {
            /*
             * Need a way to bind it to planetStats, because now it just sets to current
             * position and stays
             */
            GlobalState.focusShift[0] = planetStats.get(3).positionX;
            GlobalState.focusShift[1] = planetStats.get(3).positionY;
        } else if (e.getKeyCode() == java.awt.event.KeyEvent.VK_DOWN) {
            GlobalState.SCALE -= 100000;
        }
        GlobalState.SCALE = Math.max(1, GlobalState.SCALE);
    }

    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {
    }

    @Override
    public void keyTyped(java.awt.event.KeyEvent e) {
    }
}
