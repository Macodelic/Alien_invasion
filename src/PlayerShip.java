import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PlayerShip {

    private BufferedImage shipImg;
    private int x;
    private int y;
    private int speed;

    private int size;

    private boolean left, right;

    private int bulletsAllowed;
    private int limit;

    public static ArrayList<Bullet> bullets;

    public PlayerShip() {
        size = 48;
        x = GamePanel.WIDTH / 2;
        y = GamePanel.HEIGHT - size;
        speed = 4;
        limit = 3;
        bulletsAllowed = 100;
        bullets = new ArrayList<>();
        getShipImg();
    }

    public void setLeft(boolean b) {left = b;}
    public void setRight(boolean b) {right = b;}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public int getLimit() {
        return limit;
    }

    public void getShipImg() {
        try {
            shipImg = ImageIO.read(new File("images/playerShip.png"));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkPlayerAlienCollision() {
        for (Alien alien: Alien.aliens) {
            if ( y - size <= alien.getY()
                    && y >= alien.getSize() - size
                    && x + size >= alien.getX()
                    && alien.getX() + alien.getSize() >= x
                    && alien.getY() < GamePanel.HEIGHT) {

                shipHit();
                break;
            }
        }
    }

    public void centerShip() {
        x = GamePanel.WIDTH / 2;
    }

    public void shipHit() {

        GamePanel.stats.shipLeft -= 1;

        Alien.aliens.removeAll(Alien.aliens);
        bullets.removeAll(bullets);

        GamePanel.alien.createFleet();
        centerShip();

        long l = 500;
        try {
            Thread.sleep(l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void fire() {
        if (bullets.size() != bulletsAllowed) {
            bullets.add(new Bullet(x + size/2, y - size/2));
        }
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(shipImg
                , x
                , y
                , size
                , size
                , null);
    }

    public void update() {

        checkPlayerAlienCollision();

        if (left && x != 0) {
            x -= speed;
        }
        if (right && x != GamePanel.WIDTH - size) {
            x += speed;
        }

    }
}
