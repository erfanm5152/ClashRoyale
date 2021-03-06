package Model;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * The type Arrows.
 *
 * @author Erfanm5152
 * @version 0.1
 */
public class Arrows extends Spell {
    // damage of card
    private int damage;

    /**
     * Instantiates a new Arrows.
     *
     * @param player the player
     */
    public Arrows(Player player) {
        super(3, 4, player, "../View/pic/arrows.png");
        updateLevel();
    }

    @Override
    public void run() {
        Circle range = new Circle(getPoint2D().getX(), getPoint2D().getY(), 40);
        range.setVisible(false);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                getMap().getChildren().add(range);
                getImageView().setFitHeight(80);
                getImageView().setFitWidth(80);
                getImageView().setX(getPoint2D().getX() - 40);
                getImageView().setY(getPoint2D().getY() - 40);
            }
        });
//        getImageView().setFitHeight(80);
//        getImageView().setFitWidth(80);
//        getImageView().setX(getPoint2D().getX()-40);
//        getImageView().setY(getPoint2D().getY()-40);

        if (getSecondInGame() < 100) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    getImageView().setImage(new Image(getClass().getResourceAsStream("../View/pic/inGame/arrows/gif.gif")));
                    getMap().getChildren().add(getImageView());
                }
            });
        }
        if (getSecondInGame() < 100) {
            for (Vulnerable vulnerable :
                    getPlayer().getGame().getOpponent(getPlayer().getUser()).getPlayer().getGameAccessory().getInGameTargets()) {
                if (range.contains(vulnerable.getPoint2D())) {
                    vulnerable.decreaseHealth(damage);
                }
            }
            for (Tower tower : getPlayer().getGame().getOpponent(getPlayer().getUser()).getPlayer().getGameAccessory().getTowers()) {
                if (range.contains(tower.getPoint2D())) {
                    tower.decreaseHealth(damage);
                }
            }
        }
        if (getSecondInGame() >= 4000) {
            stop();
        }
        setSecondInGame(getSecondInGame() + 100);
    }

    @Override
    public void updateLevel() {
        switch (getPlayer().getUser().getLevel()) {
            case LEVEL1 -> damage = 144;
            case LEVEL2 -> damage = 156;
            case LEVEL3 -> damage = 174;
            case LEVEL4 -> damage = 189;
            case LEVEL5 -> damage = 210;
        }
    }
}
