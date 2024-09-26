package Display;
import javax.swing.JPanel;

import entity.Entity;

import java.awt.*;
import input.KeyHandler;
import util.Size;

public class GamePanel extends JPanel { // toile dans laquelle tout sera dessiné (toile du tableau)

  private KeyHandler keyHandler;
  private Size displaySize;

  public GamePanel(Size displaySize) {
    this.displaySize = displaySize;
    setPreferredSize(new Dimension(displaySize.getWidth(), displaySize.getHeight()));
    setFocusable(true);
    keyHandler = new KeyHandler();
    addKeyListener(keyHandler);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, displaySize.getWidth(), displaySize.getHeight());
    g.setColor(Color.WHITE);
    Entity.getEntities().forEach(entity -> entity.render((Graphics2D) g)); // appelle la fonction "render" de chaque entité
    g.dispose();
  }

  public KeyHandler getKeyHandler() {
    return keyHandler;
  }
}
