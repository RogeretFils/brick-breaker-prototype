import java.util.ArrayList;

import entity.*;
import util.*;

public class Game { // classe principale

  private Size displaySize;
  private GamePanel gamePanel;
  private Display display;
  private ArrayList<Entity> entities;

  public Game() {
    displaySize = new Size(1000, 1000);
    gamePanel = new GamePanel(this, displaySize);
    display = new Display(gamePanel);
    entities = new ArrayList<>();

    Entity.setDisplaySize(displaySize);
    entities.add(new PlayerBar(gamePanel.getKeyHandler()));
    entities.add(new Ball(new Spot(300, 400), new Size(32, 32), entities));
    entities.add(new Brick(new Spot(0, 0), new Size(70, 40)));
  }

  public void update() {
    entities.forEach(entity -> entity.update());
  }

  public void render() {
    gamePanel.repaint();
  }

  public ArrayList<Entity> getEntities() {
    return entities;
  }
}
