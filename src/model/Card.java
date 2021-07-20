package Model;

import View.MapView;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

import java.io.Serializable;
import java.util.ArrayList;
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
    private transient int secondInGame;
    private transient ImageView imageView;
    private transient Timer timer;
    private transient MapView map;

    public Card(int cost, double range,Player player,String cardAddress) {
        this.cost = cost;
        this.range = range;
        this.player = player;
        this.cardImageAddress = cardAddress;
        this.timer = new Timer();
        this.imageView = new ImageView();
        this.secondInGame = 0;
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

    public MapView getMap() {
        return map;
    }

    public void setMap(MapView map) {
        this.map = map;
    }
    public void start(){
        timer = new Timer();
        timer.schedule(this,500,100);
    }
    public synchronized void stop(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                map.getChildren().remove(imageView);
            }
        });
        timer.cancel();
        timer.purge();
    }

    public int getSecondInGame() {
        return secondInGame;
    }

    public void setSecondInGame(int secondInGame) {
        this.secondInGame = secondInGame;
    }

    public abstract void updateLevel();

    public Vulnerable findClosetTarget(){
        ArrayList<Vulnerable> vulnerableArrayList = new ArrayList<>();
        ArrayList<Card> opponentCards = getPlayer().getGame().getOpponent(getPlayer().getUser()).getPlayer().getGameAccessory().getInGameTargets();
        ArrayList<Tower> opponentTowers = getPlayer().getGame().getOpponent(getPlayer().getUser()).getPlayer().getGameAccessory().getTowers();
        for (Card card:opponentCards) {
            if (getTarget() == Target.AIR_AND_GROUND){
                if (card.getSelf() == Target.AIR || card.getSelf() == Target.GROUND || card.getSelf()==Target.AIR_AND_GROUND){
                    vulnerableArrayList.add(card);
                }
            }else {
                if (card.getSelf()==getTarget()){
                    vulnerableArrayList.add(card);
                }
            }
        }
        vulnerableArrayList.addAll(opponentTowers);
        if (vulnerableArrayList.size()==0){
            return null;
        }
        Vulnerable temp = vulnerableArrayList.get(0);
        for (Vulnerable vulnerable:vulnerableArrayList) {
            if (getPoint2D().distance(vulnerable.getPoint2D())<getPoint2D().distance(temp.getPoint2D())){
                temp = vulnerable;
            }
        }
        return temp;
    }


}