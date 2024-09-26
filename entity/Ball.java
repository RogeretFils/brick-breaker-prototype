package entity;

import java.util.ArrayList;
import java.awt.image.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

import util.Size;
import util.Spot;

public class Ball extends Entity {

  private ArrayList<Entity> entities;
  private double angle = Math.toRadians(-50);
  private int velocity = 16;
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

  public void render(Graphics2D graphics2d) {

    // g2dTrail.fillOval(100, 200, 50, 50);

    for (int i = 0; i < clones.size() - 3; i++) {
      // graphics2d.setColor(new Color(100, 100, 255, i * 6));
      // // graphics2d.fillOval(clones.get(i).getX(), clones.get(i).getY(),
      // // size.getWidth() / 2, size.getHeight() / 2);
      // graphics2d.setStroke(new BasicStroke(i / 2));
      // graphics2d.drawLine(clones.get(i).getX(), clones.get(i).getY(), clones.get(i
      // + 1).getX(),
      // clones.get(i + 1).getY());

      // int width = 400, height = 300;
      // BufferedImage bufferedImage = new BufferedImage(width, height,
      // BufferedImage.TYPE_INT_ARGB);
      // Graphics2D g2d = bufferedImage.createGraphics();
      // g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
      // RenderingHints.VALUE_ANTIALIAS_ON);
      // g2d.setColor(Color.RED);
      // g2d.fillRect(100, 75, 200, 200); // Draw a filled square
      // g2d.dispose();

      // float[] blurKernel = {
      // 1 / 9f, 1 / 9f, 1 / 9f,
      // 1 / 9f, 1 / 9f, 1 / 9f,
      // 1 / 9f, 1 / 9f, 1 / 9f
      // };

      // // // Apply the blur using ConvolveOp
      // ConvolveOp blur = new ConvolveOp(new Kernel(3, 3, blurKernel),
      // ConvolveOp.EDGE_NO_OP, null);
      // BufferedImage blurredImage = blur.filter(bufferedImage, null);

      // // Draw the blurred image on the panel
      // graphics2d.drawImage(bufferedImage, 0, 0, null);

    }
    // graphics2d.setStroke(new BasicStroke(1));
    // graphics2d.setColor(Color.BLACK);
    graphics2d.fillOval(spot.getX(), spot.getY(), size.getWidth(), size.getHeight());
  }
}
