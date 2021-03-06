package Model;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;

/**
 * The type Fireball.
 *
 * @author Erfanm5152
 * @version 0.1
 */
public class Fireball extends Spell {
    // damage of card
    private int damage;

    /**
     * Instantiates a new Fireball.
     *
     * @param player the player
     */
    public Fireball(Player player) {
        super(4, 2.5, player, "../View/pic/fireball.png");
        updateLevel();
    }

    @Override
    public void run() {
        Circle range = new Circle(getPoint2D().getX(), getPoint2D().getY(), 25);
        range.setVisible(false);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                getMap().getChildren().add(range);
                getImageView().setFitHeight(50);
                getImageView().setFitWidth(50);
                getImageView().setX(getPoint2D().getX() - 25);
                getImageView().setY(getPoint2D().getY() - 25);
            }
        });
//        getImageView().setFitHeight(50);
//        getImageView().setFitWidth(50);
//        getImageView().setX(getPoint2D().getX()-25);
//        getImageView().setY(getPoint2D().getY()-25);
        if (getSecondInGame() < 100) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    getImageView().setImage(new Image(getClass().getResourceAsStream("../View/pic/inGame/fireBall/gif.gif")));
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
        if (getSecondInGame() >= 2000) {
            stop();
        }
        setSecondInGame(getSecondInGame() + 100);
    }

    @Override
    public void updateLevel() {
        switch (getPlayer().getUser().getLevel()) {
            case LEVEL1 -> damage = 325;
            case LEVEL2 -> damage = 357;
            case LEVEL3 -> damage = 393;
            case LEVEL4 -> damage = 432;
            case LEVEL5 -> damage = 474;
        }
    }
}
