package Model;

import javafx.geometry.Point2D;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The type Game.
 *
 * @author Erfanm5152
 * @version 0.1
 */
public class Game implements Serializable {
    // user 1
    private User user1;
    // user 2
    private User user2;
    // winner user
    private User winner;
    // loser user
    private User loser;
    // is finished game
    private boolean isFinished;
    // shared data of the game
    private transient SharedData sharedData;
    // map of the game
    private String[][] map;
    // in map cards of the game
    private ArrayList<Card> inMapCards;
    // points of the bridges
    private transient Point2D rightBridgeUser1;
    private transient Point2D leftBridgeUser1;
    private transient Point2D rightBridgeUser2;
    private transient Point2D leftBridgeUser2;
    // timer of the game
    private transient TimerMe timerMe;

    /**
     * Instantiates a new Game.
     */
    public Game() {
        sharedData = SharedData.getInstance();

        user1 = sharedData.getUser();
        user2 = sharedData.getNameOfBot().equals("simple") ? new Bot() : new AdvancedBot();
        user2.setPlayer(new Player(user2));

        isFinished = false;
        map = FileUtils.readMap();
        inMapCards = new ArrayList<>();
        this.rightBridgeUser1 = new Point2D(240, 254);
        this.rightBridgeUser2 = new Point2D(240, 290);
        this.leftBridgeUser1 = new Point2D(50, 254);
        this.leftBridgeUser2 = new Point2D(50, 287);
    }

    /**
     * Gets user 1.
     *
     * @return the user 1
     */
    public User getUser1() {
        return user1;
    }

    /**
     * Sets user 1.
     *
     * @param user1 the user 1
     */
    public void setUser1(User user1) {
        this.user1 = user1;
    }

    /**
     * Gets user 2.
     *
     * @return the user 2
     */
    public User getUser2() {
        return user2;
    }

    /**
     * Sets user 2.
     *
     * @param user2 the user 2
     */
    public void setUser2(User user2) {
        this.user2 = user2;
    }

    /**
     * Get map string [ ] [ ].
     *
     * @return the string [ ] [ ]
     */
    public String[][] getMap() {
        return map;
    }

    /**
     * Sets map.
     *
     * @param map the map
     */
    public void setMap(String[][] map) {
        this.map = map;
    }

    /**
     * Is finished boolean.
     *
     * @return the boolean
     */
    public boolean isFinished() {
        return isFinished;
    }

    /**
     * Sets finished.
     *
     * @param finished the finished
     */
    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    /**
     * Gets shared data.
     *
     * @return the shared data
     */
    public SharedData getSharedData() {
        return sharedData;
    }

