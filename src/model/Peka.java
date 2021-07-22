package Model;

import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

/**
 * The type Peka.
 *
 * @author Erfanm5152
 * @version 0.1
 */
public class Peka extends Soldier {

    /**
     * Instantiates a new Peka.
     *
     * @param player the player
     */
    public Peka(Player player) {
        super(4, 1, 1.8, false, 1, Speed.FAST, player, "../View/pic/peka.png");
        setSelf(Target.GROUND);
        setTarget(Target.GROUND);
        updateLevel();
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
                    getImageView().setImage(new Image(getClass().getResourceAsStream("../View/pic/inGame/peka/gif.gif")));
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
    public void updateLevel() {
        switch (getPlayer().getUser().getLevel()) {
            case LEVEL1 -> {
                setHealth(600);
                setDamage(325);
            }
            case LEVEL2 -> {
                setHealth(660);
                setDamage(357);
            }
            case LEVEL3 -> {
                setHealth(726);
                setDamage(393);
            }
            case LEVEL4 -> {
                setHealth(798);
                setDamage(432);
            }
            case LEVEL5 -> {
                setHealth(876);
                setDamage(474);
            }
        }
    }
}
