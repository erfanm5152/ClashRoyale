package Controller;

import Model.Card;
import Model.Game;
import Model.GameAccessory;
import View.CellOfList;
import View.MapView;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class GameController implements EventHandler<MouseEvent> {

    private ObservableList<Card> hand;

    @FXML
    private MapView mapView;

    private Game game;

    @FXML
    private ImageView newCard;

    @FXML
    private ListView<Card> listOfCards;

    @FXML
    private Label timerLabel;

    @FXML
    private Label elixirLabel;

    @FXML
    private ProgressBar progressBar;

    public void initialize(){
        game = new Game();
        mapView.setImages(game);
        elixirLabel.textProperty().bind(progressBar.progressProperty().multiply(10).asString("%.0f"));
        game.getUser1().getPlayer().setGameAccessory(new GameAccessory(game.getUser1().getPlayer()));
        hand = FXCollections.observableArrayList(game.getUser1().getPlayer().getGameAccessory().getHand());
        newCard.setImage(new Image(getClass().getResourceAsStream(game.getUser1().getPlayer().getGameAccessory().getNextCard().getCardImageAddress())));
        listOfCards.setItems(hand);
        listOfCards.setCellFactory(new Callback<ListView<Card>, ListCell<Card>>() {
            @Override
            public ListCell<Card> call(ListView<Card> cardListView) {
                return new HandCellController();
            }
        });
        listOfCards.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Card>() {
                    @Override
                    public void changed(ObservableValue<? extends Card> observableValue, Card card, Card t1) {
                        game.getUser1().getPlayer().getGameAccessory().setChosenCard(t1);
                    }
                }
        );
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        Card chosenCard =game.getUser1().getPlayer().getGameAccessory().getChosenCard();
        Card nextCard =game.getUser1().getPlayer().getGameAccessory().getNextCard();
        hand.remove(chosenCard);
        game.getUser1().getPlayer().getGameAccessory().getHand().remove(chosenCard);
        hand.add(nextCard);
        game.getUser1().getPlayer().getGameAccessory().getHand().add(nextCard);
        Card createdNextCard = game.getUser1().getPlayer().getGameAccessory().createNextCard();
        game.getUser1().getPlayer().getGameAccessory().setNextCard(createdNextCard);
        newCard.setImage(new Image(getClass().getResourceAsStream(createdNextCard.getCardImageAddress())));
    }
}
