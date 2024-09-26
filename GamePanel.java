import javax.swing.JPanel;
import java.awt.*;
import input.KeyHandler;
import util.Size;

public class GamePanel extends JPanel { // toile dans laquelle tout sera dessiné (toile du tableau)

  private Game game;
  private KeyHandler keyHandler;
  private Size displaySize;

  public GamePanel(Game game, Size displaySize) {
    this.game = game;
    this.displaySize = displaySize;
    setPreferredSize(new Dimension(displaySize.getWidth(), displaySize.getHeight()));
    setFocusable(true);
    keyHandler = new KeyHandler();
    addKeyListener(keyHandler);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, displaySize.getWidth(), displaySize.getHeight());
    g.setColor(Color.BLACK);
    game.getEntities().forEach(entity -> entity.render((Graphics2D) g));
    g.dispose();
  }

  public KeyHandler getKeyHandler() {
    return keyHandler;
  }
}
