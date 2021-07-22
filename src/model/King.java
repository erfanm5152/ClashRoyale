package Model;

import javafx.geometry.Point2D;

import java.awt.*;

/**
 * The type King.
 *
 * @author Erfanm5152
 * @version 0.1
 */
public class King extends Tower {
    // To check for damage to the King Tower
    private boolean isDisabled;

    /**
     * Instantiates a new King.
     *
     * @param player the player
     * @param x      the x
     * @param y      the y
     */
    public King(Player player, double x, double y) {
        super(x, y, 7, 1, player, "./src/View/pic/KingTower.png");
        switch (getPlayer().getUser().getLevel()) {
            case LEVEL1 -> {
                setHealth(2400);
                setDamage(50);
            }
            case LEVEL2 -> {
                setHealth(2568);
                setDamage(53);
            }
            case LEVEL3 -> {
                setHealth(2736);
                setDamage(57);
            }
            case LEVEL4 -> {
                setHealth(2904);
                setDamage(60);
            }
            case LEVEL5 -> {
                setHealth(3096);
                setDamage(64);
            }
        }
        isDisabled = false;
    }

    @Override
    public void run() {
        if (isDisabled) {
            Vulnerable target = findClosetTarget();
            if (target != null && isTargetAvailable(target)) {
                if (getSecondInGame() % (getHitSpeed() * 1000) == 0) {
                    target.decreaseHealth(getDamage());
                }
            }
            if (getHealth() <= 0 || getPlayer().getGame().isFinished() || target == null) {
                stop();
                if (!getPlayer().getGame().isFinished()) {
                    getPlayer().getGameAccessory().removeTower(this);
                    getPlayer().getGame().getOpponent(getPlayer().getUser()).getPlayer().getGameAccessory().setNumberOfCups(3);
                    setFlag();
                }
//                getPlayer().getGameAccessory().getInGameTargets().remove(this);
            }
            setSecondInGame(getSecondInGame() + 100);
        }
    }

    /**
     * Is disabled boolean.
     *
     * @return the boolean
     */
    public boolean isDisabled() {
        return isDisabled;
    }

    /**
     * Sets disabled.
     *
     * @param disabled the disabled
     */
    public synchronized void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }

    @Override
    public void decreaseHealth(int decreaseValue) {
        super.decreaseHealth(decreaseValue);
        isDisabled = true;
        System.out.println(decreaseValue);
    }

    @Override
    public boolean isTargetAvailable(Vulnerable target) {
        Point2D targetPoint = target.getPoint2D();
        if (Math.abs(targetPoint.getY() - getPoint2D().getY()) <= 140) {
            return true;
        } else {
            return false;
        }
    }
}
