package Model;

import javafx.application.Platform;
import javafx.geometry.Point2D;

import java.util.Random;

/**
 * The type Soldier.
 *
 * @author Erfanm5152
 * @version 0.1
 */
public abstract class Soldier extends Card {
    // health of the card
    private int health;
    // damage of the card
    private int damage;
    // hit speed of the card
    private double hitSpeed;
    // card is area splash
    private boolean isAreaSplash;
    // number of soldiers
    private int count;
    // speed of the card
    private Speed speed;

    /**
     * Instantiates a new Soldier.
     *
     * @param cost         the cost
     * @param range        the range
     * @param hitSpeed     the hit speed
     * @param isAreaSplash the is area splash
     * @param count        the count
     * @param speed        the speed
     * @param player       the player
     * @param cardAddress  the card address
     */
    public Soldier(int cost, double range, double hitSpeed,
                   boolean isAreaSplash, int count
            , Speed speed, Player player, String cardAddress) {
        super(cost, range, player, cardAddress);
        this.hitSpeed = hitSpeed;
        this.isAreaSplash = isAreaSplash;
        this.count = count;
        this.speed = speed;
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
    public synchronized void setDamage(int damage) {
        this.damage = damage;
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
    public synchronized void setHitSpeed(double hitSpeed) {
        this.hitSpeed = hitSpeed;
    }

    /**
     * Is area splash boolean.
     *
     * @return the boolean
     */
    public boolean isAreaSplash() {
        return isAreaSplash;
    }

    /**
     * Sets area splash.
     *
     * @param areaSplash the area splash
     */
    public void setAreaSplash(boolean areaSplash) {
        isAreaSplash = areaSplash;
    }

    /**
     * Gets count.
     *
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets count.
     *
     * @param count the count
     */
    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public synchronized void decreaseHealth(int decreaseValue) {
        this.health = health - decreaseValue;
    }

    /**
     * Is target in opponent area
     *
     * @param target the target
     * @return the boolean
     */
    public boolean isTargetInOpponentArea(Vulnerable target) {
        if (getPlayer().getGame().getUser1().equals(getPlayer().getUser())) {
            if (target.getPoint2D().getY() <= 245 && getPoint2D().getY() >= 290) {
                return true;
            }
        } else {
            if (target.getPoint2D().getY() >= 290 && getPoint2D().getY() <= 245) {
                return true;
            }
        }
        return false;
    }

    /**
     * Goto bridge.
     */
    public void gotoBridge() {
        Point2D left = null;
        Point2D right = null;
        Point2D closetBridge = null;
        if (getPlayer().getUser().equals(getPlayer().getGame().getUser1())) {
            left = getPlayer().getGame().getLeftBridgeUser1();
            right = getPlayer().getGame().getRightBridgeUser1();
        } else {
            left = getPlayer().getGame().getLeftBridgeUser2();
            right = getPlayer().getGame().getRightBridgeUser2();
        }
        if (getPoint2D().distance(left) < getPoint2D().distance(right)) {
            if (Math.abs(getPoint2D().getX() - left.getX()) > 5) {
                goToTarget(new Point2D(left.getX(), getPoint2D().getY()));
            } else {
                goToTarget(left);
            }
        } else {
            if (Math.abs(getPoint2D().getX() - right.getX()) > 5) {
                goToTarget(new Point2D(right.getX(), getPoint2D().getY()));
            } else {
                goToTarget(right);
            }
        }
    }


    /**
     * Go to target.
     *
     * @param target the target
     */
    public void goToTarget(Point2D target) {
//        Point2D target = vulnerable.getPoint2D();
        Point2D newPoint;
        if (target.getX() > getPoint2D().getX()) {
            newPoint = getPoint2D().add(speed.getSpeed(), 0);
            if (getPlayer().getGame().isEmptyCell(newPoint, this)) {
                setPoint2D(newPoint);
            } else {
                target = findRandomTarget(target);
            }
        } else if (target.getX() < getPoint2D().getX()) {
            newPoint = getPoint2D().add(-1 * speed.getSpeed(), 0);
            if (getPlayer().getGame().isEmptyCell(newPoint, this)) {
                setPoint2D(newPoint);
            } else {
                target = findRandomTarget(target);
            }
        }
        if (target.getY() > getPoint2D().getY()) {
            newPoint = getPoint2D().add(0, speed.getSpeed());
            if (getPlayer().getGame().isEmptyCell(newPoint, this)) {
                setPoint2D(newPoint);
            } else {
                target = findRandomTarget(target);
            }
        } else if (target.getY() < getPoint2D().getY()) {
            newPoint = getPoint2D().add(0, -1 * speed.getSpeed());
            if (getPlayer().getGame().isEmptyCell(newPoint, this)) {
                setPoint2D(newPoint);
            } else {
                target = findRandomTarget(target);
            }
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                getImageView().setX(getPoint2D().getX());
                getImageView().setY(getPoint2D().getY());
            }
        });
//        getImageView().setX(getPoint2D().getX());
//        getImageView().setY(getPoint2D().getY());
    }

    /**
     * Find random target point 2 d.
     *
     * @param mainTarget the main target
     * @return the point 2 d
     */
    public Point2D findRandomTarget(Point2D mainTarget) {
        Random random = new Random();
        int x = random.nextInt(2) == 0 ? -1 : 1;
        int y = random.nextInt(2) == 0 ? -1 : 1;
        return mainTarget.subtract(x * random.nextInt(5), y * random.nextInt(5));
    }

    @Override
    public void effectOfRage() {
        increaseSpeed();
        setDamage((int) (damage + (damage * 0.4)));
    }

    @Override
    public void neutralizeRage() {
        neutralizeSpeed();
        setDamage((int) (damage / 1.4));
    }

    /**
     * Increase speed.
     */
    public void increaseSpeed() {
        switch (speed) {
            case SLOW -> speed = Speed.RAGE_SLOW;
            case MEDIUM -> speed = Speed.RAGE_MEDIUM;
            case FAST -> speed = Speed.RAGE_FAST;
        }
    }

    /**
     * Neutralize speed.
     */
    public void neutralizeSpeed() {
        switch (speed) {
            case RAGE_SLOW -> speed = Speed.SLOW;
            case RAGE_MEDIUM -> speed = Speed.MEDIUM;
            case RAGE_FAST -> speed = Speed.FAST;
        }
    }
}
