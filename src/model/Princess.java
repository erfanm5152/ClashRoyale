package Model;


import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;

public class Princess extends Tower{

    public Princess(Player player,double x ,double y) {
        super(x,y,7.5,0.8,player,"./src/View/pic/princessTower.png");
        switch (getPlayer().getUser().getLevel()){
            case LEVEL1 ->{ setHealth(1400);setDamage(50);}
            case LEVEL2 ->{setHealth(1512);setDamage(54);}
            case LEVEL3 ->{setHealth(1624);setDamage(58);}
            case LEVEL4 ->{setHealth(1750);setDamage(62);}
            case LEVEL5 ->{setHealth(1890);setDamage(69);}
        }
    }

    @Override
    public void run() {
        Vulnerable target =findClosetTarget();
        if (target != null && isTargetAvailable(target)){
            if (getSecondInGame() % (getHitSpeed()*1000)==0){
                target.decreaseHealth(getDamage());
            }
        }
        if (getHealth()<=0 || getPlayer().getGame().isFinished() || target==null){
            stop();
            if (!getPlayer().getGame().isFinished()) {
                getPlayer().getGameAccessory().getKing().setDisabled(true);
                getPlayer().getGame().getOpponent(getPlayer().getUser()).getPlayer().getGameAccessory().increaseCups(1);
                getPlayer().getGameAccessory().removeTower(this);
                setFlag();
            }
//            getPlayer().getGameAccessory().getInGameTargets().remove(this);
        }
        setSecondInGame(getSecondInGame()+100);
    }

    @Override
    public boolean isTargetAvailable(Vulnerable target) {
        Point2D targetPoint = target .getPoint2D();
        if (Math.abs(targetPoint.getX()-getPoint2D().getX())<= 160 &&
        Math.abs(targetPoint.getY()-getPoint2D().getY())<=150){
            return true;
        }else {
            return false;
        }
    }
}
