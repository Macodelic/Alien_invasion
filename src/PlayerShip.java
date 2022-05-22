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

    private boolean left, right, isDead;
    private int bulletsAllowed;

    public static ArrayList<Bullet> bullets;

    public PlayerShip() {
        size = 48;
        x = GamePanel.WIDTH / 2;
        y = GamePanel.HEIGHT - size;
        isDead = false;
        speed = 4;
        bulletsAllowed = 100;
        bullets = new ArrayList<>();
        getShipImg();
    }

    public void setLeft(boolean b) {left = b;}
    public void setRight(boolean b) {right = b;}
    public void setIsDead(boolean b) {isDead = b;}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }
    public boolean getIsDead() {
        return isDead;
    }

    public void getShipImg() {
        try {
            shipImg = ImageIO.read(new File("images/playerShip.png"));
        }catch (IOException e) {
            e.printStackTrace();
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

    public void fire() {
        if (bullets.size() != bulletsAllowed) {
            bullets.add(new Bullet(x + size/2, y - size/2));
        }
    }

    public void update() {

        if (left && x != 0) {
            x -= speed;
        }
        if (right && x != GamePanel.WIDTH - size) {
            x += speed;
        }

    }
}
