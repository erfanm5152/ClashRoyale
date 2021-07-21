package Model;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;

public class Archer extends Soldier{
    public Archer(Player player) {
        super(3, 5, 1.2, false, 2, Speed.MEDIUM,player, "../View/pic/archer.png");
        setSelf(Target.GROUND);
        setTarget(Target.AIR_AND_GROUND);
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
                    getImageView().setImage(new Image(getClass().getResourceAsStream("../View/pic/inGame/archer/gif.gif")));
                    getMap().getChildren().add(getImageView());
                }
            });
        }
        Vulnerable target = findClosetTarget();
        if (target != null) {
            if (getPoint2D().distance(target.getPoint2D()) > getRange()*10) {
                if (isTargetInOpponentArea(target)){
                    gotoBridge();
                }else {
                    goToTarget(target.getPoint2D());
                }
            } else {
                if (getSecondInGame() % (getHitSpeed() * 1000) == 0) {
                   target.decreaseHealth(getDamage());
                }
            }
        }
        if (getHealth()<=0||target==null ||getPlayer().getGame().isFinished()){
            stop();
            getPlayer().getGameAccessory().removeCard(this);

        }
        setSecondInGame(getSecondInGame()+100);
    }

    @Override
    public void updateLevel() {
        switch (getPlayer().getUser().getLevel()){
            case LEVEL1 ->{setHealth(125);setDamage(33);}
            case LEVEL2 ->{setHealth(127);setDamage(44);}
            case LEVEL3 ->{setHealth(151);setDamage(48);}
            case LEVEL4 ->{setHealth(166);setDamage(53);}
            case LEVEL5 ->{setHealth(182);setDamage(58);}
        }
    }
}
