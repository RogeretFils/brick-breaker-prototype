import javax.swing.JPanel;
import java.awt.*;
import input.KeyHandler;
import util.Size;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class GamePanel extends JPanel { // toile dans laquelle tout sera dessiné (toile du tableau)

  private Game game;
  private KeyHandler keyHandler;

  public GamePanel(Game game, Size displaySize) {
    this.game = game;
    setPreferredSize(new Dimension(displaySize.getWidth(), displaySize.getHeight()));
    setFocusable(true);
    keyHandler = new KeyHandler();
    addKeyListener(keyHandler);
  }

  public void paintComponent(Graphics g) {
    // super.paintComponent(g);

    // g.setColor(new Color(255, 255, 255, 20)); // White with slight transparency
    // g.fillRect(0, 0, getWidth(), getHeight()); // Fill the whole screen

    BufferedImage ballImage = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2d = ballImage.createGraphics();

    // Remplir l'image avec un arrière-plan blanc
    g2d.setColor(Color.WHITE);
    g2d.fillRect(0, 0, ballImage.getWidth(), ballImage.getHeight());

    // Dessiner une balle rouge au centre
    g2d.setColor(Color.RED);
    g2d.fillOval(350, 250, 100, 100); // Balle de 100x100 pixels

    g2d.dispose();

    // Appliquer le flou sur l'image de la balle
    float[] blurMatrix = {
        0.02f, 0.04f, 0.08f, 0.04f, 0.02f,
        0.04f, 0.08f, 0.12f, 0.08f, 0.04f,
        0.08f, 0.12f, 0.20f, 0.12f, 0.08f,
        0.04f, 0.08f, 0.12f, 0.08f, 0.04f,
        0.02f, 0.04f, 0.08f, 0.04f, 0.02f
    };

    // Appliquer la convolution pour créer l'effet de flou
    ConvolveOp blurOp = new ConvolveOp(new Kernel(5, 5, blurMatrix), ConvolveOp.EDGE_NO_OP, null);

    // Créer une nouvelle image pour stocker l'image floutée
    BufferedImage blurredImage = new BufferedImage(ballImage.getWidth(), ballImage.getHeight(), ballImage.getType());

    // Appliquer le filtre de flou
    blurOp.filter(ballImage, blurredImage);

    // Remplacer l'image d'origine par l'image floutée
    ballImage = blurredImage;

    g.drawImage(ballImage, 0, 0, null);

    // g.setColor(Color.BLACK);
    // game.getEntities().forEach(entity -> entity.render((Graphics2D) g));
    // g.dispose();
  }

  public KeyHandler getKeyHandler() {
    return keyHandler;
  }
}
