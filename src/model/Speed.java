package Model;

public enum Speed {
    SLOW(0.5),RAGE_SLOW(0.7),MEDIUM(1),RAGE_MEDIUM(1.4)
    ,FAST(1.5),RAGE_FAST(2.1);

    private double speed;

    Speed(double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
