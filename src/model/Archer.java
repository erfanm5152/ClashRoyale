package Model;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;

/**
 * The type Archer.
 *
 * @author Erfanm5152
 * @version 0.1
 */
public class Archer extends Soldier {
    // first health of the archer
    private int firstHealth;

    /**
     * Instantiates a new Archer.
     *
     * @param player the player
     */
    public Archer(Player player) {
        super(3, 5, 1.2, false, 2, Speed.MEDIUM, player, "../View/pic/archer.png");
        setSelf(Target.GROUND);
        setTarget(Target.AIR_AND_GROUND);
        updateLevel();
        firstHealth = getHealth();
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
                    getImageView().setImage(new Image(getClass().getResourceAsStream("../View/pic/inGame/archer/gif.gif")));
                    getMap().getChildren().add(getImageView());
                }
            });
        }
        Vulnerable target = findClosetTarget();
        if (target != null) {
            if (getPoint2D().distance(target.getPoint2D()) > getRange() * 10) {
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
        if (getHealth() < firstHealth / 2 && getCount() == 2) {
            setDamage(getDamage() / 2);
            setCount(1);
        }
    }

    @Override
    public void updateLevel() {
        switch (getPlayer().getUser().getLevel()) {
            case LEVEL1 -> {
                setHealth(125 * 2);
                setDamage(33 * 2);
            }
            case LEVEL2 -> {
                setHealth(127 * 2);
                setDamage(44 * 2);
            }
            case LEVEL3 -> {
                setHealth(151 * 2);
                setDamage(48 * 2);
            }
            case LEVEL4 -> {
                setHealth(166 * 2);
                setDamage(53 * 2);
            }
            case LEVEL5 -> {
                setHealth(182 * 2);
                setDamage(58 * 2);
            }
        }
    }
}
