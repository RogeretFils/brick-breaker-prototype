package Game;
import Display.Display;
import Display.GamePanel;
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
    new Ball(new Spot(500, 600), new Size(100, 100));
    new Ball(new Spot(580, 600), new Size(60, 60));
    new Ball(new Spot(660, 600), new Size(40, 40));
    new Ball(new Spot(740, 600), new Size(32, 32));
    new Ball(new Spot(820, 600), new Size(20, 20));
    Brick.createGrid(8, 4, 10);
  
  }

  public void update() {
    if (gamePanel.getKeyHandler().isPressed('R')) {
      resetGame();
    }
    Entity.getEntities().forEach(entity -> entity.update());
  }

  public void render() {
    gamePanel.repaint();
  }

  public void resetGame() {
    Entity.clearEntities();
    new PlayerBar(gamePanel.getKeyHandler());
    new Ball(new Spot(500, 600), new Size(100, 100));
    new Ball(new Spot(580, 600), new Size(60, 60));
    new Ball(new Spot(660, 600), new Size(40, 40));
    new Ball(new Spot(740, 600), new Size(32, 32));
    new Ball(new Spot(820, 600), new Size(20, 20));
    Brick.createGrid(8, 4, 10);

  }
}
