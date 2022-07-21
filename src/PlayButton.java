import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayButton extends JButton implements ActionListener {

    public PlayButton() {

        setBounds(GamePanel.WIDTH/2,GamePanel.HEIGHT/2,200,50);
        setBackground(new Color(0, 255,0));
        setForeground(new Color(255, 255,255));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setText("Play");
        setFont(new Font("Arial", Font.PLAIN, 30));
        addActionListener(this);
    }

    public void startGame() {
        Alien.aliens.clear();
        PlayerShip.bullets.clear();

        GamePanel.alien.createFleet();
        GamePanel.ship.centerShip();

        GamePanel.stats.resetStats();
        GamePanel.stats.setGameActive(true);

        this.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        startGame();
    }


}
