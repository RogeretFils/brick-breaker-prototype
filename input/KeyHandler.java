package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;;

public class KeyHandler implements KeyListener {

  private boolean[] pressed;

  public KeyHandler() {
    pressed = new boolean[256];
  }

  public void keyTyped(KeyEvent e) {
  }

  public void keyPressed(KeyEvent e) {
    pressed[e.getKeyCode()] = true;
  }

  public void keyReleased(KeyEvent e) {
    pressed[e.getKeyCode()] = false;
  }

  public boolean isPressed(char input) {
    return pressed[(int) input];
  }
}
