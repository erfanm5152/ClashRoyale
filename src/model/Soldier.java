package Model;

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
}
