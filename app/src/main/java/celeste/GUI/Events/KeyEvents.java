package celeste.GUI.Events;

import java.awt.event.KeyListener;
import java.util.ArrayList;

import celeste.GUI.GlobalState;
import celeste.GUI.Drawables.PlanetStats;

/*
 * Contains methods for the mouse events
 */

public class KeyEvents implements KeyListener {
    private ArrayList<PlanetStats> planetStats;
    private int currentIndex = 0;
    private int[] focuseablePlanets = { 0, 3, 8, 11, };

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
        } else if (e.getKeyCode() == java.awt.event.KeyEvent.VK_UP) {
            GlobalState.simulationSpeed -= GlobalState.simulationSpeedIncrement;
            GlobalState.simulationSpeed = Math.max(1, GlobalState.simulationSpeed);
        } else if (e.getKeyCode() == java.awt.event.KeyEvent.VK_DOWN) {
            GlobalState.simulationSpeed += GlobalState.simulationSpeedIncrement;
        } else if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {
    }

    @Override
    public void keyTyped(java.awt.event.KeyEvent e) {
    }
}
