package Model;

/**
 * The type Building.
 *
 * @author Erfanm5152
 * @version 0.1
 */
public abstract class Building extends Card {
    // damage of the card
    private int damage;
    // health of the card
    private int health;
    // hit speed of the card
    private double hitSpeed;
    // life time of the card
    private int lifeTime;

    /**
     * Instantiates a new Building.
     *
     * @param cost        the cost
     * @param range       the range
     * @param hitSpeed    the hit speed
     * @param lifeTime    the life time
     * @param player      the player
     * @param cardAddress the card address
     */
    public Building(int cost, double range, double hitSpeed, int lifeTime, Player player, String cardAddress) {
        super(cost, range, player, cardAddress);
        this.hitSpeed = hitSpeed;
        this.lifeTime = lifeTime;
        setSelf(Target.BUILDINGS);
        setTarget(Target.AIR_AND_GROUND);
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
     * Gets life time.
     *
     * @return the life time
     */
    public int getLifeTime() {
        return lifeTime;
    }

    /**
     * Sets life time.
     *
     * @param lifeTime the life time
     */
    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }

    @Override
    public synchronized void decreaseHealth(int decreaseValue) {
        this.health = health - decreaseValue;
    }


    @Override
    public void effectOfRage() {
        damage = (int) (damage + (damage * 0.4));
    }

    @Override
    public void neutralizeRage() {
        damage = (int) (damage / 1.4);
    }
}
