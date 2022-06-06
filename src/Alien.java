import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Alien {

    private BufferedImage alienImg;
    private int x;
    private int y;
    private int speedFactor;

    private final int size;

    private final int availableSpaceX;
    private final int numberAliensX;
    private final int availableSpaceY;
    private final int numberRows;

    public static ArrayList<Alien> aliens = new ArrayList<>();
    private int fleetDropSpeed;
    private int fleetDirection;

    public Alien(int x, int y) {
        size = 48;
        this.x = x;
        this.y = y;
        speedFactor = 5;
        getAlienImg();

        availableSpaceX = GamePanel.WIDTH - (2 * size);
        numberAliensX = availableSpaceX / (3 * size);
        availableSpaceY = GamePanel.HEIGHT - (5 * size) - size;
        numberRows = availableSpaceY / (2 * size);

        fleetDropSpeed = 10;
        fleetDirection = 1;
    }

    public void getAlienImg() {
        try {
            alienImg = ImageIO.read(new File("images/alienShip.png"));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public void createFleet () {
        for (int i = 0; i < numberRows; i++) {
            for (int k = 0; k < numberAliensX; k++) {
                int x = size + 2 * size * k;
                int y = size + 2 * size * i;
                aliens.add(new Alien(x, y));
            }
        }
    }

    public boolean checkEdges() {
        int panelWidth = GamePanel.WIDTH - size;
        return x >= panelWidth || x <= 0;
    }

    public void checkFleetEdges() {
        for (Alien alien: aliens) {
            if (alien.checkEdges()) {
                changeFleetDirection();
                break;
            }
        }
    }

    public void changeFleetDirection() {

        for (Alien alien: aliens) {
            alien.y += fleetDropSpeed;
        }
        fleetDirection *= -1;
    }

    public void checkAliensBottom() {
        for (Alien alien: Alien.aliens) {
            if ( alien.y >= GamePanel.HEIGHT - alien.size) {
                GamePanel.ship.shipHit();
                break;
            }
        }
    }

    public void checkBulletAlienCollision() {
        for (int i = 0; i < PlayerShip.bullets.size(); i++) {
            Bullet bullet = PlayerShip.bullets.get(i);

            for (int k = 0; k < Alien.aliens.size(); k++) {
                Alien alien = Alien.aliens.get(k);

                if ( (bullet.getY() - bullet.getHeight() <= alien.y
                        && bullet.getY() >= alien.y - size)
                        && (bullet.getX() + bullet.getWidth() >= alien.x
                        && alien.x + alien.size >= bullet.getX()) ) {

                    Alien.aliens.remove(alien);
                    PlayerShip.bullets.remove(bullet);
                    break;
                }
            }
            if (Alien.aliens.isEmpty()) {
                PlayerShip.bullets.removeAll(PlayerShip.bullets);
                createFleet();
            }
        }
    }

    public void draw(Graphics2D graphics2D) {

        graphics2D.drawImage(alienImg
                , x
                , y
                , size
                , size
                , null);
    }

    public void update() {
        checkAliensBottom();
        checkBulletAlienCollision();
        checkFleetEdges();

        x += speedFactor * fleetDirection;
    }
}
