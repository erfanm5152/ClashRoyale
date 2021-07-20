package Model;

import javafx.application.Platform;
import javafx.scene.image.Image;

public class Giant extends Soldier{
    public Giant(Player player) {
        super(5,1,1.5,false,1,Speed.SLOW,player,"../View/pic/giant.png");
        setSelf(Target.GROUND);
        setTarget(Target.BUILDINGS);
        updateLevel();
    }

    @Override
    public void run() {
        if (getSecondInGame()<100) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    getImageView().setFitHeight(40);
                    getImageView().setFitWidth(40);
                    getImageView().setX(getPoint2D().getX()-20);
                    getImageView().setY(getPoint2D().getY()-20);
                    getImageView().setImage(new Image(getClass().getResourceAsStream("../View/pic/inGame/giant/gif.gif")));
                    getMap().getChildren().add(getImageView());
                }
            });
        }
        Vulnerable target = findClosetTarget();
        if (target != null) {
            if (getPoint2D().distance(target.getPoint2D()) > getRange()*10) {
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

        if (getHealth()<=0 || target==null || getPlayer().getGame().isFinished()){
            stop();
            getPlayer().getGameAccessory().getInGameTargets().remove(this);
        }
        setSecondInGame(getSecondInGame()+100);
    }

    @Override
    public void updateLevel() {
        switch (getPlayer().getUser().getLevel()){
            case LEVEL1 ->{setHealth(2000);setDamage(126);}
            case LEVEL2 ->{setHealth(2200);setDamage(138);}
            case LEVEL3 ->{setHealth(2420);setDamage(152);}
            case LEVEL4 ->{setHealth(2660);setDamage(167);}
            case LEVEL5 ->{setHealth(2920);setDamage(183);}
        }
    }

}
