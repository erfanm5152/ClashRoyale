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

public class HandCellController extends ListCell<Card> {
    @FXML
    private VBox vbox;

    @FXML
    private ImageView imageCard;

    @FXML
    private Label elixirLabel;

    private FXMLLoader loader;

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
