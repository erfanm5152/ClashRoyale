package Model;

import javafx.geometry.Point2D;

import java.awt.*;

public class King extends Tower {
    private boolean isDisabled;

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
            if (target != null && isTargetAvailable(target)){
                if (getSecondInGame() % (getHitSpeed()*1000)==0){
                    target.decreaseHealth(getDamage());
                    System.out.println(target.getClass().getSimpleName() + "point = "+getPoint2D());
                }
            }
            if (getHealth() <= 0 || getPlayer().getGame().isFinished() || target == null) {
                stop();
                if (!getPlayer().getGame().isFinished()) {
                    getPlayer().getGameAccessory().getTowers().remove(this);
                    getPlayer().getGame().getOpponent(getPlayer().getUser()).getPlayer().getGameAccessory().setNumberOfCups(3);
                }
//                getPlayer().getGameAccessory().getInGameTargets().remove(this);
            }
            setSecondInGame(getSecondInGame() + 100);
        }
    }

    public boolean isDisabled() {
        return isDisabled;
    }

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