    /**
     * Sets shared data.
     *
     * @param sharedData the shared data
     */
    public void setSharedData(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    /**
     * Gets in map cards.
     *
     * @return the in map cards
     */
    public ArrayList<Card> getInMapCards() {
        return inMapCards;
    }

    /**
     * Sets in map cards.
     *
     * @param inMapCards the in map cards
     */
    public void setInMapCards(ArrayList<Card> inMapCards) {
        this.inMapCards = inMapCards;
    }

    /**
     * Gets timer me.
     *
     * @return the timer me
     */
    public TimerMe getTimerMe() {
        return timerMe;
    }

    /**
     * Sets timer me.
     *
     * @param timerMe the timer me
     */
    public void setTimerMe(TimerMe timerMe) {
        this.timerMe = timerMe;
    }

    /**
     * Winner in finish time.
     */
    public void winnerInFinishTime() {
        int numberOfCupsUser1 = user1.getPlayer().getGameAccessory().getNumberOfCups();
        int numberOfCupsUser2 = user2.getPlayer().getGameAccessory().getNumberOfCups();
        if (numberOfCupsUser1 != numberOfCupsUser2) {
            winner = numberOfCupsUser1 > numberOfCupsUser2 ? user1 : user2;
        } else {
            if (user1.getPlayer().getGameAccessory().healthOfTowers() > user2.getPlayer().getGameAccessory().healthOfTowers()) {
                winner = user1;
            } else if (user1.getPlayer().getGameAccessory().healthOfTowers() == user2.getPlayer().getGameAccessory().healthOfTowers()) {
                winner = user1;
            } else if (user1.getPlayer().getGameAccessory().healthOfTowers() < user2.getPlayer().getGameAccessory().healthOfTowers()) {
                winner = user2;
            }
        }
        loser = user1 == winner ? user2 : user1;
    }

    /**
     * Gets opponent.
     *
     * @param selfUser the self user
     * @return the opponent
     */
    public User getOpponent(User selfUser) {
        return selfUser == user1 ? user2 : user1;
    }

    /**
     * Gets winner.
     *
     * @return the winner
     */
    public User getWinner() {
        return winner;
    }

    /**
     * Sets winner.
     *
     * @param winner the winner
     */
    public void setWinner(User winner) {
        this.winner = winner;
    }

    /**
     * Gets loser.
     *
     * @return the loser
     */
    public User getLoser() {
        return loser;
    }

    /**
     * Sets loser.
     *
     * @param loser the loser
     */
    public void setLoser(User loser) {
        this.loser = loser;
    }

    /**
     * Gets right bridge user 1.
     *
     * @return the right bridge user 1
     */
    public Point2D getRightBridgeUser1() {
        return rightBridgeUser1;
    }

    /**
     * Sets right bridge user 1.
     *
     * @param rightBridgeUser1 the right bridge user 1
     */
    public void setRightBridgeUser1(Point2D rightBridgeUser1) {
        this.rightBridgeUser1 = rightBridgeUser1;
    }

    /**
     * Gets left bridge user 1.
     *
     * @return the left bridge user 1
     */
    public Point2D getLeftBridgeUser1() {
        return leftBridgeUser1;
    }

    /**
     * Sets left bridge user 1.
     *
     * @param leftBridgeUser1 the left bridge user 1
     */
    public void setLeftBridgeUser1(Point2D leftBridgeUser1) {
        this.leftBridgeUser1 = leftBridgeUser1;
    }

    /**
     * Gets right bridge user 2.
     *
     * @return the right bridge user 2
     */
    public Point2D getRightBridgeUser2() {
        return rightBridgeUser2;
    }

    /**
     * Sets right bridge user 2.
     *
     * @param rightBridgeUser2 the right bridge user 2
     */
    public void setRightBridgeUser2(Point2D rightBridgeUser2) {
        this.rightBridgeUser2 = rightBridgeUser2;
    }

    /**
     * Gets left bridge user 2.
     *
     * @return the left bridge user 2
     */
    public Point2D getLeftBridgeUser2() {
        return leftBridgeUser2;
    }

    /**
     * Sets left bridge user 2.
     *
     * @param leftBridgeUser2 the left bridge user 2
     */
    public void setLeftBridgeUser2(Point2D leftBridgeUser2) {
        this.leftBridgeUser2 = leftBridgeUser2;
    }

    /**
     * Is empty cell in map.
     *
     * @param point2D the point 2 d
     * @param card    the card
     * @return the boolean
     */
    public boolean isEmptyCell(Point2D point2D, Card card) {
        ArrayList<Card> cards = new ArrayList<>();
        for (Card temp : getUser1().getPlayer().getGameAccessory().getInGameTargets()) {
            if (!(temp instanceof Spell)) {
                cards.add(temp);
            }
        }
        for (Card temp : getUser2().getPlayer().getGameAccessory().getInGameTargets()) {
            if (!(temp instanceof Spell)) {
                cards.add(temp);
            }
        }
        for (Card temp : cards) {
            if (temp == card) {
                continue;
            }
            if ((temp.getPoint2D().equals(point2D) || temp.getPoint2D().distance(point2D) < 2) && temp.getSelf() == card.getSelf()) {
                return false;
            }
        }

        return true;
    }

    /**
     * distance of Closet tower.
     *
     * @param point2D the point 2 d
     * @param card    the card
     * @return the double
     */
    public double closetTower(Point2D point2D, Card card) {
        double distance = 200;
        for (Tower tower : getOpponent(card.getPlayer().getUser()).getPlayer().getGameAccessory().getTowers()) {
            if (tower.getPoint2D().distance(point2D) < distance) {
                distance = tower.getPoint2D().distance(point2D);
            }
        }
        return distance;
    }

    /**
     * Check point is available.
     *
     * @param point2D the point 2 d
     * @param card    the card
     * @return the boolean
     */
    public boolean checkPoint(Point2D point2D, Card card) {
        if (point2D.getY() > 530 || point2D.getY() < 4) {
            return false;
        }
        if (point2D.getX() > 310 || point2D.getX() < 10) {
            return false;
        }
        if (point2D.getY() >= 245 && point2D.getY() <= 290) {
            return false;
        }
        if (closetTower(point2D, card) < 160) {
            return false;
        }
        return isEmptyCell(point2D, card);
    }

}
