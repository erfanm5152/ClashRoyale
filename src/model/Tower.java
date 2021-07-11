package Model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.io.Serializable;


public abstract class Tower implements Runnable , Serializable {

    private int health;
    private int damage;
    private double range;
    private double hitSpeed;
    private Player player;
    private Point2D point2D;
    private String imageAddress;
    private Target self;

    public Tower( double range, double hitSpeed,Player player, String imageAddress) {
        this.range = range;
        this.hitSpeed = hitSpeed;
        this.imageAddress = imageAddress;
        this.self = Target.BUILDINGS;
        this.player = player;
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
}
