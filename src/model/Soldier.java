package Model;

import javafx.geometry.Point2D;

public abstract class Soldier extends Card{
    private int health;
    private int damage;
    private double hitSpeed;
    private boolean isAreaSplash;
    private int count;
    private Speed speed;

    public Soldier(int cost, double range,double hitSpeed,
                   boolean isAreaSplash,int count
                   ,Speed speed,Player player , String cardAddress) {
        super(cost,range,player,cardAddress);
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

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public double getHitSpeed() {
        return hitSpeed;
    }

    public void setHitSpeed(double hitSpeed) {
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
        this.health=health-decreaseValue;
    }

    public void goToTarget(Vulnerable vulnerable){
        Point2D target = vulnerable.getPoint2D();
        if (target.getX()>getPoint2D().getX()){
            setPoint2D(getPoint2D().add(speed.getSpeed(),0));
        }else if (target.getX()<getPoint2D().getX()){
            setPoint2D(getPoint2D().add(-1*speed.getSpeed(),0));
        }
        if (target.getY()>getPoint2D().getY()){
            setPoint2D(getPoint2D().add(0,speed.getSpeed()));
        }else if (target.getY()<getPoint2D().getY()){
            setPoint2D(getPoint2D().add(0,-1*speed.getSpeed()));
        }
        getImageView().setX(getPoint2D().getX());
        getImageView().setY(getPoint2D().getY());
    }
}
