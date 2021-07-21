package Model;

import javafx.application.Platform;
import javafx.scene.image.Image;

public class Cannon extends Building {
    public Cannon(Player player) {
        super(3, 5.5, 0.8, 30, player, "../View/pic/cannon.png");
        updateLevel();
    }

    @Override
    public void run() {
        if (getSecondInGame() < 100) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    getImageView().setFitHeight(30);
                    getImageView().setFitWidth(30);
                    getImageView().setX(getPoint2D().getX() - 15);
                    getImageView().setY(getPoint2D().getY() - 15);
                    getImageView().setImage(new Image(getClass().getResourceAsStream("../View/pic/inGame/cannon/cannon.png")));
                    getMap().getChildren().add(getImageView());
                }
            });
        }
        Vulnerable target = findClosetTarget();

        if (getHealth() <= 0 || getSecondInGame() == getLifeTime() * 1000 || target == null || getPlayer().getGame().isFinished()) {
            stop();
            getPlayer().getGameAccessory().removeCard(this);
            return;
        }
        if (getPoint2D().distance(target.getPoint2D()) <= getRange() * 10) {
            if (getSecondInGame() % (getHitSpeed() * 1000) == 0) {
                target.decreaseHealth(getDamage());
            }
        }
        setSecondInGame(getSecondInGame() + 100);
    }

    @Override
    public void updateLevel() {
        switch (getPlayer().getUser().getLevel()) {
            case LEVEL1 -> {
                setHealth(380);
                setDamage(60);
            }
            case LEVEL2 -> {
                setHealth(418);
                setDamage(66);
            }
            case LEVEL3 -> {
                setHealth(459);
                setDamage(72);
            }
            case LEVEL4 -> {
                setHealth(505);
                setDamage(79);
            }
            case LEVEL5 -> {
                setHealth(554);
                setDamage(87);
            }
        }
    }
}
