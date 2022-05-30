public class GameStats {

    public int shipLeft;
    public boolean gameActive;


    public GameStats() {

        gameActive = true;
        resetStats();
    }

    public void resetStats() {
        shipLeft = GamePanel.ship.getLimit();
    }

}
