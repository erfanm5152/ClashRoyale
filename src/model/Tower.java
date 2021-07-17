package Model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;


public abstract class Tower extends TimerTask implements Vulnerable{

    private int health;
    private int damage;
    private double range;
    private double hitSpeed;
    private Player player;
    private transient Point2D point2D;
    private String imageAddress;
    private Target self;
    private Timer timer;

    public Tower(double x , double y ,double range, double hitSpeed,Player player, String imageAddress) {
        point2D = new Point2D(x, y);
        this.range = range;
        this.hitSpeed = hitSpeed;
        this.imageAddress = imageAddress;
        this.self = Target.BUILDINGS;
        this.player = player;
        this.timer = new Timer();
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

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public double getHitSpeed() {
        return hitSpeed;
    }

    public void setHitSpeed(double hitSpeed) {
        this.hitSpeed = hitSpeed;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Point2D getPoint2D() {
        return point2D;
    }

    public void setPoint2D(Point2D point2D) {
        this.point2D = point2D;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String image) {
        this.imageAddress = image;
    }

    @Override
    public synchronized void decreaseHealth(int decreaseValue) {
        this.health = health-decreaseValue;
    }

    public Target getSelf() {
        return self;
    }

    public void setSelf(Target self) {
        this.self = self;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
    public void start() {
        getTimer().schedule(this,0, (long) (hitSpeed*1000));
    }

    public void stop(){
        getTimer().cancel();
        getTimer().purge();
    }


}
