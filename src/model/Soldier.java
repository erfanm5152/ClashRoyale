package Model;

import javafx.application.Platform;
import javafx.geometry.Point2D;

import java.util.Random;

public abstract class Soldier extends Card {
    private int health;
    private int damage;
    private double hitSpeed;
    private boolean isAreaSplash;
    private int count;
    private Speed speed;

    public Soldier(int cost, double range, double hitSpeed,
                   boolean isAreaSplash, int count
            , Speed speed, Player player, String cardAddress) {
        super(cost, range, player, cardAddress);
        this.hitSpeed = hitSpeed;
        this.isAreaSplash = isAreaSplash;
        this.count = count;
        this.speed = speed;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public synchronized void setDamage(int damage) {
        this.damage = damage;
    }

    public double getHitSpeed() {
        return hitSpeed;
    }

    public synchronized void setHitSpeed(double hitSpeed) {
        this.hitSpeed = hitSpeed;
    }

    public boolean isAreaSplash() {
        return isAreaSplash;
    }

    public void setAreaSplash(boolean areaSplash) {
        isAreaSplash = areaSplash;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public synchronized void decreaseHealth(int decreaseValue) {
        this.health = health - decreaseValue;
    }

// todo in tabe moshkel darad
    public boolean isTargetInOpponentArea(Vulnerable target) {
        if (getPlayer().getGame().getUser1().equals(getPlayer().getUser())) {
            if (target.getPoint2D().getY() <= 245 && getPoint2D().getY() >= 245) {
                return true;
            }
        } else {
            if (target.getPoint2D().getY() > 295 && getPoint2D().getY() < 295) {
                return true;
            }
        }
        return false;
    }

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
            goToTarget(left);
        } else {
            goToTarget(right);
        }
    }
//todo dorost shavad
    public void goToTarget(Point2D target) {
//        Point2D target = vulnerable.getPoint2D();
        Point2D newPoint;
        if (target.getX() > getPoint2D().getX()) {
            newPoint = getPoint2D().add(speed.getSpeed(), 0);
//            if (getPlayer().getGame().isEmptyCell(newPoint, this)) {
                setPoint2D(newPoint);
//            } else {
//                target = findRandomTarget(target);
//            }
        } else if (target.getX() < getPoint2D().getX()) {
            newPoint = getPoint2D().add(-1 * speed.getSpeed(), 0);
//            if (getPlayer().getGame().isEmptyCell(newPoint, this)) {
                setPoint2D(newPoint);
//            } else {
//                target = findRandomTarget(target);
//            }
        }
        if (target.getY() > getPoint2D().getY()) {
            newPoint = getPoint2D().add(0, speed.getSpeed());
//            if (getPlayer().getGame().isEmptyCell(newPoint, this)) {
                setPoint2D(newPoint);
//            } else {
//                target = findRandomTarget(target);
//            }
        } else if (target.getY() < getPoint2D().getY()) {
            newPoint = getPoint2D().add(0, -1 * speed.getSpeed());
//            if (getPlayer().getGame().isEmptyCell(newPoint, this)) {
                setPoint2D(newPoint);
//            } else {
//                target = findRandomTarget(target);
//            }
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

    public Point2D findRandomTarget(Point2D mainTarget) {
        Random random = new Random();
        int x = random.nextInt(2) == 0 ? -1 : 1;
        int y = random.nextInt(2) == 0 ? -1 : 1;
        return mainTarget.subtract(x*random.nextInt(5), y*random.nextInt(5));
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

    public void increaseSpeed() {
        switch (speed) {
            case SLOW -> speed = Speed.RAGE_SLOW;
            case MEDIUM -> speed = Speed.RAGE_MEDIUM;
            case FAST -> speed = Speed.RAGE_FAST;
        }
    }

    public void neutralizeSpeed() {
        switch (speed) {
            case RAGE_SLOW -> speed = Speed.SLOW;
            case RAGE_MEDIUM -> speed = Speed.MEDIUM;
            case RAGE_FAST -> speed = Speed.FAST;
        }
    }
}
