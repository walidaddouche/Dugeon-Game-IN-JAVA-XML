package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean UpPressed, DownPressed, LeftPressed, RightPressed;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_S) {
            DownPressed = true;
        }
        if (code == KeyEvent.VK_Z) {
            UpPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            RightPressed = true;
        }
        if (code == KeyEvent.VK_Q) {
            LeftPressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_S) {
            DownPressed = false;
        }
        if (code == KeyEvent.VK_Z) {
            UpPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            RightPressed = false;
        }
        if (code == KeyEvent.VK_Q) {
            LeftPressed = false;
        }
    }
}
