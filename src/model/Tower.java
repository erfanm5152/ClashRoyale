package Model;

import View.MapView;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public abstract class Tower extends TimerTask implements Vulnerable {

    private int health;
    private int damage;
    private double range;
    private double hitSpeed;
    private Player player;
    private transient Point2D point2D;
    private String imageAddress;
    private Target self;
    private Timer timer;
    private transient int secondInGame;
    private transient MapView map;

    public Tower(double x, double y, double range, double hitSpeed, Player player, String imageAddress) {
        point2D = new Point2D(x, y);
        this.range = range;
        this.hitSpeed = hitSpeed;
        this.imageAddress = imageAddress;
        this.self = Target.BUILDINGS;
        this.player = player;
        this.secondInGame = 0;
        this.timer = new Timer();
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

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public double getHitSpeed() {
        return hitSpeed;
    }

    public void setHitSpeed(double hitSpeed) {
        this.hitSpeed = hitSpeed;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Point2D getPoint2D() {
        return point2D;
    }

    public void setPoint2D(Point2D point2D) {
        this.point2D = point2D;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String image) {
        this.imageAddress = image;
    }

    public int getSecondInGame() {
        return secondInGame;
    }

    public void setSecondInGame(int secondInGame) {
        this.secondInGame = secondInGame;
    }

    @Override
    public synchronized void decreaseHealth(int decreaseValue) {
        this.health = health - decreaseValue;
    }

    public Target getSelf() {
        return self;
    }

    public void setSelf(Target self) {
        this.self = self;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public void start() {
        getTimer().schedule(this, 0, 100);
    }

    public void stop() {
        getTimer().cancel();
        getTimer().purge();
    }

    public Vulnerable findClosetTarget() {
        ArrayList<Vulnerable> vulnerableArrayList = new ArrayList<>();
        vulnerableArrayList.addAll(getPlayer().getGame().getOpponent(getPlayer().getUser()).getPlayer().getGameAccessory().getInGameTargets());
        vulnerableArrayList.addAll(getPlayer().getGame().getOpponent(getPlayer().getUser()).getPlayer().getGameAccessory().getTowers());
        if (vulnerableArrayList.size() == 0) {
            return null;
        }
        Vulnerable temp = vulnerableArrayList.get(0);
        for (Vulnerable vulnerable : vulnerableArrayList) {
            if (getPoint2D().distance(temp.getPoint2D()) > getPoint2D().distance(vulnerable.getPoint2D())) {
                temp = vulnerable;
            }
        }
        return temp;
    }

    @Override
    public void effectOfRage() {
        damage = (int) (damage + (damage * 0.4));
//        hitSpeed = hitSpeed - (hitSpeed*0.4);
    }

    @Override
    public void neutralizeRage() {
        damage = (int) (damage / 1.4);
//        hitSpeed = hitSpeed/0.6;
    }

    public MapView getMap() {
        return map;
    }

    public void setMap(MapView map) {
        this.map = map;
    }

    public void setFlag(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("../View/pic/flag.gif")));
                imageView.setFitWidth(32);
                imageView.setFitHeight(48);
                imageView.setX(getPoint2D().getX()-16);
                imageView.setY(getPoint2D().getY()-48);
                getMap().getChildren().add(imageView);
            }
        });
    }

    public abstract boolean isTargetAvailable(Vulnerable target);
}
