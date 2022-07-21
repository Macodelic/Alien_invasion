import java.awt.*;

public class Bullet {

    private Color bulletColor;
    private int width;
    private int height;
    private int x;
    private int y;
    private static int speed = 3;
    private boolean isExist;


    public Bullet(int x, int y) {
        width = 3;
        height = 15;
        this.x = x;
        this.y = y;
        isExist = true;
        bulletColor = new Color(60,60,60);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(bulletColor);
        graphics2D.fillRect(x, y, width, height);
    }

    public boolean update() {
        y -= speed;
        return y == 0;

    }

}
