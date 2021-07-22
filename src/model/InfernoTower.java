package Model;

import javafx.application.Platform;
import javafx.scene.image.Image;

/**
 * The type Inferno tower.
 *
 * @author Erfanm5152
 * @version 0.1
 */
public class InfernoTower extends Building {
    // lower damage of the tower
    private int lowDamage;
    // value of increase damage per second
    private int increaseDamagePerSecond;

    /**
     * Instantiates a new Inferno tower.
     *
     * @param player the player
     */
    public InfernoTower(Player player) {
        super(5, 6, 0.4, 40, player, "../View/pic/inferno.png");
        updateLevel();
        increaseDamagePerSecond = (int) (Math.floor(getDamage() - lowDamage) / 40);
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
                    getImageView().setImage(new Image(getClass().getResourceAsStream("../View/pic/inGame/inferno/inferno.gif")));
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
                target.decreaseHealth(getLowDamage());
            }
        }
        if (getSecondInGame() % 1000 == 0) {
            lowDamage += increaseDamagePerSecond;
        }
        setSecondInGame(getSecondInGame() + 100);
    }

    /**
     * Gets low damage.
     *
     * @return the low damage
     */
    public int getLowDamage() {
        return lowDamage;
    }

    /**
     * Sets low damage.
     *
     * @param lowDamage the low damage
     */
    public void setLowDamage(int lowDamage) {
        this.lowDamage = lowDamage;
    }

    /**
     * Gets increase damage per second.
     *
     * @return the increase damage per second
     */
    public int getIncreaseDamagePerSecond() {
        return increaseDamagePerSecond;
    }

    /**
     * Sets increase damage per second.
     *
     * @param increaseDamagePerSecond the increase damage per second
     */
    public void setIncreaseDamagePerSecond(int increaseDamagePerSecond) {
        this.increaseDamagePerSecond = increaseDamagePerSecond;
    }

    @Override
    public void updateLevel() {
        switch (getPlayer().getUser().getLevel()) {
            case LEVEL1 -> {
                setHealth(800);
                lowDamage = 20;
                setDamage(400);
            }
            case LEVEL2 -> {
                setHealth(880);
                lowDamage = 22;
                setDamage(440);
            }
            case LEVEL3 -> {
                setHealth(968);
                lowDamage = 24;
                setDamage(484);
            }
            case LEVEL4 -> {
                setHealth(1064);
                lowDamage = 26;
                setDamage(532);
            }
            case LEVEL5 -> {
                setHealth(1168);
                lowDamage = 29;
                setDamage(584);
            }
        }
    }
}
