package Test;

import Model.Card;
import Model.Factory;
import Model.Player;
import Model.User;
import javafx.scene.image.Image;
import javafx.scene.shape.Path;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * The type Test cards.
 *
 * @author Erfanm5152
 * @version 0.1
 */
public class TestCards {
    // user
    private static User user = new User("a", "b");
    // player
    private static Player player = new Player(user);
    // cards
    private static ArrayList<Card> cards;

    /**
     * Add.
     */
    @BeforeAll
    static void add() {
        cards = Factory.createRemainingCards(new Player(user));
        player.setUser(user);
        user.setPlayer(player);
    }

    /**
     * Print.
     */
    @Test
    void print() {
        for (Card card : cards) {
            System.out.println(card);
        }
    }
}
