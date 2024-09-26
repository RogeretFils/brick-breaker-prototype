public class Launcher { // point d'entrée du programmez

  public static void main(String[] args) {

    new Thread(new GameLoop(new Game())).start();

  }
}
