import entity.*;
import util.*;

public class Game { // classe principale

  private Size displaySize;
  private GamePanel gamePanel;
  private Display display;

  public Game() {
    displaySize = new Size(1280, 1000);
    gamePanel = new GamePanel(displaySize);
    display = new Display(gamePanel);
    Entity.setDisplaySize(displaySize);

    // Entities
    new PlayerBar(gamePanel.getKeyHandler());
    new Ball(new Spot(300, 600), new Size(100, 100));
    new Ball(new Spot(300, 600), new Size(60, 60));
    new Ball(new Spot(200, 600), new Size(40, 40));
    new Ball(new Spot(400, 600), new Size(32, 32));
    new Ball(new Spot(500, 600), new Size(20, 20));

    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 3; j++) {
        new Brick(new Spot(i * 156 + 19, j * 105 + 19), new Size(150, 100));
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
