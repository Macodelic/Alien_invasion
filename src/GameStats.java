import java.awt.*;

public class GameStats {

    public static int shipLeft;
    private boolean gameActive;


    public GameStats() {

        gameActive = false;
        resetStats();
    }

    public boolean getGameActive() {
        return this.gameActive;
    }

    public void setGameActive(boolean gameActive) {
        this.gameActive = gameActive;
    }

    public void resetStats() {
        shipLeft = GamePanel.ship.getLimit();
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(Color.ORANGE);
        graphics2D.setFont(new Font("Arial", Font.PLAIN, 20));
        graphics2D.drawString(String.valueOf(shipLeft), 20, 30);
    }
}
