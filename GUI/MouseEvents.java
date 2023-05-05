package GUI;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseEvents implements MouseWheelListener {
    public MouseEvents() {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int notches = -e.getWheelRotation();
        if (notches < 0) {
            GlobalState.SCALE += 1;
        } else {
            GlobalState.SCALE -= 1;
        }
        GlobalState.SCALE = Math.max(1, GlobalState.SCALE);
    }
}
