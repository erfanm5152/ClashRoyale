package Model;

import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.Objects;

public abstract class Card implements Runnable , Serializable {
    private String cardImageAddress;
//    private Image inGameImage;
    private int cost;
    private double range;
    private Player player;
//  نوع هدف
    private Target target;
//  نوع خود کارت
    private Target self;

    public Card(int cost, double range,Player player,String cardAddress) {
        this.cost = cost;
        this.range = range;
        this.player = player;
        this.cardImageAddress = cardAddress;
//        this.inGameImage = new Image("file: "+inGameImage);
    }

    public String getCardImageAddress() {
        return cardImageAddress;
    }

    public void setCardImageAddress(String cardImage) {
        this.cardImageAddress = cardImage;
    }

//    public Image getInGameImage() {
//        return inGameImage;
//    }
//
//    public void setInGameImage(Image inGameImage) {
//        this.inGameImage = inGameImage;
//    }

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
}
