import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements Runnable, KeyListener {

    public static int WIDTH = 1200;
    public static int HEIGHT = 800;
    private Color bgColor;

    public static PlayerShip ship = new PlayerShip();
    public static Alien alien = new Alien(0,0);

    private Thread gameThread;
    private int FPS = 30;

    public GamePanel() {
        super();
        addKeyListener(this);
        setFocusable(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(bgColor);
        setDoubleBuffered(true);
    }

    public void startGameThread() {
            gameThread = new Thread(this);
            gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        alien.createFleet();

        while (gameThread != null) {
            long currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1.0) {
                update();
                repaint();
                delta--;
            }

        }
    }

    private void update() {

        ship.update();
        for (int i = 0; i < PlayerShip.bullets.size(); i++) {
            boolean remove = PlayerShip.bullets.get(i).update();
            if (remove) {
                PlayerShip.bullets.remove(i);
            }
        }

        for (int i = 0; i < Alien.aliens.size(); i++) {
                Alien.aliens.get(i).update();
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        ship.draw(graphics2D);

        for (int i = 0; i < PlayerShip.bullets.size(); i++) {
            PlayerShip.bullets.get(i).draw(graphics2D);
        }

        for (int i = 0; i < Alien.aliens.size(); i++) {
            Alien.aliens.get(i).draw(graphics2D);
        }


        graphics2D.dispose();
        Toolkit.getDefaultToolkit().sync();
        }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_A:
                ship.setLeft(true);
                break;
            case KeyEvent.VK_D:
                ship.setRight(true);
                break;
            case KeyEvent.VK_SPACE:
                ship.fire();
                break;
            case KeyEvent.VK_Q:
                System.exit(0);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_A:
                ship.setLeft(false);
                break;
            case KeyEvent.VK_D:
                ship.setRight(false);
                break;
        }
    }
}
