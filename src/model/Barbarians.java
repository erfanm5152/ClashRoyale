package Model;

import javafx.application.Platform;
import javafx.scene.image.Image;

public class Barbarians extends Soldier{
    public Barbarians(Player player) {
        super(5, 1, 1.5,false,4,Speed.MEDIUM,player,"../View/pic/barbarians.png");
        setTarget(Target.GROUND);
        setSelf(Target.GROUND);
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
        if (getHealth()<=0 || target==null || getPlayer().getGame().isFinished()){
            stop();
            getPlayer().getGameAccessory().getInGameTargets().remove(this);
        }
        setSecondInGame(getSecondInGame()+100);
    }

    @Override
    public void updateLevel() {
        switch (getPlayer().getUser().getLevel()){
            case LEVEL1 ->{setHealth(300);setDamage(75);}
            case LEVEL2 ->{setHealth(330);setDamage(82);}
            case LEVEL3 ->{setHealth(363);setDamage(90);}
            case LEVEL4 ->{setHealth(438);setDamage(99);}
            case LEVEL5 ->{setHealth(480);setDamage(109);}
        }
    }
}
