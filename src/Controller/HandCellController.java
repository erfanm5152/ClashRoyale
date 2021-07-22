package Controller;

import Model.Card;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;

/**
 * The type Hand cell controller.
 * for in hand cards in game and next card
 *
 * @author Erfanm5152
 * @version 0.1
 */
public class HandCellController extends ListCell<Card> {
    // vbox for card
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

    /**
     * for list view
     * @param card
     * @param b
     */
    @Override
    protected void updateItem(Card card, boolean b) {
        super.updateItem(card, b);
        if (b || card == null){
            setGraphic(null);
        }else {
            if (loader==null){
                loader=new FXMLLoader(getClass().getResource("../View/HandCell.fxml"));
                loader.setController(this);
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            imageCard.setImage(new Image(getClass().getResource(card.getCardImageAddress()).toExternalForm()));
            elixirLabel.setText(String.valueOf(card.getCost()));
            setGraphic(vbox);
        }
    }
}
