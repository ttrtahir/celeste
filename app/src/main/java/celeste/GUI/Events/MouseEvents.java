package celeste.GUI.Events;

/*
 * Contains methods for the mouse events
 */
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import celeste.GUI.GlobalState;

public class MouseEvents implements MouseWheelListener {
    public MouseEvents() {
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int notches = -e.getWheelRotation();
        int scaleIncrease = 300000;

        if (GlobalState.SCALE < 100001) {
            scaleIncrease = 1000;
        } else if (GlobalState.SCALE <= 900001) {
            scaleIncrease = 20000;
        } else {
            scaleIncrease = 300000;
        }
        if (notches < 0) {
            GlobalState.SCALE += scaleIncrease;
        } else {
            GlobalState.SCALE -= scaleIncrease;
        }
        GlobalState.SCALE = Math.max(1, GlobalState.SCALE);
    }
}
