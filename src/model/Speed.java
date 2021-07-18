package Model;

public enum Speed {
    SLOW(0.5),MEDIUM(1),FAST(1.5);

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
