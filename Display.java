import javax.swing.JFrame;

public class Display extends JFrame { // fenêtre du jeu (cadre du tableau)

  public Display(GamePanel gamePanel) {

    add(gamePanel);

    setTitle("Casse-briques");
    setDefaultCloseOperation(EXIT_ON_CLOSE); // stoppe le programme à la fermeture de la fenêtre
    setResizable(false); // empêche le redimensionnement de la fenêtre
    pack(); // redimensionne la fenêtre selon les éléments qu'elle contient
    setLocationRelativeTo(null); // centre la fenêtre à l'écran
    setVisible(true); // rend la fenêtre visible (se met à la fin)

  }
}