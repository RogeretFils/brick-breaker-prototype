package entity;

import java.awt.Graphics2D;

import util.Size;
import util.Spot;

public abstract class Entity { // classe parent à tous les objects visuels

  protected Spot spot;
  protected Size size;
  protected static Size displaySize;

  public Entity(Spot spot, Size size) {

    this.spot = spot;
    this.size = size;

  }

  public Entity() {
    this(new Spot(0, 0), new Size(0, 0));
  }

  public abstract void update();

  public abstract void render(Graphics2D graphics2d);

  public static void setDisplaySize(Size size) {
    displaySize = size;
  }
}
