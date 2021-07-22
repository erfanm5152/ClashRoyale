package Model;

import View.MapView;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The type Card.
 *
 * @author Erfanm5152
 * @version 0.1
 */
public abstract class Card extends TimerTask implements Serializable, Vulnerable {
    // address of the image
    private String cardImageAddress;
    // cost of the card
    private int cost;
    // range of the card
    private double range;
    // player of the card
    private Player player;
    // type of the target of the card
    private Target target;
    // type of the self
    private Target self;
    // seconds in game of card
    private transient int secondInGame;
    // image view of card
    private transient ImageView imageView;
    // point of the card
    private transient Point2D point2D;
    // timer of card
    private transient Timer timer;
    // map of the game
    private transient MapView map;

    /**
     * Instantiates a new Card.
     *
     * @param cost        the cost
     * @param range       the range
     * @param player      the player
     * @param cardAddress the card address
     */
    public Card(int cost, double range, Player player, String cardAddress) {
        this.cost = cost;
        this.range = range;
        this.player = player;
        this.cardImageAddress = cardAddress;
        this.timer = new Timer();
        this.imageView = new ImageView();
        this.secondInGame = 0;
    }

    /**
     * Gets card image address.
     *
     * @return the card image address
     */
    public String getCardImageAddress() {
        return cardImageAddress;
    }

    /**
     * Sets card image address.
     *
     * @param cardImage the card image
     */
    public void setCardImageAddress(String cardImage) {
        this.cardImageAddress = cardImage;
    }

    /**
     * Gets cost.
     *
     * @return the cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * Sets cost.
     *
     * @param cost the cost
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * Gets range.
     *
     * @return the range
     */
    public double getRange() {
        return range;
    }

    /**
     * Sets range.
     *
     * @param range the range
     */
    public void setRange(double range) {
        this.range = range;
    }

    /**
     * Gets player.
     *
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Sets player.
     *
     * @param player the player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Gets target.
     *
     * @return the target
     */
    public Target getTarget() {
        return target;
    }

    /**
     * Sets target.
     *
     * @param target the target
     */
    public void setTarget(Target target) {
        this.target = target;
    }

    /**
     * Gets self.
     *
     * @return the self
     */
    public Target getSelf() {
        return self;
    }

    /**
     * Sets self.
     *
     * @param self the self
     */
    public void setSelf(Target self) {
        this.self = self;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardImage=" + cardImageAddress +
                ", cost=" + cost +
                ", range=" + range +
                ", target=" + target +
                ", self=" + self +
                '}' + getClass();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardImageAddress);
    }

    public Point2D getPoint2D() {
        return point2D;
    }

    /**
     * Sets point 2 d.
     *
     * @param point2D the point 2 d
     */
    public void setPoint2D(Point2D point2D) {
        this.point2D = point2D;
    }

    /**
     * Gets image view.
     *
     * @return the image view
     */
    public ImageView getImageView() {
        return imageView;
    }

    /**
     * Sets image view.
     *
     * @param imageView the image view
     */
    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    /**
     * Gets timer.
     *
     * @return the timer
     */
    public Timer getTimer() {
        return timer;
    }

    /**
     * Sets timer.
     *
     * @param timer the timer
     */
    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    /**
     * Gets map.
     *
     * @return the map
     */
    public MapView getMap() {
        return map;
    }

    /**
     * Sets map.
     *
     * @param map the map
     */
    public void setMap(MapView map) {
        this.map = map;
    }

    /**
     * Start timer.
     */
    public void start() {
        timer = new Timer();
        timer.schedule(this, 500, 100);
    }

    /**
     * Stop timer.
     */
    public synchronized void stop() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                map.getChildren().remove(imageView);
            }
        });
        timer.cancel();
        this.cancel();
        timer.purge();
    }

    /**
     * Gets second in game.
     *
     * @return the second in game
     */
    public int getSecondInGame() {
        return secondInGame;
    }

    /**
     * Sets second in game.
     *
     * @param secondInGame the second in game
     */
    public void setSecondInGame(int secondInGame) {
        this.secondInGame = secondInGame;
    }

    /**
     * Update level.
     */
    public abstract void updateLevel();

    /**
     * Find closet target .
     *
     * @return the target
     */
    public Vulnerable findClosetTarget() {
        ArrayList<Vulnerable> vulnerableArrayList = new ArrayList<>();
        ArrayList<Card> opponentCards = getPlayer().getGame().getOpponent(getPlayer().getUser()).getPlayer().getGameAccessory().getInGameTargets();
        ArrayList<Tower> opponentTowers = getPlayer().getGame().getOpponent(getPlayer().getUser()).getPlayer().getGameAccessory().getTowers();
        for (Card card : opponentCards) {
            if (getTarget() == Target.AIR_AND_GROUND) {
                if (card.getSelf() == Target.AIR || card.getSelf() == Target.GROUND || card.getSelf() == Target.AIR_AND_GROUND || card.getSelf() == Target.BUILDINGS) {
                    vulnerableArrayList.add(card);
                }
            } else {
                if (card.getSelf() == getTarget()) {
                    vulnerableArrayList.add(card);
                }
            }
        }
        vulnerableArrayList.addAll(opponentTowers);
        if (vulnerableArrayList.size() == 0) {
            return null;
        }
        Vulnerable temp = vulnerableArrayList.get(0);
        for (Vulnerable vulnerable : vulnerableArrayList) {
            if (getPoint2D().distance(vulnerable.getPoint2D()) < getPoint2D().distance(temp.getPoint2D())) {
                temp = vulnerable;
            }
        }
        return temp;
    }

}