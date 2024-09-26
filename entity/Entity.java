package entity;

import java.awt.Graphics2D;
import java.util.ArrayList;

import util.Size;
import util.Spot;

public abstract class Entity { // classe parent à tous les objects visuels

  protected static ArrayList<Entity> entities = new ArrayList<>();

  protected Spot spot;
  protected Size size;
  protected static Size displaySize;
  protected boolean visible = true;
  
  public Entity(Spot spot, Size size) {

    entities.add(this);
    this.spot = spot;
    this.size = size;

  }

  public Entity() {
    this(new Spot(0, 0), new Size(0, 0));
  }

  public abstract void update();

  public abstract void render(Graphics2D graphics2d);
  
  public static ArrayList<Entity> getEntities() {
    return entities;
  }

  public static void setDisplaySize(Size size) {
    displaySize = size;
  }
}
