package GUI;

import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.lang.model.util.ElementScanner6;

/*
 * Contains methods for the mouse events
 */

public class KeyEvents implements KeyListener {
    private ArrayList<PlanetStats> planetStats;
    private int currentIndex = 0;
    private int[] focuseablePlanets = { 0, 3, 7, 11 };

    public KeyEvents(ArrayList<PlanetStats> planetStats) {
        this.planetStats = planetStats;
    }

    public void setPlanetStats(ArrayList<PlanetStats> planetStats) {
        this.planetStats = planetStats;
    }

    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
        if (e.getKeyCode() == java.awt.event.KeyEvent.VK_LEFT) {
            if (currentIndex == 0) {
                return;
            }

            currentIndex--;
            GlobalState.planetFocused = planetStats.get(focuseablePlanets[currentIndex]);
        } else if (e.getKeyCode() == java.awt.event.KeyEvent.VK_RIGHT) {
            if (currentIndex == focuseablePlanets.length - 1) {
                return;
            }

            currentIndex++;
            GlobalState.planetFocused = planetStats.get(focuseablePlanets[currentIndex]);
        } else if (e.getKeyCode() == java.awt.event.KeyEvent.VK_SPACE) {
            GlobalState.paused = !GlobalState.paused;
        }
    }

    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {
    }

    @Override
    public void keyTyped(java.awt.event.KeyEvent e) {
    }
}
