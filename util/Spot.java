package util;

public class Spot {
  private double x;
  private double y;

  public Spot(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public void setSpot(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return (int) x;
  }

  public double getDoubleX() {
    return x;
  }

  public int getY() {
    return (int) y;
  }

  public double getDoubleY() {
    return y;
  }

  public void setX(double x) {
    this.x = x;
  }

  public void setY(double y) {
    this.y = y;
  }
}
