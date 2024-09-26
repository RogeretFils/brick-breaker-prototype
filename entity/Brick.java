package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import util.Size;
import util.Spot;

public class Brick extends Entity {

  public Brick(Spot spot, Size size) {
    super(spot, size);
  }

  public void update() {
  }

  public void render(Graphics2D graphics2d) {
    graphics2d.setPaint(Color.CYAN);
    graphics2d.fillRect(spot.getX(), spot.getY(), size.getWidth(), size.getHeight());
    graphics2d.dispose();
  }
}
