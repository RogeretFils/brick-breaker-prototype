package Game;
public class GameLoop implements Runnable { // boucle de jeu

  private Game game;
  private boolean running;
  private final int FPS = 60;

  public GameLoop(Game game) {
    this.game = game;
  }

  @Override
  public void run() {

    running = true;
    final double drawInterval = 1000000000.0 / FPS; // durée d'une frame en nanosecondes (=0.01666 secondes)
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;
    long timer = 0;
    int drawCount = 0;

    while (running) {

      currentTime = System.nanoTime();

      delta += (currentTime - lastTime) / drawInterval;
      timer += (currentTime - lastTime);
      lastTime = currentTime;

      if (delta >= 1) {
        game.update();
        game.render();
        delta--;
        drawCount++;
      }

      if (timer >= 1000000000) { // print FPS
        System.out.println("FPS: " + drawCount);
        timer = 0;
        drawCount = 0;
      }

    }
  }
}
