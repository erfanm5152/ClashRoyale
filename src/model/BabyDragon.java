package Model;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;

/**
 * The type Baby dragon.
 *
 * @author Erfanm5152
 * @version 0.1
 */
public class BabyDragon extends Soldier {
    /**
     * Instantiates a new Baby dragon.
     *
     * @param player the player
     */
    public BabyDragon(Player player) {
        super(4, 3, 1.8, true, 1, Speed.FAST, player, "../View/pic/dragon.png");
        setSelf(Target.AIR);
        setTarget(Target.AIR_AND_GROUND);
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
                    getImageView().setImage(new Image(getClass().getResourceAsStream("../View/pic/inGame/dragon/gif.gif")));
                    getMap().getChildren().add(getImageView());
                }
            });
        }
        Vulnerable target = findClosetTarget();
        if (target != null) {
            if (getPoint2D().distance(target.getPoint2D()) > getRange() * 10) {
                goToTarget(target.getPoint2D());
            } else {
                if (getSecondInGame() % (getHitSpeed() * 1000) == 0) {
                    Circle range = new Circle(target.getPoint2D().getX(), target.getPoint2D().getY(), 10);
                    range.setVisible(false);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            getMap().getChildren().add(range);
                        }
                    });
                    for (Vulnerable vulnerable :
                            getPlayer().getGame().getOpponent(getPlayer().getUser()).getPlayer().getGameAccessory().getInGameTargets()) {
                        if (range.contains(vulnerable.getPoint2D())) {
                            vulnerable.decreaseHealth(getDamage());
                        }
                    }
                    for (Tower tower : getPlayer().getGame().getOpponent(getPlayer().getUser()).getPlayer().getGameAccessory().getTowers()) {
                        if (range.contains(tower.getPoint2D())) {
                            tower.decreaseHealth(getDamage());
                        }
                    }
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
                setHealth(800);
                setDamage(100);
            }
            case LEVEL2 -> {
                setHealth(880);
                setDamage(110);
            }
            case LEVEL3 -> {
                setHealth(968);
                setDamage(121);
            }
            case LEVEL4 -> {
                setHealth(1064);
                setDamage(133);
            }
            case LEVEL5 -> {
                setHealth(1168);
                setDamage(146);
            }
        }
    }
}
