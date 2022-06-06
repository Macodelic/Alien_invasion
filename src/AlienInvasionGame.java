import javax.swing.*;
import java.awt.*;


public class AlienInvasionGame extends JFrame {

    private final ImageIcon icon = new ImageIcon("images/iconAlien.png");

    private static void createAndShowGUI() {
        AlienInvasionGame gameWindow = new AlienInvasionGame();
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setIconImage(gameWindow.icon.getImage());
        gameWindow.setTitle("Alien Invasion");
        gameWindow.setResizable(false);
        GamePanel panel = new GamePanel();
        panel.setOpaque(true);
        panel.startGameThread();
        gameWindow.setContentPane(panel);

        gameWindow.pack();
        gameWindow.setVisible(true);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater( ()-> {
            createAndShowGUI();
        });
    }

}
