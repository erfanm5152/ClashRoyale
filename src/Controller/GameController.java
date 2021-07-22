package Controller;

import Model.*;
import View.MapView;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;


/**
 * The type Game controller.
 *
 * @author Erfanm5152
 * @version 0.1
 */
public class GameController {
    // observable list for list view
    private ObservableList<Card> hand;

    //    private TimerMe timerMe;
    // game of the app
    private Game game;
    // map of the game
    @FXML
    private MapView mapView;
    // next card image
    @FXML
    private ImageView newCard;
    // hand cards
    @FXML
    private ListView<Card> listOfCards;
    // timer text
    @FXML
    private Label timerLabel;
    // value of elixir
    @FXML
    private Label elixirLabel;
    // progressBar for increase elixir
    @FXML
    private ProgressBar progressBar;

    /**
     * Initialize.
     */
    public void initialize() {
        // create game & set Essentials of the game
        game = new Game();
        game.setTimerMe(new TimerMe(timerLabel, game));
        game.getTimerMe().start();
        new FinishThread(game).start();
        mapView.setImages(game);
        elixirLabel.textProperty().bind(progressBar.progressProperty().multiply(10).asString("%.0f"));

        game.getUser1().getPlayer().setGame(game);
        game.getUser2().getPlayer().setGame(game);

        game.getUser1().getPlayer().setGameAccessory(new GameAccessory(game.getUser1().getPlayer()));
        game.getUser2().getPlayer().setGameAccessory(new GameAccessory(game.getUser2().getPlayer()));


        game.getUser2().getPlayer().getGameAccessory().setMapOnTowers(mapView);
        game.getUser2().getPlayer().getGameAccessory().setMapOnTowers(mapView);

        game.getUser1().getPlayer().getGameAccessory().startTowers();
        game.getUser2().getPlayer().getGameAccessory().startTowers();


        ((Bot) game.getUser2()).setMapView(mapView);

        hand = FXCollections.observableArrayList(game.getUser1().getPlayer().getGameAccessory().getHand());
        newCard.setImage(new Image(getClass().getResourceAsStream(game.getUser1().getPlayer().getGameAccessory().getNextCard().getCardImageAddress())));
        listOfCards.setItems(hand);
        listOfCards.setCellFactory(new Callback<ListView<Card>, ListCell<Card>>() {
            @Override
            public ListCell<Card> call(ListView<Card> cardListView) {
                return new HandCellController();
            }
        });
        // add listener for hand
        listOfCards.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Card>() {
                    @Override
                    public void changed(ObservableValue<? extends Card> observableValue, Card card, Card t1) {
                        game.getUser1().getPlayer().getGameAccessory().setChosenCard(t1);
                    }
                }
        );
        progressBar.progressProperty().bind(game.getUser1().getPlayer().getGameAccessory().getElixir().elixirValueProperty().divide(10));

        ((Bot) game.getUser2()).start();
    }


    /**
     * Handle.
     *
     * @param mouseEvent the mouse event
     */
    @FXML
    public void handle(MouseEvent mouseEvent) {

        Card chosenCard = game.getUser1().getPlayer().getGameAccessory().getChosenCard();
        if (chosenCard == null) {
            return;
        }

        if (chosenCard.getCost() > game.getUser1().getPlayer().getGameAccessory().getElixir().getElixirValue()) {
            return;
        }


        if (!game.checkPoint(new Point2D(mouseEvent.getX(), mouseEvent.getY()), chosenCard) && !(chosenCard instanceof Spell)) {
            return;
        }
        game.getUser1().getPlayer().getGameAccessory().getElixir().add(chosenCard.getCost() * -1);

        chosenCard.setPoint2D(new Point2D(mouseEvent.getX(), mouseEvent.getY()));
        chosenCard.setMap(mapView);
        chosenCard.setImageView(new ImageView());


        if (!(chosenCard instanceof Spell)) {
            game.getUser1().getPlayer().getGameAccessory().getInGameTargets().add(chosenCard);
        }

        chosenCard.start();

        game.getInMapCards().add(chosenCard);

        Card nextCard = game.getUser1().getPlayer().getGameAccessory().getNextCard();
        //remove chosen Card from hand & deck
        hand.remove(chosenCard);
        game.getUser1().getPlayer().getGameAccessory().getHand().remove(chosenCard);
        game.getUser1().getPlayer().getDeck().remove(chosenCard);
        //add new of old card to deck for refresh health ,...
        game.getUser1().getPlayer().getDeck().add(Factory.createReplaceCard(chosenCard));
        //add next card to hand
        hand.add(nextCard);
        game.getUser1().getPlayer().getGameAccessory().getHand().add(nextCard);
        //create new next card
        Card createdNextCard = game.getUser1().getPlayer().getGameAccessory().createNextCard();
        game.getUser1().getPlayer().getGameAccessory().setNextCard(createdNextCard);
        newCard.setImage(new Image(getClass().getResourceAsStream(createdNextCard.getCardImageAddress())));
    }
}
