package entity;

import java.util.ArrayList;
import java.awt.*;
import java.awt.image.BufferedImage;

import util.Size;
import util.Spot;

public class Ball extends Entity {

  private ArrayList<Entity> entities;
  private double angle = Math.toRadians(-90);
  private int velocity = 10;
  private double spin;

  private ArrayList<Spot> clones = new ArrayList<>();

  public Ball(Spot spot, Size size, ArrayList<Entity> entities) {
    super(spot, size);
    this.entities = entities;
  }

  public void update() {

    clones.add(new Spot(spot.getX() + (size.getWidth() / 2), spot.getY() + (size.getHeight() / 2)));
    if (clones.size() > 40) {
      clones.remove(0);
    }

    spot.setX(spot.getDoubleX() + Math.cos(angle) * velocity);
    spot.setY(spot.getDoubleY() + Math.sin(angle) * velocity);

    angle += spin / 10000;
    if (spin < 0) {
      spin += 2;
    }
    if (spin > 0) {
      spin -= 2;
    }

    if (spot.getX() <= 0 || spot.getX() + size.getWidth() >= displaySize.getWidth()) {
      angle = Math.PI - angle;
    }
    if (spot.getY() <= 0) {
      angle = -angle;
    }
    if (horizontalCollision()) {
      angle = -angle;
      PlayerBar playerBar = (PlayerBar) entities.get(0);
      spin -= 200 * playerBar.getMovementDirection();
    }
  }

  public boolean horizontalCollision() {
    PlayerBar playerBar = (PlayerBar) entities.get(0);
    return spot.getX() >= playerBar.getSpot().getX()
        && spot.getX() <= playerBar.getSpot().getX() + playerBar.getSize().getWidth()
        && spot.getY() >= playerBar.getSpot().getY() - size.getHeight()
        && spot.getY() <= playerBar.getSpot().getY() - size.getHeight() + velocity;
  }

  public void render(Graphics2D g2D) {

    g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2D.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
    
    for (int i = 0; i < clones.size() - 1; i++) {
      int nbInterpolation = 3; // number of additionnal clones to be drawn between known spots
      for (int j = 0; j < nbInterpolation; j++) {
        double cloneSize = 20;
        double xCloneGap = (clones.get(i + 1).getX() - clones.get(i).getX()) / nbInterpolation;
        double yCloneGap = (clones.get(i + 1).getY() - clones.get(i).getY()) / nbInterpolation;
        g2D.setColor(new Color(255, 100, 100, i * (255 / clones.size()) / nbInterpolation));
        g2D.fillOval(
          (int) (clones.get(i).getX() + xCloneGap * j - cloneSize / 2),
          (int) (clones.get(i).getY() + yCloneGap * j - cloneSize / 2),
          (int) cloneSize,
          (int) cloneSize
        );
      }
    }

    g2D.setColor(Color.BLACK);
    g2D.fillOval(spot.getX(), spot.getY(), size.getWidth(), size.getHeight());
    g2D.dispose();

  }
}
