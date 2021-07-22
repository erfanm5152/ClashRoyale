package Model;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The type Rage.
 *
 * @author Erfanm5152
 * @version 0.1
 */
public class Rage extends Spell{
    // duration of rage
    private double duration;
    // guide for effect of rage
    private ArrayList<Vulnerable> guide;
    // counter for guide
    private int counter;

    /**
     * Instantiates a new Rage.
     *
     * @param player the player
     */
    public Rage(Player player) {
        super(3, 5, player,"../View/pic/rage.png");
        updateLevel();
        guide = new ArrayList<>();
        counter = 0;
    }

    @Override
    public void run() {

        Circle range = new Circle(getPoint2D().getX(),getPoint2D().getY(),50);
        range.setVisible(false);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                getMap().getChildren().add(range);
                getImageView().setFitHeight(110);
                getImageView().setFitWidth(110);
                getImageView().setX(getPoint2D().getX()-55);
                getImageView().setY(getPoint2D().getY()-55);
                for (Card card:getPlayer().getGameAccessory().getInGameTargets()) {
                    if (range.contains(card.getPoint2D()) && !(card instanceof Spell)) {
                        if (!isInGuide(card)) {
                            guide.add(card);
                        }
                    }
                }
            }
        });
        while (counter<guide.size()){
            guide.get(counter).effectOfRage();
            counter++;
        }

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
                    stop();
                }
            });
            for (Vulnerable vulnerable:guide) {
                vulnerable.neutralizeRage();
            }
        }
        setSecondInGame(getSecondInGame()+100);
    }

    @Override
    public void updateLevel() {
        switch (getPlayer().getUser().getLevel()){
            case LEVEL1 -> duration = 6;
            case LEVEL2 -> duration = 6.5;
            case LEVEL3 -> duration = 7;
            case LEVEL4 -> duration = 7.5;
            case LEVEL5 -> duration = 8;
        }
    }

    /**
     * Is in guide boolean.
     *
     * @param vulnerable the vulnerable
     * @return the boolean
     */
    public boolean isInGuide(Vulnerable vulnerable){
        for (Vulnerable temp:guide) {
            if (temp==vulnerable){
                return true;
            }
        }
        return false;
    }

    /**
     * Increase ability.
     *
     * @param vulnerable the vulnerable
     */
    public void increaseAbility(Vulnerable vulnerable){
        vulnerable.effectOfRage();
    }
}
