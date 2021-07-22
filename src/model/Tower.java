package Model;

import View.MapView;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


/**
 * The type Tower.
 *
 * @author Erfanm5152
 * @version 0.1
 */
public abstract class Tower extends TimerTask implements Vulnerable {
    // health of the tower
    private int health;
    // damage of strike
    private int damage;
    // range of strike
    private double range;
    // hit speed of strike
    private double hitSpeed;
    // player of the tower
    private Player player;
    // point of the tower
    private transient Point2D point2D;
    // address of the image
    private String imageAddress;
    // type of the self
    private Target self;
    // timer
    private Timer timer;
    // seconds of the tower in game
    private transient int secondInGame;
    // map of the game
    private transient MapView map;

    /**
     * Instantiates a new Tower.
     *
     * @param x            the x
     * @param y            the y
     * @param range        the range
     * @param hitSpeed     the hit speed
     * @param player       the player
     * @param imageAddress the image address
     */
    public Tower(double x, double y, double range, double hitSpeed, Player player, String imageAddress) {
        point2D = new Point2D(x, y);
        this.range = range;
        this.hitSpeed = hitSpeed;
        this.imageAddress = imageAddress;
        this.self = Target.BUILDINGS;
        this.player = player;
        this.secondInGame = 0;
        this.timer = new Timer();
    }

    /**
     * Gets health.
     *
     * @return the health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets health.
     *
     * @param health the health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Gets damage.
     *
     * @return the damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Sets damage.
     *
     * @param damage the damage
     */
    public void setDamage(int damage) {
        this.damage = damage;
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
     * Gets hit speed.
     *
     * @return the hit speed
     */
    public double getHitSpeed() {
        return hitSpeed;
    }

    /**
     * Sets hit speed.
     *
     * @param hitSpeed the hit speed
     */
    public void setHitSpeed(double hitSpeed) {
        this.hitSpeed = hitSpeed;
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
     * Gets image address.
     *
     * @return the image address
     */
    public String getImageAddress() {
        return imageAddress;
    }

    /**
     * Sets image address.
     *
     * @param image the image
     */
    public void setImageAddress(String image) {
        this.imageAddress = image;
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

    @Override
    public synchronized void decreaseHealth(int decreaseValue) {
        this.health = health - decreaseValue;
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
     * Start timer.
     */
    public void start() {
        getTimer().schedule(this, 0, 100);
    }

    /**
     * Stop timer.
     */
    public void stop() {
        getTimer().cancel();
        getTimer().purge();
    }

    /**
     * Find closet target.
     *
     * @return the target
     */
    public Vulnerable findClosetTarget() {
        ArrayList<Vulnerable> vulnerableArrayList = new ArrayList<>();
        vulnerableArrayList.addAll(getPlayer().getGame().getOpponent(getPlayer().getUser()).getPlayer().getGameAccessory().getInGameTargets());
        vulnerableArrayList.addAll(getPlayer().getGame().getOpponent(getPlayer().getUser()).getPlayer().getGameAccessory().getTowers());
        if (vulnerableArrayList.size() == 0) {
            return null;
        }
        Vulnerable temp = vulnerableArrayList.get(0);
        for (Vulnerable vulnerable : vulnerableArrayList) {
            if (getPoint2D().distance(temp.getPoint2D()) > getPoint2D().distance(vulnerable.getPoint2D())) {
                temp = vulnerable;
            }
        }
        return temp;
    }

    @Override
    public void effectOfRage() {
        damage = (int) (damage + (damage * 0.4));
    }

    @Override
    public void neutralizeRage() {
        damage = (int) (damage / 1.4);
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
     * Set flag for end of life of the tower.
     */
    public void setFlag() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("../View/pic/flag.gif")));
                imageView.setFitWidth(32);
                imageView.setFitHeight(48);
                imageView.setX(getPoint2D().getX() - 16);
                imageView.setY(getPoint2D().getY() - 48);
                getMap().getChildren().add(imageView);
            }
        });
    }

    /**
     * Is target available.
     *
     * @param target the target
     * @return the boolean
     */
    public abstract boolean isTargetAvailable(Vulnerable target);
}
