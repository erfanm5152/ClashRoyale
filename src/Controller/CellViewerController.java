package Controller;

import Model.Card;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Random;

/**
 * The type Cell viewer controller.
 * for list view of cards
 *
 * @author Erfanm5152
 * @version 0.1
 */
public class CellViewerController extends ListCell<Card> {
    //random numbers for create random colors
    private static Random random = new Random();
    private static int red = random.nextInt(255), green = random.nextInt(255), blue = random.nextInt(255);
    // vbox of cell
    @FXML
    private VBox vbox;
    // image of card
    @FXML
    private ImageView imageCard;
    // cost of card
    @FXML
    private Label elixirLabel;
    // loader
    private FXMLLoader loader;

    @Override
    protected void updateItem(Card card, boolean b) {
        super.updateItem(card, b);
        if (b || card == null) {
            setGraphic(null);
        } else {
            if (loader == null) {
                loader = new FXMLLoader(getClass().getResource("../View/CellView.fxml"));
                loader.setController(this);
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            vbox.setStyle("-fx-background-color: #" + Color.rgb(red, green, blue).toString().substring(4));
            blue = (blue + 10) % 255;
            red = (red + 10) % 255;
            green = (green + 10) % 255;
            imageCard.setImage(new Image(getClass().getResource(card.getCardImageAddress()).toExternalForm()));
            elixirLabel.setText(String.valueOf(card.getCost()));
            setGraphic(vbox);
        }
    }
}
