package Model;

public enum Speed {
    SLOW(1),MEDIUM(2),FAST(3);

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
