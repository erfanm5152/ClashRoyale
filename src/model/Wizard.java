package Model;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;

public class Wizard extends Soldier{
    public Wizard(Player player) {
        super(5, 5, 1.7, true, 1, Speed.MEDIUM,player ,"../View/pic/wizard.png");
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
                    getImageView().setFitHeight(30);
                    getImageView().setFitWidth(30);
                    getImageView().setX(getPoint2D().getX()-20);
                    getImageView().setY(getPoint2D().getY()-20);
                    getImageView().setImage(new Image(getClass().getResourceAsStream("../View/pic/inGame/wizard/gif.gif")));
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
        if (getHealth()<=0||target==null ||getPlayer().getGame().isFinished()){
            stop();
            getPlayer().getGameAccessory().removeCard(this);
        }
        setSecondInGame(getSecondInGame()+100);
    }

    @Override
    public void updateLevel() {
        switch (getPlayer().getUser().getLevel()){
            case LEVEL1 ->{setHealth(340);setDamage(130);}
            case LEVEL2 ->{setHealth(374);setDamage(143);}
            case LEVEL3 ->{setHealth(411);setDamage(157);}
            case LEVEL4 ->{setHealth(452);setDamage(172);}
            case LEVEL5 ->{setHealth(496);setDamage(189);}
        }
    }
}
