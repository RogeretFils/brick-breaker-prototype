package entity;

import java.util.ArrayList;
import java.awt.*;

import util.Size;
import util.Spot;

public class Ball extends Entity {

  private double angle = Math.toRadians(90);
  private int velocity = 12;
  private double spin;

  private ArrayList<Spot> clones = new ArrayList<>();

  public Ball(Spot spot, Size size) {
    super(spot, size);
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

    brickCollision();
  }

  public void render(Graphics2D g2D) {

    g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2D.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
    
    for (int i = 0; i < clones.size() - 1; i++) {
      int nbInterpolation = 2; // number of additionnal clones to be drawn between known spots
      for (int j = 0; j < nbInterpolation; j++) {
        double cloneSize = 24;
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

    g2D.setColor(Color.WHITE);
    g2D.fillOval(spot.getX(), spot.getY(), size.getWidth(), size.getHeight());

  }

  private boolean horizontalCollision() {
    PlayerBar playerBar = (PlayerBar) entities.get(0);
    return spot.getX() >= playerBar.getSpot().getX()
        && spot.getX() <= playerBar.getSpot().getX() + playerBar.getSize().getWidth()
        && spot.getY() >= playerBar.getSpot().getY() - size.getHeight()
        && spot.getY() <= playerBar.getSpot().getY() - size.getHeight() + velocity;
  }

  private void brickCollision() {
    for (Brick brick : Brick.getBricks()) {

      if (Math.sqrt(Math.pow(brick.getSpot().getX() + 60 - spot.getX(), 2) + Math.pow(brick.getSpot().getY() + 30 - spot.getY(), 2)) > 200) {
        continue;
      }

      if ( // collision avec le bas d'une brique
        spot.getX() >= brick.getSpot().getX() - size.getWidth() * 0.5 &&
        spot.getX() <= brick.getSpot().getX() + brick.getSize().getWidth() - size.getWidth() * 0.5 &&
        spot.getY() <= brick.getSpot().getY() + brick.getSize().getHeight() &&
        spot.getY() >= brick.getSpot().getY() + brick.getSize().getHeight() - velocity
      ) {
        angle = -angle;
        Brick.destroyBrick(brick);
        break;
      }

      if ( // collision avec le haut d'une brique
        spot.getX() >= brick.getSpot().getX() - size.getWidth() * 0.5 &&
        spot.getX() <= brick.getSpot().getX() + brick.getSize().getWidth() - size.getWidth() * 0.5 &&
        spot.getY() >= brick.getSpot().getY() - size.getHeight() &&
        spot.getY() <= brick.getSpot().getY() - size.getHeight() + velocity
      ) {
        angle = -angle;
        Brick.destroyBrick(brick);
        break;
      }

      if ( // collision avec la gauche d'une brique
        spot.getX() >= brick.getSpot().getX() - size.getWidth() &&
        spot.getX() <= brick.getSpot().getX() - size.getWidth() + velocity &&
        spot.getY() <= brick.getSpot().getY() + brick.getSize().getHeight() - size.getHeight() * 0.5 &&
        spot.getY() >= brick.getSpot().getY() - size.getHeight() - size.getHeight() * 0.5
      ) {
        angle = Math.PI - angle;
        Brick.destroyBrick(brick);
        break;
      }

      if ( // collision avec la droite d'une brique
        spot.getX() <= brick.getSpot().getX() + brick.getSize().getWidth() &&
        spot.getX() >= brick.getSpot().getX() + brick.getSize().getWidth() - velocity &&
        spot.getY() <= brick.getSpot().getY() + brick.getSize().getHeight() - size.getHeight() * 0.5 &&
        spot.getY() >= brick.getSpot().getY() - size.getHeight() - size.getHeight() * 0.5
      ) {
        angle = Math.PI - angle;
        Brick.destroyBrick(brick);
        break;
      }

    }
  }
}
