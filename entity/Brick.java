package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import util.Size;
import util.Spot;

public class Brick extends Entity {

  private static ArrayList<Brick> bricks = new ArrayList<>();

  public Brick(Spot spot, Size size) {
    super(spot, size);
    bricks.add(this);
  }

  public void update() {
  }

  public void render(Graphics2D graphics2d) {
    if (!visible) return;
    graphics2d.setPaint(Color.BLACK);
    graphics2d.fillRect(spot.getX(), spot.getY(), size.getWidth(), size.getHeight());
  }

  public static void destroyBrick(Brick brick) {
    bricks.remove(brick);
    brick.visible = false;
  }

  public static ArrayList<Brick> getBricks() {
    return bricks;
  }

  public Spot getSpot() {
    return spot;
  }

  public Size getSize() {
    return size;
  }
}
