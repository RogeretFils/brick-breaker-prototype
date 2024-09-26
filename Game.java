import entity.*;
import util.*;

public class Game { // classe principale

  private Size displaySize;
  private GamePanel gamePanel;
  private Display display;

  public Game() {
    displaySize = new Size(1000, 1000);
    gamePanel = new GamePanel(displaySize);
    display = new Display(gamePanel);
    Entity.setDisplaySize(displaySize);

    // Entities
    new PlayerBar(gamePanel.getKeyHandler());
    new Ball(new Spot(300, 600), new Size(32, 32));

    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 3; j++) {
        new Brick(new Spot(i * 160 + 50, j * 100 + 50), new Size(140, 80));
      }
    } 
  
  }

  public void update() {
    Entity.getEntities().forEach(entity -> entity.update());
  }

  public void render() {
    gamePanel.repaint();
  }
}
