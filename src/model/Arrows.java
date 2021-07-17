package Model;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Arrows extends Spell{
    private int damage;
    public Arrows(Player player) {
        super(3,4,player ,"../View/pic/arrows.png");
        switch (getPlayer().getUser().getLevel()){
            case LEVEL1 -> damage = 144;
            case LEVEL2 -> damage = 156;
            case LEVEL3 -> damage = 174;
            case LEVEL4 -> damage = 189;
            case LEVEL5 -> damage = 210;
        }
    }

    @Override
    public void run() {
        Circle range = new Circle(getPoint2D().getX(),getPoint2D().getY(),40);
        range.setVisible(false);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                getMap().getChildren().add(range);
            }
        });
        getImageView().setFitHeight(80);
        getImageView().setFitWidth(80);
        getImageView().setX(getPoint2D().getX()-40);
        getImageView().setY(getPoint2D().getY()-40);

        if (getSecondInGame()<100) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    getImageView().setImage(new Image(getClass().getResourceAsStream("../View/pic/inGame/arrows/gif.gif")));
                    getMap().getChildren().add(getImageView());
                }
            });
        }
        if (getSecondInGame()<100) {
            for (Vulnerable vulnerable :
                    getPlayer().getGame().getOpponent(getPlayer().getUser()).getPlayer().getGameAccessory().getInGameTargets()) {
                if (range.contains(vulnerable.getPoint2D())) {
                    vulnerable.decreaseHealth(damage);
                }
            }
        }
        if (getSecondInGame()>=2000) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    stop();
                }
            });
        }
        setSecondInGame(getSecondInGame()+100);
    }
}
