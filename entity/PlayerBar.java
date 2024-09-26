package entity;

import java.awt.Graphics2D;

import input.KeyHandler;
import util.Size;
import util.Spot;

public class PlayerBar extends Entity { // barre controllée par le joueur

  private KeyHandler keyHandler;
  private int velocity = 8; // en pixel par frame
  private int movementDirection; // -1 = gauche, 0 = immobile, 1 = droite

  public PlayerBar(KeyHandler keyHandler) {
    super();
    this.keyHandler = keyHandler;
    size.setSize(300, 30);
    spot.setSpot((displaySize.getWidth() - size.getWidth()) / 2, (int) (displaySize.getHeight() * 0.85));
  }

  public void update() {
    movementDirection = 0;
    if (keyHandler.isPressed('Q')) {
      spot.setX(Math.max(spot.getX() - velocity, 0));
      movementDirection = -1;
    }
    if (keyHandler.isPressed('D')) {
      spot.setX(Math.min(spot.getX() + velocity, (int) displaySize.getWidth() - size.getWidth()));
      movementDirection = 1;
    }
  }

  public void render(Graphics2D graphics2d) {
    graphics2d.fillRect(spot.getX(), spot.getY(), size.getWidth(), size.getHeight());
  }

  public Spot getSpot() {
    return spot;
  }

  public Size getSize() {
    return size;
  }

  public int getMovementDirection() {
    return movementDirection;
  }
}
