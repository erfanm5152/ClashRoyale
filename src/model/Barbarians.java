package Model;

import javafx.application.Platform;
import javafx.scene.image.Image;

/**
 * The type Barbarians.
 *
 * @author Erfanm5152
 * @version 0.1
 */
public class Barbarians extends Soldier {
    // first health of card
    private int firstHealth;
    // first damage
    private int firstDamage;


    /**
     * Instantiates a new Barbarians.
     *
     * @param player the player
     */
    public Barbarians(Player player) {
        super(5, 1, 1.5, false, 4, Speed.MEDIUM, player, "../View/pic/barbarians.png");
        setTarget(Target.GROUND);
        setSelf(Target.GROUND);
        updateLevel();
        firstHealth = getHealth() / getCount();
        firstDamage = getDamage() / getCount();
    }

    @Override
    public void run() {
        if (getSecondInGame() < 100) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    getImageView().setFitHeight(40);
                    getImageView().setFitWidth(40);
                    getImageView().setX(getPoint2D().getX() - 20);
                    getImageView().setY(getPoint2D().getY() - 20);
                    getImageView().setImage(new Image(getClass().getResourceAsStream("../View/pic/inGame/barbar/gif.gif")));
                    getMap().getChildren().add(getImageView());
                }
            });
        }
        Vulnerable target = findClosetTarget();
        if (target != null) {
            if (getPoint2D().distance(target.getPoint2D()) > 10) {
                if (isTargetInOpponentArea(target)) {
                    gotoBridge();
                } else {
                    goToTarget(target.getPoint2D());
                }
            } else {
                if (getSecondInGame() % (getHitSpeed() * 1000) == 0) {
                    target.decreaseHealth(getDamage());
                }
            }
        }
        if (getHealth() <= 0 || target == null || getPlayer().getGame().isFinished()) {
            stop();
            getPlayer().getGameAccessory().removeCard(this);

        }
        setSecondInGame(getSecondInGame() + 100);
    }

    @Override
    public synchronized void decreaseHealth(int decreaseValue) {
        super.decreaseHealth(decreaseValue);
        if ( getCount()==4 && getHealth()<=firstHealth*3){
            setDamage(firstDamage*3);
            setCount(3);
        }
        if ( getCount()==3 && getHealth()<=firstHealth*2){
            setDamage(firstDamage*2);
            setCount(2);
        }if ( getCount()==2 && getHealth()<= firstHealth){
            setDamage(firstDamage);
            setCount(1);
        }
    }

    @Override
    public void updateLevel() {
        switch (getPlayer().getUser().getLevel()) {
            case LEVEL1 -> {
                setHealth(300 * 4);
                setDamage(75 * 4);
            }
            case LEVEL2 -> {
                setHealth(330 * 4);
                setDamage(82 * 4);
            }
            case LEVEL3 -> {
                setHealth(363 * 4);
                setDamage(90 * 4);
            }
            case LEVEL4 -> {
                setHealth(438 * 4);
                setDamage(99 * 4);
            }
            case LEVEL5 -> {
                setHealth(480 * 4);
                setDamage(109 * 4);
            }
        }
    }
}
