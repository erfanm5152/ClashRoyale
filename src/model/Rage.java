package Model;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;

import java.util.HashMap;

public class Rage extends Spell{

    private double duration;
    private HashMap<Vulnerable,Boolean> guide;

    public Rage(Player player) {
        super(3, 5, player,"../View/pic/rage.png");
        switch (getPlayer().getUser().getLevel()){
            case LEVEL1 -> duration = 6;
            case LEVEL2 -> duration = 6.5;
            case LEVEL3 -> duration = 7;
            case LEVEL4 -> duration = 7.5;
            case LEVEL5 -> duration = 8;
        }
        guide = new HashMap<>();
    }

    @Override
    public void run() {

        Circle range = new Circle(getPoint2D().getX(),getPoint2D().getY(),50);
        range.setVisible(false);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                getMap().getChildren().add(range);
            }
        });
        getImageView().setFitHeight(100);
        getImageView().setFitWidth(100);
        getImageView().setX(getPoint2D().getX()-50);
        getImageView().setY(getPoint2D().getY()-50);

        if (getSecondInGame()<100) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    getImageView().setImage(new Image(getClass().getResourceAsStream("../View/pic/inGame/rage/gif.gif")));
                    getMap().getChildren().add(getImageView());
                }
            });
        }
        if (getSecondInGame()>=duration*1000) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    for (Vulnerable vulnerable:guide.keySet()) {

                    }
                    stop();
                }
            });
        }
        setSecondInGame(getSecondInGame()+100);
    }
}
