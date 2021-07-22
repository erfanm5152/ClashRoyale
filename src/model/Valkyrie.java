package Model;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;

public class Valkyrie extends Soldier{
    public Valkyrie(Player player) {
        super(4, 1, 1.5, true, 1, Speed.MEDIUM, player,"../View/pic/valkyrie.png");
        setSelf(Target.GROUND);
        setTarget(Target.GROUND);
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
                    getImageView().setImage(new Image(getClass().getResourceAsStream("../View/pic/inGame/valkyrie/gif.gif")));
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
                    Circle range = new Circle(getPoint2D().getX(),getPoint2D().getY(),10);
                    range.setVisible(false);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            getMap().getChildren().add(range);
                        }
                    });
                    for (Card card:getPlayer().getGame().getOpponent(getPlayer().getUser()).getPlayer().getGameAccessory().getInGameTargets()) {
                        if (range.contains(card.getPoint2D()) && card.getSelf()==Target.GROUND){
                            card.decreaseHealth(getDamage());
                        }
                    }
                    for (Tower tower: getPlayer().getGame().getOpponent(getPlayer().getUser()).getPlayer().getGameAccessory().getTowers()) {
                        if (range.contains(tower.getPoint2D())){
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
            case LEVEL1 ->{setHealth(880);setDamage(120);}
            case LEVEL2 ->{setHealth(968);setDamage(132);}
            case LEVEL3 ->{setHealth(1064);setDamage(145);}
            case LEVEL4 ->{setHealth(1170);setDamage(159);}
            case LEVEL5 ->{setHealth(1284);setDamage(175);}
        }
    }
}
