package Model;

public abstract class  Building extends Card{

    private int damage;
    private int health;
    private double hitSpeed;
    private int lifeTime;

    public Building(int cost, double range,double hitSpeed,int lifeTime,Player player ,String cardAddress) {
        super(cost, range,player ,cardAddress);
        this.hitSpeed = hitSpeed;
        this.lifeTime = lifeTime;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public double getHitSpeed() {
        return hitSpeed;
    }

    public void setHitSpeed(double hitSpeed) {
        this.hitSpeed = hitSpeed;
    }

    public int getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }

    @Override
    public synchronized void decreaseHealth(int decreaseValue) {
        this.health = health-decreaseValue;
    }
}
