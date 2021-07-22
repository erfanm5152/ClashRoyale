package Model;

/**
 * The enum Speed.
 *
 * @author Erfanm5152
 * @version 0.1
 */
public enum Speed {
    /**
     * Slow speed.
     */
    SLOW(0.5),
    /**
     * Rage slow speed.
     */
    RAGE_SLOW(0.7),
    /**
     * Medium speed.
     */
    MEDIUM(1),
    /**
     * Rage medium speed.
     */
    RAGE_MEDIUM(1.4),
    /**
     * Fast speed.
     */
    FAST(1.5),
    /**
     * Rage fast speed.
     */
    RAGE_FAST(2.1);
    // value of speed
    private double speed;

    /**
     * create new speed
     *
     * @param speed value of speed
     */
    Speed(double speed) {
        this.speed = speed;
    }

    /**
     * Gets speed.
     *
     * @return the speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Sets speed.
     *
     * @param speed the speed
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
