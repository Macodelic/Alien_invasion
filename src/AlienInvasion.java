import javax.swing.*;
import java.awt.*;


public class AlienInvasion extends JFrame {

    public AlienInvasion() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("images/iconAlien.png").getImage());
        setTitle("Alien Invasion");
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater( ()-> {
            AlienInvasion window = new AlienInvasion();
            GamePanel panel = new GamePanel();
            window.setContentPane(panel);
            window.pack();
            panel.startGameThread();
        });
    }

}
