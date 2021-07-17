package Model;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

import java.io.Serializable;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Card extends TimerTask implements Serializable,Vulnerable{
    private String cardImageAddress;
    private int cost;
    private double range;
    private Player player;
    private transient Point2D point2D;
    private Target target;
    private Target self;
    private ImageView imageView;
    private transient Timer timer;

    public Card(int cost, double range,Player player,String cardAddress) {
        this.cost = cost;
        this.range = range;
        this.player = player;
        this.cardImageAddress = cardAddress;
        this.timer = new Timer();
    }

    public String getCardImageAddress() {
        return cardImageAddress;
    }

    public void setCardImageAddress(String cardImage) {
        this.cardImageAddress = cardImage;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public Target getSelf() {
        return self;
    }

    public void setSelf(Target self) {
        this.self = self;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardImage=" + cardImageAddress +
                ", cost=" + cost +
                ", range=" + range +
                ", target=" + target +
                ", self=" + self +
                '}'+getClass();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardImageAddress);
    }

    public Point2D getPoint2D() {
        return point2D;
    }

    public void setPoint2D(Point2D point2D) {
        this.point2D = point2D;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    
}